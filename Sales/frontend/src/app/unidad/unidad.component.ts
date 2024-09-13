import { Component, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';
import { MessagesModule } from 'primeng/messages';
import { MessageModule } from 'primeng/message';
import { UnidadmedidaService } from '../services/unidadmedida.service';
import { Unidad } from '../models/unidad';
import { Router } from '@angular/router'; // Importa Router aquí

@Component({
  selector: 'app-unidad',
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
  templateUrl: './unidad.component.html',
  styleUrls: ['./unidad.component.css']
})
export class UnidadComponent implements OnInit {
  unidades$: Observable<Unidad[]> = of([]);
  displayDialog: boolean = false;
  selectedUnidad: Unidad = { id: 0, nombre: '' }; // Inicializa con un objeto vacío
  isDeleteDialogVisible: boolean = false;
  searchTerm: string = '';

  constructor(private unidadService: UnidadmedidaService, private router: Router) {} // Asegúrate de que Router esté inyectado aquí

  ngOnInit(): void {
    this.loadUnidades();
  }

  loadUnidades(): void {
    this.unidadService.getUnidades()
      .pipe(catchError(error => {
        console.error('Error fetching unidades', error);
        return of([]); // Retorna un array vacío en caso de error
      }))
      .subscribe(data => this.unidades$ = of(data ?? [])); // Asegúrate de que data no sea null
  }

  showDialog(unidad?: Unidad): void {
    this.selectedUnidad = unidad ? { ...unidad } : { id: 0, nombre: '' };
    this.displayDialog = true;
  }

  saveUnidad(): void {
    if (this.selectedUnidad) {
      if (this.selectedUnidad.id) {
        // Asegúrate de que id sea un número válido
        const id = this.selectedUnidad.id;
        if (id !== undefined) {
          this.unidadService.updateUnidad(this.selectedUnidad)
            .pipe(catchError(error => {
              console.error('Error updating unidad', error);
              return of();
            }))
            .subscribe(() => this.loadUnidades());
        }
      } else {
        this.unidadService.createUnidad(this.selectedUnidad)
          .pipe(catchError(error => {
            console.error('Error creating unidad', error);
            return of();
          }))
          .subscribe(() => this.loadUnidades());
      }
      this.displayDialog = false;
    }
  }

  deleteUnidad(): void {
    if (this.selectedUnidad && this.selectedUnidad.id !== undefined) {
      this.unidadService.deleteUnidad(this.selectedUnidad.id)
        .pipe(catchError(error => {
          console.error('Error deleting unidad', error);
          return of();
        }))
        .subscribe(() => this.loadUnidades());
      this.isDeleteDialogVisible = false;
    }
  }

  confirmDelete(unidad: Unidad): void {
    this.selectedUnidad = unidad;
    this.isDeleteDialogVisible = true;
  }

  cancelDelete(): void {
    this.isDeleteDialogVisible = false;
  }

  goBack(): void {
    this.router.navigate(['/']); // Navega a la página principal de productos
  }

  filterUnidades(): void {
    // Filtrar unidades basado en searchTerm
    this.unidades$ = this.unidadService.getUnidades().pipe(
      map(unidades => unidades.filter(unidad => unidad.nombre.toLowerCase().includes(this.searchTerm.toLowerCase()))),
      catchError(error => {
        console.error('Error fetching unidades', error);
        return of([]);
      })
    );
  }
}
