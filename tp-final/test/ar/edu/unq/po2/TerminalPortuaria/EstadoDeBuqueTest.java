package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import Buque.Buque;
import Buque.EstadoDeBuque;
import Buque.EstadoDeBuqueArrived;
import Buque.EstadoDeBuqueDepart;
import Buque.EstadoDeBuqueInbound;
import Buque.EstadoDeBuqueOutbound;
import Buque.EstadoDeBuqueWorking;

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
	void testOutboundMenorA50() throws Exception {
		//Cuando pida la distancia, está cerca.
		when(buque.getDistanciaDeLaTerminal()).thenReturn(15d);
		outbound.activarGPS(buque);
		verify(buque, times(1)).getDistanciaDeLaTerminal();
		// E indica el arribo inminente.
		verify(buque, times(1)).comunicarConLaTerminal();
		verify(buque, times(1)).setEstado(inbound);
	}
	
	@Test
	void testOutboundMayorA50() throws Exception {
		//Cuando pida la distancia, es demasiado lejos.
		when(buque.getDistanciaDeLaTerminal()).thenReturn(70d);
		outbound.comunicarConTerminal(buque);
		//Entonces llama a la condicion y no cambia de fase.
		outbound.activarGPS(buque);
		verify(buque, times(1)).getDistanciaDeLaTerminal();
		verify(buque, never()).comunicarConLaTerminal();
	}

	@Test
	void testOutboundComunicarTerminal() throws Exception {
		outbound.comunicarConTerminal(buque);
		verify(buque, times(1)).avisarQueSePartio();
	}
	
	@Test
	void testInboundMayorA0() throws Exception {
		//Cuando pida la distancia, no es igual a 0.
		when(buque.getDistanciaDeLaTerminal()).thenReturn(2d);
		//Entonces no cambia de fase.
		inbound.activarGPS(buque);
		verify(buque, times(1)).getDistanciaDeLaTerminal();
		verify(buque, never()).comunicarConLaTerminal();
	}
	
	@Test
	void testInboundIGUAL0() throws Exception {
		//Cuando pida la distancia, es igual a 00.
		when(buque.getDistanciaDeLaTerminal()).thenReturn(0d);
		//Entonces cambia de fase.
		inbound.activarGPS(buque);
		verify(buque, times(1)).getDistanciaDeLaTerminal();
		// E indica que se arribo.
		verify(buque, times(1)).comunicarConLaTerminal();
		verify(buque, times(1)).setEstado(arrived);
	}
	
	@Test
	void testInboundComunicarTerminal() throws Exception {
		inbound.comunicarConTerminal(buque);
		verify(buque, times(1)).avisarArriboInminente();
	}
	
	@Test
	void testArrived() throws Exception {
		//Activo gps.
		arrived.activarGPS(buque);
		//En este caso no se pide distancia con la terminal.
		verify(buque, never()).getDistanciaDeLaTerminal();
		verify(buque, never()).comunicarConLaTerminal();
	}
	
	@Test
	void testArrivedComunicarTerminal() throws Exception {
		arrived.comunicarConTerminal(buque);
		verify(buque, times(1)).avisarQueSeArribo();
	}
	
	@Test
	void testWorking() throws Exception {
		//Activo gps.
		working.activarGPS(buque);
		//Comunico con la terminal. No hace nada.
		working.comunicarConTerminal(buque);
		//No hace nada porque el cambio de estado depende de la terminal.
		verify(buque, never()).comunicarConLaTerminal();
		// La condicion para pasar de fase, no pregunta distancia
		verify(buque, never()).getDistanciaDeLaTerminal();
	}
	
	@Test
	void testDepart() throws Exception {
		//Cuando pida la distancia, ya está suficientemente lejos
		when(buque.getDistanciaDeLaTerminal()).thenReturn(2d);
		//Activo gps
		depart.activarGPS(buque);
		verify(buque, times(1)).getDistanciaDeLaTerminal();
		verify(buque, times(1)).setEstado(outbound);
		verify(buque, times(1)).comunicarConLaTerminal();
	}
	
	@Test
	void testDepartNoSeEjecuta() throws Exception {
		//Cuando pida la distancia, ya está suficientemente lejos
		when(buque.getDistanciaDeLaTerminal()).thenReturn(0d);
		//Activo gps
		depart.activarGPS(buque);
		verify(buque, times(1)).getDistanciaDeLaTerminal();
		verify(buque, never()).setEstado(outbound);
		verify(buque, never()).comunicarConLaTerminal();
	}
}
