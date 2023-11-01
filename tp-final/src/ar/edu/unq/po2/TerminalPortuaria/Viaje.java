package ar.edu.unq.po2.TerminalPortuaria;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import ar.edu.unq.poo2.TerminalGestionada.Circuito;

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
			return fechaSalida;
		}
		
		public Circuito getCircuitoRecorrido() {
			return circuitoRecorrido;
		}
		
		public Buque getBuqueRecorrido() {
			return buqueRecorrido;
		}
		
		//Calcula la fecha de llegada a un puerto pasado por parámetro
		public LocalDateTime fechaDeArriboAlPuerto(TerminalPortuaria p) throws Exception {
			
	        int horasViaje = circuitoRecorrido.getTiempoEntrePuertos(circuitoRecorrido.puertoOrigen(), p);
				        
	        return fechaSalida.plus(horasViaje, ChronoUnit.HOURS);
		}

}
