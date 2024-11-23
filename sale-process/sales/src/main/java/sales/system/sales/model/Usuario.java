package sales.system.sales.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreUsuario;
    private String password;
    private String rol;
    private Boolean estado = true;

    @OneToMany(mappedBy = "usuario")
    @JsonBackReference
    private List<Venta> ventas;
}
