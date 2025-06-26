package com.example.perfumelandia.service;

import com.example.perfumelandia.model.Pedido;
import com.example.perfumelandia.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class    PedidoService {

    @Autowired
    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido actualizarPedido(Long Id,Pedido pedido) {

        if(pedidoRepository.existsById(Id)) {
            pedido.setId(Id);}
            return pedidoRepository.save(pedido);
        }

    public Pedido crearPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    public Pedido buscarPedido(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public Pedido guardarPedido(Pedido pedido)  {
        return pedidoRepository.save(pedido);
    }
}




