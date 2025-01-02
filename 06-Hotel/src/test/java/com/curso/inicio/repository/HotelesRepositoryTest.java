package com.curso.inicio.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.curso.model.Hotel;
import com.curso.repository.HotelesRepository;

@SpringBootTest
class HotelesRepositoryTest {

	 @Autowired
	    private HotelesRepository repository;

	    @Test
	    void testFindByDisponibleTrue() {
	        var hotelesDisponibles = repository.findByDisponibleTrue();
	        assertEquals(3, hotelesDisponibles.size());
	        assertEquals("Hotel Paraíso", hotelesDisponibles.get(0).getNombre());
	    }

	    @Test
	    void testFindByNombre() {
	        Optional<Hotel> resultadoVerdadero = repository.findByNombre("Hotel Sol");
	        assertTrue(resultadoVerdadero.isPresent());
	        assertEquals("Hotel Sol", resultadoVerdadero.get().getNombre());
	        
	        Optional<Hotel> resultadoFalso = repository.findByNombre("Hotel Inventado");
	        assertFalse(resultadoFalso.isPresent());
	    }

	    @Test
	    void testExistsByNombre() {
	        assertTrue(repository.existsByNombre("Hotel Sol"));
	        assertFalse(repository.existsByNombre("Hotel Inventado"));
	    }

}
