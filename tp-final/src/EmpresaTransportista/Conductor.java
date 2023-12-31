package EmpresaTransportista;

import Container.Container;
import TerminalGestionada.Turno;

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
		//Setea el camión y el turno por los pasados por parámetro, y asigna un falso
		//a disponible, modelando la no disponibilidad del conductor.
		disponible = false;
		camionManejado = camion;
		turnoTerminal = turno;
	}
	
	public boolean isDisponible() {
		//Getter de disponible
		return disponible;
	}
	
	public Camion getCamion() {
		//Getter del camión que el conductor va a manejar
		return camionManejado;
	}
	
	public Turno getTurno() {
		//Getter del turno
		return turnoTerminal;
	}
	
	public Container getCarga() {
		//Getter de carga
		return camionManejado.getCarga();
	}
	
	public void setCamionManejado(Camion camion) {
		//Setter del camion manejado.
		this.camionManejado = camion;
	}
	
	public void setTurno(Turno t) {
		//Setter del turno.
		this.turnoTerminal = t;
	}
}
