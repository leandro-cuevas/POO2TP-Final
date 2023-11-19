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
	
	protected Cliente cliente;
	
	protected boolean cargaDepositada;
	
	//Constructor de Orden, para que le hagan super las subclases ya que es abstracta
	public Orden(Viaje viaje, Container container, TerminalPortuaria terminalDestino, Cliente cliente) {
		this.viaje = viaje;
		this.container = container;
		this.terminalDestino = terminalDestino;
		this.servicios = new ArrayList<Servicio>();
		this.fechaRetirada = null;
		this.cliente = cliente;
		this.cargaDepositada = false;
	}

	//Getter de fecha de salida, se calcula ya que es algo que depende del viaje.
	public LocalDateTime fechaSalida() {
		return viaje.fechaDeArriboAlPuerto(terminalDestino);			

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
	
	public boolean tieneMismoViaje(Viaje viaje) {
		return this.viaje == viaje;
	}
	
	public TerminalPortuaria getDestino() {
		return terminalDestino;
	}

	public boolean esDeCliente(Cliente consignee) {
		return cliente == consignee;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public boolean esContainer(Container carga) {
		return carga == container;
	}
	
	public void setCargaDepositada() {
		this.cargaDepositada = true;
	}

	public boolean isCargaDepositada() {
		return this.cargaDepositada;
	}
	
	public boolean esViaje(Viaje viaje) {
		return this.viaje == viaje;
	}
}
