package ar.edu.unq.po2.TerminalPortuaria;

import java.util.ArrayList;
import java.util.List;

public class Buque {
	
	private TerminalPortuaria terminal;
	
	private EstadoDeBuque estado;
	
	private boolean disponible;
	
	private List<Container> containers;

	public Buque(TerminalPortuaria terminal, EstadoDeBuque estado) {
		this.terminal = terminal;
		this.estado = estado;
		this.containers = new ArrayList<Container>();
	}

	public void setEstado(EstadoDeBuque estado) {
		this.estado = estado;
	}

	public int getDistanciaDeLaTerminal() {
		// TODO Terminar de implementar
		return 0;
	}
	
	public boolean isDisponible() {
		return this.disponible;
	}
	
	public void activarGPS() {
		estado.activarGPS(this);
	}
	
	public void recibirOrdenInicioDeTrabajo() throws Exception {
		if (this.getDistanciaDeLaTerminal() > 0 && !estado.isHabilitadoParaSalir()) {
			throw new Exception("La nave no está en la terminal y no puede empezar a trabajar");
		} else estado.cambiarFase(this);
	}
	
	public void depart() throws Exception {
		
		estado.setHabilitadoParaSalir(true);
		if (this.getDistanciaDeLaTerminal() == 0 && !estado.isHabilitadoParaSalir()) {
			throw new Exception("La nave no está en la terminal y por tanto no puede ser Depart");
		} else estado.cambiarFase(this);
	}
	
	public void avisarArriboInminente() {
		terminal.arriboInminenteDelBuque(this);
	}

	public void avisarQueSeArribo() {
		terminal.arriboElBuque(this);
		
	}
	
	public void avisarQueSePartio() {
		terminal.elBuqueHaPartido(this);
	}
	
	public void cargarContainer(Container c) {
		containers.add(c);
	}
	
	
	

}
