package ar.edu.unq.poo2.TerminalGestionada;

import java.time.LocalDateTime;

import ar.edu.unq.po2.TerminalPortuaria.TerminalPortuaria;
import ar.edu.unq.po2.TerminalPortuaria.Viaje;

public class HojaFechaLlegada implements Condicion {
	LocalDateTime fecha;
	TerminalPortuaria ptoDestino;
	
	public HojaFechaLlegada(LocalDateTime fecha, TerminalPortuaria ptoDestino) {
		super();
		this.fecha = fecha;
		this.ptoDestino = ptoDestino;
	}
	
	@Override
	public boolean chequear(Viaje viaje) { 
	// Indica si el la fecha de arribo del viaje al ptoDestino ingresada es posterior a la de la condicion
	// Como el metodo fechaDeArriboAlPuerto tira error en caso de no estar, catchea y devuelve
	// falso en caso de no estar, ya que de todas maneras va a poder cumplir con el requisito de ser filtrada.
			try {
				LocalDateTime fechaViaje = viaje.fechaDeArriboAlPuerto(ptoDestino);
				return fechaViaje.isBefore(fecha) || fechaViaje.isEqual(fecha);
			} catch (Exception e) {
				return false;
			}	
		}
}
