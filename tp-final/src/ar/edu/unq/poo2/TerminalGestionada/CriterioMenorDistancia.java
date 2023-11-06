package ar.edu.unq.poo2.TerminalGestionada;

public class CriterioMenorDistancia extends Criterio {
	@Override
	public int comparar(Circuito c1, Circuito c2) {
		return Double.compare(c1.getTiempo(), c2.getTiempo());
	}
}
