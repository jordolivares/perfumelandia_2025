package com.example.perfumelandia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name = "Pedido")
@NoArgsConstructor
@AllArgsConstructor

public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //* revisar esta parte
    private Long id; // Código producto

    @Column(nullable = false) //*Fecha del pedido
    private Date fechaPedido;

    @Column(nullable = false)
    private Integer cantidad; // Cantidad de productos

    @Column(nullable = false)
    private Integer precioTotal; // Precio total del producto

    @ManyToOne
    @JoinColumn(name = "id_usuario_fk", nullable = false)
    private Usuario usuario;

}
