package ar.edu.unq.poo2.TerminalGestionada;

public class And implements Condicion {
	Condicion exp;
	Condicion expr;
	@Override
	public boolean chequear(Viaje viaje) {
		return exp && expr;
	}
}
