import { Component, OnInit, ViewChild } from '@angular/core';
import { Cliente } from '../../interfaces/cliente';
import { Producto } from '../../interfaces/producto';
import { DetalleVenta } from '../../interfaces/detalle-venta';
import { ClienteService } from '../../services/cliente.service';
import { ProductoService } from '../../services/producto.service';
import { VentaService } from '../../services/venta.service';
import { Venta } from '../../interfaces/venta';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { CommonModule } from '@angular/common';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { Usuario } from '../../interfaces/usuario';
import { UsuarioService } from '../../services/usuario.service';
import { ComprobanteDialogComponent } from '../comprobante-dialog/comprobante-dialog.component';
import { MatDialog } from '@angular/material/dialog';

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
    FormsModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
  ],
  templateUrl: './venta.component.html',
  styleUrls: ['./venta.component.css'],
})
export class VentaComponent implements OnInit {
  ventaForm!: FormGroup;
  productos: Producto[] = [];
  clientes: Cliente[] = [];
  usuarios: Usuario[] = [];
  detalleVentas: DetalleVenta[] = [];
  dataSource = new MatTableDataSource<DetalleVenta>(this.detalleVentas);
  ventas: Venta[] = [];
  dataSources = new MatTableDataSource<Venta>(this.ventas);
  displayedColumns: string[] = ['id', 'cliente', 'fechaEmision', 'total', 'actions'];
  direccionCliente: string = ''; 
  dniCliente: string = '';
  subtotal: number = 0;
  igv: number = 0;
  total: number = 0;
  productoSeleccionado: Producto | null = null;
  cantidadSeleccionada: number = 1;
  tiposComprobante = [
    { value: 'B', label: 'Boleta' },
    { value: 'F', label: 'Factura' },
  ];

  @ViewChild(MatPaginator) paginator: MatPaginator | null = null;
  @ViewChild(MatSort) sort: MatSort | null = null;

  constructor(
    private fb: FormBuilder,
    private clienteService: ClienteService,
    private productoService: ProductoService,
    private ventaService: VentaService,
    private usuarioService: UsuarioService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.obtenerClientes();
    this.obtenerProductos();
    this.iniciarFormulario();
    this.obtenerUsuarios();
    this.obtenerVentas();

    this.dataSources.filterPredicate = (data: Venta, filter: string) => {
      const transformedFilter = filter.trim().toLowerCase();
      const cliente = data.cliente.nombre.toLowerCase().includes(transformedFilter);
      const total = data.total.toString().includes(transformedFilter);
      return cliente || total;
    };
  }

  obtenerVentas(): void {
    this.ventaService.obtenerTodas().subscribe(
      (ventas) => {
        this.ventas = ventas;
        this.dataSources.data = ventas; 
        if (this.paginator) {
          this.dataSources.paginator = this.paginator; 
        }
        if (this.sort) {
          this.dataSources.sort = this.sort;
        }
      },
      (error) => console.error('Error al obtener las ventas', error)
    );
  }

  verDetalles(id: number): void {
    console.log('Ver detalles de la venta con ID:', id);
    
    // Obtener el comprobante de la venta
    this.ventaService.obtenerComprobante(id).subscribe(
      (comprobante) => {
        // Abrir un modal con el comprobante
        this.dialog.open(ComprobanteDialogComponent, {
          data: comprobante // Pasar el comprobante al dialog
        });
      },
      (error) => {
        console.error('Error al obtener el comprobante:', error);
      }
    );
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSources.filter = filterValue.trim().toLowerCase();

    if (this.dataSources.paginator) {
      this.dataSources.paginator.firstPage();
    }
  }

  iniciarFormulario(): void {
    const today = new Date();
    this.ventaForm = this.fb.group({
      clienteId: ['', Validators.required],
      usuarioId: ['', Validators.required],
      fechaEmision: [today.toISOString().slice(0, 16), Validators.required],
      tipoComprobante: ['', Validators.required],
      direccion: ['', Validators.required], 
      dniRuc: ['', Validators.required]     
    });
  }
  

  obtenerClientes(): void {
    this.clienteService.obtenerTodos().subscribe(
      (clientes) => (this.clientes = clientes),
      (error) => console.error('Error al obtener los clientes', error)
    );
  }
  obtenerUsuarios(): void {
    this.usuarioService.obtenerTodos().subscribe(
      (usuarios) => {
        this.usuarios = usuarios;
        console.log('hoal', this.usuarios);
      },
      (error) => console.error('Error al obtener los clientes', error)
    );
  }

  onClienteSeleccionado(event: any): void {
    const clienteId = event.value;
  
    const clienteSeleccionado = this.clientes.find(
      (cliente) => cliente.id === clienteId
    );
    if (clienteSeleccionado) {
      this.direccionCliente = clienteSeleccionado.direccion;
      this.dniCliente = clienteSeleccionado.dniRuc;
  
      this.ventaForm.patchValue({
        direccion: this.direccionCliente,
        dniRuc: this.dniCliente
      });
    }
  }
  

