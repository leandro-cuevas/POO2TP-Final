package ar.edu.unq.po2.TerminalPortuaria;

import java.time.LocalDateTime;

public abstract class Orden {
	
	protected Viaje viaje;
	
	protected Container carga;
	
	protected Conductor chofer;
	
	protected Camion camion;
	
	protected TerminalPortuaria terminalOrigen;
	
	protected TerminalPortuaria terminalDestino;
	
	public LocalDateTime fechaSalida() throws Exception{
		return viaje.fechaDeArriboAlPuerto(terminalOrigen);			
	}
	
	public LocalDateTime fechaLlegada() throws Exception{
		return viaje.fechaDeArriboAlPuerto(terminalDestino);
	}
}
