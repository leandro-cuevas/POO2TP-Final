package ar.edu.unq.poo2.TerminalGestionada;

import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.TerminalPortuaria;
import ar.edu.unq.po2.TerminalPortuaria.Tramo;

public class CriterioMenorTiempo extends Criterio{
	ComparadorCircuito comparador = new ComparadorCircuitoTiempo();
	
	@Override
	public List<Tramo> elMejor(TerminalPortuaria origen, TerminalPortuaria destino, List<Circuito> circuitos) {
		return circuitos.stream()
				.min((v1,v2) -> v1.compararCon(v2, comparador))
				.get()
				.getTramos();
	}

}
