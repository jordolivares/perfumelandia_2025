package com.example.perfumelandia.dto.response;

import com.example.perfumelandia.model.Inventario;

import lombok.Data;
import java.util.List;

@Data
public class InventarioDTO {
    private Long id;
    private String codigo;
    private String descripcion;
    private Integer totalcantidadDisponible;
    private Integer totalcantidadVendida;
    private Integer totalcantidadReservada;
    private List<ProductoDTO> productos;
    //= new ArrayList<>();  // Lista de productos simplificados

    public InventarioDTO(Inventario inventario) {
        this.id = inventario.getId();
        this.codigo = inventario.getCodigo();
        this.descripcion = inventario.getDescripcion();
        this.totalcantidadDisponible = inventario.getTotalcantidadDisponible();
        this.totalcantidadVendida = inventario.getTotalcantidadVendida();
        this.totalcantidadReservada = inventario.getTotalcantidadReservada();
    }
}

