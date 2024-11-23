package sales.system.sales.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import jakarta.persistence.*;

import java.util.List;


@Data
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String direccion;
    private String dniRuc;
    private String telefono;
    private String email;

    private Boolean estado = true;

    @OneToMany(mappedBy = "cliente")
    @JsonBackReference
    private List<Venta> ventas;
}
