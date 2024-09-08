package Shujaa.sales_system.Models;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String descripcion;
    private Double precio;
    private Double peso;
    private Double volumen;
    private Integer stock;
    private Integer stockMinimo;
    private Integer stockMaximo;
    private Integer categoriaId;
    private Integer unidadMedidaId;
}

