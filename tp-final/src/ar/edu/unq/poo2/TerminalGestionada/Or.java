package ar.edu.unq.poo2.TerminalGestionada;

import ar.edu.unq.po2.TerminalPortuaria.Viaje;

public class Or implements Condicion {
	Condicion exp;
	Condicion expr;
	
	public Or(Condicion exp, Condicion expr) {
		super();
		this.exp = exp;
		this.expr = expr;
	}
	
	@Override
	public boolean chequear(Viaje viaje) {
		return exp.chequear(viaje) || expr.chequear(viaje);
	}
	
	
}
