package com.curso.inicio.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.curso.model.Reserva;
import com.curso.repository.ReservasRepository;

@SpringBootTest
class ReservasRepositoryTest {

	@Autowired
	private ReservasRepository repository;

	@Test
	void testFindByIdHotel() {
		Long idHotel = 1L;
		List<Reserva> reservas = repository.findByIdHotel(idHotel);

		assertEquals(2, reservas.size());
		assertTrue(reservas.stream().allMatch(r -> r.getIdHotel().equals(idHotel)));
	}

	@Test
	void testFindByIdHotelSinResultados() {
		Long idHotel = 99L;
		List<Reserva> reservas = repository.findByIdHotel(idHotel);

		assertEquals(0, reservas.size());
	}
}
