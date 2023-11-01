package ar.edu.unq.poo2.TerminalGestionada;

public class CriterioMenorTiempo extends Criterio{
	// Constructor para el CriterioMenorTiempo, que contara con un colaborador que compara dos circuitos por su tiempo
	public CriterioMenorTiempo() {
		this.comparador = new ComparadorCircuitoTiempo(); 
	}

}
