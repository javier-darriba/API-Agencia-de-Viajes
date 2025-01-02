package com.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.model.Reserva;
import com.curso.model.ReservaYPlazas;
import com.curso.service.ReservaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.persistence.EntityNotFoundException;

/**
 * Clase controlador que hace uso de la capa de servicio para exponer un metodo
 * post para crear una reserva y un get para recuperar las reservas de un hotel.
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 1.0 31/12/2024
 */
@RestController
@RequestMapping("api/reservas")
public class ReservaController {
	@Autowired
	ReservaService service;

	// http://localhost:9000/api/reservas
	@Operation(summary = "Crear una reserva", description = "Crea una reserva y actualiza la ocupación de plazas del vuelo", responses = {
			@ApiResponse(responseCode = "201", description = "Reserva creada", content = @Content(schema = @Schema(implementation = String.class))),
			@ApiResponse(responseCode = "400", description = "No se pudo crear la reserva porque los argumentos de la petición son incorrectos."),
			@ApiResponse(responseCode = "404", description = "Vuelo no encontrado") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> postReserva(@RequestBody ReservaYPlazas reservaYPlazas) {
		try {
			service.altaReserva(reservaYPlazas.getReserva(), reservaYPlazas.getPlazasReservadas());
			return new ResponseEntity<>("Reserva creada", HttpStatus.CREATED);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	// http://localhost:9000/api/reservas/hoteles/Hotel Sol
	@Operation(summary = "Lista de reservas de un hotel", description = "Devuelve una lista de las reservas del hotel indicado por el nombre", responses = {
			@ApiResponse(responseCode = "200", description = "Lista de reservas encontradas", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Reserva.class)))),
			@ApiResponse(responseCode = "400", description = "Lista de reservas no buscadas porque los argumentos de la petición son incorrectos."),
			@ApiResponse(responseCode = "404", description = "Hotel no encontrado") })
	@GetMapping(value = "/hoteles/{nombreHotel}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Reserva>> getReservasPorHotel(@PathVariable String nombreHotel) {
		try {
			return new ResponseEntity<>(service.reservasPorHotel(nombreHotel), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
