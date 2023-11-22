package Criterio;

import java.time.LocalDateTime;

import Naviera.Viaje;
import TerminalGestionada.TerminalPortuaria;

public class HojaFechaSalida implements Condicion{
	LocalDateTime fecha;
	TerminalPortuaria ptoSalida;
	
	public HojaFechaSalida(LocalDateTime fecha, TerminalPortuaria ptoSalida) {
		super();
		this.fecha = fecha;
		this.ptoSalida = ptoSalida;
	}
	
	@Override
	public boolean chequear(Viaje viaje)  {
		// Indica si el la fecha de arribo del viaje al ptoSalida ingresada es posterior a la de la condicion
		// Como el metodo fechaDeArriboAlPuerto tira error en caso de no estar, catchea y devuelve
		// falso en caso de no estar, ya que de todas maneras va a poder cumplir con el requisito de ser filtrada.
		if (viaje.contienePuertos(ptoSalida, null)) {
			LocalDateTime fechaViaje = viaje.fechaDeArriboAlPuerto(ptoSalida);
				return fechaViaje.isBefore(fecha) || fechaViaje.isEqual(fecha);
			} else {
				return false;
			}	
		}		
		
}
