import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PessoaService {
  url: any = 'http://localhost:8080/pessoas';
  constructor(private http: HttpClient) {}

  pesquisar(filtro: any): Observable<any> {
    let params = new HttpParams();
    params = params.set('nome', filtro.nome);
    params = params.set('page', filtro.pagina);
    params = params.set('size', 5);
    return this.http.get(this.url, { params });
  }

  excluir(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }

  ativar(id: number, ativo: boolean): Observable<void> {
    return this.http.put<void>(`${this.url}/${id}/ativo`, ativo);
  }
}
