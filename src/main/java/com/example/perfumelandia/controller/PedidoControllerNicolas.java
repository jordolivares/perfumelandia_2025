package com.example.perfumelandia.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.perfumelandia.model.Pedido;
import com.example.perfumelandia.service.PedidoService;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoControllerNicolas {

    @Autowired
    private final PedidoService pedidoService;

    public PedidoControllerNicolas(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/crearPedido")
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {

        Pedido nuevoPedido = pedidoService.crearPedido(pedido);
        return ResponseEntity.ok(nuevoPedido);
    }

    @PutMapping("/actualizarPedido/{id}")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        Pedido pedidoActualizado = pedidoService.actualizarPedido(id, pedido);
        return pedidoActualizado !=null ? ResponseEntity.ok(pedidoActualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("eliminarPedido/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }

}
