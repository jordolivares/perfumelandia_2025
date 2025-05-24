package com.example.perfumelandia.controller;

import com.example.perfumelandia.model.Inventario;
import com.example.perfumelandia.model.Producto;
import com.example.perfumelandia.service.InventarioService;
import com.example.perfumelandia.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class ControllerJordanInventario {


    @Autowired
    private ProductoService productoService;

    @Autowired
    private InventarioService inventarioService;

    @PostMapping("/producto")
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
        Producto productos = productoService.guardarProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productos);

    }



    @GetMapping
    public ResponseEntity<List<Inventario>> obtenerInventarios() {
        List<Inventario> inventarios = inventarioService.listarInventarios();
        return ResponseEntity.ok(inventarios);
    }

    @PostMapping("/inventario")
    public ResponseEntity<Inventario>  agregarInventario(@RequestBody Inventario inventario) {
        Inventario inventario1 = inventarioService.crearInventario(inventario);
        return ResponseEntity.status(HttpStatus.CREATED).body(inventario1);
    }


    @PostMapping("/inventarios/{id_inventario}/productos")
    public ResponseEntity<Producto> agregarProductoAInventario(
            @PathVariable Long id_inventario,
            @RequestBody Producto producto) {
        return ResponseEntity.ok(inventarioService.agregarProductoAInventario(id_inventario, producto));
    }




}

