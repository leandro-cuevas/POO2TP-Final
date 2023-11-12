package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.TerminalGestionada.TerminalGestionada;

class BuqueTest {

	TerminalGestionada terminal;
	EstadoDeBuque estado;
	EstadoDeBuque otroEstado;
	Buque buque;
	
	
	@BeforeEach
	void setUp() throws Exception {
		terminal = mock(TerminalGestionada.class);
		estado = mock(EstadoDeBuque.class);
		otroEstado = mock(EstadoDeBuque.class);
		buque = new Buque(terminal, estado);
	}

	@Test
	void estaEnViajeTest() {
		//Por defecto no lo está.
		assertFalse(buque.estaEnViaje());
	}
	
	@Test
	void setEstadoYGpsTest() {
		//Pruebo el setter.
		buque.setEstado(otroEstado);
		//Activo GPS.
		buque.activarGPS();
		//Debe llamarse una vez a activar GPS del estado del buque.
		verify(otroEstado, times(1)).activarGPS(buque);
	}
	
	@Test
	void distanciaDeTerminalTest() {
		//Armo el mock.
		when(terminal.getX()).thenReturn(3d);
		when(terminal.getY()).thenReturn(4d);
		//El método Point.distance me devuelve la hipotenuusa entre dos puntos.
		//El buque está en 0,0 y la terminal en 3,4 para que la hipotenusa me de 5.
		assertEquals(5, buque.getDistanciaDeLaTerminal());
	}
	
	@Test
	void recibirOrdenInicioDeTrabajoTest() throws Exception {
		//Armo el mock.
		when(terminal.getX()).thenReturn(3d);
		when(terminal.getY()).thenReturn(4d);
		//Como no está en la terminal no puede hacerlo.
		assertThrowsExactly(Exception.class,()->{buque.recibirOrdenInicioDeTrabajo();}, "La nave no está en la terminal o no puede empezar a trabajar") ;
		//Ahora está en la misma ubicación
		when(terminal.getX()).thenReturn(0d);
		when(terminal.getY()).thenReturn(0d);
		//No está habilitado para salir
		when(estado.isHabilitadoParaSalir()).thenReturn(false);
		//Se cumplen las condiciones y funciona.
		buque.recibirOrdenInicioDeTrabajo();
		//Ahora está habilitado para salir y por tanto no deberia funcionar.
		when(estado.isHabilitadoParaSalir()).thenReturn(true);	
		assertThrowsExactly(Exception.class,()->{buque.recibirOrdenInicioDeTrabajo();}, "La nave no está en la terminal o no puede empezar a trabajar") ;
//

		
	}

}
