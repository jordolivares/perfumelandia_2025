package com.example.perfumelandia.service;

import com.example.perfumelandia.model.Inventario;
import com.example.perfumelandia.model.Producto;
import com.example.perfumelandia.repository.InventarioRepository;
import com.example.perfumelandia.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public Inventario crearInventario(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public Producto agregarProductoAInventario(Long inventarioId, Producto producto) {
        Inventario inventario = inventarioRepository.findById(inventarioId);
        producto.setInventario(inventario);
        return productoRepository.save(producto);
    }

    public Inventario obtenerInventarioConProductos(Long id) {
        return inventarioRepository.findById(id);
    }

    public List<Inventario> listarInventarios() {
        return inventarioRepository.findAll();
    }

    public Inventario eliminarInventario(Long id) {
        return inventarioRepository.findById(id);
    }

    public Inventario actualizarInventario(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }
}
