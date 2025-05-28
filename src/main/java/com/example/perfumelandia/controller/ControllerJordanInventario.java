package com.example.perfumelandia.controller;

import com.example.perfumelandia.dto.response.InventarioDTO;
import com.example.perfumelandia.model.Inventario;
import com.example.perfumelandia.model.Producto;
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


    @GetMapping
    public ResponseEntity<List<Inventario>> obtenerInventarios() {
        List<Inventario> inventarios = inventarioService.listarInventarios();
        return ResponseEntity.ok(inventarios);
    }

    @PostMapping("/inventario")
    public ResponseEntity<Inventario> agregarInventario(@RequestBody Inventario inventario) {
        Inventario inventario1 = inventarioService.guardarInventario(inventario);
        return ResponseEntity.status(HttpStatus.CREATED).body(inventario1);
    }

    @PutMapping("/inventario/{id}")
    public ResponseEntity<Inventario> actualizarInventario(@RequestBody Inventario inventario, @PathVariable Long id) {
        Inventario inventario1 = inventarioService.buscarInventario(id);
        inventario1.setId(inventario.getId());
        inventario1.setCodigo(inventario.getCodigo());
        inventario1.setDescripcion(inventario.getDescripcion());
        inventario1.setTotalcantidadDisponible(inventario.getTotalcantidadDisponible());
        inventario1.setTotalcantidadVendida(inventario.getTotalcantidadVendida());
        inventario1.setTotalcantidadReservada(inventario.getTotalcantidadReservada());
        inventarioService.guardarInventario(inventario1);
        return ResponseEntity.ok(inventario1);
    }

    @DeleteMapping("/inventario/{id}")
    public void eliminarInventario(@PathVariable Long id) {
        inventarioService.eliminarInventario(id);

    }


}
