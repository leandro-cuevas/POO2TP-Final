package ar.edu.unq.po2.TerminalPortuaria;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.poo2.TerminalGestionada.TerminalGestionada;

public class Buque {
		
	private EstadoDeBuque estado;
	
	private boolean estaEnViaje;
	
	private List<Container> containers;
	
	private Point coordenada;
	
	private TerminalGestionada terminal;
	
	private Viaje viajeAsignado;

	public Buque(TerminalGestionada terminal, EstadoDeBuque estado) {
		this.estaEnViaje = false;
		this.estado = estado;
		this.containers = new ArrayList<Container>();
		this.coordenada = new Point(0, 0);
		this.viajeAsignado = null;
		this.terminal = terminal;
	}

	public void setEstado(EstadoDeBuque estado) {
		this.estado = estado;
	}

	public double getDistanciaDeLaTerminal() {
		return Point.distance(coordenada.getX(), coordenada.getY(), terminal.getX(), terminal.getY());
	}
	
	public boolean estaEnViaje() {
		return estaEnViaje;
	}
	
	public void activarGPS() {
		estado.activarGPS(this);
	}
	
	public void recibirOrdenInicioDeTrabajo() throws Exception {
		if (this.getDistanciaDeLaTerminal() > 0 || estado.isHabilitadoParaSalir()) {
			throw new Exception("La nave no está en la terminal o no puede empezar a trabajar");
		} else estado.cambiarFase(this);
	}
	
	public void depart() throws Exception {
		
		estado.setHabilitadoParaSalir(true);
		if (this.getDistanciaDeLaTerminal() == 0 && estado.isHabilitadoParaSalir()) {
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
	
	public void descargarContainer(Container c) {
		containers.remove(c);
	}
	
	public void asignarViaje() {
		this.estaEnViaje = true;
	}
	
	public List<Container> containersParaDescargar(TerminalPortuaria terminal){
		return containers.stream().filter(c->c.finDelRecorrido(terminal)).toList();
	}
}
