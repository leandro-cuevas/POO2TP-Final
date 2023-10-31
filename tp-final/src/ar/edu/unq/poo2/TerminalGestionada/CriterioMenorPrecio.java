package ar.edu.unq.poo2.TerminalGestionada;

public class CriterioMenorPrecio extends Criterio {
	public CriterioMenorPrecio() {
		comparador = new ComparadorCircuitoPrecio();
	}
}	
