package ar.edu.unq.poo2.TerminalGestionada;

import ar.edu.unq.po2.TerminalPortuaria.TerminalPortuaria;

public class HojaPuertoDestino implements Condicion {
	TerminalPortuaria ptoSalida;
	TerminalPortuaria ptoDestino;

	@Override
	public boolean chequear(Viaje viaje) {
		return viaje.viajaDesdeA(ptoSalida, ptoDestino);
	}
}
