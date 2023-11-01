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
		return circuitos.stream().anyMatch(circuito->circuito.contienePuertos(origen, destino));
	}
	
	private List<Buque> busquesDisponibles(){
		  return buques.stream()
				  .filter(b -> b.isDisponible())
				  .toList();
	}
	
	public void agregarBuque(Buque b) {
		buques.add(b);
	}
	
	public void establecerViaje(LocalDateTime fechaSalida, Circuito circuitoElegido) {
		Buque buqueAsignado = this.busquesDisponibles().get(0); 
		viajes.add(new Viaje(fechaSalida, circuitoElegido, buqueAsignado));
	}
	
	public void agregarCircuito(Circuito c) {
		circuitos.add(c);
	}
	
}
