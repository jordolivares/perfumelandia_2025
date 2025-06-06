package com.example.perfumelandia.repository;

import com.example.perfumelandia.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoRepository extends JpaRepository<Producto, Integer> {


    Producto findById(Long id);

    void deleteById(Long id);
}
