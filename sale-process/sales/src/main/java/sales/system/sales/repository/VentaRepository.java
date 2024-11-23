package sales.system.sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sales.system.sales.model.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
}
