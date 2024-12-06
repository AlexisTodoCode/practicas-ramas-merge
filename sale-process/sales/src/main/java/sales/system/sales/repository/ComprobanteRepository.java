package sales.system.sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sales.system.sales.model.Comprobante;
import sales.system.sales.model.Venta;

import java.util.Optional;

@Repository
public interface ComprobanteRepository extends JpaRepository<Comprobante, Long> {
    Optional<Comprobante> findTopBySerieOrderByNumeroDesc(String serie);

    Optional<Comprobante> findByVenta(Venta venta);
}
