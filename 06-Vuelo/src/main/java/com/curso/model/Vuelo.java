package com.curso.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Clase que representa un vuelo. Contiene su identificador, nombre de la
 * compañia encagada, fecha, precio y numero de plazas disponibles.
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 1.0 31/12/2024
 */
@Entity
@Table(name = "vuelos")
public class Vuelo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_vuelo")
	private Long idVuelo;
	@Enumerated(EnumType.STRING)
	private Compania compania;
	@Column(name = "fecha_vuelo")
	private LocalDate fechaVuelo;
	private Double precio;
	@Column(name = "plazas_disponibles")
	private Integer plazasDisponibles;

	public Vuelo() {
		super();
	}

	public Vuelo(Long idVuelo, Compania compania, LocalDate fechaVuelo, Double precio, Integer plazasDisponibles) {
		super();
		this.idVuelo = idVuelo;
		this.compania = compania;
		this.fechaVuelo = fechaVuelo;
		this.precio = precio;
		this.plazasDisponibles = plazasDisponibles;
	}

	public Vuelo(long l, Compania vueling, String string, double d, int i) {
		// TODO Auto-generated constructor stub
	}

	public Long getIdVuelo() {
		return idVuelo;
	}

	public void setIdVuelo(Long idVuelo) {
		this.idVuelo = idVuelo;
	}

	public Compania getCompania() {
		return compania;
	}

	public void setCompania(Compania compania) {
		this.compania = compania;
	}

	public LocalDate getFechaVuelo() {
		return fechaVuelo;
	}

	public void setFechaVuelo(LocalDate fechaVuelo) {
		this.fechaVuelo = fechaVuelo;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getPlazasDisponibles() {
		return plazasDisponibles;
	}

	public void setPlazasDisponibles(Integer plazasDisponibles) {
		this.plazasDisponibles = plazasDisponibles;
	}

}
