package ar.edu.unq.poo2.TerminalGestionada;

import ar.edu.unq.po2.TerminalPortuaria.TerminalPortuaria;
import ar.edu.unq.po2.TerminalPortuaria.Viaje;

public class HojaPuertoDestino implements Condicion {
	TerminalPortuaria ptoSalida;
	TerminalPortuaria ptoDestino;

	@Override
	public boolean chequear(Viaje viaje) {
		return viaje.contienePuertos(ptoSalida, ptoDestino);
	}
}
