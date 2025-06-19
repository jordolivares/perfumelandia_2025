package com.example.perfumelandia.controller;


import com.example.perfumelandia.model.Usuario;
import com.example.perfumelandia.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Crud usuarioo", description = "Creacion,Eliminacion,Obtener,actualizar")

public class ControllerJoaquinUsuario {
    @Autowired
    private UsuarioService usuarioService;


    @Operation(summary = "Listado de usuarios", description = "Genera un listado de usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busqueda exitosa"),
            @ApiResponse(responseCode = "404", description = "No encontrado" )
    })
    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> usuarios = usuarioService.findAll();

        return ResponseEntity.ok(usuarios);
    }



    @Operation(summary = "Crud de usuarios", description = "Creacion de usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Creacion exitosa"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos")
    })
    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
        Usuario usuarioSaved = usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSaved);

    }


    @Operation(summary = "Busca usuario por id", description = "Busqueda de usuario mediante id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")

    })
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable Long id) {
        try{
            Usuario usuario = usuarioService.findById(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }




    @Operation(summary = "Actualizar usuario", description = "Actualiza usuario mediante id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(responseCode = "400", description = "datos invalidos"),
            @ApiResponse(responseCode = "404", description = "No encontrado")
    })
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



    @Operation(summary = "Eliminar mediante id",description = "Elimina un usuario mediante id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
            @ApiResponse(responseCode = "400", description = "Id invalida"),
            @ApiResponse(responseCode = "404", description = "No encotra")
    })
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
