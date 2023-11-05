package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.mockito.Mock;
import ar.edu.unq.poo2.TerminalGestionada.Circuito;

import org.junit.jupiter.api.Test;

class NavieraTest {
	
	//DOC
	Buque buque1;
	Buque buque2;
	Circuito circuito1;
	Circuito circuito2;
	TerminalPortuaria terminal1;
	TerminalPortuaria terminal2;
	LocalDateTime fecha;
	
	//SUT
	Naviera naviera;

	@BeforeEach
	void setUp() throws Exception {
		buque1 = mock(Buque.class);
		buque2 = mock(Buque.class);
		circuito1 = mock(Circuito.class);
		circuito2 = mock(Circuito.class);
		terminal1 = mock(TerminalPortuaria.class);
		terminal2 = mock(TerminalPortuaria.class);
		fecha = mock(LocalDateTime.class);
		naviera = new Naviera();
		
		when(buque1.estaEnViaje()).thenReturn(true);
		when(buque2.estaEnViaje()).thenReturn(false);
		when(circuito1.contienePuertos(terminal1, terminal2)).thenReturn(false);
		when(circuito2.contienePuertos(terminal1, terminal2)).thenReturn(true);		
	}
	
	@Test
	void contienePuertos() {
		naviera.agregarCircuito(circuito2);
		assertTrue(naviera.contienePuertos(terminal1, terminal2));
	}
	
	@Test
	void noContienePuertos() {
		naviera.agregarCircuito(circuito1);
		assertFalse(naviera.contienePuertos(terminal1, terminal2));
	}
	
	@Test
	void testEstablecerViaje() throws Exception {
		naviera.agregarBuque(buque1);
		naviera.agregarBuque(buque2);
		naviera.agregarCircuito(circuito1);
		naviera.agregarCircuito(circuito2);
		naviera.establecerViaje(fecha, circuito1);
		verify(buque2, times(1)).asignarViaje();
		assertFalse(naviera.getViajes().isEmpty());
	}

}
