package ar.edu.unq.poo2.TerminalGestionada;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.Tramo;

public abstract class Criterio {
	ComparadorCircuito comparador;
	
	public List<Tramo> elMejor(List<Circuito> circuitos) {
		if (circuitos.size() == 0) {
			return new ArrayList<Tramo>();
		} else {
		return circuitos.stream()
				.min((v1,v2) -> getComparador().comparar(v1, v2))
				.get()
				.getTramos();
		}
	}
	
	private ComparadorCircuito getComparador() {
		return comparador;
	}
}