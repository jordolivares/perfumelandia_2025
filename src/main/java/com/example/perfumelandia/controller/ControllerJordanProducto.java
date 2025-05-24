package com.example.perfumelandia.controller;

import com.example.perfumelandia.model.Producto;
import com.example.perfumelandia.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class ControllerJordanProducto {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/producto")
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
        Producto productos = productoService.guardarProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productos);

    }
}
