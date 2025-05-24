package com.example.perfumelandia.repository;

import com.example.perfumelandia.model.Inventario;

import java.util.List;


public interface InventarioRepository {

    Inventario save(Inventario inventario);

    Inventario findById(Long inventarioId);

    List<Inventario> findAll();
}
