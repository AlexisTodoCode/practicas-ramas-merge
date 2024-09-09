import { Component, OnInit } from '@angular/core';
import { Categoria } from '../models/categoria';
import { CategoriaService } from '../services/categoria.service';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';
import { MessagesModule } from 'primeng/messages';
import { MessageModule } from 'primeng/message';

@Component({
  selector: 'app-categoria',
  standalone: true,
  imports: [
    CommonModule,
    ButtonModule,
    CardModule,
    TableModule,
    DialogModule,
    FormsModule,
    MessagesModule,
    MessageModule
  ],
  templateUrl: './categoria-form.component.html',
  styleUrls: ['./categoria-form.component.css']
})
export class CategoriaComponent implements OnInit {
  categorias: Categoria[] = [];
  filteredCategorias: Categoria[] = [];
  selectedCategoria: Categoria = {} as Categoria;
  isModalVisible = false;
  isDeleteDialogVisible = false;
  categoriaToDelete: Categoria | null = null;
  searchTerm = '';

  constructor(private categoriaService: CategoriaService) {}

  ngOnInit(): void {
    this.fetchCategorias();
  }

  fetchCategorias(): void {
    this.categoriaService.getCategorias()
      .pipe(
        catchError(error => {
          console.error('Error fetching categories:', error);
          return of([]); // Retorna un array vacío en caso de error
        })
      )
      .subscribe(categorias => {
        this.categorias = categorias;
        this.filteredCategorias = categorias;
      });
  }

  openModal(categoria?: Categoria): void {
    this.selectedCategoria = categoria ? { ...categoria } : {} as Categoria;
    this.isModalVisible = true;
  }

  saveCategoria(categoria: Categoria): void {
    const form = document.querySelector('form') as HTMLFormElement;
    if (!form.checkValidity()) {
      form.reportValidity(); // Show validation messages
      return;
    }

    if (categoria.id) {
      this.categoriaService.updateCategoria(categoria)
        .pipe(
          catchError(error => {
            console.error('Error updating category:', error);
            return of(null);
          })
        )
        .subscribe(() => {
          this.fetchCategorias();
          this.isModalVisible = false;
        });
    } else {
      this.categoriaService.createCategoria(categoria)
        .pipe(
          catchError(error => {
            console.error('Error creating category:', error);
            return of(null);
          })
        )
        .subscribe(() => {
          this.fetchCategorias();
          this.isModalVisible = false;
        });
    }
  }

  confirmDelete(categoria: Categoria): void {
    this.categoriaToDelete = categoria;
    this.isDeleteDialogVisible = true;
  }

  cancelDelete(): void {
    this.isDeleteDialogVisible = false;
    this.categoriaToDelete = null;
  }

  deleteCategoria(): void {
    if (this.categoriaToDelete && this.categoriaToDelete.id) {
      this.categoriaService.deleteCategoria(this.categoriaToDelete.id)
        .pipe(
          catchError(error => {
            console.error('Error deleting category:', error);
            return of(null);
          })
        )
        .subscribe(() => {
          this.fetchCategorias();
          this.isDeleteDialogVisible = false;
          this.categoriaToDelete = null;
        });
    }
  }

  filterCategories(): void {
    if (this.searchTerm) {
      this.filteredCategorias = this.categorias.filter(categoria =>
        categoria.nombre.toLowerCase().includes(this.searchTerm.toLowerCase())
      );
    } else {
      this.filteredCategorias = this.categorias;
    }
  }
}
