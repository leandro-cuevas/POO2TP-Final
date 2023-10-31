package ar.edu.unq.poo2.TerminalGestionada;

import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.Tramo;

public abstract class Criterio {
	ComparadorCircuito comparador;
	public List<Tramo> elMejor(List<Circuito> circuitos) {
		return circuitos.stream()
				.min((v1,v2) -> comparador.comparar(v1, v2))
				.get()
				.getTramos();
	}
}