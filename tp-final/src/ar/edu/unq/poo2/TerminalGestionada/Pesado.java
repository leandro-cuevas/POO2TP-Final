package ar.edu.unq.poo2.TerminalGestionada;

import ar.edu.unq.po2.TerminalPortuaria.Servicio;

public class Pesado implements Servicio {
	
	private double costo;
	
	public Pesado(double costo) {
		this.costo = costo;
	}

	public double getCostoDeServicio(int cantHoras) {
		return costo;
	} 

}
