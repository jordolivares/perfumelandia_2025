package com.example.perfumelandia.ServiceTest;


import com.example.perfumelandia.model.Rol;
import com.example.perfumelandia.model.Usuario;
import com.example.perfumelandia.repository.UsuarioRepository;
import com.example.perfumelandia.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ServiceUsuario {
    @Autowired
    private UsuarioService usuarioService;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Test
    public void testFindAll() {
        Rol rol = new Rol(1L, "USER");
        Usuario usuario = new Usuario(1L, "joaquin", "clave123", rol);

        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List<Usuario> usuarios = usuarioService.findAll();

        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
        assertEquals("joaquin", usuarios.get(0).getNombre());
    }

    @Test
    public void testFindById() {
        Rol rol = new Rol(1L, "ADMIN");
        Usuario usuario = new Usuario(2L, "admin", "adminpass", rol);

        when(usuarioRepository.findById(2L)).thenReturn(Optional.of(usuario));

        Usuario found = usuarioService.findById(2L);

        assertNotNull(found);
        assertEquals("admin", found.getNombre());
        assertEquals("ADMIN", found.getRol().getNombre());
    }

    @Test
    public void testSave() {
        Rol rol = new Rol(1L, "USER");
        Usuario usuario = new Usuario(null, "nuevo", "1234", rol);

        when(usuarioRepository.save(usuario)).thenReturn(new Usuario(3L, "nuevo", "1234", rol));

        Usuario saved = usuarioService.save(usuario);

        assertNotNull(saved);
        assertEquals("nuevo", saved.getNombre());
        assertEquals("USER", saved.getRol().getNombre());
    }

    @Test
    public void testDeleteById() {
        Long id = 4L;
        doNothing().when(usuarioRepository).deleteById(id);

        usuarioService.eliminarUsuario(id);

        verify(usuarioRepository, times(1)).deleteById(id);
    }
}