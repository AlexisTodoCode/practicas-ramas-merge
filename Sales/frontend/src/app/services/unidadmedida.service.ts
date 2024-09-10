import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Unidad } from '../models/unidad';

@Injectable({
  providedIn: 'root'
})
export class UnidadmedidaService {

  private uniUrl = 'http://localhost:8080/unidadMedida';

  constructor(private http: HttpClient) { }

  getUnidades(): Observable<Unidad[]>{
    return this.http.get<Unidad[]>(this.uniUrl);
  }

  getUnidadById(id: number): Observable<Unidad>{
    return this.http.get<Unidad>(`${this.uniUrl}/${id}`);
  }

  createUnidad(unidad: Unidad): Observable<Unidad>{
    return this.http.post<Unidad>(this.uniUrl, unidad);
  }

  updateUnidad(unidad: Unidad): Observable<void>{
    return this.http.put<void>(`${this.uniUrl}/${unidad.id}`, unidad);
  }

  deleteUnidad(id: number): Observable<void>{
    return this.http.delete<void>(`${this.uniUrl}/${id}`)
  }

}
