package com.example.perfumelandia.controller;

import com.example.perfumelandia.model.Inventario;
import com.example.perfumelandia.service.InventarioService;
import com.example.perfumelandia.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ControllerJordanInventario {


    @Autowired
    private ProductoService productoService;

    @Autowired
    private InventarioService inventarioService;


    @PostMapping("/inventario")
    public ResponseEntity<Inventario> crearInventario(@RequestBody Inventario inventario) {

        Inventario inventario1 = inventarioService.agregarInventario(inventario);
        return ResponseEntity.status(HttpStatus.CREATED).body(inventario1);
    }

    @GetMapping("/inventario")
    public ResponseEntity<List<Inventario>> obtenerTodosInventario() {

        List<Inventario> inventario = inventarioService.buscarInventarios();
        return ResponseEntity.ok(inventario);
    }

    @GetMapping("/inventario/{id}")
    public ResponseEntity<Inventario> obtenerInventario(@PathVariable Long id) {

        Inventario inventario = inventarioService.buscarInventarioPorId(id);
        return ResponseEntity.ok(inventario);
    }

    @PutMapping("/inventario/{id}")
    public ResponseEntity<Inventario> actualizarInventario(@PathVariable Long id, @RequestBody Inventario inventario) {
        inventario.setId(id);
        inventarioService.agregarInventario(inventario);
        return ResponseEntity.ok(inventario);
    }

    @DeleteMapping("/inventario/{id}")
    public ResponseEntity<String> eliminarInventario(@PathVariable Long id) {
        inventarioService.eliminarInventario(id);
        return ResponseEntity.ok("Producto con ID " + id + " eliminado correctamente.");
    }



}
