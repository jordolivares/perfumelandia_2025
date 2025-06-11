package com.example.perfumelandia.service;


import com.example.perfumelandia.model.Inventario;
import com.example.perfumelandia.model.Producto;
import com.example.perfumelandia.repository.InventarioRepository;
import com.example.perfumelandia.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private InventarioRepository inventarioRepository;

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id);
    }

    public Producto guardarProducto(Producto producto) {

        Long inventarioId = producto.getInventario().getId();

        if (inventarioId == null || !inventarioRepository.existsById(inventarioId)) {
            throw new IllegalArgumentException("Inventario inválido o inexistente");
        }

        Inventario inventario = inventarioRepository.findById(inventarioId).orElseThrow();
        producto.setInventario(inventario);

        return productoRepository.save(producto);


    }
    public Producto actualizarProducto(Long id, Producto productoNuevo) {
        Producto producto = productoRepository.findById(id);

        producto.setCodigo(productoNuevo.getCodigo());
        producto.setNombre(productoNuevo.getNombre());
        producto.setPrecio(productoNuevo.getPrecio());
        producto.setCantidad(productoNuevo.getCantidad());

        return productoRepository.save(producto);

    }

    @Transactional
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
