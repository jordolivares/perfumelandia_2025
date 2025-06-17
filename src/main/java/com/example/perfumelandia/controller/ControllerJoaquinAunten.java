package com.example.perfumelandia.controller;
import com.example.perfumelandia.model.Rol;
import com.example.perfumelandia.model.Usuario;
import com.example.perfumelandia.service.RolService;
import com.example.perfumelandia.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DecimalStyle;


@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auntenticacion", description = "Auntenticacion de usuario")
public class ControllerJoaquinAunten {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;


    @Data
    public static class RegistroRequest {
        private String nombre;
        private String contraseña;
        private Long rolId;
    }


    @Data
    public static class LoginRequest {
        private String nombre;
        private String contraseña;
    }


    @Operation(summary = "Creacionn de usuario", description = "Crea un nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Creacion exitosa"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos")
    })
    @PostMapping("/register")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroRequest request) {
        try {
            Rol rol = rolService.findById(request.getRolId().longValue());
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(request.getNombre());
            nuevoUsuario.setContraseña(request.getContraseña());
            nuevoUsuario.setRol(rol);
            Usuario usuarioGuardado = usuarioService.save(nuevoUsuario);

            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar usuario: " + e.getMessage());
        }
    }

    // Login simple
    @Operation(summary = "Iniciar sesion de usuario", description = "Inicia sesion mediante id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login exitoso"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos")
    })
    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody LoginRequest request) {
        try {
            Usuario usuario = usuarioService.findByNombre(request.getNombre());
            if (usuario == null || !usuario.getContraseña().equals(request.getContraseña())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
            }
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error durante login");
        }
    }


    @GetMapping("/perfil/{id}")
    @Operation(summary = "Obtener usuario mediante ID", description = "Obtiene ID mediante ingreso de id")
    public ResponseEntity<?> obtenerPerfil(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.findById(id);
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener perfil");
        }
    }
}

