package com.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.model.Hotel;
import com.curso.repository.HotelesRepository;

import jakarta.persistence.EntityNotFoundException;

/**
 * Clase que implementa los metodos de la interfaz HotelService.
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 1.0 31/12/2024
 */
@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	HotelesRepository repository;

	/**
	 * Devuelve los hoteles que están disponibles
	 */
	@Override
	public List<Hotel> buscarHotelesDisponibles() {
		return repository.findByDisponibleTrue();
	}

	/**
	 * Devuelve el hotel que coincide con el nombre indicado
	 * 
	 * @param nombre Nombre del hotel que se quiere recuperar
	 * @throws IllegalArgumentException si no se introdujo un nombre o tiene
	 *                                  caracteres extraños
	 * @throws EntityNotFoundException  si no se encuentra un hotel con el nombre
	 *                                  indicado
	 */
	@Override
	public Hotel buscarPorNombre(String nombre) {
		if (nombre == null || !nombre.matches("^[A-Za-z\\s]+$")) {
			throw new IllegalArgumentException(
					"Para hacer esta operación debe introducir el nombre del hotel correctamente.");
		}

		return repository.findByNombre(nombre)
				.orElseThrow(() -> new EntityNotFoundException("Hotel no encontrado: " + nombre));
	}

	/**
	 * Devuelve identificador del hotel que coincide con el nombre indicado
	 * 
	 * @param nombre Nombre del hotel del que se quiere recuperar el identificador
	 * @throws IllegalArgumentException si no se introdujo un nombre o tiene
	 *                                  caracteres extraños
	 * @throws EntityNotFoundException  si no se encuentra un hotel con el nombre
	 *                                  indicado
	 */
	@Override
	public Long buscarIdentificadorPorNombre(String nombre) {
		if (nombre == null || !nombre.matches("^[A-Za-z\\s]+$")) {
			throw new IllegalArgumentException(
					"Para hacer esta operación debe introducir el nombre del hotel correctamente.");
		}
		Hotel hotel = repository.findByNombre(nombre)
				.orElseThrow(() -> new EntityNotFoundException("Hotel no encontrado: " + nombre));
		return hotel.getIdHotel();
	}

	/**
	 * Devuelve un boolean indicando la existencia del hotel indicado por su
	 * identificador
	 * 
	 * @param idHotel Identificador del hotel que se quiere comprobar
	 * @throws IllegalArgumentException si no se introdujo un identificador o tiene
	 *                                  un valor incorrecto
	 */
	@Override
	public Boolean existeHotel(Long idHotel) {
		if (idHotel == null || idHotel <= 0) {
			throw new IllegalArgumentException("Identificador de hotel incorrecto.");
		}
		return repository.existsById(idHotel);
	}

	/**
	 * Devuelve un boolean indicando la existencia del hotel indicado por su nombre
	 * 
	 * @param nombreHotel Nombre del hotel que se quiere comprobar
	 * @throws IllegalArgumentException si no se introdujo un nombre o tiene
	 *                                  caracteres extraños
	 */
	@Override
	public Boolean existeNombreHotel(String nombreHotel) {
		if (nombreHotel == null || !nombreHotel.matches("^[A-Za-z\\s]+$")) {
			throw new IllegalArgumentException("Nombre de hotel incorrecto.");
		}
		return repository.existsByNombre(nombreHotel);
	}

}
