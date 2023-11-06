package ar.edu.unq.po2.TerminalPortuaria;

public class Conductor {
	
	private boolean disponible;
	
	public Conductor() {
		disponible = true;
	}
	
	public void asignarTurno() {
		disponible = false;
	}
	
	public boolean isDisponible() {
		return disponible;
	}
}