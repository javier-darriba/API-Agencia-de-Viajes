package com.curso.inicio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.curso.model.Hotel;
import com.curso.service.HotelService;

import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
class HotelServiceImplTest {

	@Autowired
	private HotelService service;

	@Test
	void testBuscarHotelesDisponibles() {
		List<Hotel> disponibles = service.buscarHotelesDisponibles();
		assertEquals(3, disponibles.size());
		assertEquals("Hotel Paraíso", disponibles.get(0).getNombre());
	}

	@Test
	void testBuscarPorNombreExistente() {
		Hotel hotel = service.buscarPorNombre("Hotel Sol");
		assertEquals("Hotel Sol", hotel.getNombre());
	}

	@Test
	void testBuscarPorNombreInexistente() {
		assertThrows(EntityNotFoundException.class, () -> {
			service.buscarPorNombre("Hotel Inventado");
		});
	}
	
	@Test
	void testBuscarPorNombreErroneo() {
		assertThrows(IllegalArgumentException.class, () -> {
			service.buscarPorNombre("654dfg5");
		});
	}

	@Test
	void testBuscarIdentificadorPorNombre() {
		Long id = service.buscarIdentificadorPorNombre("Hotel Sol");
		assertEquals(3, id);
	}

	@Test
	void testBuscarIdentificadorPorNombreInexistente() {
		assertThrows(EntityNotFoundException.class, () -> {
			service.buscarIdentificadorPorNombre("Hotel Inventado");
		});
	}
	
	@Test
	void testBuscarIdentificadorPorNombreErroneo() {
		assertThrows(IllegalArgumentException.class, () -> {
			service.buscarIdentificadorPorNombre("654dfg5");
		});
	}

	@Test
	void testExisteHotelPorId() {
		Long id = service.buscarIdentificadorPorNombre("Hotel Sol");
		Boolean existe = service.existeHotel(id);
		assertTrue(existe);
	}

	@Test
	void testNoExisteHotelPorId() {
		Boolean existe = service.existeHotel(999L);
		assertFalse(existe);
	}
	
	@Test
	void testExisteHotelPorIdErroneo() {
		assertThrows(IllegalArgumentException.class, () -> {
			service.existeHotel(-9L);
		});
	}

	@Test
	void testExisteNombreHotel() {
		Boolean existe = service.existeNombreHotel("Hotel Sol");
		assertTrue(existe);
	}

	@Test
	void testNoExisteNombreHotel() {
		Boolean existe = service.existeNombreHotel("Hotel Inventado");
		assertFalse(existe);
	}

	@Test
	void testExisteNombreHotelErroneo() {
		assertThrows(IllegalArgumentException.class, () -> {
			service.existeNombreHotel("123Hotel");
		});
	}

}
