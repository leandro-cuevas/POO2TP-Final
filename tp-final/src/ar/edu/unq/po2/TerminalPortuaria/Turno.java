package ar.edu.unq.po2.TerminalPortuaria;

import java.time.LocalDateTime;
import java.time.temporal.Temporal;

public class Turno {
	Conductor chofer;
	Camion camion;
	Cliente cliente;
	LocalDateTime diaYHora;
	
	public Turno(Conductor chofer, Camion camion, Cliente cliente, LocalDateTime diaYHora, Container carga) {
		super();
		this.chofer = chofer;
		this.camion = camion;
		this.cliente = cliente;
		this.diaYHora = diaYHora;
		chofer.setTurno(this);
		chofer.setCamionManejado(camion);
		camion.cargar(carga);
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
	
	public boolean esFecha(LocalDateTime fecha) {
		return diaYHora == fecha;
	}
	
	public boolean esChofer(Conductor conductor) {
		return chofer == conductor;
	}
	
	public boolean esCamion(Camion coche) {
		return camion == coche;
	}
}
