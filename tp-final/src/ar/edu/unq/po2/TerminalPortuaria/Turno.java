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
	
	public Turno(Cliente cliente, LocalDateTime diaYHora, Container carga) {
		super();
		this.chofer = null;
		this.camion = null;
		this.cliente = cliente;
		this.diaYHora = diaYHora;
	}

	public Camion getCamion() {
		return camion;
	}
	public LocalDateTime getDiaYHora() {
		return diaYHora;
	}
	
	public boolean esChofer(Conductor conductor) {
		return chofer == conductor;
	}
	
	public boolean esCamion(Camion coche) {
		return camion == coche;
	}
	
	public void setCamion(Camion coche) {
		this.camion = coche;
	}
	
	public void setChofer(Conductor chofer) {
		this.chofer = chofer;
		chofer.setCamionManejado(camion);
	}
	
	public boolean esDeCliente(Cliente c) {
		return cliente == c;
	}
}
