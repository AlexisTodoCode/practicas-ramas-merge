package sales.system.sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sales.system.sales.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
