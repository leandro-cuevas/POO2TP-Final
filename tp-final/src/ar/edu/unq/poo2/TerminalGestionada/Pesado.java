package ar.edu.unq.poo2.TerminalGestionada;

import ar.edu.unq.po2.TerminalPortuaria.Orden;
import ar.edu.unq.po2.TerminalPortuaria.Servicio;

public class Pesado implements Servicio {
	
	private double costo;
	private Orden orden;
	
	public Pesado(double costo, Orden orden) {
		this.costo = costo;
		this.orden = orden;
	}

	public double getCostoDeServicio() {
		return costo;
	} 

}
