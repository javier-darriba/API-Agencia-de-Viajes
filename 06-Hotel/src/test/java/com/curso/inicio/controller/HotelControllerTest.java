package com.curso.inicio.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.curso.model.Categoria;
import com.curso.model.Hotel;
import com.curso.service.HotelService;

@SpringBootTest
@AutoConfigureMockMvc
class HotelControllerTest {

	@MockitoBean
	private HotelService service;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		Hotel hotel1 = new Hotel(1L, "Hotel Paraíso", Categoria.CINCO_ESTRELLAS, 250.50, true);
		Hotel hotel2 = new Hotel(2L, "Hotel Luna", Categoria.CUATRO_ESTRELLAS, 150.75, true);
		Hotel hotel4 = new Hotel(4L, "Hotel Estrella", Categoria.DOS_ESTRELLAS, 75.25, true);
		List<Hotel> hotelesDisponibles = List.of(hotel1, hotel2, hotel4);
		when(service.buscarHotelesDisponibles()).thenReturn(hotelesDisponibles);
		when(service.buscarPorNombre("Hotel Paraíso")).thenReturn(hotel1);
		when(service.buscarIdentificadorPorNombre("Hotel Paraíso")).thenReturn(1L);
		when(service.existeHotel(1L)).thenReturn(true);
		when(service.existeNombreHotel("Hotel Paraíso")).thenReturn(true);
	}

	@Test
	public void testGetHotelesDisponibles() throws Exception {
		mockMvc.perform(get("/api/hoteles/disponibles")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].nombre", is("Hotel Paraíso")))
				.andExpect(jsonPath("$[1].nombre", is("Hotel Luna")))
				.andExpect(jsonPath("$[2].nombre", is("Hotel Estrella")));
	}

	@Test
	public void testGetHotelPorNombre() throws Exception {
		mockMvc.perform(get("/api/hoteles/Hotel Paraíso")).andExpect(status().isOk())
				.andExpect(jsonPath("$.nombre", is("Hotel Paraíso")))
				.andExpect(jsonPath("$.categoria", is("CINCO_ESTRELLAS"))).andExpect(jsonPath("$.precio", is(250.50)))
				.andExpect(jsonPath("$.disponible", is(true)));
	}

	@Test
	public void testGetIdHotelPorNombre() throws Exception {
		mockMvc.perform(get("/api/hoteles/identificadores/Hotel Paraíso")).andExpect(status().isOk())
				.andExpect(jsonPath("$", is(1)));
	}

	@Test
	public void testExisteHotel() throws Exception {
		mockMvc.perform(get("/api/hoteles/existe/1")).andExpect(status().isOk()).andExpect(jsonPath("$", is(true)));
	}

	@Test
	public void testNoExisteHotel() throws Exception {
		when(service.existeHotel(999L)).thenReturn(false);
		mockMvc.perform(get("/api/hoteles/existe/999"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", is(false)));
	}

	@Test
	public void testExisteHotelConNombre() throws Exception {
		mockMvc.perform(get("/api/hoteles/existe/nombre/Hotel Paraíso")).andExpect(status().isOk())
				.andExpect(jsonPath("$", is(true)));
	}

	@Test
	public void testNoExisteHotelConNombre() throws Exception {
		when(service.existeNombreHotel("Hotel Inexistente")).thenReturn(false);
		mockMvc.perform(get("/api/hoteles/existe/nombre/Hotel Inexistente"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", is(false)));
	}
}
