import { Producto } from './producto';
import { Venta } from './venta';

export interface DetalleVenta {
  id: number;
  venta: Venta | null; 
  producto: Producto;
  cantidad: number;
  precioUnitario: number;
  subtotal: number;
  igv: number;
  total: number;
  estado: boolean;
}
