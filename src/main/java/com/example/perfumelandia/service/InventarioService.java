package com.example.perfumelandia.service;


import com.example.perfumelandia.model.Inventario;
import com.example.perfumelandia.repository.InventarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public List<Inventario> buscarInventarios() { return inventarioRepository.findAll(); }

    public Inventario buscarInventarioPorId(Long id) { return inventarioRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Inventario no encontrado con id: " + id)); }

    public Inventario agregarInventario(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }


    public void eliminarInventario(Long id) { inventarioRepository.deleteById(id); }


}

