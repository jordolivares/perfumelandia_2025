package com.example.perfumelandia.controller;

import org.springframework.web.bind.annotation.*;

import com.example.perfumelandia.model.Pedido;
import com.example.perfumelandia.service.PedidoService;
import org.springframework.http.ResponseEntity;



@RestController
@RequestMapping("/api/pedidos")
public class PedidoControllerNicolas {

    private final PedidoService pedidoService;

    public PedidoControllerNicolas(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {

        Pedido nuevoPedido = pedidoService.crearPedido(pedido);
        return ResponseEntity.ok(nuevoPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable int id, @RequestBody Pedido pedido) {
        Pedido pedidoActualizado = pedidoService.actualizarPedido(id, pedido);
        return pedidoActualizado !=null ? ResponseEntity.ok(pedidoActualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    Public ResponseEntity<String> eliminarPedido(@PathVariable Integer id) {

        Boolean eliminar = pedidoService.eliminarPedido(id);
        IF (eliminado) {
            return ResponseEntity.ok("Pedido eliminado con exito");
        } else {
            return ResponseEntity.status(404).body("Pedido no encontrado");
        }
    }

}
