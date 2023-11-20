package ar.edu.unq.po2.TerminalPortuaria;

import java.time.LocalDateTime;

public class Cliente {

	public void avisarExportacion() {}

	public void avisarProntaLlegada(LocalDateTime fechaDeLlegada) {}
	
	public void listoPararRetirar(TerminalPortuaria terminal, Container carga) {
		//En este momento, el cliente es quien debe advertir a la terminal que chofer y que camion son los que van a ir a retirarlo. 
	}
}
