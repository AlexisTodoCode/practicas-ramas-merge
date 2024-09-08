package Shujaa.sales_system.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "detalle_venta")
@Data
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int cantidad;
    private double precioUnitario;
    private double subTotal;
    private int productoId;
    private int ventaId;
}
