package Shujaa.sales_system.Services;

import Shujaa.sales_system.Models.Compra;

import java.util.List;
import java.util.Optional;

public interface ICompraService {
    List<Compra> getCompras();
    Optional<Compra> getCompraById(Long id);
    Compra saveCompra(Compra compra);
    Compra updateCompra(Compra compra);
    void deleteCompra(Long id);
}
