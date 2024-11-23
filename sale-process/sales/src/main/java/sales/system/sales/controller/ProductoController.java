package sales.system.sales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sales.system.sales.model.Producto;
import sales.system.sales.service.ProductoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodos() {
        List<Producto> productos = productoService.obtenerTodos();
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build(); // Si no hay productos, devolvemos código 204
        }
        return ResponseEntity.ok(productos); // Si hay productos, devolvemos código 200 con la lista
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        Optional<Producto> productoOpt = productoService.obtenerPorId(id);
        if (!productoOpt.isPresent()) {
            return ResponseEntity.notFound().build(); // Si no existe el producto, devolvemos código 404
        }
        return ResponseEntity.ok(productoOpt.get()); // Si existe, devolvemos el producto con código 200
    }

    // Crear un nuevo producto
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        try {
            Producto nuevoProducto = productoService.crear(producto);
            return ResponseEntity.status(201).body(nuevoProducto); // Devolvemos código 201 (creado) con el producto
        } catch (Exception e) {
            return ResponseEntity.badRequest().build(); // Si hay error en la creación, devolvemos código 400
        }
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        try {
            Producto productoActualizado = productoService.actualizar(id, producto);
            return ResponseEntity.ok(productoActualizado); // Si se actualiza correctamente, devolvemos código 200
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // Si no se encuentra el producto, devolvemos código 404
        }
    }

    // Eliminar (soft delete) un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        try {
            productoService.eliminar(id); // Llamamos al servicio para eliminar el producto
            return ResponseEntity.noContent().build(); // Devolvemos código 204 (sin contenido) si fue eliminada correctamente
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // Si no existe el producto, devolvemos código 404
        }
    }
}
