package ar.edu.unq.po2.TerminalPortuaria;

import java.util.ArrayList;
import java.util.List;
import ar.edu.unq.poo2.TerminalGestionada.Circuito;

public class Naviera {
	
	private List<Buque> buques = new ArrayList<Buque>();
	private List<Viaje> viajes = new ArrayList<Viaje>();
	private List<Circuito> circuitos = new ArrayList<Circuito>();
	
	public boolean contienePuertos(TerminalPortuaria origen, TerminalPortuaria destino) {
		circuitos.stream().anyMatch(circuito->circuito.contienePuertos(origen, destino));
}
}
