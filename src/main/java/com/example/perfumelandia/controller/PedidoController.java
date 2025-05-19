package com.example.perfumelandia.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.perfumelandia.model.Pedido;
import com.example.perfumelandia.service.PedidoService;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {

        Pedido nuevoPedido = pedidoService.crearPedido(pedido);
        return ResponseEntity.ok(nuevoPedido);
    }

    @PutMapping("/{Id}")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable int Id, @RequestBody Pedido pedido) {
        Pedido pedidoActualizado = pedidoService.actualizarPedido(Id, pedido);
        return pedidoActualizado !=nulll ? ResponseEntity.ok(pedidoActualizado) : new ResponseEntity.notFound().build();
    }

}
