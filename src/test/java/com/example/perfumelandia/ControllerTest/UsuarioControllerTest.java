package com.example.perfumelandia.ControllerTest;


import com.example.perfumelandia.controller.ControllerJoaquinUsuario;
import com.example.perfumelandia.model.Rol;
import com.example.perfumelandia.model.Usuario;
import com.example.perfumelandia.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ControllerJoaquinUsuario.class)
public class UsuarioControllerTest {
    @Autowired
    private MockMvc mockMvc;
    //d
    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private Usuario usuario;
    private Rol rol;

    @BeforeEach
    void setUp() {
        rol = new Rol(1L, "ADMIN");
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Juan");
        usuario.setContraseña("1234");
        usuario.setRol(rol);
    }

    @Test
    public void testGetAllUsuarios() throws Exception {
        when(usuarioService.findAll()).thenReturn(List.of(usuario));

        mockMvc.perform(get("/api/v1/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Juan"))
                .andExpect(jsonPath("$[0].contraseña").value("1234"))
                .andExpect(jsonPath("$[0].rol.id").value(1))
                .andExpect(jsonPath("$[0].rol.nombre").value("ADMIN"));
    }

    @Test
    public void testGetUsuarioById() throws Exception {
        when(usuarioService.findById(1L)).thenReturn(usuario);

        mockMvc.perform(get("/api/v1/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.contraseña").value("1234"))
                .andExpect(jsonPath("$.rol.id").value(1))
                .andExpect(jsonPath("$.rol.nombre").value("ADMIN"));
    }

    @Test
    public void testCreateUsuario() throws Exception {
        when(usuarioService.save(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(post("/api/v1/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.contraseña").value("1234"))
                .andExpect(jsonPath("$.rol.id").value(1))
                .andExpect(jsonPath("$.rol.nombre").value("ADMIN"));
    }

    @Test
    public void testUpdateUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Juan");
        usuario.setContraseña("1234");
        usuario.setRol(new Rol(1L, "ADMIN"));

        when(usuarioService.findById(1L)).thenReturn(usuario);
        when(usuarioService.save(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(put("/api/v1/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.contraseña").value("1234"))
                .andExpect(jsonPath("$.rol.id").value(1))
                .andExpect(jsonPath("$.rol.nombre").value("ADMIN"));
    }

    @Test
    public void testDeleteUsuario() throws Exception {
        when(usuarioService.eliminarUsuario(1L)).thenReturn("Usuario eliminado correctamente");

        mockMvc.perform(delete("/api/v1/usuarios/1"))
                .andExpect(status().isOk());

        verify(usuarioService, times(1)).eliminarUsuario(1L);
    }

}
