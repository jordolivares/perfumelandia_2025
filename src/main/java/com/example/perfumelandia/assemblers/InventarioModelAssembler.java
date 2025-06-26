package com.example.perfumelandia.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.perfumelandia.controller.ControllerInventarioV2;
import com.example.perfumelandia.model.Inventario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class InventarioModelAssembler implements  RepresentationModelAssembler<Inventario, EntityModel<Inventario>> {

    @Override
    public EntityModel<Inventario> toModel(Inventario inventario) {
        return EntityModel.of(inventario,
                linkTo(methodOn(ControllerInventarioV2.class).getInventarioById(inventario.getId())).withSelfRel(),
                linkTo(methodOn(ControllerInventarioV2.class).getAllInventarios()).withRel("inventario"));
    }
}