  obtenerProductos(): void {
    this.productoService.obtenerTodos().subscribe(
      (productos) => (this.productos = productos),
      (error) => console.error('Error al obtener los productos', error)
    );
  }
  isProductoSeleccionado(producto: Producto): boolean {
    return this.detalleVentas.some(
      (detalle) => detalle.producto.id === producto.id
    );
  }
  
  agregarDetalleVenta(productoId: number, cantidad: number, descuento: number = 0): void {
    const producto = this.productos.find((p) => p.id === productoId);
    if (producto && cantidad > 0) {
      const detalleExistente = this.detalleVentas.find(
        (detalle) => detalle.producto.id === productoId
      );
  
      if (detalleExistente) {
        // Actualizar detalle existente
        detalleExistente.cantidad += cantidad;
        detalleExistente.desconto = descuento >= 0 ? descuento : 0; // Prevent negative discount
        detalleExistente.subtotal = 
          detalleExistente.precioUnitario * detalleExistente.cantidad - detalleExistente.desconto;
        detalleExistente.igv = detalleExistente.subtotal * 0.18;
        detalleExistente.total = detalleExistente.subtotal + detalleExistente.igv;
      } else {
        // Crear nuevo detalle
        const finalDesconto = descuento >= 0 ? descuento : 0; // Prevent negative discount
        const subtotalProducto = producto.precio * cantidad - finalDesconto;
        const igvProducto = subtotalProducto * 0.18;
        const totalProducto = subtotalProducto + igvProducto;
  
        const detalle: DetalleVenta = {
          id: 0,
          producto: producto,
          cantidad: cantidad,
          precioUnitario: producto.precio,
          desconto: finalDesconto,
          subtotal: subtotalProducto,
          igv: igvProducto,
          total: totalProducto,
          venta: null,
          estado: true,
        };
  
        this.detalleVentas.push(detalle);
      }
  
      // Refrescar la tabla y los totales
      this.dataSource.data = [...this.detalleVentas];
      this.actualizarTotales();
    }
  }
  
  incrementarCantidad(index: number): void {
    const detalle = this.detalleVentas[index];
    
    // Incrementar la cantidad
    detalle.cantidad += 1;
    
    // Mantener el descuento actual
    const descuentoAplicado = detalle.desconto >= 0 ? detalle.desconto : 0;
  
    // Recalcular el subtotal, IGV y total con el descuento actual
    detalle.subtotal = detalle.precioUnitario * detalle.cantidad - descuentoAplicado;
    detalle.igv = detalle.subtotal * 0.18;
    detalle.total = detalle.subtotal + detalle.igv;
  
    // Actualizar la tabla y los totales
    this.dataSource.data = [...this.detalleVentas];
    this.actualizarTotales();
  }
  
  decrementarCantidad(index: number): void {
    const detalle = this.detalleVentas[index];
    
    if (detalle.cantidad > 1) {
      // Decrementar la cantidad
      detalle.cantidad -= 1;
  
      // Mantener el descuento actual
      const descuentoAplicado = detalle.desconto >= 0 ? detalle.desconto : 0;
  
      // Recalcular el subtotal, IGV y total con el descuento actual
      detalle.subtotal = detalle.precioUnitario * detalle.cantidad - descuentoAplicado;
      detalle.igv = detalle.subtotal * 0.18;
      detalle.total = detalle.subtotal + detalle.igv;
  
      // Actualizar la tabla y los totales
      this.dataSource.data = [...this.detalleVentas];
      this.actualizarTotales();
    }
  }
  
  actualizarDescuento(index: number, descuento: number): void {
    const detalle = this.detalleVentas[index];
    
    // Asegurarse de que el descuento no sea negativo
    detalle.desconto = descuento >= 0 ? descuento : 0; 
  
    // Recalcular los valores con el nuevo descuento
    detalle.subtotal = detalle.precioUnitario * detalle.cantidad - detalle.desconto;
    detalle.igv = detalle.subtotal * 0.18;
    detalle.total = detalle.subtotal + detalle.igv;
  
    // Actualizar tabla y totales
    this.dataSource.data = [...this.detalleVentas];
    this.actualizarTotales();
  }
  
  
  actualizarTotales(): void {
    this.subtotal = this.detalleVentas.reduce((acc, item) => acc + item.subtotal, 0);
    this.igv = this.detalleVentas.reduce((acc, item) => acc + item.igv, 0);
    this.total = this.subtotal + this.igv;
  }
  
  

  eliminarDetalle(index: number): void {
    this.detalleVentas.splice(index, 1);
    this.dataSource.data = [...this.detalleVentas];
    this.actualizarTotales();
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
        desconto: detalle.desconto,
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
        this.obtenerVentas();
      },
      (error) => {
        console.error('Error al crear la venta', error);
      }
    );
  }
}
