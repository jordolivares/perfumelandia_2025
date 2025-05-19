package com.example.perfumelandia.service;

import com.example.perfumelandia.model.Pedido;
import com.example.perfumelandia.repository.PedidoRepository;
import org.springframework.stereotype.Service;


@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido actualizarPedido(Integer Id,Pedido pedido) {

        if(pedidoRepository.existsById(Id)) {
            pedido.setId(Id);}
            return pedidoRepository.save(pedido);
        }
        //* Agregar return?
    }

}
