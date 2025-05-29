package com.example.perfumelandia.service;


import com.example.perfumelandia.model.Inventario;
import com.example.perfumelandia.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    @Autowired
    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }
    public Inventario guardarInventario(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public Inventario buscarInventario(Long inventarioId) {
        return inventarioRepository.findById(inventarioId).orElse(null);
    }

    public List<Inventario> listarInventarios() {
        return inventarioRepository.findAll();
    }

    public void eliminarInventario(Long id) {
        inventarioRepository.deleteById(id);
    }


}

