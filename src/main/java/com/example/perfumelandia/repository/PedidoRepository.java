package com.example.perfumelandia.repository;

import com.example.perfumelandia.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


public interface PedidoRepository extends JpaRepository<Pedido,Integer> {


}
