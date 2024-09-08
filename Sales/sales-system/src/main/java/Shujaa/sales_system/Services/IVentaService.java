package Shujaa.sales_system.Services;

import Shujaa.sales_system.Models.Venta;

import java.util.List;
import java.util.Optional;

public interface IVentaService {
    List<Venta> getVenta();
    Optional<Venta> getVentaById(Long id);
    Venta saveVenta(Venta venta);
    Venta updateVenta(Venta venta);
    void deleteVenta(Long id);

}
