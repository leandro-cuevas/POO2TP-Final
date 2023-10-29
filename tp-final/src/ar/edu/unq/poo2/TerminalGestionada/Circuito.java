package ar.edu.unq.poo2.TerminalGestionada;

import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.Tramo;

public class Circuito {

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
	public double getDistancia() {
		// TODO Auto-generated method stub
		return 0;
	}

}
