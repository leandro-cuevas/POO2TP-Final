package TerminalGestionada;

import java.time.LocalDateTime;

import Container.Container;

public class Cliente {

	public void avisarExportacion() {}

	public void avisarProntaLlegada(LocalDateTime fechaDeLlegada) {}
	
	public void listoPararRetirar(TerminalPortuaria terminal, Container carga) {
		//En este momento, el cliente es quien debe advertir a la terminal que chofer y que camion son los que van a ir a retirarlo. 
	}
}
