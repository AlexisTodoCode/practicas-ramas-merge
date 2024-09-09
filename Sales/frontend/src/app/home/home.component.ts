import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'; // Importa Router
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';
import { DropdownModule } from 'primeng/dropdown';
import { MessagesModule } from 'primeng/messages';
import { MessageModule } from 'primeng/message';
import { Producto } from '../models/producto';
import { ProductoService } from '../services/producto.service';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    ButtonModule,
    CardModule,
    TableModule,
    DialogModule,
    DropdownModule,
    FormsModule,
    MessagesModule,
    MessageModule
  ],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  productos: Producto[] = [];
  filteredProductos: Producto[] = [];
  categorias: any[] = [];
  unidades: any[] = [];
  selectedProducto: Producto = {} as Producto;
  isModalVisible = false;
  isDeleteDialogVisible = false;
  productToDelete: Producto | null = null;
  searchTerm = '';

  constructor(private productoService: ProductoService, private router: Router) {} // Inyecta Router

  ngOnInit(): void {
    this.fetchProductos();
    this.fetchCategorias();
    this.fetchUnidades();
  }

  fetchProductos(): void {
    this.productoService.getProductos()
      .pipe(
        catchError(error => {
          console.error('Error fetching products:', error);
          return of([]); // Retorna un array vacío en caso de error
        })
      )
      .subscribe(productos => {
        this.productos = productos;
        this.filteredProductos = productos;
      });
  }

  fetchCategorias(): void {
    this.productoService.getCategorias()
      .pipe(
        catchError(error => {
          console.error('Error fetching categories:', error);
          return of([]); // Retorna un array vacío en caso de error
        })
      )
      .subscribe(categorias => this.categorias = categorias);
  }

  fetchUnidades(): void {
    this.productoService.getUnidades()
      .pipe(
        catchError(error => {
          console.error('Error fetching units:', error);
          return of([]); // Retorna un array vacío en caso de error
        })
      )
      .subscribe(unidades => this.unidades = unidades);
  }

  openModal(producto?: Producto): void {
    this.selectedProducto = producto ? { ...producto } : {} as Producto;
    this.isModalVisible = true;
  }

  saveProducto(producto: Producto): void {
    // Handle validation
    const form = document.querySelector('form') as HTMLFormElement;
    if (!form.checkValidity()) {
      form.reportValidity(); // Show validation messages
      return;
    }

    if (producto.id) {
      this.productoService.updateProducto(producto)
        .pipe(
          catchError(error => {
            console.error('Error updating product:', error);
            return of(null);
          })
        )
        .subscribe(() => {
          this.fetchProductos();
          this.isModalVisible = false;
        });
    } else {
      this.productoService.createProducto(producto)
        .pipe(
          catchError(error => {
            console.error('Error creating product:', error);
            return of(null);
          })
        )
        .subscribe(() => {
          this.fetchProductos();
          this.isModalVisible = false;
        });
    }
  }

  confirmDelete(producto: Producto): void {
    this.productToDelete = producto;
    this.isDeleteDialogVisible = true;
  }

  cancelDelete(): void {
    this.isDeleteDialogVisible = false;
    this.productToDelete = null;
  }

  deleteProducto(): void {
    if (this.productToDelete && this.productToDelete.id) {
      this.productoService.deleteProducto(this.productToDelete.id)
        .pipe(
          catchError(error => {
            console.error('Error deleting product:', error);
            return of(null);
          })
        )
        .subscribe(() => {
          this.fetchProductos();
          this.isDeleteDialogVisible = false;
          this.productToDelete = null;
        });
    }
  }

  filterProducts(): void {
    if (this.searchTerm) {
      this.filteredProductos = this.productos.filter(producto =>
        producto.nombre.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        producto.descripcion.toLowerCase().includes(this.searchTerm.toLowerCase())
      );
    } else {
      this.filteredProductos = this.productos;
    }
  }

  getCategoryName(categoryId?: number): string {
    if (categoryId === undefined) return 'Unknown';
    const category = this.categorias.find(c => c.id === categoryId);
    return category ? category.nombre : 'Unknown';
  }

  getUnitName(unitId?: number): string {
    if (unitId === undefined) return 'Unknown';
    const unit = this.unidades.find(u => u.id === unitId);
    return unit ? unit.nombre : 'Unknown';
  }

  goToCategories(): void {
    this.router.navigate(['/categoria']); // Redirige a la ruta de categorías
  }
}
