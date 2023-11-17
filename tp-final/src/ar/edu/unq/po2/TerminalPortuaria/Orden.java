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
	
	protected LocalDateTime fechaDeRetiro;
	
	public LocalDateTime fechaSalida() throws Exception{
		return viaje.fechaDeArriboAlPuerto(terminalOrigen);			
	}
	
	public LocalDateTime fechaLlegada() throws Exception{
		return viaje.fechaDeArriboAlPuerto(terminalDestino);
	}
	
	public void agregarServicio(Servicio s) {
		servicios.add(s);
	}
	
	public Container getContainer() {
		return this.container;
	}

	public LocalDateTime getFechaDeRetiro() {
		return fechaDeRetiro;
	}

	public void setFechaDeRetiro(LocalDateTime fechaDeRetiro) {
		this.fechaDeRetiro = fechaDeRetiro;
	}
}
