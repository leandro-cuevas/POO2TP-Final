package ar.edu.unq.poo2.TerminalGestionada;

import ar.edu.unq.po2.TerminalPortuaria.TerminalPortuaria;

public class CriterioMenorTiempo extends Criterio{
	@Override
	protected int comparar(Circuito c1, TerminalPortuaria origen, TerminalPortuaria destino, Circuito c2) {
		return Double.compare(c1.getTiempoEntrePuertos(origen, destino), c2.getTiempoEntrePuertos(origen, destino));
	}
}
