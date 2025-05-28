package com.example.perfumelandia.controller;

import com.example.perfumelandia.dto.response.ProductoDTO;
import com.example.perfumelandia.model.Inventario;
import com.example.perfumelandia.model.Producto;
import com.example.perfumelandia.service.InventarioService;
import com.example.perfumelandia.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ControllerJordanProducto {


    @Autowired
    private ProductoService productoService;

    @Autowired
    private InventarioService inventarioService;

    //Agregar producto a un inventario existente
    @PostMapping("/producto")
    public ResponseEntity<?> agregarProducto(@RequestBody ProductoDTO dto) {
        try {
            Inventario inventarioExistente = inventarioService.buscarInventario(dto.getInventarioId());
            if (inventarioExistente == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Inventario con ID " + dto.getInventarioId() + " no existe.");
            }

            Producto producto = new Producto();
            producto.setCodigo(dto.getCodigo());
            producto.setNombre(dto.getNombre());
            producto.setMarca(dto.getMarca());
            producto.setPrecio(dto.getPrecio());
            producto.setCantidad(dto.getCantidad());
            producto.setInventario(inventarioExistente);

            Producto guardado = productoService.guardarProducto(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el producto: " + e.getMessage());
        }
    }


    //Eliminar Producto
    @DeleteMapping("/producto/{id}")
    public void eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);

    }


}

