package com.example.perfumelandia.dto.response;


import com.example.perfumelandia.model.Usuario;
import lombok.Data;

@Data
public class UsuarioResponseDTO {

    private Long id;
    private String nombre;
    private String email;
    private String rolnombre;

    public UsuarioResponseDTO(Usuario usuario) {


        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.rolnombre = usuario.getRol().getNombre();

    }

}