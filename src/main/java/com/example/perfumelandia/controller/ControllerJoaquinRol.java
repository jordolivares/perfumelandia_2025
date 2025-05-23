package com.example.perfumelandia.controller;


import java.util.List;

import com.example.perfumelandia.model.Rol;
import com.example.perfumelandia.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roles")

public class ControllerJoaquinRol {

    @Autowired
    private RolService rolService;

    @GetMapping
    public ResponseEntity<List<Rol>> findAll() {
        List<Rol> rols = rolService.findAll();
        if (rols.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rols);
    }

    @PostMapping
    public ResponseEntity<Rol> save(@RequestBody Rol roll) {
        Rol rolsaved = rolService.save(roll);
        return ResponseEntity.status(HttpStatus.CREATED).body(rolsaved);

    }


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
    public ResponseEntity<Rol> delete(@PathVariable Long id) {
        try {
            rolService.delete(id);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
