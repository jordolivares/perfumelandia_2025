package com.example.perfumelandia.controller;

import com.example.perfumelandia.assemblers.InventarioModelAssembler;
import com.example.perfumelandia.model.Inventario;
import com.example.perfumelandia.service.InventarioService;
import com.example.perfumelandia.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/v1")
public class ControllerInventarioV2 {


    @Autowired
    private ProductoService productoService;

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private InventarioModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Inventario>> getAllInventarios() {
        List<EntityModel<Inventario>> inventario = inventarioService.buscarInventarios().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(inventario,
                linkTo(methodOn(ControllerInventarioV2.class).getAllInventarios()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Inventario> getInventarioById(@PathVariable Long id) {
        Inventario inventario = inventarioService.buscarInventarioPorId(id);
        return assembler.toModel(inventario);
    }


    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Inventario>> createInventario(@RequestBody Inventario inventario) {
        Inventario nuevoInventario = inventarioService.agregarInventario(inventario);

        return ResponseEntity
                .created(linkTo(methodOn(ControllerInventarioV2.class).getInventarioById(nuevoInventario.getId())).toUri())
                .body(assembler.toModel(nuevoInventario));

    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Inventario>> updateInventario(@PathVariable Long id, @RequestBody Inventario inventario) {
        inventario.setId(id);
        Inventario actualizarInventario = inventarioService.agregarInventario(inventario);
        return ResponseEntity
                .ok(assembler.toModel(actualizarInventario));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteInventario(@PathVariable Long id) {
        inventarioService.eliminarInventario(id);
        return ResponseEntity.noContent().build();
    }








}
