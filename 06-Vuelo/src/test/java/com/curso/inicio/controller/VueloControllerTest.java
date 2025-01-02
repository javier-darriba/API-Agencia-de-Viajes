package com.curso.inicio.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.curso.model.Compania;
import com.curso.model.Vuelo;
import com.curso.service.VueloService;

@SpringBootTest
@AutoConfigureMockMvc
class VueloControllerTest {

	@MockitoBean
	private VueloService service;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		Vuelo vuelo1 = new Vuelo(1L, Compania.VUELING, LocalDate.parse("2024-01-15"), 120.50, 150);
		Vuelo vuelo2 = new Vuelo(2L, Compania.IBERIA, LocalDate.parse("2024-01-20"), 200.75, 100);

		List<Vuelo> vuelosDisponibles = new ArrayList<>();
		vuelosDisponibles.add(vuelo1);
		vuelosDisponibles.add(vuelo2);

		when(service.vuelosConPlazasDisponibles(100)).thenReturn(vuelosDisponibles);
		when(service.vuelosConPlazasDisponibles(200)).thenReturn(new ArrayList<>());
		when(service.existeVuelo(1L)).thenReturn(true);
		when(service.existeVuelo(99L)).thenReturn(false);
	}

	@Test
	void testGetVuelosConPlazasDisponibles() throws Exception {
		mockMvc.perform(get("/api/vuelos/disponibles/100")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].compania", is("VUELING"))).andExpect(jsonPath("$[1].compania", is("IBERIA")));
	}

	@Test
	void testGetVuelosConPlazasDisponiblesNoResultados() throws Exception {
		mockMvc.perform(get("/api/vuelos/disponibles/200")).andExpect(status().isOk());
	}

	@Test
	void testActualizarPlazas() throws Exception {
		mockMvc.perform(put("/api/vuelos/reservar/1/10")).andExpect(status().isNoContent());
	}

	@Test
	void testExisteVuelo() throws Exception {
		mockMvc.perform(get("/api/vuelos/existe/1")).andExpect(status().isOk()).andExpect(jsonPath("$", is(true)));

		mockMvc.perform(get("/api/vuelos/existe/99")).andExpect(status().isOk()).andExpect(jsonPath("$", is(false)));
	}

}
