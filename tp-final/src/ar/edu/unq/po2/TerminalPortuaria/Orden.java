package ar.edu.unq.po2.TerminalPortuaria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Orden {
	
	protected Viaje viaje;
	
	protected Container container;
	
	protected Conductor chofer;
	
	protected Camion camion;
	
	protected TerminalPortuaria terminalDestino;
	
	protected List<Servicio> servicios;
	
	protected LocalDateTime fechaRetirada;
	
	
	
	public Orden(Viaje viaje, Container container, TerminalPortuaria terminalDestino) {
		this.viaje = viaje;
		this.container = container;
		this.terminalDestino = terminalDestino;
		this.servicios = new ArrayList<Servicio>();
		this.fechaRetirada = null;
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

	public LocalDateTime fechaRetirada() {
		return fechaRetirada;
	}

	public void setFechaRetirada(LocalDateTime fechaDeRetiro) {
		this.fechaRetirada = fechaDeRetiro;
	}
	
	public boolean tieneMismoViaje(Viaje viaje) {
		return this.viaje == viaje;
	}
	
	public TerminalPortuaria getDestino() {
		return terminalDestino;
	}
}
