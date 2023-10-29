package ar.edu.unq.poo2.TerminalGestionada;

import java.util.Date;
import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.TerminalPortuaria;
import ar.edu.unq.po2.TerminalPortuaria.Tramo;

public class Circuito {
	
	private List<Tramo> listaDeTramos;
	
	public boolean contienePuertos(TerminalPortuaria origen, TerminalPortuaria destino) {
		
		
		return true; 
	}
	
	public double getPrecioEntreLosTramos(Tramo a1, Tramo a2) {
		
		int indexDelTramo1 = listaDeTramos.indexOf(a1);
		
		int indexDelTramo2 = listaDeTramos.indexOf(a2);
		
		return 0;
		
	}

	public int compararCon(Circuito c2, ComparadorCircuito comp) {
		return comp.comparar(this, c2);
	}
	public double getTiempo() {
		return 0;
	}
	public List<Tramo> getTramos() {
		return null;
	}
	public double getPrecio() {
		return 0;
	}

}
