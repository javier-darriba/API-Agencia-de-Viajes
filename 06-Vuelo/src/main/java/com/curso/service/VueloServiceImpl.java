package com.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.model.Vuelo;
import com.curso.repository.VuelosRepository;

import jakarta.persistence.EntityNotFoundException;

/**
 * Clase que implementa los metodos de la interfaz VueloService.
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 1.0 31/12/2024
 */
@Service
public class VueloServiceImpl implements VueloService {

	@Autowired
	VuelosRepository repository;

	/**
	 * Devuelve los vuelos que tienen, como mínimo, el número de plazas libres
	 * indicadas
	 * 
	 * @param plazas Número mínimo de plazas libres que debe tener el vuelo
	 * @throws IllegalArgumentException si no se introdujo el número de plazas o es
	 *                                  un valor negativo
	 */
	@Override
	public List<Vuelo> vuelosConPlazasDisponibles(Integer plazas) {
		if (plazas == null || plazas < 0) {
			throw new IllegalArgumentException("El número de plazas reservadas debe ser un número positivo.");
		}
		return repository.findByPlazasDisponiblesGreaterThanEqual(plazas);
	}

	/**
	 * Acutaliza el vuelo ocupando el número de plazas indicadas
	 * 
	 * @param idVuelo          Identificador del vuelo que se quiere actualizar
	 * @param plazasReservadas Número de plazas que se proceden a ocupar
	 * @throws IllegalArgumentException si cualquiera de los dos parámetros no se
	 *                                  indicó o tiene un valor incorrecto
	 * @throws EntityNotFoundException  si no se encuentra el vuelo indicado
	 */
	@Override
	public void actualizarOcupacionPlazas(Long idVuelo, Integer plazasReservadas) {
		if (plazasReservadas == null || plazasReservadas <= 0) {
			throw new IllegalArgumentException("El número de plazas reservadas debe ser mayor a cero.");
		}

		if (idVuelo == null || idVuelo <= 0) {
			throw new IllegalArgumentException("Identificador de vuelo incorrecto.");
		}

		Vuelo vuelo = repository.findById(idVuelo)
				.orElseThrow(() -> new EntityNotFoundException("Vuelo no encontrado: " + idVuelo));

		if (vuelo.getPlazasDisponibles() >= plazasReservadas) {
			vuelo.setPlazasDisponibles(vuelo.getPlazasDisponibles() - plazasReservadas);
			repository.save(vuelo);
		} else {
			throw new IllegalArgumentException("No hay suficientes plazas disponibles");
		}
	}

	/**
	 * Devuelve un booleano indicando la existencia de un vuelo por identificador
	 * 
	 * @param idVuelo Identificador del vuelo que se quiere comprobar
	 * @throws IllegalArgumentException si no se introdujo un identificador o tiene
	 *                                  un valor incorrecto
	 */
	@Override
	public Boolean existeVuelo(Long idVuelo) {
		if (idVuelo == null || idVuelo <= 0) {
			throw new IllegalArgumentException("Identificador de vuelo incorrecto.");
		}
		return repository.existsById(idVuelo);
	}

}
