package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class EstadoDeBuqueTest {

	Buque buque;
	EstadoDeBuqueOutbound outbound;
	EstadoDeBuqueInbound inbound;
	EstadoDeBuqueArrived arrived;
	EstadoDeBuqueWorking working;
	EstadoDeBuqueDepart depart;
	
	@BeforeEach
	void setUp() throws Exception {
		buque = mock(Buque.class);
		outbound = new EstadoDeBuqueOutbound(null);
		inbound = new EstadoDeBuqueInbound(null);
		arrived = new EstadoDeBuqueArrived(null);
		working = new EstadoDeBuqueWorking(null);
		depart = new  EstadoDeBuqueDepart(null);
		outbound.setSiguiente(inbound);
		inbound.setSiguiente(arrived);
		arrived.setSiguiente(working);
		working.setSiguiente(depart);
		depart.setSiguiente(outbound);

	}

	@Test
	void testOutboundMayorA50() {
		//Cuando pida la distancia, es demasiado lejos.
		when(buque.getDistanciaDeLaTerminal()).thenReturn(70);
		outbound.comunicarConTerminal(buque);
		//Entonces llama a la condicion y no cambia de fase.
		outbound.activarGPS(buque);
		verify(buque, times(1)).getDistanciaDeLaTerminal();
		verify(buque, never()).avisarArriboInminente();
	}
	
	@Test
	void testOutboundMenorA50() {
		//Cuando pida la distancia, está cerca.
		when(buque.getDistanciaDeLaTerminal()).thenReturn(15);
		//Entonces llama al a condicion y cambia de fase.
		outbound.activarGPS(buque);
		verify(buque, times(1)).getDistanciaDeLaTerminal();
		verify(buque, times(1)).avisarArriboInminente();

	}
	
	@Test
	void testInboundMayora0() {
		//Cuando pida la distancia, no es igual a la terminal.
		when(buque.getDistanciaDeLaTerminal()).thenReturn(2);
		//Entonces no cambia de fase.
		inbound.activarGPS(buque);
		verify(buque, times(1)).getDistanciaDeLaTerminal();
		verify(buque, never()).avisarArriboInminente();
	}
	
	@Test
	void testInboundIgualA0() {
		//Cuando pida la distancia, es igual a la terminal.
		when(buque.getDistanciaDeLaTerminal()).thenReturn(0);
		//Entonces cambia de fase.
		inbound.activarGPS(buque);
		verify(buque, times(1)).getDistanciaDeLaTerminal();
		verify(buque, times(1)).avisarArriboInminente();

	}
	
	@Test
	void testArrivedNoHaceNada() {
		
	}
	

}
