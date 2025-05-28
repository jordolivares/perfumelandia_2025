package com.example.perfumelandia.dto.response;

import  com.example.perfumelandia.model.Producto;
import lombok.Data;

@Data
public class ProductoDTO {
    private Long id;
    private String codigo;
    private String nombre;
    private String marca;
    private Integer precio;
    private Integer cantidad;
    private Long inventarioId;

    public ProductoDTO(Producto producto) {
        this.id = producto.getId();
        this.codigo = producto.getCodigo();
        this.nombre = producto.getNombre();
        this.marca = producto.getMarca();
        this.precio = producto.getPrecio();
    }

    public ProductoDTO(Long id,String codigo, String nombre, String marca, Integer precio) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
    }
}
