package ar.edu.unq.poo2.TerminalGestionada;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import ar.edu.unq.po2.TerminalPortuaria.Orden;
import ar.edu.unq.po2.TerminalPortuaria.Servicio;

public class Almacenamiento implements Servicio{

	double costoEstadia;
	Orden orden;
	
	public Almacenamiento(double costoPorEstadia, Orden orden) {
		this.costoEstadia = costoPorEstadia;
		this.orden = orden;
	}

	public double getCostoDeServicio() {
		//Tiene un tiempo de tolerancia de un dia.
		LocalDateTime fechaTolerada = orden.fechaLlegada().plus(1, ChronoUnit.DAYS);
		
		if (orden.fechaRetirada().isAfter(fechaTolerada)) {
			return costoEstadia;
		} else {
			return 0;
		}
	}

}
