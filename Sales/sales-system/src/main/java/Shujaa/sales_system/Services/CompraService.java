package Shujaa.sales_system.Services;

import Shujaa.sales_system.Models.Compra;
import Shujaa.sales_system.Repositorys.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraService implements ICompraService{
    @Autowired
    CompraRepository compraRepository;

    @Override
    public List<Compra> getCompras() {
        return compraRepository.findAll();
    }

    @Override
    public Optional<Compra> getCompraById(Long id) {
        return compraRepository.findById(id);
    }

    @Override
    public Compra saveCompra(Compra compra) {
        return compraRepository.save(compra);
    }

    @Override
    public Compra updateCompra(Compra compra) {
        return compraRepository.save(compra);
    }

    @Override
    public void deleteCompra(Long id) {
        compraRepository.deleteById(id);
    }
}
