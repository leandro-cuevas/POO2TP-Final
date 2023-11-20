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

	public double getCostoDeServicio(int cantHoras) {
		//Tiene un tiempo de tolerancia de un dia.
		int dias = cantHoras / 24;
		return costoEstadia * dias;
	}

}
