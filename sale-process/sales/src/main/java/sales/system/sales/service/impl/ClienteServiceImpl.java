package sales.system.sales.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sales.system.sales.model.Cliente;
import sales.system.sales.repository.ClienteRepository;
import sales.system.sales.service.ClienteService;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente crear(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminar(Long id) {
        // Aquí cambiamos el estado del cliente a false en lugar de eliminarlo
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            cliente.setEstado(false);  // Cambiar estado a false (lógicamente eliminado)
            clienteRepository.save(cliente);  // Guardar el cliente con estado cambiado
        } else {
            throw new RuntimeException("Cliente no encontrado con id: " + id);
        }
    }

    @Override
    public Cliente actualizar(Long id, Cliente cliente) {
        // Verificar si el cliente existe
        Optional<Cliente> clienteExistenteOpt = clienteRepository.findById(id);
        if (!clienteExistenteOpt.isPresent()) {
            throw new RuntimeException("Cliente no encontrado con id: " + id);
        }

        // Obtener el cliente existente
        Cliente clienteExistente = clienteExistenteOpt.get();

        // Actualizar los campos del cliente
        clienteExistente.setNombre(cliente.getNombre());
        clienteExistente.setDireccion(cliente.getDireccion());
        clienteExistente.setDniRuc(cliente.getDniRuc());
        clienteExistente.setTelefono(cliente.getTelefono());
        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.setEstado(cliente.getEstado());

        // Guardar el cliente actualizado
        return clienteRepository.save(clienteExistente);
    }
}
