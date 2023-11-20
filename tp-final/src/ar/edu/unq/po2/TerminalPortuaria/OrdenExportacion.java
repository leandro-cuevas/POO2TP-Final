package ar.edu.unq.po2.TerminalPortuaria;

import java.time.LocalDateTime;

public class OrdenExportacion extends Orden {
		
	public OrdenExportacion(Viaje viaje, Container container, Conductor chofer, Camion camion,
			TerminalPortuaria terminalDestino, TerminalPortuaria terminalOrigen, Cliente shipper) {
		super(viaje, container, terminalDestino, shipper, terminalOrigen);
		this.camion = camion;
		this.chofer = chofer;				
	}
	
	@Override
	protected LocalDateTime fechaAEvaluar() {
		return this.fechaSalida();
	}
	
	public LocalDateTime fechaSalida(){
		return viaje.fechaDeArriboAlPuerto(terminalOrigen);			
	}

	@Override
	protected double precioViaje() {
		// Devuelve 0 porque el shipper no paga el precio del viaje.
		return 0;
	}

}
