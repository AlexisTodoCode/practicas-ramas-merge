package Shujaa.sales_system.Services;

import Shujaa.sales_system.Models.UnidadMedida;

import java.util.List;
import java.util.Optional;

public interface IUnidadMedidaService {
    List<UnidadMedida> getUnidadMedidas();
    Optional<UnidadMedida> getUnidadMedidaById(Long id);
    UnidadMedida saveUnidadMedida(UnidadMedida unidadMedida);
    UnidadMedida updateUnidadMedida(UnidadMedida unidadMedida);
    void deleteUnidadMedida(Long id);
}
