package Shujaa.sales_system.Services;

import Shujaa.sales_system.Models.UnidadMedida;
import Shujaa.sales_system.Repositorys.UnidadMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadMedidaService implements IUnidadMedidaService{

    @Autowired
     UnidadMedidaRepository unidadMedidaRepository;

    @Override
    public List<UnidadMedida> getUnidadMedidas() {
        return unidadMedidaRepository.findAll();
    }

    @Override
    public Optional<UnidadMedida> getUnidadMedidaById(Long id) {
        return unidadMedidaRepository.findById(id);
    }

    @Override
    public UnidadMedida saveUnidadMedida(UnidadMedida unidadMedida) {
        return unidadMedidaRepository.save(unidadMedida);
    }

    @Override
    public UnidadMedida updateUnidadMedida(UnidadMedida unidadMedida) {
        return unidadMedidaRepository.save(unidadMedida);
    }

    @Override
    public void deleteUnidadMedida(Long id) {
        unidadMedidaRepository.deleteById(id);
    }
}
