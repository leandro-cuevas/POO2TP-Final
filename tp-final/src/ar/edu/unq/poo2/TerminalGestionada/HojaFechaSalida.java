package ar.edu.unq.poo2.TerminalGestionada;

import java.sql.Date;

import ar.edu.unq.po2.TerminalPortuaria.TerminalPortuaria;

public class HojaFechaSalida implements Condicion{
	Date fecha;
	TerminalPortuaria ptoSalida;
	
	public HojaFechaSalida(Date fecha, TerminalPortuaria ptoSalida) {
		super();
		this.fecha = fecha;
		this.ptoSalida = ptoSalida;
	}
	
	@Override
	public boolean chequear(Viaje viaje) {
		return fecha > viaje.getFechaDeLlegadaA(ptoSalida);
	}	
}
