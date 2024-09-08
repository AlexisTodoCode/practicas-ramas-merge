package Shujaa.sales_system.Services;

import Shujaa.sales_system.Models.DetalleVenta;
import Shujaa.sales_system.Repositorys.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaService implements IDetalleVentaService {
    @Autowired
    DetalleVentaRepository detalleVentaRepository;


    @Override
    public List<DetalleVenta> getDetalleVenta() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public Optional<DetalleVenta> getDetalleVentaById(Long id) {
        return detalleVentaRepository.findById(id);
    }

    @Override
    public DetalleVenta saveDetalleVenta(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public DetalleVenta updateDetalleVenta(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public void deleteDetalleVenta(Long id) {
        detalleVentaRepository.deleteById(id);
    }
}
