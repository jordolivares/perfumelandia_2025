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
    private Integer id;

    @Column(nullable = false)
    private String detalle;

    @Column(nullable = false)
    private Integer precioNeto;

    @Column(nullable = false)
    private Integer Iva;

    @Column(nullable = false)
    private Integer precioTotal;
}
