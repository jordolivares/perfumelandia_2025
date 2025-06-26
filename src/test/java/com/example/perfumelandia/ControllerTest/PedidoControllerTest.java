package com.example.perfumelandia.ControllerTest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.example.perfumelandia.controller.PedidoControllerNicolas;
import com.example.perfumelandia.model.Pedido;
import com.example.perfumelandia.service.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.Date;

@WebMvcTest(PedidoControllerNicolas.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Pedido pedido;

    @BeforeEach
    void setUp() {
        pedido = new Pedido();
        pedido.setId(1L);
        pedido.setFechaPedido(java.sql.Date.valueOf("2025-06-25"));
        pedido.setCantidad(3);
        pedido.setPrecioTotal(15000);
    }

    @Test
    public void testCrearPedido() throws Exception {

        when(pedidoService.crearPedido(any(Pedido.class))).thenReturn(pedido);

        mockMvc.perform(post("/api/pedidos/crearPedido")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedido)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.fechaPedido").value("2025-06-25"))
                .andExpect(jsonPath("$.cantidad").value(3))
                .andExpect(jsonPath("$.precioTotal").value(15000));
    }

    @Test
    public void testUpdatePedido() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setFechaPedido(java.sql.Date.valueOf("2025-06-07"));
        pedido.setCantidad(3);
        pedido.setPrecioTotal(15000);

        when(pedidoService.actualizarPedido(eq(1L), any(Pedido.class))).thenReturn(pedido);

        mockMvc.perform(put("/api/pedidos/actualizarPedido/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedido)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.fechaPedido").value("2025-06-07"))
                .andExpect(jsonPath("$.cantidad").value(3))
                .andExpect(jsonPath("$.precioTotal").value(15000));
    }


    @Test
    public void testGetPedidoById() throws Exception {
        when(pedidoService.buscarPedido(1L)).thenReturn(pedido);

        mockMvc.perform(get("/api/pedidos/buscarPedido/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.fechaPedido").value("2025-06-25"))
                .andExpect(jsonPath("$.cantidad").value(3))
                .andExpect(jsonPath("$.precioTotal").value(15000));
    }

    @Test
    public void testDeletePedido() throws Exception {
        doNothing().when(pedidoService).eliminarPedido(1L);

        mockMvc.perform(delete("/api/pedidos/eliminarPedido/1"))
                .andExpect(status().isNoContent());

        verify(pedidoService, times(1)).eliminarPedido(1L);
    }
}