package Shujaa.sales_system.Repositorys;

import Shujaa.sales_system.Models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
