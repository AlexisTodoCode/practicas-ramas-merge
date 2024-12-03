import { Cliente } from './cliente';
import { Usuario } from './usuario';
import { DetalleVenta } from './detalle-venta';

export interface Venta {
  id: number;
  tipoComprobante: string; 
  cliente: Cliente;  
  usuario: Usuario;  
  fechaEmision: string;  
  subtotal: number;
  igv: number;
  total: number;
  estado: boolean;
  detalleVentas: DetalleVenta[];  
}
