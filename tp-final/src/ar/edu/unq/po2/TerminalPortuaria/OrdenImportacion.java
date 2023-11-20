package ar.edu.unq.po2.TerminalPortuaria;

import java.time.LocalDateTime;

public class OrdenImportacion extends Orden {
	

	public OrdenImportacion(Viaje viaje, Container container, TerminalPortuaria terminalDestino, Cliente consignee, TerminalPortuaria terminalOrigen) {
		super(viaje, container, terminalDestino, consignee, terminalOrigen);
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
		this.camion = c;
	}
	
	public void setChofer(Conductor c) {
		this.chofer = c;
	}

	public Camion getCamion() {
		return camion;
	}
	
}
