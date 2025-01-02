package com.curso.inicio.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.curso.model.Reserva;
import com.curso.repository.ReservasRepository;
import com.curso.service.ReservaService;

import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
class ReservaServicioTest {
	@Autowired
	private ReservaService service;

	@Autowired
	private ReservasRepository repository;

	@Test
	void testAltaReservaExitosa() {
		Reserva reserva = new Reserva(null, "Pedro Gomez", "99988877Z", 1L, 2L);
		Integer plazasReservadas = 3;

		assertDoesNotThrow(() -> service.altaReserva(reserva, plazasReservadas));

		List<Reserva> reservas = repository.findAll();
		assertTrue(reservas.stream().anyMatch(r -> r.getDni().equals("99988877Z")));
	}

	@Test
	void testAltaReservaDatosErroneos() {
		Reserva reserva = new Reserva(null, "Pedro Gomez", "INVALID_DNI", 1L, 2L);
		Integer plazasReservadas = -1;

		assertThrows(IllegalArgumentException.class, () -> service.altaReserva(reserva, plazasReservadas));
	}

	@Test
	void testAltaReservaHotelInexistente() {
		Reserva reserva = new Reserva(null, "Pedro Gomez", "99988877Z", 99L, 2L);
		Integer plazasReservadas = 3;

		assertThrows(EntityNotFoundException.class, () -> service.altaReserva(reserva, plazasReservadas));
	}

	@Test
	void testAltaReservaVueloInexistente() {
		Reserva reserva = new Reserva(null, "Pedro Gomez", "99988877Z", 1L, 99L);
		Integer plazasReservadas = 3;

		assertThrows(EntityNotFoundException.class, () -> service.altaReserva(reserva, plazasReservadas));
	}

	@Test
	void testReservasPorHotelExistente() {
		List<Reserva> reservas = service.reservasPorHotel("Hotel Sol");

		assertEquals(1, reservas.size());
		assertEquals("María López", reservas.get(0).getNombreCliente());
	}

	@Test
	void testReservasPorHotelInexistente() {
		assertThrows(EntityNotFoundException.class, () -> service.reservasPorHotel("Hotel Inventado"));
	}

	@Test
	void testReservasPorHotelNombreErroneo() {
		assertThrows(IllegalArgumentException.class, () -> service.reservasPorHotel("123Hotel"));
	}
}
