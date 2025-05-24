package com.example.perfumelandia.dto.response;

import com.example.perfumelandia.model.Inventario;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InventarioDTO {
    private Long id;
    private String codigo;
    private String descripcion;
    private Integer cantidadDisponible;
    private Integer cantidadVendida;
    private Integer cantidadReservada;
    private List<ProductoDTO> productos = new ArrayList<>();

    public InventarioDTO(Inventario inventario) {
        this.id = inventario.getId();
        this.codigo = inventario.getCodigo();
        this.descripcion = inventario.getDescripcion();
        this.cantidadDisponible = inventario.getCantidadDisponible();
        this.cantidadVendida = inventario.getCantidadVendida();
        this.cantidadReservada = inventario.getCantidadReservada();
    }
}
