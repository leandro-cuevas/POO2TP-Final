package ar.edu.unq.poo2.TerminalGestionada;

public class Pesado implements Servicio {
	
	private double costo;
	
	public Pesado(double costo) {
		this.costo = costo;
	}

	public double getCostoDeServicio(int cantHoras) {
		return costo;
	} 

}
