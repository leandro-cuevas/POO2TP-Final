package ar.edu.unq.poo2.TerminalGestionada;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import Orden.Orden;

public class Almacenamiento implements Servicio{

	double costoEstadia;
	
	public Almacenamiento(double costoPorEstadia) {
		this.costoEstadia = costoPorEstadia;
	}

	public double getCostoDeServicio(int cantHoras) {
		//Tiene un tiempo de tolerancia de un dia.
		int dias = cantHoras / 24;
		return costoEstadia * dias;
	}

}
