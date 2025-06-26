package com.example.perfumelandia.controller;

import com.example.perfumelandia.model.Inventario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Crear Pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido Creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pedido.class))),
            @ApiResponse(responseCode = "404", description = "Pedido no Creado")})
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {

        Pedido nuevoPedido = pedidoService.crearPedido(pedido);
        return ResponseEntity.ok(nuevoPedido);
    }

    @PutMapping("/actualizarPedido/{id}")
    @Operation(summary = "Actualizar Pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido actualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pedido.class))),
            @ApiResponse(responseCode = "404", description = "Pedido no actualizado")})
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        Pedido pedidoActualizado = pedidoService.actualizarPedido(id, pedido);
        return pedidoActualizado !=null ? ResponseEntity.ok(pedidoActualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("eliminarPedido/{id}")
    @Operation(summary = "Eliminar Pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido Eliminado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventario.class))),
            @ApiResponse(responseCode = "404", description = "Pedido no Eliminado")})
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscarPedido/{id}")
    @Operation(summary = "Buscar Pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido Encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventario.class))),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")})
    public ResponseEntity<Pedido> buscarPedido(@PathVariable Long id) {
        Pedido pedidoEncontrado = pedidoService.buscarPedido(id);
        return pedidoEncontrado !=null ? ResponseEntity.ok(pedidoEncontrado) :  ResponseEntity.notFound().build();
    }

}
