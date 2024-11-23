package sales.system.sales.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoProducto;
    private String nombreProducto;
    private String descripcion;
    private Double precio;
    private Integer stock;

    private Boolean estado = true;

    @OneToMany(mappedBy = "producto")
    @JsonBackReference
    private List<DetalleVenta> detalleVentas;
}

