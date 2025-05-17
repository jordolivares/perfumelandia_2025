package com.example.perfumelandia.controller;

import com.example.perfumelandia.model.Usuario;
import com.example.perfumelandia.model.Producto;
import com.example.perfumelandia.service.PedidoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/pedido")


public class controllerNicolas {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntityn<List<Producto>> Listar(){
        List<Producto> productos = pedidoService.listar();
        if(productos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(productos);
        }

        return ResponseEntity.ok(productos);
    }

}
