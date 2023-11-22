package ar.edu.unq.poo2.TerminalGestionada;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.TerminalPortuaria;
import ar.edu.unq.po2.TerminalPortuaria.Tramo;

class CriterioMenorPrecioTest {
	// Declaramos variables que van a intercambiar mensajes.
	Criterio criterioMP;
	Circuito c1;
	Circuito c2;
	Circuito c3;
	Tramo t1;
	TerminalPortuaria terminal1;
	TerminalPortuaria terminal2;
	List<Circuito> circuitos;
	List<Tramo> tramos;
	@BeforeEach
	void setUp() throws Exception {
		// Instanciamos SUT
		criterioMP = new CriterioMenorPrecio();
		// Instanciamos DOC con Mocks.
		c1 = mock(Circuito.class);
		c2 = mock(Circuito.class);
		c3 = mock(Circuito.class);
		t1 = mock(Tramo.class);
		terminal1 = mock(TerminalPortuaria.class);
		terminal2 = mock(TerminalPortuaria.class);
		// Creamos Arrays de circuitos y tramos de mocks.
		circuitos = new ArrayList<Circuito>();
		tramos = new ArrayList<Tramo>();
		tramos.add(t1);
		
	}

	@Test
	void elMejorCon3Circuitos() throws Exception {
		//Agrego los circuitos
		circuitos.add(c1);
		circuitos.add(c2);
		circuitos.add(c3);
		// Seteo precios de cada circuito, ya que el comparador compara por precio.
		when(c1.getPrecioEntrePuertos(terminal1, terminal2)).thenReturn(100.0);
		when(c2.getPrecioEntrePuertos(terminal1, terminal2)).thenReturn(200.0);
		when(c3.getPrecioEntrePuertos(terminal1, terminal2)).thenReturn(240.0);
		// C1 es el tramo mas barato, pero el metodo elMejor devuelve una lista de Tramos, por lo que cuando
		// le pregunten el tramo queremos que nos devuelva eso mismo.
		when(c1.getListaDeTramos()).thenReturn(tramos);
		// La lista de tramos de C1 == elMejor circuito 
		assertEquals(tramos, criterioMP.elMejor(terminal1, terminal2, circuitos));
	}
	
	@Test
	void elMejorCon2CircuitosIguales() throws Exception {
		circuitos.add(c1);
		circuitos.add(c2);
		// Seteo precios de cada circuito, ya que el comparador del criterio compara por distancia.
		when(c1.getPrecioEntrePuertos(terminal1, terminal2)).thenReturn(800.0);
		when(c2.getPrecioEntrePuertos(terminal1, terminal2)).thenReturn(800.0);
		// C1 es el tramo mas barato, pero el metodo elMejor devuelve una lista de Tramos, por lo que cuando
		// le pregunten el tramo queremos que nos devuelva eso mismo.
		when(c1.getListaDeTramos()).thenReturn(tramos);
		// La lista de tramos de C1 == elMejor circuito 
		assertEquals(tramos, criterioMP.elMejor(terminal1, terminal2, circuitos));
	}
	
	@Test
	void elMejorCon1SoloCircuito() throws Exception {
		circuitos.add(c1);
		when(c1.getPrecioEntrePuertos(terminal1, terminal2)).thenReturn(100.0);
		when(c1.getListaDeTramos()).thenReturn(tramos);
		assertEquals(tramos, criterioMP.elMejor(terminal1, terminal2, circuitos));
	}
	
	@Test 
	void elMejorConListaDeCircuitosVacia() {
		// Testeamos que devuelve una lista vacia.
		assertEquals(criterioMP.elMejor(terminal1, terminal2, circuitos), circuitos);
	}
	
	@Test
	void elMejorConUnCircuitoQueFallaNoExplota() throws Exception {
		circuitos.add(c1);
		circuitos.add(c2);
		// Seteo precios de cada circuito, ya que el comparador del criterio compara por distancia.
		when(c1.getPrecioEntrePuertos(terminal1, terminal2)).thenReturn(800.0);
		when(c2.getPrecioEntrePuertos(terminal1, terminal2)).thenThrow(Exception.class);
		// C1 es el tramo mas barato, pero el metodo elMejor devuelve una lista de Tramos, por lo que cuando
		// le pregunten el tramo queremos que nos devuelva eso mismo.
		when(c1.getListaDeTramos()).thenReturn(tramos);
		// La lista de tramos de C1 == elMejor circuito 
		assertEquals(tramos, criterioMP.elMejor(terminal1, terminal2, circuitos));
	}
}
