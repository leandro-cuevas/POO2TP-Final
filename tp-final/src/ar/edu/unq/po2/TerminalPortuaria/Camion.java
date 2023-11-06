package ar.edu.unq.po2.TerminalPortuaria;

import java.time.LocalDateTime;

public class Camion {
	
	Container carga;
		
	private boolean disponible;
	
	public Camion() {
		disponible = true;
		carga = null;
	}
	
	public void solicitarIngreso(TerminalPortuaria terminal) {
		//
	}
	
	public LocalDateTime horarioDelTurno() {
		return null;//Retorna el horario del turno asignado a este camión.
	}
	
	public void descargar() {
		carga = null;
	}
	
	public void cargar(Container container) {
		carga = container;
	}
	
	public void asignarTurno() {
		disponible = false;
	}
	
	public boolean isDisponible() {
		return disponible;
	}
}
