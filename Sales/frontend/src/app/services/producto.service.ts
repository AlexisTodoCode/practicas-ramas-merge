import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Producto } from '../models/producto';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  private apiUrl = 'http://localhost:8080/product';
  private categoriaUrl = 'http://localhost:8080/categoria'; // Añadir URL de categoría
  private unidadUrl = 'http://localhost:8080/unidadMedida'; // Añadir URL de unidad

  constructor(private http: HttpClient) { }

  getProductos(): Observable<Producto[]> {
    return this.http.get<Producto[]>(this.apiUrl);
  }

  getProductoById(id: number): Observable<Producto> {
    return this.http.get<Producto>(`${this.apiUrl}/${id}`);
  }

  createProducto(producto: Producto): Observable<Producto> {
    return this.http.post<Producto>(this.apiUrl, producto);
  }

  updateProducto(producto: Producto): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${producto.id}`, producto);
  }

  deleteProducto(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getCategorias(): Observable<any[]> { // Ajusta el tipo según la estructura de tu API
    return this.http.get<any[]>(this.categoriaUrl);
  }

  getUnidades(): Observable<any[]> { // Ajusta el tipo según la estructura de tu API
    return this.http.get<any[]>(this.unidadUrl);
  }
}
