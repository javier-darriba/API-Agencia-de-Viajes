package com.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.curso.model.Reserva;
import com.curso.repository.ReservasRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

/**
 * Clase que implementa los metodos de la interfaz ReservaService.
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 1.0 31/12/2024
 */
@Service
public class ReservaServiceImpl implements ReservaService {

	@Autowired
	private ReservasRepository repository;

	@Autowired
	private RestTemplate template;

	private final String URL_ACTUALIZAR_PLAZAS_VUELO = "http://localhost:9090/api/vuelos/reservar/{idVuelo}/{plazasReservadas}";

	private final String URL_BUSCAR_HOTEL_POR_NOMBRE = "http://localhost:8080/api/hoteles/identificadores/{nombre}";

	private final String URL_COMPROBACION_HOTEL = "http://localhost:8080/api/hoteles/existe/{idHotel}";

	private final String URL_COMPROBACION_HOTEL_POR_NOMBRE = "http://localhost:8080/api/hoteles/existe/nombre/{nombreHotel}";

	private final String URL_COMPROBACION_VUELO = "http://localhost:9090/api/vuelos/existe/{idVuelo}";

	private void validarHotel(Long idHotel) {
		Boolean existe = template.getForObject(URL_COMPROBACION_HOTEL, Boolean.class, idHotel);
		if (Boolean.FALSE.equals(existe)) {
			throw new EntityNotFoundException("El hotel con ID " + idHotel + " no existe.");
		}
	}

	private void validarHotel(String nombreHotel) {
		Boolean existe = template.getForObject(URL_COMPROBACION_HOTEL_POR_NOMBRE, Boolean.class, nombreHotel);
		if (Boolean.FALSE.equals(existe)) {
			throw new EntityNotFoundException("El hotel " + nombreHotel + " no existe.");
		}
	}

	private void validarVuelo(Long idVuelo) {
		Boolean existe = template.getForObject(URL_COMPROBACION_VUELO, Boolean.class, idVuelo);
		if (Boolean.FALSE.equals(existe)) {
			throw new EntityNotFoundException("El vuelo con ID " + idVuelo + " no existe.");
		}
	}

	/**
	 * Crea una reserva y actualiza las plazas del vuelo indicado
	 * 
	 * @param reserva          Datos de la reserva
	 * @param plazasReservadas Número de plazas que se proceden a ocupar
	 * @throws IllegalArgumentException si falata cualquiera de los datos o tiene un
	 *                                  valor incorrecto
	 * @throws EntityNotFoundException  si no se encuentran el vuelo o hotel que se
	 *                                  quieren reservar
	 */
	@Override
	@Transactional
	public void altaReserva(Reserva reserva, Integer plazasReservadas) {
		if (reserva == null || reserva.getDni() == null || !reserva.getDni().matches("^\\d{8}[A-Z]$")
				|| reserva.getNombreCliente() == null || !reserva.getNombreCliente().matches("^[A-Za-z\\s]+$")
				|| reserva.getIdHotel() == null || reserva.getIdHotel() <= 0 || reserva.getIdVuelo() == null
				|| reserva.getIdVuelo() <= 0) {
			throw new IllegalArgumentException("Revise los datos de la reseva, están incompletos o son erróneos.");
		}
		if (plazasReservadas <= 0 || plazasReservadas == null) {
			throw new IllegalArgumentException("El número de plazas reservadas debe ser mayor a cero.");
		}
		validarHotel(reserva.getIdHotel());
		validarVuelo(reserva.getIdVuelo());
		repository.save(reserva);
		template.put(URL_ACTUALIZAR_PLAZAS_VUELO, null, reserva.getIdVuelo(), plazasReservadas);
	}

	/**
	 * Devuelve la información de todas las reservas del hote indicado por nombre
	 * 
	 * @param nombreHotel Nombre del hotel del que se quiren recuperar las reservas
	 * @throws IllegalArgumentException si no se introdujo un nombre o tiene
	 *                                  caracteres extraños
	 * @throws EntityNotFoundException  si no se encuentra hotel indicado
	 */
	@Override
	public List<Reserva> reservasPorHotel(String nombreHotel) {
		if (nombreHotel == null || !nombreHotel.matches("^[A-Za-z\\s]+$")) {
			throw new IllegalArgumentException(
					"Para hacer esta operación debe introducir el nombre del hotel correctamente.");
		}
		validarHotel(nombreHotel);
		Long idHotel = template.getForObject(URL_BUSCAR_HOTEL_POR_NOMBRE, Long.class, nombreHotel);
		return repository.findByIdHotel(idHotel);
	}

}
