package sales.system.sales.service;

import sales.system.sales.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> obtenerTodos();
    Optional<Producto> obtenerPorId(Long id);
    Producto crear(Producto producto);
    Producto actualizar(Long id, Producto producto);
    void eliminar(Long id);
}
