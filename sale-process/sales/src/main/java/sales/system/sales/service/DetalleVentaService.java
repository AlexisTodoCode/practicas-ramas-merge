package sales.system.sales.service;

import sales.system.sales.model.DetalleVenta;

import java.util.List;

public interface DetalleVentaService {
    List<DetalleVenta> obtenerPorVentaId(Long ventaId);
    DetalleVenta crear(DetalleVenta detalleVenta);
    void eliminar(Long id);
}
