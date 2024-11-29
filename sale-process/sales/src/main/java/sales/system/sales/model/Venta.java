package sales.system.sales.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private Date fechaEmision;
    private Double subtotal;
    private String tipoComprobante;
    private Double igv;
    private Double total;

    private Boolean estado = true;

    // Usamos @JsonManagedReference en el lado "padre" de la relación
    @OneToMany(mappedBy = "venta")
    @JsonManagedReference
    private List<DetalleVenta> detalleVentas;
}
