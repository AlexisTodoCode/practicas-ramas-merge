package sales.system.sales.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sales.system.sales.model.*;
import sales.system.sales.repository.DetalleVentaRepository;
import sales.system.sales.repository.VentaRepository;
import sales.system.sales.service.VentaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;
    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public Optional<Venta> obtenerPorId(Long id) {
        return ventaRepository.findById(id);
    }

    @Override
    public List<Venta> obtenerTodas() {
        return ventaRepository.findAll();
    }

    @Override
    @Transactional
    public Venta crear(Venta venta) {
        Venta nuevaVenta = new Venta();
        nuevaVenta.setFechaEmision(venta.getFechaEmision());
        nuevaVenta.setSubtotal(venta.getSubtotal());
        nuevaVenta.setIgv(venta.getIgv());
        nuevaVenta.setTotal(venta.getTotal());

        Cliente cliente = new Cliente();
        cliente.setId(venta.getCliente().getId());
        nuevaVenta.setCliente(cliente);

        Usuario usuario = new Usuario();
        usuario.setId(venta.getUsuario().getId());
        nuevaVenta.setUsuario(usuario);

        nuevaVenta = ventaRepository.save(nuevaVenta);

        List<DetalleVenta> detalleVentas = new ArrayList<>();
        for (DetalleVenta detalleDTO : venta.getDetalleVentas()) {
            Producto producto = new Producto();
            producto.setId(detalleDTO.getProducto().getId());

            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setProducto(producto);
            detalleVenta.setCantidad(detalleDTO.getCantidad());
            detalleVenta.setPrecioUnitario(detalleDTO.getPrecioUnitario());
            detalleVenta.setSubtotal(detalleDTO.getSubtotal());
            detalleVenta.setIgv(detalleDTO.getIgv());
            detalleVenta.setTotal(detalleDTO.getTotal());
            detalleVenta.setVenta(nuevaVenta);

            detalleVentas.add(detalleVenta);
        }

        nuevaVenta.setDetalleVentas(detalleVentas);

        detalleVentaRepository.saveAll(detalleVentas);

        return nuevaVenta;
    }

    @Override
    @Transactional
    public Venta actualizar(Long id, Venta venta) {
        Optional<Venta> ventaExistenteOpt = ventaRepository.findById(id);
        if (!ventaExistenteOpt.isPresent()) {
            throw new RuntimeException("Venta no encontrada con id: " + id);
        }

        Venta ventaExistente = ventaExistenteOpt.get();

        ventaExistente.setCliente(venta.getCliente());
        ventaExistente.setUsuario(venta.getUsuario());
        ventaExistente.setSubtotal(venta.getSubtotal());
        ventaExistente.setIgv(venta.getIgv());
        ventaExistente.setTotal(venta.getTotal());

        List<DetalleVenta> detalleVentas = new ArrayList<>();
        for (DetalleVenta detalleVentaDTO : venta.getDetalleVentas()) {
            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setProducto(detalleVentaDTO.getProducto());
            detalleVenta.setCantidad(detalleVentaDTO.getCantidad());
            detalleVenta.setPrecioUnitario(detalleVentaDTO.getPrecioUnitario());
            detalleVenta.setTotal(detalleVentaDTO.getTotal());
            detalleVenta.setVenta(ventaExistente);
            detalleVentas.add(detalleVenta);
        }
        ventaExistente.setDetalleVentas(detalleVentas);

        return ventaRepository.save(ventaExistente);
    }

    @Override
    public void eliminar(Long id) {
        ventaRepository.deleteById(id);
    }
}
