package com.curso.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.model.Hotel;

/**
 * Interfaz que implementa hereda de JpaRepository y proporciona métodos para
 * recuperar la informacion de un hotel indicando su nombre , buscar los hoteles
 * que están disponibles y comprobar la existencia de un hotel por su nombre.
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 1.0 31/12/2024
 */
public interface HotelesRepository extends JpaRepository<Hotel, Long> {

	List<Hotel> findByDisponibleTrue();

	Optional<Hotel> findByNombre(String nombre);

	boolean existsByNombre(String nombre);

}
