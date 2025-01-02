package com.curso.model;

/**
 * Clase utilizada para almacenar recibir una reserva junto al número de
 * personas que abarca.
 * 
 * @author Javier Darriba Gonzalez / Viewnext
 * @version 1.0 31/12/2024
 */
public class ReservaYPlazas {
	private Reserva reserva;
	private Integer plazasReservadas;

	public ReservaYPlazas() {
		super();
	}

	public ReservaYPlazas(Reserva reserva, Integer plazasReservadas) {
		super();
		this.reserva = reserva;
		this.plazasReservadas = plazasReservadas;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Integer getPlazasReservadas() {
		return plazasReservadas;
	}

	public void setPlazasReservadas(Integer plazasReservadas) {
		this.plazasReservadas = plazasReservadas;
	}

}
