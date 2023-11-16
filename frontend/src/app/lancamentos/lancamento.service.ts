import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import * as moment from 'moment';
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
}
