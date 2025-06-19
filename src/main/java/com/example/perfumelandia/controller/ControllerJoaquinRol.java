package com.example.perfumelandia.controller;


import java.util.List;

import com.example.perfumelandia.model.Rol;
import com.example.perfumelandia.service.RolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roles")
@Tag(name = "CRUD DE ROLl ", description = "Creacion,Eliminacion,Modificacion,Eliminacion de roles")
public class ControllerJoaquinRol {

    @Autowired
    private RolService rolService;

    @GetMapping
    @Operation(summary = "Obtiene todos los rolees ", description = "Obtiene una lista de todos los roles creados")
    public ResponseEntity<List<Rol>> findAll() {
        List<Rol> rols = rolService.findAll();
        if (rols.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rols);
    }

    @PostMapping
    @Operation(summary = "Creacion de rol", description = "Crea un nuevo rol")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(responseCode = "400", description =  "Datos invalidos")
    })
    public ResponseEntity<Rol> save(@RequestBody Rol roll) {
        Rol rolsaved = rolService.save(roll);
        return ResponseEntity.status(HttpStatus.CREATED).body(rolsaved);

    }


    @Operation(summary = "Busca un rol por id", description = "Muestra el rol buscado mediante el id ingresado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol encontrado"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Rol> findById(@PathVariable Long id) {
        try {
            Rol rol = rolService.findById(id);
            return ResponseEntity.ok(rol);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina por id", description = "Elimina un rol por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    public ResponseEntity<Rol> delete(@PathVariable Long id) {

        try {
            rolService.delete(id);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
