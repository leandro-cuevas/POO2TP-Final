package Naviera;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Buque.Buque;
import TerminalGestionada.TerminalGestionada;
import TerminalGestionada.TerminalPortuaria;

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
				  .filter(b -> !b.estaEnViaje())
				  .toList();
	}
	
	public void agregarBuque(Buque b) {
		buques.add(b);
	}
	
	public void establecerViaje(LocalDateTime fechaSalida, Circuito circuitoElegido) throws Exception {
		validarCircuito(circuitoElegido);
		Buque buqueAsignado = this.busquesDisponibles().get(0); 
		Viaje viaje = new Viaje(fechaSalida, circuitoElegido, buqueAsignado);
		viajes.add(viaje);
		buqueAsignado.asignarViaje(viaje); 
	}
	
	private void validarCircuito(Circuito circuitoAValidar) throws Exception {
		if (!circuitos.contains(circuitoAValidar)) {
			throw new Exception("El circuito no puede ser elegido.");
		}
	}
	
	public void agregarCircuito(Circuito c) {
		circuitos.add(c);
	}

	public List<Viaje> getViajes() {
		return viajes;
	}

	public List<Circuito> getCircuitos() {
		return circuitos;
	}

	public double enCuantoLlega(TerminalPortuaria origen, TerminalPortuaria destino) {
	if (this.contienePuertos(origen, destino)) {
		return circuitos.stream().filter(circuito -> circuito.contienePuertos(origen, destino)).mapToDouble(circuito -> circuito.getTiempoEntrePuertos(origen, destino)).min().getAsDouble();
	} else {
		return 0d;
	}
	}
	
}
