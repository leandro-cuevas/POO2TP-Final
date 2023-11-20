package ar.edu.unq.poo2.TerminalGestionada;

import ar.edu.unq.po2.TerminalPortuaria.Orden;
import ar.edu.unq.po2.TerminalPortuaria.Servicio;

public class Electricidad implements Servicio {
	
	private double costoPorKw;
	Orden orden;
	
	public Electricidad(double costoPorKw, Orden orden) {
		this.costoPorKw = costoPorKw;
		this.orden = orden;
	}

	public double getCostoDeServicio(int cantHoras) { 
		
		return cantHoras * orden.getContainer().getConsumo() * costoPorKw;
	}

}
