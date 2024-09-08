package Shujaa.sales_system.Services;

import Shujaa.sales_system.Models.Proveedor;

import java.util.List;
import java.util.Optional;

public interface IProveedorService {
    List<Proveedor> getProveedores();
    Optional<Proveedor> getProveedor(Long id);
    Proveedor saveProveedor(Proveedor proveedor);
    Proveedor updateProveedor(Proveedor proveedor);
    void deleteProveedor(Long id);
}
