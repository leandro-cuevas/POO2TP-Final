package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

class EstadoDeBuqueTest {

	Buque buque;
	EstadoDeBuque outbound;
	EstadoDeBuque inbound;
	EstadoDeBuque arrived;
	EstadoDeBuque working;
	EstadoDeBuque depart;
	
	@BeforeEach
	void setUp() throws Exception {
		buque = mock(Buque.class);
		outbound = new EstadoDeBuqueOutbound();
		inbound = new EstadoDeBuqueInbound();
		arrived = new EstadoDeBuqueArrived();
		working = new EstadoDeBuqueWorking();
		depart = new  EstadoDeBuqueDepart();
		outbound.setSiguiente(inbound);
		inbound.setSiguiente(arrived);
		arrived.setSiguiente(working);
		working.setSiguiente(depart);
		depart.setSiguiente(outbound);
	}
	@Test
	void testOutboundMenorA50() {
		//Cuando pida la distancia, está cerca.
		when(buque.getDistanciaDeLaTerminal()).thenReturn(15d);
		outbound.activarGPS(buque);
		verify(buque, times(1)).getDistanciaDeLaTerminal();
		verify(buque, times(1)).setEstado(inbound);

	}
	
	@Test
	void testOutboundMayorA50() {
		//Cuando pida la distancia, es demasiado lejos.
		when(buque.getDistanciaDeLaTerminal()).thenReturn(70d);
		outbound.comunicarConTerminal(buque);
		//Entonces llama a la condicion y no cambia de fase.
		outbound.activarGPS(buque);
		verify(buque, times(1)).getDistanciaDeLaTerminal();
		verify(buque, never()).avisarArriboInminente();
	}

	@Test
	void testInboundMayora0() {
		//Cuando pida la distancia, no es igual a la terminal.
		when(buque.getDistanciaDeLaTerminal()).thenReturn(2d);
		//Entonces no cambia de fase.
		inbound.activarGPS(buque);
		verify(buque, times(1)).getDistanciaDeLaTerminal();
		verify(buque, never()).avisarArriboInminente();
	}
	
	@Test
	void testInboundIgualA0() {
		//Cuando pida la distancia, es igual a la terminal.
		when(buque.getDistanciaDeLaTerminal()).thenReturn(0d);
		//Entonces cambia de fase.
		inbound.activarGPS(buque);
		verify(buque, times(1)).getDistanciaDeLaTerminal();
		verify(buque, times(1)).avisarArriboInminente();

	}
	
	@Test
	void testArrived() throws Exception {
		//Activo gps.
		arrived.activarGPS(buque);
		//En este caso no se pide distancia con la terminal.
		verify(buque, never()).getDistanciaDeLaTerminal();
		//Pero sí se indica que se arribó.
		verify(buque, times(1)).avisarQueSeArribo();
	}
	
	@Test
	void testWorking() throws Exception {
		//Activo gps.
		working.activarGPS(buque);
		//Comunico con la terminal. No hace nada.
		working.comunicarConTerminal(buque);
		//No hace nada porque el cambio de estado depende de la terminal.
		verify(buque, never()).getDistanciaDeLaTerminal();
		verify(buque, never()).avisarQueSeArribo();
	}
	
	@Test
	void testDepart() {
		//Cuando pida la distancia, ya está suficientemente lejos
		when(buque.getDistanciaDeLaTerminal()).thenReturn(2d);
		//Activo gps
		depart.activarGPS(buque);
		verify(buque, times(1)).getDistanciaDeLaTerminal();
		verify(buque, times(1)).avisarQueSePartio();

	}
	
	@Test
	void testHabilitacionParaSalir() {
		//Por defecto, ninguno esta habilitado para salir.
		assertFalse(inbound.isHabilitadoParaSalir()); 
		assertFalse(outbound.isHabilitadoParaSalir()); 
		assertFalse(arrived.isHabilitadoParaSalir()); 
		assertFalse(working.isHabilitadoParaSalir());
		assertFalse(depart.isHabilitadoParaSalir()); 
		//Seteo que pueden salir.
		inbound.setHabilitadoParaSalir(true);
		outbound.setHabilitadoParaSalir(true);
		arrived.setHabilitadoParaSalir(true);
		working.setHabilitadoParaSalir(true);
		depart.setHabilitadoParaSalir(true);
		//Ahora todos estan habilitados.
		assertTrue(inbound.isHabilitadoParaSalir()); 
		assertTrue(outbound.isHabilitadoParaSalir()); 
		assertTrue(arrived.isHabilitadoParaSalir()); 
		assertTrue(working.isHabilitadoParaSalir());
		assertTrue(depart.isHabilitadoParaSalir()); 
	}

}
