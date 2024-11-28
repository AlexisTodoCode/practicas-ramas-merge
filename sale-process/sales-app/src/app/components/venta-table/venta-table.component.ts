import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { Venta } from '../../interfaces/venta';
import { VentaService } from '../../services/venta.service';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-venta-table',
  standalone: true,
  imports: [
    MatTableModule,
    MatPaginatorModule,
    MatInputModule,
    MatFormFieldModule,
    MatIconModule,
    FormsModule,
    CommonModule,
  ],
  templateUrl: './venta-table.component.html',
  styleUrls: ['./venta-table.component.css'],
})
export class VentaTableComponent implements OnInit, AfterViewInit {
  displayedColumns: string[] = ['cliente', 'fechaEmision', 'total'];
  dataSource = new MatTableDataSource<Venta>();
  searchText: string = '';

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private ventaService: VentaService) {}

  ngOnInit(): void {
    this.obtenerVentas();

    this.dataSource.filterPredicate = (data: Venta, filter: string) => {
      const searchText = filter.trim().toLowerCase();
      return (
        data.cliente?.nombre.toLowerCase().includes(searchText) || 
        data.id.toString().includes(searchText) 
      );
    };
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator; 
  }

  obtenerVentas(): void {
    this.ventaService.obtenerTodas().subscribe(
      (ventas) => {
        this.dataSource.data = ventas; 
      },
      (error) => console.error('Error al obtener las ventas', error)
    );
  }

  buscarVentas(): void {
    this.dataSource.filter = this.searchText.trim().toLowerCase(); 
  }
}
