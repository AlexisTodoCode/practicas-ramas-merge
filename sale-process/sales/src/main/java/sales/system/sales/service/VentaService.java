package sales.system.sales.service;

import sales.system.sales.model.Venta;

import java.util.List;
import java.util.Optional;

public interface VentaService {
    List<Venta> obtenerTodas();
    Optional<Venta> obtenerPorId(Long id);
    Venta crear(Venta venta);
    Venta actualizar(Long id, Venta venta);  // Nuevo método para actualizar
    void eliminar(Long id);
}
