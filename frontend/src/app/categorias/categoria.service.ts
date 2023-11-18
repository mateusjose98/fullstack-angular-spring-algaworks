import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CategoriaService {
  categoriasUrl: any = 'http://localhost:8080/categorias';

  constructor(private http: HttpClient) {}

  listarTodas(): Observable<any[]> {
    return this.http.get<any[]>(this.categoriasUrl);
  }
}
