package com.example.perfumelandia.controller;


import com.example.perfumelandia.model.Inventario;
import com.example.perfumelandia.model.Producto;
import com.example.perfumelandia.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Producto", description = "CRUD de producto")
public class ControllerJordanProducto {


    @Autowired
    private ProductoService productoService;

    @PostMapping("/producto")
    @Operation(summary = "Crear Producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventario.class))),
            @ApiResponse(responseCode = "404", description = "Producto no creado")
    })
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        productoService.guardarProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(producto);
    }

    @GetMapping("/producto")
    @Operation(summary = "Buscar Todos los Producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventario.class))),
            @ApiResponse(responseCode = "404", description = "Productos no encontrado")
    })
    public ResponseEntity<List<Producto>> obtenerProductos() {
        List<Producto> productos = productoService.listarProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        Producto producto = productoService.obtenerProductoPorId(id);
        return ResponseEntity.ok(producto);
    }

    @PutMapping("/producto/{id}")
    @Operation(summary = "Actualizar producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos actualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventario.class))),
            @ApiResponse(responseCode = "404", description = "Productos no actualizado")
    })
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
         Producto nuevoProducto = productoService.actualizarProducto(id, producto);
        return ResponseEntity.ok(nuevoProducto);
    }


    @DeleteMapping("producto/{id}")
    @Operation(summary = "Eliminar producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos eliminado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventario.class))),
            @ApiResponse(responseCode = "404", description = "Productos no eliminado")
    })
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.ok("Producto eliminado correctamente.");
    }

}

