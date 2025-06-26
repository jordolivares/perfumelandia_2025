package com.example.perfumelandia.ControllerTest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.example.perfumelandia.controller.ControllerJordanInventario;
import com.example.perfumelandia.controller.ControllerJordanProducto;
import com.example.perfumelandia.model.Producto;
import com.example.perfumelandia.service.InventarioService;
import com.example.perfumelandia.service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.List;

@WebMvcTest(ControllerJordanProducto.class)
class ControllerJordanProductoTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @MockBean
    private InventarioService inventarioService;

    @Autowired
    private ObjectMapper objectMapper;
    private Producto producto;


    @BeforeEach
    void setUp() {
        producto = new Producto();
        producto.setId(1L);
        producto.setNombre("DULCE ROSA");
        producto.setMarca("X");
        producto.setPrecio(10000);
        producto.setCantidad(10);

    }

    @Test
    public void obtenerProductos() throws Exception {
        when(productoService.listarProductos()).thenReturn(List.of(producto));

        mockMvc.perform(get("/api/v1/producto"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("DULCE ROSA"))
                .andExpect(jsonPath("$[0].marca").value("X"))
                .andExpect(jsonPath("$[0].precio").value(10000))
                .andExpect(jsonPath("$[0].cantidad").value(10));

    }

    @Test
    public void obtenerProductoId() throws Exception {
        when(productoService.obtenerProductoPorId(1L)).thenReturn((producto));

        mockMvc.perform(get("/api/v1/producto/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("DULCE ROSA"))
                .andExpect(jsonPath("$.marca").value("X"))
                .andExpect(jsonPath("$.precio").value(10000))
                .andExpect(jsonPath("$.cantidad").value(10));

        verify(productoService, times(1)).obtenerProductoPorId(1L);
    }

    @Test
    public void guardarProducto() throws Exception {


        when(productoService.guardarProducto(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(post("/api/v1/producto")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                {
                    "codigo": "PROD-01",
                    "nombre": "DULCE ROSA",
                    "marca": "X",
                    "precio": 10000,
                    "cantidad": 10,
                    "inventario": {
                                   "id": 1
                                   }\s
                }
               \s""")).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void eliminarProducto() throws Exception {
        doNothing().when(productoService).eliminarProducto(1L);

        mockMvc.perform(delete("/api/v1/producto/1")).andExpect(status().isOk());

        verify(productoService, times(1)).eliminarProducto(1L);
    }



}