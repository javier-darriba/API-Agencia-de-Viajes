package com.curso.service;

import java.util.List;

import com.curso.model.Reserva;

/**
 * Interfaz que proporciona los métodos dar de alta una reserva para el número
 * de personas indicadas y para recuperar la información de todas las reservas
 * de un hotel.
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 1.0 31/12/2024
 */
public interface ReservaService {
	void altaReserva(Reserva reserva, Integer personas);

	List<Reserva> reservasPorHotel(String nombreHotel);
}
