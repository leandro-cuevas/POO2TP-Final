package ar.edu.unq.po2.TerminalPortuaria;

import java.time.LocalDateTime;
import java.time.temporal.Temporal;

public class Turno {
	Conductor chofer;
	Camion camion;
	Cliente cliente;
	LocalDateTime diaYHora;
	
	public Turno(Conductor chofer, Camion camion, Cliente cliente, LocalDateTime diaYHora) {
		super();
		this.chofer = chofer;
		this.camion = camion;
		this.cliente = cliente;
		this.diaYHora = diaYHora;
	}

	public LocalDateTime getFecha() {
		return diaYHora;
	}
	
	public Conductor getChofer() {
		return chofer;
	}

	public Camion getCamion() {
		return camion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public LocalDateTime getDiaYHora() {
		return diaYHora;
	}
}
