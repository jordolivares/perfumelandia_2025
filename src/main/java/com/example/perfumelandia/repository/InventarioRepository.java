package com.example.perfumelandia.repository;

import com.example.perfumelandia.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface InventarioRepository extends JpaRepository<Inventario, Long> {


}
