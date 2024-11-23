package sales.system.sales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sales.system.sales.model.DetalleVenta;
import sales.system.sales.service.DetalleVentaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/detalleventa")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    // Obtener los detalles de venta por ID de venta
    @GetMapping("/venta/{ventaId}")
    public ResponseEntity<List<DetalleVenta>> obtenerPorVentaId(@PathVariable Long ventaId) {
        List<DetalleVenta> detalles = detalleVentaService.obtenerPorVentaId(ventaId);
        if (detalles.isEmpty()) {
            return ResponseEntity.noContent().build(); // Si no hay detalles, devolvemos código 204
        }
        return ResponseEntity.ok(detalles); // Si hay detalles, devolvemos código 200 con la lista
    }

    // Crear un nuevo detalle de venta
    @PostMapping
    public ResponseEntity<DetalleVenta> crearDetalleVenta(@RequestBody DetalleVenta detalleVenta) {
        try {
            DetalleVenta nuevoDetalle = detalleVentaService.crear(detalleVenta);
            return ResponseEntity.status(201).body(nuevoDetalle); // Devolvemos código 201 (creado) con el detalle
        } catch (Exception e) {
            return ResponseEntity.badRequest().build(); // Si hay error, devolvemos código 400
        }
    }

    // Eliminar un detalle de venta (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalleVenta(@PathVariable Long id) {
        try {
            detalleVentaService.eliminar(id);
            return ResponseEntity.noContent().build(); // Si la eliminación fue exitosa, devolvemos código 204
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // Si no se encuentra el detalle, devolvemos código 404
        }
    }
}
