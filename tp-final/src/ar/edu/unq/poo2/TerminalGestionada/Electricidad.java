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
	// Retorna el costo de servicio de electricidad. Solo el container refeer dice un costo distinto a 0.
	// Para aprovechar polimorfismo, se lo aplicamos a todos y
	// los que no son reefer facturan 0.
		return cantHoras * orden.getContainer().getConsumo() * costoPorKw;
	}
}
