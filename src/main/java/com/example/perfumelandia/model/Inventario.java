package com.example.perfumelandia.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String codigo;  // Identificador único del producto (Código del producto).

    @Column(nullable = false)
    private String descripcion;  // Descripción del producto.

    @Column(nullable = false)
    private Integer cantidadDisponible;  // Cantidad disponible en inventario.

    @Column(nullable = false)
    private Integer cantidadVendida;

    @Column(nullable = false)
    private Integer cantidadReservada;  // Cantidad reservada (por ejemplo, en pedidos).

    @OneToMany(mappedBy = "inventario",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true)
    private List<Producto> productos = new ArrayList<>();
}
