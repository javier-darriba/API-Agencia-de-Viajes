package com.curso.service;

import java.util.List;

import com.curso.model.Hotel;

/**
 * Interfaz que proporciona los métodos buscar todos los hoteles disponibles,
 * recuperar la información de un hotel indicando su nombre, recuperar el
 * identificador de un hotel indicando su nombre y comprobar la existencia de un
 * hotel.
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 1.0 31/12/2024
 */
public interface HotelService {
	List<Hotel> buscarHotelesDisponibles();

	Hotel buscarPorNombre(String nombre);

	Long buscarIdentificadorPorNombre(String nombre);

	Boolean existeHotel(Long idHotel);
	
	Boolean existeNombreHotel(String nombreHotel);
}
