package ar.edu.unq.poo2.TerminalGestionada;

public class CriterioMenorDistancia extends Criterio {
	ComparadorCircuito comparador;
	
	public CriterioMenorDistancia() {
		comparador = new ComparadorCircuitoDistancia();
	}
}
