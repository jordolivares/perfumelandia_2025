package com.example.perfumelandia.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "inventario")
@NoArgsConstructor
@AllArgsConstructor

public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String codigo;  // Identificador único del producto (Código del producto).

    @Column(nullable = false)
    private String descripcion;  // Descripción del producto.

    @Column(nullable = false)
    private Integer totalcantidadDisponible = 0;  // Cantidad disponible en inventario. suma total producto

    @Column(nullable = false)
    private Integer totalcantidadVendida;

    @Column(nullable = false)
    private Integer totalcantidadReservada;  // Cantidad reservada (por ejemplo, en pedidos).

    @OneToMany(mappedBy = "inventario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Producto> productos = new ArrayList<>();
}

