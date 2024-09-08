package Shujaa.sales_system.Services;

import Shujaa.sales_system.Models.Venta;
import Shujaa.sales_system.Repositorys.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService implements IVentaService{
    @Autowired
    VentaRepository ventaRepository;

    @Override
    public List<Venta> getVenta() {
        return ventaRepository.findAll();
    }

    @Override
    public Optional<Venta> getVentaById(Long id) {
        return ventaRepository.findById(id);
    }

    @Override
    public Venta saveVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public Venta updateVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public void deleteVenta(Long id) {
        ventaRepository.deleteById(id);
    }
}
