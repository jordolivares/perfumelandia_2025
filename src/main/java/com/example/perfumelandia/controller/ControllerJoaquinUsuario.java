package com.example.perfumelandia.controller;



import com.example.perfumelandia.model.Usuario;
import com.example.perfumelandia.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/v1/usuarios")

public class ControllerJoaquinUsuario {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> usuarios = usuarioService.findAll();

        return ResponseEntity.ok(usuarios);
    }



    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
        Usuario usuarioSaved = usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSaved);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable Long id) {
        try{
            Usuario usuario = usuarioService.findById(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }





    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        try{
            Usuario usu = usuarioService.findById(id);
            usu.setId(id);
            usu.setNombre(usuario.getNombre());
            usu.setContraseña(usuario.getContraseña());
            usu.setRol(usuario.getRol());
            usuarioService.save(usu);
            return ResponseEntity.ok(usu);


        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String mensaje = "";
        try{
            mensaje = usuarioService.eliminarUsuario(id);
            return ResponseEntity.status(HttpStatus.OK).body(mensaje);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }




}
