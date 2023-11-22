package Criterio;

import Naviera.Circuito;
import TerminalGestionada.TerminalPortuaria;

public class CriterioMenorDistancia extends Criterio {
	@Override
	protected int comparar(Circuito c1, TerminalPortuaria origen, TerminalPortuaria destino, Circuito c2) {
		return Double.compare(c1.getCantidadDeTerminalesEntrePuertos(origen, destino), c2.getCantidadDeTerminalesEntrePuertos(origen, destino));
	}
}
