import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Observable, map } from 'rxjs';
import * as moment from 'moment';
import { Lancamento } from '../core/model';
export class LancamentoFiltro {
  descricao?: string;
  dataVencimentoDe?: Date;
  dataVencimentoAte?: Date;
  pagina?: number;
  itensPorPagina = 5;
}

@Injectable({
  providedIn: 'root',
})
export class LancamentoService {
  lancamentoUrl: any = 'http://localhost:8080/lancamentos';

  constructor(private http: HttpClient) {}

  pesquisar(filtro: LancamentoFiltro): Observable<any> {
    let params = new HttpParams();
    params = params.set('page', filtro.pagina || 0);
    params = params.set('size', filtro.itensPorPagina || 2);

    if (filtro.descricao) {
      params = params.set('descricao', filtro.descricao);
    }
    if (filtro.dataVencimentoDe) {
      params = params.set(
        'dataVencimentoDe',
        moment(filtro.dataVencimentoDe).format('YYYY-MM-DD')
      );
    }
    if (filtro.dataVencimentoAte) {
      params = params.set(
        'dataVencimentoAte',
        moment(filtro.dataVencimentoAte).format('YYYY-MM-DD')
      );
    }
    return this.http.get(`${this.lancamentoUrl}/resumo`, { params });
  }

  excluir(codigo: number): Observable<void> {
    return this.http.delete<void>(`${this.lancamentoUrl}/${codigo}`);
  }

  adicionar(lancamento: Lancamento): Observable<Lancamento> {
    return this.http.post<any>(`${this.lancamentoUrl}`, lancamento);
  }

  atualizar(lancamento: Lancamento): Observable<Lancamento> {
    return this.http
      .put<any>(`${this.lancamentoUrl}/${lancamento.id}`, lancamento)
      .pipe(
        map((response: Lancamento) => {
          this.converterStringParaDate(Array.of(response));
          return response;
        })
      );
  }

  buscarPorId(id: number) {
    return this.http.get<Lancamento>(`${this.lancamentoUrl}/${id}`).pipe(
      map((response: Lancamento) => {
        this.converterStringParaDate(Array.of(response));
        return response;
      })
    );
  }

  converterStringParaDate(lancamentos: Lancamento[]) {
    for (let lanc of lancamentos) {
      lanc.dataPagamento = lanc.dataPagamento
        ? moment(lanc.dataPagamento, 'YYYY-MM-DD').toDate()
        : undefined;
      lanc.dataVencimento = lanc.dataVencimento
        ? moment(lanc.dataVencimento, 'YYYY-MM-DD').toDate()
        : undefined;
    }
  }
}
