package Buque;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Container.Container;
import Naviera.Viaje;
import TerminalGestionada.TerminalGestionada;
import TerminalGestionada.TerminalPortuaria;

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
		//Setter de estado.
		this.estado = estado;
	}

	public Viaje getViaje() {
		return viajeAsignado;
	}

	public double getDistanciaDeLaTerminal() {
		//Retorna la distancia entre las coordenadas del buque respecto de su terminal.
		return Point.distance(coordenada.getX(), coordenada.getY(), terminal.getX(), terminal.getY());
	}
	
	public boolean estaEnViaje() {
		//Retorna si está o no en un viaje.
		return estaEnViaje;
	}
	
	public void activarGPS() throws Exception {
		//Invoca al gps del estado.
		estado.activarGPS(this);
	}
	
	public void recibirOrdenInicioDeTrabajo() throws Exception {
		//Orden de inicio de trabajo que debería ser ejecutada por la terminal.
		//Para que ocurra debe estar dentro de la terminal, es decir, su distancia debe ser 0.
		if (this.getDistanciaDeLaTerminal() > 0) {
			throw new Exception("La nave no está en la terminal o no puede empezar a trabajar");
		} else estado.cambiarFase(this);
	}
	
	public void depart() {
		//Orden de depart que debería ser ejecutada por la terminal.
		estado.cambiarFase(this);
	}
	
	public void avisarArriboInminente() {
		//Avisa a la terminal del arribo inminente del buque.
		//Este método es invocado por el estado que es el que se encarga de gestionar este mensaje.
		terminal.arriboInminenteDelBuque(this);
	}

	public void avisarQueSeArribo() throws Exception{
		//Avisa a la terminal que el buque arribó
		//Este método es invocado por el estado que es el que se encarga de gestionar este mensaje.
		terminal.arriboElBuque(this);		
	}
	
	public void avisarQueSePartio() {
		//Avisa a la terminal que el buque partió.
		//Este método es invocado por el estado que es el que se encarga de gestionar este mensaje.
		terminal.elBuqueHaPartido(this);
	}
	
	public void cargarContainer(Container c) {
		//Carga containers.
		getContainers().add(c);
	}
		
	public void descargarContainer(Container c) {
		//Descarga containers.
		containers.remove(c);
	}
	
	public void asignarViaje(Viaje viaje) {
		//Una vez que se asigna un viaje, el buque pasa a estar en viaje.
		//Es una manera de poder decidir que buque está o no disponible para un nuevo viaje.
		this.estaEnViaje   = true;
		this.viajeAsignado = viaje;
	}

	
	public List<Container> containersParaDescargar(TerminalPortuaria terminal){
		return containers.stream().filter(c->c.finDelRecorrido(terminal)).toList();
	}


	public List<Container> getContainers() {
		//Retorna la lista de los containers de este buque.
		return containers;

	}

	public void comunicarConLaTerminal() throws Exception {
		// Avisa a la terminal que ha cambiado de fase
		estado.comunicarConTerminal(this);
	}
}
