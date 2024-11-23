package sales.system.sales.service;

import sales.system.sales.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> obtenerTodos();
    Optional<Cliente> obtenerPorId(Long id);
    Cliente crear(Cliente cliente);
    void eliminar(Long id);
    Cliente actualizar(Long id, Cliente cliente);
}
