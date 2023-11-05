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
		viaje = new Viaje(fecha, circuito, buque);
		
		when(circuito.getTiempoEntrePuertos(terminal1, terminal2)).thenReturn(500);
		when(fecha.plus(500,ChronoUnit.HOURS)).thenReturn(fecha2);
		when(circuito.puertoOrigen()).thenReturn(terminal1);
		when(circuito.contienePuertos(terminal1, terminal2)).thenReturn(true);
		
	}
	
	@Test
	void testGetterFecha() {
		assertEquals(fecha, viaje.getFechaSalida());
	}
	
	@Test
	void testGetterBuque() {
		assertEquals(buque, viaje.getBuqueRecorrido());
	}

	@Test
	void testGetterCircuito() {
		assertEquals(circuito, viaje.getCircuitoRecorrido());
	}
	
	@Test
	void testFechaDeArribo() throws Exception {
		assertEquals(fecha2, viaje.fechaDeArriboAlPuerto(terminal2));
	}
}

