package Shujaa.sales_system.Controllers;

import Shujaa.sales_system.Models.Producto;
import Shujaa.sales_system.Services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> getProductos(){
        return productoService.getProductos();
    }

    @GetMapping("/{id}")
    public Optional<Producto> getProductoById(@PathVariable long id){
        return productoService.getProductoById(id);
    }

    @PostMapping
    public Producto saveProducto(@RequestBody Producto producto){
        return productoService.saveProducto(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable int id,@RequestBody Producto producto){
        producto.setId(id);
        return productoService.updateProducto(producto);
    }

    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable long id){
        productoService.deleteProducto(id);
    }
}
