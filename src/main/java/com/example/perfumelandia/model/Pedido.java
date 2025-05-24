package com.example.perfumelandia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //* revisar esta parte
    private Integer id; // Código producto

    @Column(unique = true, nullable = false)
    private String Usuario; // Cliente

    @Column(unique = true, nullable = false)
    private List<Producto> productos; // Productos a comprar

    @Column(unique = true, nullable = false)
    private Integer cantidad; // Cantidad de productos

    @Column(unique = true, nullable = false)
    private Integer precioTotal; // Precio total del producto

}
