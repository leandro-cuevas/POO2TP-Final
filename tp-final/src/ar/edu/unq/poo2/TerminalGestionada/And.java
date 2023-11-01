package ar.edu.unq.poo2.TerminalGestionada;

import ar.edu.unq.po2.TerminalPortuaria.Viaje;

public class And implements Condicion {
	Condicion exp;
	Condicion expr;
	@Override
	public boolean chequear(Viaje viaje) throws Exception {
		return exp.chequear(viaje) && expr.chequear(viaje);
	}
}
