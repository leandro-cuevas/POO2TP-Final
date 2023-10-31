package ar.edu.unq.poo2.TerminalGestionada;

public class CriterioMenorDistancia extends Criterio {
	// Constructor para el CriterioMenorDistancia, que contara con un colaborador que compara dos circuitos por su distancia
	public CriterioMenorDistancia() {
		comparador = new ComparadorCircuitoDistancia();
	}
}
