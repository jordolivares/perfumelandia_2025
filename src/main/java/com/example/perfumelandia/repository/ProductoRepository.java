package com.example.perfumelandia.repository;

import com.example.perfumelandia.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {


}
