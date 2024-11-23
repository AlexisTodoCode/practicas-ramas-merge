package sales.system.sales.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sales.system.sales.model.DetalleVenta;
import sales.system.sales.repository.DetalleVentaRepository;
import sales.system.sales.service.DetalleVentaService;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Override
    public List<DetalleVenta> obtenerPorVentaId(Long ventaId) {
        return detalleVentaRepository.findByVentaId(ventaId); // Consulta todos los detalles de una venta específica
    }

    @Override
    @Transactional
    public DetalleVenta crear(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta); // Guarda un detalle de venta
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Optional<DetalleVenta> detalleVentaOpt = detalleVentaRepository.findById(id);
        if (detalleVentaOpt.isPresent()) {
            DetalleVenta detalleVenta = detalleVentaOpt.get();
            detalleVenta.setEstado(false); // Marcamos como inactivo el detalle
            detalleVentaRepository.save(detalleVenta); // Guardamos el detalle con el estado cambiado
        } else {
            throw new RuntimeException("DetalleVenta no encontrado con id: " + id);
        }
    }
}
