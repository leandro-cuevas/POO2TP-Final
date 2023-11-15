package ar.edu.unq.poo2.TerminalGestionada;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.Tramo;

public abstract class Criterio {
	
	public List<Tramo> elMejor(List<Circuito> circuitos) {
		if (circuitos.isEmpty()) {
			return new ArrayList<Tramo>();
		} else {
		return circuitos.stream()
				.min((v1,v2) -> this.comparar(v1, v2))
				.get()
				.getListaDeTramos();
		}
	}

	protected abstract int comparar(Circuito c1, Circuito c2);
}