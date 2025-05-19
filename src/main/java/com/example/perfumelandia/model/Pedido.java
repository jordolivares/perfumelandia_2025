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
    private Integer id;

    private String Usuario;

    private List<Producto> productos;

    private Integer cantidad;

    private Integer precioTotal;

}
