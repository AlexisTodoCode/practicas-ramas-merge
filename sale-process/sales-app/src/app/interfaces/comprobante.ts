import { Venta } from "./venta";

export interface Comprobante {
    id: number;
    venta: Venta;
    fecha: string;
    numero: string;
    serie: string;
    estado: boolean;
  }
  