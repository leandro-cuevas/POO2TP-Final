package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.mockito.Mock;
import ar.edu.unq.poo2.TerminalGestionada.Circuito;
import org.junit.jupiter.api.Test;

class ViajeTest {
	
	//DOC
	Buque buque;
	Circuito circuito;
	TerminalPortuaria terminal1;
	TerminalPortuaria terminal2;
	LocalDateTime fecha;
	LocalDateTime fecha2;
	
	//SUT
	Viaje viaje;
	
	@BeforeEach
	void setUp() throws Exception {
		buque = mock(Buque.class);
		circuito = mock(Circuito.class);
		terminal1 = mock(TerminalPortuaria.class);
		terminal2 = mock(TerminalPortuaria.class);
		fecha = mock(LocalDateTime.class);
		fecha2 = mock(LocalDateTime.class);
		//Instancio el SUT con mis mocks
		viaje = new Viaje(fecha, circuito, buque);
		
		//Defino las respuestas que se van a usar de los mocks
		when(circuito.getTiempoEntrePuertos(terminal1, terminal2)).thenReturn(500);
		when(fecha.plus(500,ChronoUnit.HOURS)).thenReturn(fecha2);
		when(circuito.puertoOrigen()).thenReturn(terminal1);
		when(circuito.contienePuertos(terminal1, terminal2)).thenReturn(true);
		
	}
	
	@Test
	void testGetterFecha() {
		//Testea la correcta instanciación de la clase y el getter de fecha
		assertEquals(fecha, viaje.getFechaSalida());
	}
	
	@Test
	void testGetterBuque() {
		//Testea la correcta instanciación de la clase y el getter de buque
		assertEquals(buque, viaje.getBuqueRecorrido());
	}

	@Test
	void testGetterCircuito() {
		//Testea la correcta instanciación de la clase y el getter de circuito
		assertEquals(circuito, viaje.getCircuitoRecorrido());
	}
	
	@Test
	void testFechaDeArribo() throws Exception {
		//Testea la fecha de arribo y que el circuito esté recibiendo el mensaje correspondiente
		assertEquals(fecha2, viaje.fechaDeArriboAlPuerto(terminal2));
		verify(circuito, times(1)).getTiempoEntrePuertos(terminal1, terminal2);
	}
	
	@Test
	void testContieneCircuitos() {
		//Testea que direcciona bien el mensaje a su circuito de variable de instancia.
		when(circuito.contienePuertos(terminal1, terminal2)).thenReturn(false);
		when(circuito.contienePuertos(terminal2, terminal1)).thenReturn(true);
		assertTrue(viaje.contienePuertos(terminal2, terminal1));
		assertFalse(viaje.contienePuertos(terminal1, terminal2));
	}
}

