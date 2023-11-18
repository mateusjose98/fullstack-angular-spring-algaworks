import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Lancamento, Pessoa } from '../core/model';

@Injectable({
  providedIn: 'root',
})
export class PessoaService {
  url: any = 'http://localhost:8080/pessoas';
  constructor(private http: HttpClient) {}

  pesquisar(filtro: any): Observable<any> {
    let params = new HttpParams();
    if (filtro.nome) {
      params = params.set('nome', filtro.nome);
    }
    if (filtro.pagina) {
      params = params.set('page', filtro.pagina);
    }

    params = params.set('size', 5);
    params = params.set('devePaginar', filtro.devePaginar);
    return this.http.get(this.url, { params });
  }

  excluir(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }

  ativar(id: number, ativo: boolean): Observable<void> {
    return this.http.put<void>(`${this.url}/${id}/ativo`, ativo);
  }

  salvar(pessoa: Pessoa) {
    return this.http.post<Pessoa>(`${this.url}`, pessoa);
  }
}
