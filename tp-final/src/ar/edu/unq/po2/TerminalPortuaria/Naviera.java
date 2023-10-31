package ar.edu.unq.po2.TerminalPortuaria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import ar.edu.unq.poo2.TerminalGestionada.Circuito;

public class Naviera {
	
	private List<Buque> buques;
	private List<Viaje> viajes;
	private List<Circuito> circuitos;
	
	public Naviera() {
		this.buques =  new ArrayList<Buque>();
		this.viajes = new ArrayList<Viaje>();
		this.circuitos = new ArrayList<Circuito>();
	}

	public boolean contienePuertos(TerminalPortuaria origen, TerminalPortuaria destino) {
		circuitos.stream().anyMatch(circuito->circuito.contienePuertos(origen, destino));
	}
	
	public void agregarBuque(Buque b) {
		buques.add(b);
	}
	
	public void establecerViaje(LocalDateTime fechaSalida, Circuito circuitoElegido) {
		Buque buque; //TODO: Falta poder filtrar por buques que no estén en un viaje.
		viajes.add(new Viaje(fechaSalida, circuitoElegido, buque));
	}
	
	public void agregarCircuito(Circuito c) {
		circuitos.add(c);
	}
	
}
