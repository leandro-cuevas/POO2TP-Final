package Naviera;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import Buque.Buque;
import TerminalGestionada.TerminalPortuaria;

public class Viaje {

		private LocalDateTime fechaSalida;
		private Circuito circuitoRecorrido;
		private Buque buqueRecorrido;
		
		public Viaje(LocalDateTime fechaSalida, Circuito circuitoRecorrido, Buque buqueRecorrido) {
			this.fechaSalida = fechaSalida;
			this.circuitoRecorrido = circuitoRecorrido;
			this.buqueRecorrido = buqueRecorrido;
		}
		
		public LocalDateTime getFechaSalida() {
			//Devuelve la fecha de salida de este viaje.
			return fechaSalida;
		}
		
		public Circuito getCircuitoRecorrido() {
			//Devuelve el circuito que recorre este viaje
			return circuitoRecorrido;
		}
		
		public Buque getBuqueRecorrido() {
			//Devuelve el buque que recorre este viaje.
			return buqueRecorrido;
		}
		
		public LocalDateTime fechaDeArriboAlPuerto(TerminalPortuaria p) {
			//Calcula la fecha de llegada a un puerto pasado por parámetro
			
	        int horasViaje = circuitoRecorrido.getTiempoEntrePuertos(circuitoRecorrido.puertoOrigen(), p);
				        
	        return fechaSalida.plus(horasViaje, ChronoUnit.HOURS);
		}
		
		public boolean contienePuertos (TerminalPortuaria origen, TerminalPortuaria destino){
			return circuitoRecorrido.contienePuertos(origen, destino);
		}
}
