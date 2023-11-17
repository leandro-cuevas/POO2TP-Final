package ar.edu.unq.poo2.TerminalGestionada;

import ar.edu.unq.po2.TerminalPortuaria.Container;
import ar.edu.unq.po2.TerminalPortuaria.Servicio;

public class Lavado implements Servicio {
	
	private int costoPorContainerPequenio;
	private int costoPorContainerGrande;
	private Container c;

	public Lavado(int costoPorContainerPequenio, int costoPorContainerGrande, Container c) {
		this.costoPorContainerPequenio = costoPorContainerPequenio;
		this.costoPorContainerGrande = costoPorContainerGrande;
		this.c = c;
	}

	public double getCostoDeServicio() {
		if (c.getMetrosCubicos() > 70) {
			return costoPorContainerPequenio;
		}
			else {
				return costoPorContainerGrande;
			}
		}
}
