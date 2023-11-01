package ar.edu.unq.poo2.TerminalGestionada;

public class CriterioMenorPrecio extends Criterio {
	// Constructor para el CriterioMenorPrecio, que contara con un colaborador que compara dos circuitos por su precio.
	public CriterioMenorPrecio() {
		this.comparador = new ComparadorCircuitoPrecio();	
	}
}	
