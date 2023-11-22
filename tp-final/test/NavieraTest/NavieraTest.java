package NavieraTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;

import Buque.Buque;
import Naviera.Circuito;
import Naviera.Naviera;
import Naviera.Viaje;
import TerminalGestionada.TerminalPortuaria;

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
	
	@Test 
	void enCuantoLlegaFalso() {
		// Seteamos el DOC para el circuito
		naviera.agregarCircuito(circuito1);
		when(circuito1.contienePuertos(terminal1, terminal2)).thenReturn(false);
		// Como no va a contener los circuitos, devuelve 0.
		assertEquals(0d, naviera.enCuantoLlega(terminal1, terminal2));
	}
	
	@Test 
	void enCuantoLlegaCorrecto() {
		// Seteamos el DOC para el circuito
		naviera.agregarCircuito(circuito1);
		naviera.agregarCircuito(circuito2);
		when(circuito1.contienePuertos(terminal1, terminal2)).thenReturn(true);
		when(circuito1.getTiempoEntrePuertos(terminal1, terminal2)).thenReturn(50);
		when(circuito2.contienePuertos(terminal1, terminal2)).thenReturn(true);
		when(circuito2.getTiempoEntrePuertos(terminal1, terminal2)).thenReturn(100);
		// Como el menor tiempo entre los puertos es del circuito 1, devuelve 50.
		assertEquals(50d, naviera.enCuantoLlega(terminal1, terminal2));
	}
	
	@Test
	void getterCircuitos() {
		List<Circuito> listaVacia = new ArrayList<Circuito>();
		assertEquals(listaVacia, naviera.getCircuitos());
		listaVacia.add(circuito1);
		naviera.agregarCircuito(circuito1);
		assertEquals(listaVacia, naviera.getCircuitos());
	}
}
