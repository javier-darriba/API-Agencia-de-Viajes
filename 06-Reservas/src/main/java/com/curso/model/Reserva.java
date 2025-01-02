package com.curso.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Clase que representa una reserva. Contiene su identificador, nombre y dni del
 * cliente, identificador del hotel y del vuelo.
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 1.0 31/12/2024
 */
@Entity
@Table(name = "reservas")
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_reserva")
	private Long idReserva;
	@Column(name = "nombre_cliente")
	private String nombreCliente;
	private String dni;
	@Column(name = "id_hotel")
	private Long idHotel;
	@Column(name = "id_vuelo")
	private Long idVuelo;

	public Reserva() {
		super();
	}

	public Reserva(Long idReserva, String nombreCliente, String dni, Long idHotel, Long idVuelo) {
		super();
		this.idReserva = idReserva;
		this.nombreCliente = nombreCliente;
		this.dni = dni;
		this.idHotel = idHotel;
		this.idVuelo = idVuelo;
	}

	public Long getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(Long idReserva) {
		this.idReserva = idReserva;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Long getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(Long idHotel) {
		this.idHotel = idHotel;
	}

	public Long getIdVuelo() {
		return idVuelo;
	}

	public void setIdVuelo(Long idVuelo) {
		this.idVuelo = idVuelo;
	}

}
