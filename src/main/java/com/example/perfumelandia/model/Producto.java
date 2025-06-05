package com.example.perfumelandia.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Entity
@Table(name = "producto")
@NoArgsConstructor
@AllArgsConstructor

public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identificador único del producto.

    @Column(unique = true, nullable = false)
    private String codigo;  // Código único del producto.

    @Column(nullable = false)
    private String nombre;  // Nombre del producto.

    @Column(nullable = false)
    private String marca;  // Categoría del producto (e.g., perfumes, cosméticos, etc.)

    @Column(nullable = false)
    private Integer precio;  // Precio del producto.

    @Column(nullable = false)
    private Integer cantidad;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_inventario", nullable = false)
    @JsonBackReference
    private Inventario inventario;

}

