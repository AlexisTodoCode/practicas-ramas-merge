import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Categoria } from '../models/categoria';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {
  private categoriaUrl = 'http://localhost:8080/categoria';

  constructor(private http: HttpClient) { }

  getCategorias(): Observable<Categoria[]>{
    return this.http.get<Categoria[]>(this.categoriaUrl);
  }

  getCategoriaById(id: number): Observable<Categoria>{
    return this.http.get<Categoria>(`${this.categoriaUrl}/${id}`);
  }

  createCategoria(categoria: Categoria): Observable<Categoria>{
    return this.http.post<Categoria>(this.categoriaUrl, categoria);
  }

  updateCategoria(categoria: Categoria): Observable<void>{
    return this.http.put<void>(`${this.categoriaUrl}/${categoria.id}`,categoria);
  }

  deleteCategoria(id: number): Observable<void>{
    return this.http.delete<void>(`${this.categoriaUrl}/${id}`)
  }

}
