package Criterio;

import java.util.ArrayList;
import java.util.List;

import Naviera.Circuito;
import Naviera.Tramo;
import TerminalGestionada.TerminalPortuaria;

public abstract class Criterio {
	
	public List<Tramo> elMejor(TerminalPortuaria origen, TerminalPortuaria destino, List<Circuito> circuitos) {
		if (circuitos.isEmpty()) {
			return new ArrayList<Tramo>();
		} else {
		return circuitos.stream()
				.min((v1,v2) -> this.comparar(v1, origen, destino, v2))
				.get()
				.getListaDeTramos();
		}
	}

	protected abstract int comparar(Circuito c1, TerminalPortuaria origen, TerminalPortuaria destino, Circuito c2);
}