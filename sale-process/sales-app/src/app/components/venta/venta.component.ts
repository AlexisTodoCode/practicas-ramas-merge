import { Component, OnInit } from '@angular/core';
import { Cliente } from '../../interfaces/cliente';
import { Producto } from '../../interfaces/producto';
import { DetalleVenta } from '../../interfaces/detalle-venta';
import { ClienteService } from '../../services/cliente.service';
import { ProductoService } from '../../services/producto.service';
import { VentaService } from '../../services/venta.service';
import { Venta } from '../../interfaces/venta';
import { VentaTableComponent } from '../venta-table/venta-table.component';
import {
  FormGroup,
  FormBuilder,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { CommonModule } from '@angular/common';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';

@Component({
  selector: 'app-venta',
  standalone: true,
  imports: [
    MatDatepickerModule,
    MatNativeDateModule,
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    VentaTableComponent,
  ],
  templateUrl: './venta.component.html',
  styleUrls: ['./venta.component.css'],
})
export class VentaComponent implements OnInit {
  ventaForm!: FormGroup;
  clientes: Cliente[] = [];
  productos: Producto[] = [];
  detalleVentas: DetalleVenta[] = [];
  dataSource = new MatTableDataSource<DetalleVenta>(this.detalleVentas);

  ventas: Venta[] = [];
  subtotal: number = 0;
  igv: number = 0;
  total: number = 0;
  productoSeleccionado: Producto | null = null;
  cantidadSeleccionada: number = 1;

  constructor(
    private fb: FormBuilder,
    private clienteService: ClienteService,
    private productoService: ProductoService,
    private ventaService: VentaService
  ) {}

  ngOnInit(): void {
    this.obtenerClientes();
    this.obtenerProductos();
    this.iniciarFormulario();
  }

  iniciarFormulario(): void {
    const today = new Date();
    this.ventaForm = this.fb.group({
      clienteId: ['', Validators.required],
      usuarioId: [1, Validators.required],
      fechaEmision: [today.toISOString().slice(0, 16), Validators.required], 
    });
  }

  obtenerClientes(): void {
    this.clienteService.obtenerTodos().subscribe(
      (clientes) => (this.clientes = clientes),
      (error) => console.error('Error al obtener los clientes', error)
    );
  }

  obtenerProductos(): void {
    this.productoService.obtenerTodos().subscribe(
      (productos) => (this.productos = productos),
      (error) => console.error('Error al obtener los productos', error)
    );
  }
  isProductoSeleccionado(producto: Producto): boolean {
    return this.detalleVentas.some(detalle => detalle.producto.id === producto.id);
  }
  agregarDetalleVenta(productoId: number, cantidad: number): void {
    const producto = this.productos.find((p) => p.id === productoId);
    if (producto && cantidad > 0) {
      const detalleExistente = this.detalleVentas.find(
        (detalle) => detalle.producto.id === productoId
      );
  
      if (detalleExistente) {
        detalleExistente.cantidad += cantidad;
        detalleExistente.subtotal = detalleExistente.precioUnitario * detalleExistente.cantidad;
        detalleExistente.igv = detalleExistente.subtotal * 0.18;
        detalleExistente.total = detalleExistente.subtotal + detalleExistente.igv;
      } else {
        const subtotalProducto = producto.precio * cantidad;
        const igvProducto = subtotalProducto * 0.18;
        const totalProducto = subtotalProducto + igvProducto;
  
        const detalle: DetalleVenta = {
          id: 0,
          producto: producto,
          cantidad: cantidad,
          precioUnitario: producto.precio,
          subtotal: subtotalProducto,
          igv: igvProducto,
          total: totalProducto,
          venta: null, 
          estado: true,
        };
  
        this.detalleVentas.push(detalle);
      }
  
      this.dataSource.data = [...this.detalleVentas];
      this.actualizarTotales();
    }
  }

  eliminarDetalle(index: number): void {
    this.detalleVentas.splice(index, 1);
    this.dataSource.data = [...this.detalleVentas];
    this.actualizarTotales();
  }

  incrementarCantidad(index: number): void {
    const detalle = this.detalleVentas[index];
    detalle.cantidad += 1;
    detalle.subtotal = detalle.precioUnitario * detalle.cantidad;
    detalle.igv = detalle.subtotal * 0.18;
    detalle.total = detalle.subtotal + detalle.igv;
    this.dataSource.data = [...this.detalleVentas];
    this.actualizarTotales();
  }

  decrementarCantidad(index: number): void {
    const detalle = this.detalleVentas[index];
    if (detalle.cantidad > 1) {
      detalle.cantidad -= 1;
      detalle.subtotal = detalle.precioUnitario * detalle.cantidad;
      detalle.igv = detalle.subtotal * 0.18;
      detalle.total = detalle.subtotal + detalle.igv;
      this.dataSource.data = [...this.detalleVentas];
      this.actualizarTotales();
    }
  }

  actualizarTotales(): void {
    this.subtotal = this.detalleVentas.reduce(
      (acc, item) => acc + item.subtotal,
      0
    );
    this.igv = this.detalleVentas.reduce((acc, item) => acc + item.igv, 0);
    this.total = this.subtotal + this.igv;
  }

  guardarVenta(): void {
    if (this.ventaForm.invalid || this.detalleVentas.length === 0) {
      return;
    }

    const venta: Venta = {
      ...this.ventaForm.value,
      detalleVentas: this.detalleVentas.map((detalle) => ({
        ...detalle,
        producto: { id: detalle.producto.id }, 
      })),
      subtotal: this.subtotal,
      igv: this.igv,
      total: this.total,
      cliente: { id: this.ventaForm.value.clienteId },
      usuario: { id: this.ventaForm.value.usuarioId },
    };

    this.ventaService.crear(venta).subscribe(
      (ventaCreada) => {
        console.log('Venta creada', ventaCreada);

        this.ventaForm.reset(); 
        this.detalleVentas = []; 
        this.dataSource.data = []; 
        this.subtotal = 0;
        this.igv = 0;
        this.total = 0;

        this.iniciarFormulario();
      },
      (error) => {
        console.error('Error al crear la venta', error);
      }
    );
  }
}