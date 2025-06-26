package com.example.perfumelandia.controller;

import com.example.perfumelandia.assemblers.PedidoModelAssemblers;
import com.example.perfumelandia.service.PedidoService;
import com.example.perfumelandia.model.Pedido;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/Pedidos")
public class PedidoControllerNicolasV2 {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoModelAssemblers assembler;

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Pedido> getPedidoById(@PathVariable Long id) {
        Pedido pedido = pedidoService.buscarPedido(id);
        return assembler.toModel(pedido);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Pedido>> createPedido(@RequestBody Pedido pedido) {
        Pedido newPedido = pedidoService.guardarPedido(pedido);
        return ResponseEntity
                .created(linkTo(methodOn(PedidoControllerNicolasV2.class).getPedidoById(newPedido.getId())).toUri())
                .body(assembler.toModel(pedido));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Pedido>> actualizarPedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        pedido.setId(id);
        Pedido actualizarPedido = pedidoService.guardarPedido(pedido);
        return ResponseEntity.ok(assembler.toModel(actualizarPedido));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deletePedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
