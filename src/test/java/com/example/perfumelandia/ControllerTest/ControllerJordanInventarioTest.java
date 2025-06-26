package com.example.perfumelandia.ControllerTest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.example.perfumelandia.controller.ControllerJordanInventario;
import com.example.perfumelandia.model.Inventario;
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


@WebMvcTest(ControllerJordanInventario.class)
class ControllerJordanInventarioTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventarioService inventarioService;

    @MockBean
    private ProductoService productoService;


    @Autowired
    private ObjectMapper objectMapper;
    private Inventario inventario;

    @BeforeEach
    void setUp() {
        inventario = new Inventario();
        inventario.setId(1L);
        inventario.setCodigo("INV MPO");
        inventario.setDescripcion("MALL PLAZA OESTE");
        inventario.setTotalcantidadDisponible(0);
        inventario.setTotalcantidadVendida(0);
        inventario.setTotalcantidadReservada(0);
        inventario.setProductos(null);

    }

    @Test
    public void obtenerTodosInventario() throws Exception {
        when(inventarioService.buscarInventarios()).thenReturn(List.of(inventario));

        mockMvc.perform(get("/api/v1/inventario"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].codigo").value("INV MPO"))
                .andExpect(jsonPath("$[0].descripcion").value("MALL PLAZA OESTE"));

    }

    @Test
    public void obtenerTodosInventarioId() throws Exception {
        when(inventarioService.buscarInventarioPorId(1L)).thenReturn((inventario));

        mockMvc.perform(get("/api/v1/inventario/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.codigo").value("INV MPO"))
                .andExpect(jsonPath("$[0].descripcion").value("MALL PLAZA OESTE"));
    }

    @Test
    public void guardarInventario() throws Exception {
        when(inventarioService.agregarInventario(any(Inventario.class))).thenReturn(inventario);

        mockMvc.perform(post("/api/v1/inventario"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.codigo").value("INV MPO"))
                .andExpect(jsonPath("$[0].descripcion").value("MALL PLAZA OESTE"));
    }

    @Test
    public void eliminarInventario() throws Exception {
        doNothing().when(inventarioService).eliminarInventario(1L);

        mockMvc.perform(delete("/api/v1/inventario/1"))
                .andExpect(status().isOk());

        verify(inventarioService, times(1)).eliminarInventario(1L);
    }


}