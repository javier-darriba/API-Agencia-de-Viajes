package com.curso.inicio.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.curso.model.Vuelo;
import com.curso.repository.VuelosRepository;
import com.curso.service.VueloService;

import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
class VueloServiceImplTest {

	@Autowired
	private VueloService service;

	@Autowired
	private VuelosRepository repository;

	@Test
	void testVuelosConPlazasDisponibles() {
		List<Vuelo> vuelos = service.vuelosConPlazasDisponibles(90);

		assertEquals(2, vuelos.size());
	}

	@Test
	void testVuelosConPlazasDisponiblesSinResultados() {
		List<Vuelo> vuelos = service.vuelosConPlazasDisponibles(200);

		assertEquals(0, vuelos.size());
	}

	@Test
	void testVuelosConPlazasDisponiblesPlazasNegativas() {
		assertThrows(IllegalArgumentException.class, () -> service.vuelosConPlazasDisponibles(-5));
	}

	@Test
	void testActualizarOcupacionPlazasExitosa() {
		Long idVuelo = repository.findAll().get(0).getIdVuelo();
		Integer plazasReservadas = 50;

		assertDoesNotThrow(() -> service.actualizarOcupacionPlazas(idVuelo, plazasReservadas));

		Vuelo vuelo = repository.findById(idVuelo).orElseThrow();
		assertEquals(100, vuelo.getPlazasDisponibles());
	}

	@Test
	void testActualizarOcupacionPlazasInsuficientes() {
		Long idVuelo = repository.findAll().get(0).getIdVuelo();
		Integer plazasReservadas = 200;

		assertThrows(IllegalArgumentException.class,
				() -> service.actualizarOcupacionPlazas(idVuelo, plazasReservadas));
	}

	@Test
	void testActualizarOcupacionPlazasVueloInexistente() {
		assertThrows(EntityNotFoundException.class, () -> service.actualizarOcupacionPlazas(999L, 10));
	}

	@Test
	void testActualizarOcupacionPlazasParametrosErroneos() {
		Long idVuelo = repository.findAll().get(0).getIdVuelo();

		assertThrows(IllegalArgumentException.class, () -> service.actualizarOcupacionPlazas(idVuelo, -10));
		assertThrows(IllegalArgumentException.class, () -> service.actualizarOcupacionPlazas(-1L, 10));
	}

	@Test
	void testExisteVueloExistente() {
		Long idVuelo = repository.findAll().get(0).getIdVuelo();

		assertTrue(service.existeVuelo(idVuelo));
	}

	@Test
	void testExisteVueloInexistente() {
		assertThrows(IllegalArgumentException.class, () -> service.existeVuelo(-5L));
		assertTrue(!service.existeVuelo(999L));
	}
}