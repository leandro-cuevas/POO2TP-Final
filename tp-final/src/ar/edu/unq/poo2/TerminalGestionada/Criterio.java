package ar.edu.unq.poo2.TerminalGestionada;

import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.Tramo;

public abstract class Criterio {
	public abstract List<Tramo> elMejor(List<Circuito> circuitos);
}