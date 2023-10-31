package ar.edu.unq.poo2.TerminalGestionada;

public class CriterioMenorPrecio extends Criterio {
	ComparadorCircuito comparador;
	
	public CriterioMenorPrecio() {
		this.comparador = new ComparadorCircuitoPrecio();	
	}
}
