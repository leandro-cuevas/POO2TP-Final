package Criterio;

import Naviera.Circuito;
import TerminalGestionada.TerminalPortuaria;

public class CriterioMenorTiempo extends Criterio{
	@Override
	protected int comparar(Circuito c1, TerminalPortuaria origen, TerminalPortuaria destino, Circuito c2) {
		return Double.compare(c1.getTiempoEntrePuertos(origen, destino), c2.getTiempoEntrePuertos(origen, destino));
	}
}
