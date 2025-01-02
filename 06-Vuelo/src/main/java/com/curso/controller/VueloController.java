package com.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.model.Vuelo;
import com.curso.service.VueloService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.persistence.EntityNotFoundException;

/**
 * Clase controlador que hace uso de la capa de servicio para exponer un método
 * get para recuperar los vuelos con plazas disponibles, un put para actualizar
 * la cantidad de plazas libres en un vuelo y un get para comprobar la
 * existencia de un vuelo.
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 1.0 31/12/2024
 */
@RestController
@RequestMapping("api/vuelos")
public class VueloController {
	@Autowired
	VueloService service;

	// http://localhost:9090/api/vuelos/disponibles/80
	@Operation(summary = "Recuperar vuelos con suficientes plazas disponibles", description = "Devuelve una lista de vuelos con al menos el número de plazas disponibles indicado", responses = {
			@ApiResponse(responseCode = "200", description = "Lista de vuelos", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = Vuelo.class)))),
			@ApiResponse(responseCode = "400", description = "Búsqueda no realizada porque los argumentos de la petición son incorrectos.") })
	@GetMapping(value = "/disponibles/{plazas}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Vuelo>> getVuelosConPlazasDisponibles(@PathVariable Integer plazas) {
		try {
			return new ResponseEntity<>(service.vuelosConPlazasDisponibles(plazas), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// http://localhost:9090/api/vuelos/reservar/1/3
	@Operation(summary = "Actualiza las plazas disponibles del vuelo", description = "Reduce el número de plazas indicado en el vuelo", responses = {
			@ApiResponse(responseCode = "200", description = "Vuelo actualizado"),
			@ApiResponse(responseCode = "400", description = "No se pudo actualizar el vuelo porque los argumentos de la petición son incorrectos."),
			@ApiResponse(responseCode = "404", description = "Vuelo no encontrado") })
	@PutMapping(value = "/reservar/{idVuelo}/{plazasReservadas}")
	public ResponseEntity<String> actualizarPlazas(@PathVariable Long idVuelo, @PathVariable Integer plazasReservadas) {
		try {
			service.actualizarOcupacionPlazas(idVuelo, plazasReservadas);
			return new ResponseEntity<>("Vuelo actualizado", HttpStatus.NO_CONTENT);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/existe/{idVuelo}")
	public ResponseEntity<Boolean> existeVuelo(@PathVariable Long idVuelo) {
		try {
			return new ResponseEntity<>(service.existeVuelo(idVuelo), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}
