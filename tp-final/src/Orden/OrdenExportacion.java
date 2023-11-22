package Orden;

import java.time.LocalDateTime;

import Container.Container;
import Naviera.Viaje;
import TerminalGestionada.Cliente;
import TerminalGestionada.TerminalPortuaria;
import TerminalGestionada.Turno;

public class OrdenExportacion extends Orden {
		
	public OrdenExportacion(Viaje viaje, Container container, TerminalPortuaria terminalDestino,
							TerminalPortuaria terminalOrigen, Turno turno, Cliente cliente) {
		super(viaje, container, terminalDestino, terminalOrigen, turno, cliente);			
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
