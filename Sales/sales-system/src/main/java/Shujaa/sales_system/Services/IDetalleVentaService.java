package Shujaa.sales_system.Services;


import Shujaa.sales_system.Models.DetalleVenta;

import java.util.List;
import java.util.Optional;

public interface IDetalleVentaService {
    List<DetalleVenta> getDetalleVenta();
    Optional<DetalleVenta> getDetalleVentaById(Long id);
    DetalleVenta saveDetalleVenta(DetalleVenta detalleVenta);
    DetalleVenta updateDetalleVenta(DetalleVenta detalleVenta);
    void deleteDetalleVenta(Long id);
}
