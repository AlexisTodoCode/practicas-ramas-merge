package Shujaa.sales_system.Repositorys;

import Shujaa.sales_system.Models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
