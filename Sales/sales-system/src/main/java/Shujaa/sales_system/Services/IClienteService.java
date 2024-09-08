package Shujaa.sales_system.Services;

import Shujaa.sales_system.Models.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {
    List<Cliente> getClientes();
    Optional<Cliente> getClienteById(Long id);
    Cliente saveCliente(Cliente cliente);
    Cliente updateCliente(Cliente cliente);
    void deleteCliente(Long id);

}
