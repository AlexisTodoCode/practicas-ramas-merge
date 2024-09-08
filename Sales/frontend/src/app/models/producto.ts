export interface Producto {
  id?: number;
  nombre: string;
  descripcion: string;
  precio: number;
  peso: number;
  volumen: number;
  stock: number;
  stockMinimo: number;
  stockMaximo: number;
  categoriaId: number;
  unidadMedidaId: number;
}
