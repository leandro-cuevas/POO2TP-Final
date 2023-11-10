package ar.edu.unq.po2.TerminalPortuaria;

public class Conductor {
	private boolean disponible;
	
	private Turno turnoTerminal;
	
	private Camion camionManejado;
	
	public Conductor() {
		disponible = true;
		camionManejado = null;
		turnoTerminal = null;
	}
	
	public void asignarTurno(Camion camion, Turno turno) {
		disponible = false;
		camionManejado = camion;
		turnoTerminal = turno;
	}
	
	public boolean isDisponible() {
		return disponible;
	}
	
	public Camion getCamion() {
		return camionManejado;
	}
	
	public Turno getTurno() {
		return turnoTerminal;
	}
}
