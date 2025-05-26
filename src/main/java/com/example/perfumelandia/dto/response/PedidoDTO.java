package com.example.perfumelandia.dto.response;

import com.example.perfumelandia.model.Pedido;
import lombok.Data;

import java.util.Date;

@Data
public class PedidoDTO {

    private Long id;
    private Date fechaPedido;
    private Integer cantidad;
    private Integer precioTotal;
    private Long usuarioid;

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.fechaPedido = pedido.getFechaPedido();
        this.cantidad = pedido.getCantidad();
        this.precioTotal = pedido.getPrecioTotal();
        this.usuarioid = pedido.getUsuario().getId();
    }
}