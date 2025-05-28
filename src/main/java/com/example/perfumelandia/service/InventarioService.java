package com.example.perfumelandia.service;


import com.example.perfumelandia.model.Inventario;
import com.example.perfumelandia.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public Inventario guardarInventario(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public Inventario buscarInventario(Long id) {
        return inventarioRepository.findById(id).orElse(null);
    }

    public List<Inventario> listarInventarios() {
        return inventarioRepository.findAll();
    }

    public void eliminarInventario(Long id) {
        inventarioRepository.deleteById(id);
    }

}

