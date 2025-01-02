package com.curso.inicio.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.curso.model.Reserva;
import com.curso.model.ReservaYPlazas;
import com.curso.service.ReservaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class ReservaControllerTest {

	@MockitoBean
	private ReservaService service;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	public void setup() {
		Reserva reserva1 = new Reserva(1L, "Juan Pérez", "12345678A", 1L, 2L);
		Reserva reserva2 = new Reserva(2L, "María López", "87654321B", 3L, 1L);

		List<Reserva> reservasHotel1 = new ArrayList<>();
		reservasHotel1.add(reserva1);

		List<Reserva> reservasHotel3 = new ArrayList<>();
		reservasHotel3.add(reserva2);

		when(service.reservasPorHotel("Hotel Sol")).thenReturn(reservasHotel1);
		when(service.reservasPorHotel("Hotel Luna")).thenReturn(new ArrayList<>());
	}

	@Test
	void testPostReserva() throws Exception {
		ReservaYPlazas reservaYPlazas = new ReservaYPlazas(new Reserva(null, "Juan Pérez", "12345678A", 1L, 2L), 2);

		mockMvc.perform(post("/api/reservas").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(reservaYPlazas))).andExpect(status().isCreated())
				.andExpect(jsonPath("$", is("Reserva creada")));
	}

	@Test
	void testGetReservasPorHotelSinResultados() throws Exception {
		mockMvc.perform(get("/api/reservas/hoteles/Hotel Luna")).andExpect(status().isOk());
	}

}
