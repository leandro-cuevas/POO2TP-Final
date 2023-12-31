package Criterio;

import Naviera.Circuito;
import TerminalGestionada.TerminalPortuaria;

public class CriterioMenorPrecio extends Criterio {
	@Override
	protected int comparar(Circuito c1, TerminalPortuaria origen, TerminalPortuaria destino, Circuito c2) {
		try {
			return Double.compare(c1.getPrecioEntrePuertos(origen, destino), c2.getPrecioEntrePuertos(origen, destino));
		} catch (Exception e) {
			// No va a fallar nunca, ya que previamente se filtro.
			return 0;
		}
	}
}	
