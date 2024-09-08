package Shujaa.sales_system.Services;

import Shujaa.sales_system.Models.Proveedor;
import Shujaa.sales_system.Repositorys.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService implements IProveedorService{
    @Autowired
    ProveedorRepository proveedorRepository;

    @Override
    public List<Proveedor> getProveedores() {
        return proveedorRepository.findAll();
    }

    @Override
    public Optional<Proveedor> getProveedor(Long id) {
        return proveedorRepository.findById(id);
    }

    @Override
    public Proveedor saveProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public Proveedor updateProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public void deleteProveedor(Long id) {
        proveedorRepository.deleteById(id);
    }
}
