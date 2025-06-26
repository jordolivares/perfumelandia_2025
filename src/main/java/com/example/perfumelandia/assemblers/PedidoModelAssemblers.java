package com.example.perfumelandia.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.Link;
import com.example.perfumelandia.controller.PedidoControllerNicolas;
import com.example.perfumelandia.model.Pedido;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PedidoModelAssemblers implements RepresentationModelAssembler<Pedido, EntityModel<Pedido>>{

    @Override
    public EntityModel<Pedido> toModel(Pedido pedido) {
        return EntityModel.of(pedido,
                linkTo(methodOn(PedidoControllerNicolas.class).buscarPedido(pedido.getId())).withSelfRel()
        );
    }
}
