package ar.edu.unq.poo2.TerminalGestionada;

public class CriterioMenorTiempo extends Criterio{
	ComparadorCircuito comparador;
	
	public CriterioMenorTiempo() {
		this.comparador = new ComparadorCircuitoTiempo(); 
	}

}
