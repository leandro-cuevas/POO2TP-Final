package ar.edu.unq.poo2.TerminalGestionada;

import ar.edu.unq.po2.TerminalPortuaria.Container;
import ar.edu.unq.po2.TerminalPortuaria.Orden;
import ar.edu.unq.po2.TerminalPortuaria.Servicio;

public class Lavado implements Servicio {
	
	private int costoPorContainerPequenio;
	private int costoPorContainerGrande;
	private Orden orden;

	public Lavado(int costoPorContainerPequenio, int costoPorContainerGrande, Orden orden) {
		this.costoPorContainerPequenio = costoPorContainerPequenio;
		this.costoPorContainerGrande = costoPorContainerGrande;
		this.orden = orden;
	}

	public double getCostoDeServicio(int cantHoras) {
		if (orden.getContainer().getMetrosCubicos() > 70) {
			return costoPorContainerGrande;
		}
			else {
				return costoPorContainerPequenio;
			}
		}
}
