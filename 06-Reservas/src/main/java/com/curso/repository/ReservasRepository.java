package com.curso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.model.Reserva;

/**
 * Interfaz que implementa hereda de JpaRepository y proporciona un método para
 * recuperar todas las reservas de un hotel indicando el identificador de éste.
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 1.0 31/12/2024
 */
public interface ReservasRepository extends JpaRepository<Reserva, Long> {
	List<Reserva> findByIdHotel(Long idHotel);
}
