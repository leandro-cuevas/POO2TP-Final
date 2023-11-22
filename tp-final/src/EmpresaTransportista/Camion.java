package EmpresaTransportista;

import java.time.LocalDateTime;

import Container.Container;

public class Camion {
	
	private Container carga;
	private boolean disponible;

	public Camion() {
		disponible = true;
		carga = null;
	}
	
	public void descargar() {
		//Setea la carga en null para modelar la descarga
		carga = null;
	}
	
	public void cargar(Container container) {
		//Setea la carga con el container pasado por parámetro
		carga = container;
	}
			
	public void asignarTurno() {
		//Asigna un falso a disponible porque al tener turno el camión no está disponible.
		disponible = false;
	}
	
	public boolean isDisponible() {
		//Getter de disponible
		return disponible;
	}
	
	public Container getCarga() {
		//Getter de carga
		return carga;
	}
}
