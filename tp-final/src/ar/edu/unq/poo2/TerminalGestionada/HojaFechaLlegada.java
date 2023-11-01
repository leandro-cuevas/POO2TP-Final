package ar.edu.unq.poo2.TerminalGestionada;

import java.sql.Date;

import ar.edu.unq.po2.TerminalPortuaria.TerminalPortuaria;

public class HojaFechaLlegada implements Condicion {
	Date fecha;
	TerminalPortuaria ptoDestino;
	
	public HojaFechaLlegada(Date fecha, TerminalPortuaria ptoDestino) {
		super();
		this.fecha = fecha;
		this.ptoDestino = ptoDestino;
	}
	
	@Override
	public boolean chequear(Viaje viaje) {
		return fecha > viaje.getFechaDeLlegada(ptoDestino);
	}
}
