package com.example.perfumelandia.controller;

import com.example.perfumelandia.model.Inventario;
import com.example.perfumelandia.service.InventarioService;
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
@RequestMapping("api/v1")
@Tag(name = "Inventario", description = "CRUD de inventario")
public class ControllerJordanInventario {


    @Autowired
    private ProductoService productoService;

    @Autowired
    private InventarioService inventarioService;



    @PostMapping("/inventario")
    @Operation(summary = "Crear Inventario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario guardado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventario.class))),
            @ApiResponse(responseCode = "404", description = "Inventario no guardado")
    })
    public ResponseEntity<Inventario> crearInventario(@RequestBody Inventario inventario) {

        Inventario inventario1 = inventarioService.agregarInventario(inventario);
        return ResponseEntity.status(HttpStatus.CREATED).body(inventario1);
    }


    @GetMapping("/inventario")
    @Operation(summary = "Buscar Todos Inventario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventario.class))),
            @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    public ResponseEntity<List<Inventario>> obtenerTodosInventario() {

        List<Inventario> inventario = inventarioService.buscarInventarios();
        return ResponseEntity.ok(inventario);
    }

    @GetMapping("/inventario/{id}")
    public ResponseEntity<Inventario> obtenerInventarioId(@PathVariable Long id) {

        Inventario inventario = inventarioService.buscarInventarioPorId(id);
        return ResponseEntity.ok(inventario);
    }


    @PutMapping("/inventario/{id}")
    @Operation(summary = "Actualizar Inventario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario actualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventario.class))),
            @ApiResponse(responseCode = "404", description = "Inventario no actualizado")
    })
    public ResponseEntity<Inventario> actualizarInventario(@PathVariable Long id, @RequestBody Inventario inventario) {
        inventario.setId(id);
        inventarioService.agregarInventario(inventario);
        return ResponseEntity.ok(inventario);
    }

    @DeleteMapping("/inventario/{id}")
    @Operation(summary = "Eliminar Inventario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario eliminado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventario.class))),
            @ApiResponse(responseCode = "404", description = "Inventario no elimando")
    })
    public ResponseEntity<String> eliminarInventario(@PathVariable Long id) {
        inventarioService.eliminarInventario(id);
        return ResponseEntity.ok("Producto con ID " + id + " eliminado correctamente.");
    }



}
