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
		
		//Defino las respuestas que se van a usar de los mocks
		when(buque1.estaEnViaje()).thenReturn(true);
		when(buque2.estaEnViaje()).thenReturn(false);
		when(circuito1.contienePuertos(terminal1, terminal2)).thenReturn(false);
		when(circuito2.contienePuertos(terminal1, terminal2)).thenReturn(true);		
	}
	
	@Test
	void contienePuertos() {
		//Testea si está el circuito con los puertos dados
		naviera.agregarCircuito(circuito2);
		assertTrue(naviera.contienePuertos(terminal1, terminal2));
	}
	
	@Test
	void noContienePuertos() {
		//Testea que no naviera no contiene el circuito con los puertos dados 
		naviera.agregarCircuito(circuito1);
		assertFalse(naviera.contienePuertos(terminal1, terminal2));
	}
	
	@Test
	void testEstablecerViaje() throws Exception {
		//Testea que se asigne el buque que no está en viaje y que se valide que el circuito existe en la naviera.
		//Al crearse el viaje, se agrega a la lista de viajes y se testea que se haya creado como es esperado.
		naviera.agregarBuque(buque1);
		naviera.agregarBuque(buque2);
		naviera.agregarCircuito(circuito1);
		naviera.agregarCircuito(circuito2);
		naviera.establecerViaje(fecha, circuito1);
		Viaje viaje = naviera.getViajes().get(0);
		verify(buque2, times(1)).asignarViaje(viaje);
		assertEquals(buque2,viaje.getBuqueRecorrido());
		assertEquals(fecha, viaje.getFechaSalida());
		assertEquals(circuito1, viaje.getCircuitoRecorrido());
	}
	
	@Test
	void testEstablecerViajeLanzaExcepcion() throws Exception {
		//Testea que se asigne el buque que está disponible y que se valide que el circuito no existe en la naviera.
		//Por lo tanto el viaje no se crea y la lista de viajes es vacía.
		naviera.agregarBuque(buque1);
		naviera.agregarBuque(buque2);
		naviera.agregarCircuito(circuito2);
		assertThrows (Exception.class,()->{naviera.establecerViaje(fecha, circuito1);});
		assertTrue(naviera.getViajes().isEmpty());
	}

}
