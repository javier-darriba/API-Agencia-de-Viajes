package com.curso.inicio.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.curso.model.Vuelo;
import com.curso.repository.VuelosRepository;

@SpringBootTest
class VuelosRepositoryTest {

	@Autowired
	private VuelosRepository repository;

	@Test
	void testFindByPlazasDisponiblesGreaterThanEqual() {
		List<Vuelo> vuelos = repository.findByPlazasDisponiblesGreaterThanEqual(80);
		assertEquals(2, vuelos.size());
		assertTrue(vuelos.stream().allMatch(v -> v.getPlazasDisponibles() >= 100));
	}

	@Test
	void testFindByPlazasDisponiblesGreaterThanEqualSinResultados() {
		List<Vuelo> vuelos = repository.findByPlazasDisponiblesGreaterThanEqual(200);
		assertEquals(0, vuelos.size());
	}

	@Test
	void testFindByPlazasDisponiblesGreaterThanEqualPlazasNegativas() {
		List<Vuelo> vuelos = repository.findByPlazasDisponiblesGreaterThanEqual(-10);
		assertEquals(5, vuelos.size());
	}
}
