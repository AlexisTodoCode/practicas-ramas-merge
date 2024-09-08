package Shujaa.sales_system.Services;

import Shujaa.sales_system.Models.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {

    Producto saveProducto(Producto producto);
    Producto updateProducto(Producto producto);
    List<Producto> getProductos();
    Optional<Producto> getProductoById(Long id);
    void deleteProducto(Long id);
}
