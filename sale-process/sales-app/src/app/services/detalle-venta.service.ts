import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DetalleVenta } from '../interfaces/detalle-venta';

@Injectable({
  providedIn: 'root'
})
export class DetalleVentaService {

  private apiUrl = 'http://localhost:8080/detalleventa';

  constructor(private http: HttpClient) { }

  obtenerPorVentaId(ventaId: number): Observable<DetalleVenta[]> {
    return this.http.get<DetalleVenta[]>(`${this.apiUrl}/venta/${ventaId}`);
  }

  crear(detalleVenta: DetalleVenta): Observable<DetalleVenta> {
    return this.http.post<DetalleVenta>(this.apiUrl, detalleVenta);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
