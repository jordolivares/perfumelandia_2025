package com.example.perfumelandia.service;

import com.example.perfumelandia.model.Usuario;
import com.example.perfumelandia.repository.RolRepository;
import com.example.perfumelandia.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }


    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).get();

    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public String eliminarUsuario(Long usuarioid) {
        usuarioRepository.deleteById(usuarioid);
        return"ok";
    }



    public Usuario findByNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }


    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

}
