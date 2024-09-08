package Shujaa.sales_system.Services;

import Shujaa.sales_system.Models.Categoria;

import java.util.List;
import java.util.Optional;

public interface ICategoriaService {
    Categoria saveCategoria(Categoria categoria);
    List<Categoria> getCategorias();
    Optional<Categoria> getCategoriaById(Long id);
    Categoria updateCategoria(Categoria categoria);
    void deleteCategoria(Long id);
}
