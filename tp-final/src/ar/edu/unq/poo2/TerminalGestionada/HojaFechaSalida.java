package ar.edu.unq.poo2.TerminalGestionada;

import java.sql.Date;
import java.time.LocalDateTime;

import ar.edu.unq.po2.TerminalPortuaria.TerminalPortuaria;
import ar.edu.unq.po2.TerminalPortuaria.Viaje;

public class HojaFechaSalida implements Condicion{
	LocalDateTime fecha;
	TerminalPortuaria ptoSalida;
	
	public HojaFechaSalida(LocalDateTime fecha, TerminalPortuaria ptoSalida) {
		super();
		this.fecha = fecha;
		this.ptoSalida = ptoSalida;
	}
	
	@Override
	public boolean chequear(Viaje viaje) throws Exception {
		if (viaje.contienePuertos(viaje.getCircuitoRecorrido().puertoOrigen(), ptoSalida)) {
			return viaje.fechaDeArriboAlPuerto(ptoSalida).isBefore(fecha);	
		} else {
			return false;
		}		
	}	
}
