package ar.edu.unq.poo2.TerminalGestionada;

import java.time.LocalDateTime;

import ar.edu.unq.po2.TerminalPortuaria.TerminalPortuaria;
import ar.edu.unq.po2.TerminalPortuaria.Viaje;

public class HojaFechaLlegada implements Condicion {
	LocalDateTime fecha;
	TerminalPortuaria ptoDestino;
	
	public HojaFechaLlegada(LocalDateTime fecha, TerminalPortuaria ptoDestino) {
		super();
		this.fecha = fecha;
		this.ptoDestino = ptoDestino;
	}
	
	@Override
	public boolean chequear(Viaje viaje) throws Exception { 
		if (viaje.contienePuertos(viaje.getCircuitoRecorrido().puertoOrigen(), ptoDestino)) {
			return viaje.fechaDeArriboAlPuerto(ptoDestino).isBefore(fecha);	
		} else {
			return false;
		}
		
	}
}
