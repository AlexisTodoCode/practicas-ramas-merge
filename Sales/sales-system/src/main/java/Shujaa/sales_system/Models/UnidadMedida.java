package Shujaa.sales_system.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "unidad_medida")
public class UnidadMedida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
}
