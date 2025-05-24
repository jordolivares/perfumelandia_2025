package com.example.perfumelandia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "producto")
@NoArgsConstructor
@AllArgsConstructor
@Entity


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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_inventario",  nullable = false)
    private Inventario inventario;
}
