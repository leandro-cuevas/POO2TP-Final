package Orden;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Container.Container;
import EmpresaTransportista.Camion;
import EmpresaTransportista.Conductor;
import Naviera.Viaje;
import Servicios.Servicio;
import TerminalGestionada.Cliente;
import TerminalGestionada.TerminalPortuaria;
import TerminalGestionada.Turno;

public abstract class Orden {
	
	protected Viaje viaje;
	
	protected Container container;	
	
	protected TerminalPortuaria terminalOrigen;
	
	protected TerminalPortuaria terminalDestino;
	
	protected List<Servicio> servicios;
	
	protected LocalDateTime fechaRetirada;
	
	protected boolean cargaDepositada;
	
	protected Turno turno;
	
	protected Cliente cliente;
	
	//Constructor de Orden, para que le hagan super las subclases ya que es abstracta
	public Orden(Viaje viaje, Container container, TerminalPortuaria terminalDestino, TerminalPortuaria terminalOrigen, Turno turno, Cliente cliente) {
		this.viaje = viaje;
		this.container = container;
		this.terminalOrigen = terminalOrigen;
		this.terminalDestino = terminalDestino;
		this.servicios = new ArrayList<Servicio>();
		this.fechaRetirada = null;
		this.cargaDepositada = false;
		this.turno = turno;
		this.cliente = cliente;
	}
	
	public LocalDateTime fechaLlegada(){
		return viaje.fechaDeArriboAlPuerto(terminalDestino);
	}
	
	public void agregarServicio(Servicio s) {
		servicios.add(s);
	}
	
	abstract protected LocalDateTime fechaAEvaluar();
	
	protected abstract double precioViaje() throws Exception;
	
	public double getCostosDeServicios() throws Exception {
		int horas = (int) Duration.between(fechaRetirada, this.fechaAEvaluar()).toHours();
		double precioServicios = servicios.stream().mapToDouble(serv -> serv.getCostoDeServicio(horas)).sum();
		return precioServicios + this.precioViaje();
	}
	
	//GETTERS
	
	public Container getContainer() {
		return container;
	}
	
	public TerminalPortuaria getDestino() {
		return terminalDestino;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public LocalDateTime fechaRetirada() {
		return fechaRetirada;
	}
	
	public Conductor getChofer() {
		return turno.getConductor();
	}
	
	public Camion getCamion() {
		return turno.getCamion();
	}
	
	public boolean isCargaDepositada() {
		return cargaDepositada;
	}
	
	//SETTERS

	public void setFechaRetirada(LocalDateTime fechaDeRetiro) {
		this.fechaRetirada = fechaDeRetiro;
	}
	
	public void setCargaDepositada() {
		this.cargaDepositada = true;
	}
	
	//EVALUADORES
	
	public boolean tieneMismoViaje(Viaje viaje) {
		return this.viaje == viaje;
	}

	public boolean esDeCliente(Cliente consignee) {
		return cliente == consignee;
	}
	
	public boolean esContainer(Container carga) {
		return container == carga;
	}
	
}
