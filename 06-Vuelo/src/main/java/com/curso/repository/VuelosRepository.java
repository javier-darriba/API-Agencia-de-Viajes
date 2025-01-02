package com.curso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.model.Vuelo;

/**
 * Interfaz que implementa hereda de JpaRepository y proporciona un método para
 * recuperar los vuelos que tienen, como mínimo, el número de plazas libres
 * indicadas.
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 1.0 31/12/2024
 */
public interface VuelosRepository extends JpaRepository<Vuelo, Long> {

	List<Vuelo> findByPlazasDisponiblesGreaterThanEqual(Integer plazas);
}
