package ar.edu.unq.poo2.TerminalGestionada;

public class CriterioMenorPrecio extends Criterio {
	@Override
	public int comparar(Circuito c1, Circuito c2) {
		return Double.compare(c1.getPrecio(), c2.getPrecio());
	}
}	
