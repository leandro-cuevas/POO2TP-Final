package Orden;

import java.time.LocalDateTime;

import Container.Container;
import EmpresaTransportista.Camion;
import EmpresaTransportista.Conductor;
import ar.edu.unq.po2.TerminalPortuaria.Cliente;
import ar.edu.unq.po2.TerminalPortuaria.TerminalPortuaria;
import ar.edu.unq.po2.TerminalPortuaria.Turno;
import ar.edu.unq.po2.TerminalPortuaria.Viaje;

public class OrdenImportacion extends Orden {
	

	public OrdenImportacion(Viaje viaje, Container container, TerminalPortuaria terminalDestino, TerminalPortuaria terminalOrigen, Cliente cliente) {
		super(viaje, container, terminalDestino, terminalOrigen, null, cliente);
		this.cargaDepositada = false;
	}
	
	@Override
	protected LocalDateTime fechaAEvaluar() {
		return fechaLlegada();
	}

	@Override
	protected double precioViaje() throws Exception {
		return viaje.getCircuitoRecorrido().getPrecioEntrePuertos(terminalOrigen, terminalDestino);
	}
	
	public void setCamion(Camion c) {
		turno.setCamion(c);
	}
	
	public void setChofer(Conductor c) {
		turno.setChofer(c);
	}
	
	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	
}
