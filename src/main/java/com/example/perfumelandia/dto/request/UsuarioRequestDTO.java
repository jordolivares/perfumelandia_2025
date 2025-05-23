package com.example.perfumelandia.dto.request;

import lombok.Data;

@Data
public class UsuarioRequestDTO {
    private String nombre;
    private String contraseña;
    private Long rolId;

}
