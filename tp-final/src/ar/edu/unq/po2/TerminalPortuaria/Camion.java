package ar.edu.unq.po2.TerminalPortuaria;

import java.time.LocalDateTime;
import java.util.List;

public class Camion {
	
	Conductor conductor;
	
	List<Container> containers;
	
	Turno turno;
	
	public void solicitarIngreso(TerminalPortuaria terminal) {
		//
	}
	
	public LocalDateTime horarioDelTurno() {
		//Retorna el horario del turno asignado a este camión.
	}
	
	public void descargar() {
		containers.clear();
	}
	
	public void cargar(Container c) {
		containers.add(c);
	}

}
