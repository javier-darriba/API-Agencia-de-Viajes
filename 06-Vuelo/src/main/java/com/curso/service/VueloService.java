package com.curso.service;

import java.util.List;

import com.curso.model.Vuelo;

/**
 * Interfaz que proporciona los métodos buscar los vuelos con las plazas
 * disponibles indicadas, para actualizar el número de plazas ocupadas y para
 * comprobar la existencia de un vuelo.
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 1.0 31/12/2024
 */
public interface VueloService {
	List<Vuelo> vuelosConPlazasDisponibles(Integer plazas);

	void actualizarOcupacionPlazas(Long idVuelo, Integer plazasReservadas);

	Boolean existeVuelo(Long idVuelo);
}
