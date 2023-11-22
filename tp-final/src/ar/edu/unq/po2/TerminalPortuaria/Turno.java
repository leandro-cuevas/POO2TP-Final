package ar.edu.unq.po2.TerminalPortuaria;

import java.time.LocalDateTime;
import java.time.temporal.Temporal;

import EmpresaTransportista.Camion;
import EmpresaTransportista.Conductor;

public class Turno {
	Conductor chofer;
	Camion camion;
	LocalDateTime diaYHora;
	
	public Turno(Conductor chofer, Camion camion , LocalDateTime diaYHora, Container carga) {
		super();
		this.chofer = chofer;
		this.camion = camion;
		this.diaYHora = diaYHora;
		chofer.setTurno(this);
		chofer.setCamionManejado(camion);
		camion.cargar(carga);
	}
	
	public Turno(Cliente cliente, LocalDateTime diaYHora, Container carga) {
		super();
		this.chofer = null;
		this.camion = null;
		this.diaYHora = diaYHora;
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
	
	public Conductor getConductor() {
		return this.chofer;
	}
	
	public Camion getCamion() {
		return this.camion;
	}
}
