package Criterio;

import Naviera.Viaje;
import TerminalGestionada.TerminalPortuaria;

public class HojaPuertoDestino implements Condicion {
	TerminalPortuaria ptoSalida;
	TerminalPortuaria ptoDestino;
	
	public HojaPuertoDestino(TerminalPortuaria ptoSalida, TerminalPortuaria ptoDestino) {
		this.ptoSalida = ptoSalida;
		this.ptoDestino = ptoDestino;
	}

	@Override
	public boolean chequear(Viaje viaje) {
		return viaje.contienePuertos(ptoSalida, ptoDestino);
	}
}
