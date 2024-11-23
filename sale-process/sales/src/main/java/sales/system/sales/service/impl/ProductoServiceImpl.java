package sales.system.sales.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sales.system.sales.model.Producto;
import sales.system.sales.repository.ProductoRepository;
import sales.system.sales.service.ProductoService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    @Transactional
    public Producto crear(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    @Transactional
    public Producto actualizar(Long id, Producto producto) {
        Optional<Producto> productoExistenteOpt = productoRepository.findById(id);
        if (productoExistenteOpt.isPresent()) {
            Producto productoExistente = productoExistenteOpt.get();
            productoExistente.setCodigoProducto(producto.getCodigoProducto());
            productoExistente.setNombreProducto(producto.getNombreProducto());
            productoExistente.setDescripcion(producto.getDescripcion());
            productoExistente.setPrecio(producto.getPrecio());
            productoExistente.setStock(producto.getStock());
            productoExistente.setEstado(producto.getEstado());
            return productoRepository.save(productoExistente);
        }
        throw new RuntimeException("Producto no encontrado con el id: " + id);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Optional<Producto> productoExistenteOpt = productoRepository.findById(id);
        if (productoExistenteOpt.isPresent()) {
            Producto productoExistente = productoExistenteOpt.get();
            productoExistente.setEstado(false);  // Marcamos como inactivo en lugar de eliminarlo físicamente
            productoRepository.save(productoExistente);
        } else {
            throw new RuntimeException("Producto no encontrado con el id: " + id);
        }
    }
}
