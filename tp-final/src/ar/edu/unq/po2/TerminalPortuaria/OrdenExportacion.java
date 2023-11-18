package ar.edu.unq.po2.TerminalPortuaria;

import java.time.LocalDateTime;

public class OrdenExportacion extends Orden {

	private TerminalPortuaria terminalOrigen;
	
	private Cliente shipper;
	
	protected Conductor chofer;
	
	protected Camion camion;

	public OrdenExportacion(Viaje viaje, Container container, Conductor chofer, Camion camion,
			TerminalPortuaria terminalDestino, TerminalPortuaria terminalOrigen, Cliente shipper) {
		super(viaje, container, terminalDestino);
		this.terminalOrigen = terminalOrigen;
		this.shipper = shipper;
		this.camion = camion;
		this.chofer = chofer;
				
	}

	public LocalDateTime fechaSalida() throws Exception{
		return viaje.fechaDeArriboAlPuerto(terminalOrigen);			
	}
	
	public Cliente getShipper() {
		return shipper;
	}

}
