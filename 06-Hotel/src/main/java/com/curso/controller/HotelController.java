package com.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.model.Hotel;
import com.curso.service.HotelService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.persistence.EntityNotFoundException;

/**
 * Clase controlador que hace uso de la capa de servicio para exponer los
 * diferentes metodos get que devuelven información sobre la tabla hoteles
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 1.0 31/12/2024
 */
@RestController
@RequestMapping("api/hoteles")
public class HotelController {
	@Autowired
	HotelService service;

	// http://localhost:8080/api/hoteles/disponibles
	@Operation(summary = "Obtener lista de hoteles disponibles", responses = {
			@ApiResponse(responseCode = "200", description = "Lista de hoteles disponibles encontrados", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Hotel.class)))) })
	@GetMapping(value = "/disponibles", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Hotel>> getHotelesDisponibles() {
		return new ResponseEntity<>(service.buscarHotelesDisponibles(), HttpStatus.OK);
	}

	// http://localhost:8080/api/hoteles/Hotel Sol
	@Operation(summary = "Obtener hotel por nombre", description = "Devuelve la información del hotel", responses = {
			@ApiResponse(responseCode = "200", description = "Hotel encontrado", content = @Content(schema = @Schema(implementation = Hotel.class))),
			@ApiResponse(responseCode = "400", description = "Hotel no buscado porque los argumentos de la petición son incorrectos."),
			@ApiResponse(responseCode = "404", description = "Hotel no encontrado") })
	@GetMapping(value = "/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> getHotelPorNombre(@PathVariable String nombre) {
		try {
			return new ResponseEntity<>(service.buscarPorNombre(nombre), HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// http://localhost:8080/api/hoteles/identificadores/Hotel Sol
	@Operation(summary = "Obtener identificador del hotel por nombre", description = "Devuelve el identificador del hotel correspondiente al nombre indicado", responses = {
			@ApiResponse(responseCode = "200", description = "Hotel encontrado", content = @Content(schema = @Schema(implementation = Long.class))),
			@ApiResponse(responseCode = "400", description = "Hotel no buscado porque los argumentos de la petición son incorrectos."),
			@ApiResponse(responseCode = "404", description = "Hotel no encontrado") })
	@GetMapping(value = "/identificadores/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> getIdHotelPorNombre(@PathVariable String nombre) {
		try {
			return new ResponseEntity<>(service.buscarIdentificadorPorNombre(nombre), HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// http://localhost:8080/api/hoteles/existe/1
	@GetMapping("/existe/{idHotel}")
	public ResponseEntity<Boolean> existeHotel(@PathVariable Long idHotel) {
		try {
			return new ResponseEntity<>(service.existeHotel(idHotel), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	// http://localhost:8080/api/hoteles/existe/nombre/Hotel Sol
	@GetMapping("/existe/nombre/{nombreHotel}")
	public ResponseEntity<Boolean> existeHotelConNombre(@PathVariable String nombreHotel) {
		try {
			return new ResponseEntity<>(service.existeNombreHotel(nombreHotel), HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
