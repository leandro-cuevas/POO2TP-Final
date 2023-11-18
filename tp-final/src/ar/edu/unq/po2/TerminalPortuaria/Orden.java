package ar.edu.unq.po2.TerminalPortuaria;

import java.time.LocalDateTime;
import java.util.List;

public abstract class Orden {
	
	protected Viaje viaje;
	
	protected Container container;
	
	protected Conductor chofer;
	
	protected Camion camion;
	
	protected TerminalPortuaria terminalOrigen;
	
	protected TerminalPortuaria terminalDestino;
	
	protected List<Servicio> servicios;
	
	protected LocalDateTime fechaRetirada;
	
	public LocalDateTime fechaSalida() {
		return viaje.fechaDeArriboAlPuerto(terminalOrigen);			
	}
	
	public LocalDateTime fechaLlegada(){
		return viaje.fechaDeArriboAlPuerto(terminalDestino);
	}
	
	public void agregarServicio(Servicio s) {
		servicios.add(s);
	}
	
	public Container getContainer() {
		return this.container;
	}

	public LocalDateTime fechaRetirada() {
		return fechaRetirada;
	}

	public void setFechaRetirada(LocalDateTime fechaDeRetiro) {
		this.fechaRetirada = fechaDeRetiro;
	}
}
