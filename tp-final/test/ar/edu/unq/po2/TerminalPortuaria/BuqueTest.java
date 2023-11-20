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
	Container container;
	
	
	@BeforeEach
	void setUp() throws Exception {
		terminal = mock(TerminalGestionada.class);
		estado = mock(EstadoDeBuque.class);
		otroEstado = mock(EstadoDeBuque.class);
		container = mock(Container.class);
		buque = new Buque(terminal, estado);
	}

	@Test
	void estaEnViajeTest() {
		//Por defecto no lo está.
		assertFalse(buque.estaEnViaje());
		//Se asigna un viaje.
		buque.asignarViaje();
		//Ahora es verdadero.
		assertTrue(buque.estaEnViaje());
	}
	
	@Test
	void setEstadoYGpsTest() throws Exception {
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
		//Se cumplen las condiciones y funciona.
		buque.recibirOrdenInicioDeTrabajo();
		verify(estado, times(1)).cambiarFase(buque);
	}
	
	@Test
	void departTest() throws Exception{
		//Esta en la terminal y es igual a la ubicación del buque.
		when(terminal.getX()).thenReturn(0d);
		when(terminal.getY()).thenReturn(0d);
		//Ejecuto depart
		buque.depart();
		//Como funciona, se realiza una vez el cambio de fase del buque.
		verify(estado, times(1)).cambiarFase(buque);
	}
	
	@Test
	void avisosATerminal() throws Exception {
		//Aviso los distintos mensajes a terminarl.
		buque.avisarArriboInminente();
		buque.avisarQueSeArribo();
		buque.avisarQueSePartio();
		//Verifico que cada uno es invocado una vez.
		verify(terminal, times(1)).arriboInminenteDelBuque(buque);
		verify(terminal, times(1)).arriboElBuque(buque);
		verify(terminal, times(1)).elBuqueHaPartido(buque);
	}
	
	@Test
	void cargaContainerTest() {
		//Cargo
		buque.cargarContainer(container);
		//Descargo
		buque.descargarContainer(container);
		//No tengo containers cargados.
		assertEquals(0,buque.getContainers().size());
	}

}
