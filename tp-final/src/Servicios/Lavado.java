package Servicios;

import Container.Container;
import Orden.Orden;

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
