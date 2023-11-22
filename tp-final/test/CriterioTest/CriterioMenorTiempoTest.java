package CriterioTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Criterio.Criterio;
import Criterio.CriterioMenorTiempo;
import Naviera.Circuito;
import Naviera.Tramo;
import TerminalGestionada.TerminalPortuaria;

class CriterioMenorTiempoTest {
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
				criterioMP = new CriterioMenorTiempo();
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
			void elMejorCon3CircuitosDistintos() {
				circuitos.add(c1);
				circuitos.add(c2);
				circuitos.add(c3);
				// Seteo precios de cada circuito, ya que el comparador del criterio compara por tiempo.
				when(c1.getTiempoEntrePuertos(terminal1, terminal2)).thenReturn(800);
				when(c2.getTiempoEntrePuertos(terminal1, terminal2)).thenReturn(900);
				when(c3.getTiempoEntrePuertos(terminal1, terminal2)).thenReturn(2040);
				// C1 es el tramo mas barato, pero el metodo elMejor devuelve una lista de Tramos, por lo que cuando
				// le pregunten el tramo queremos que nos devuelva eso mismo.
				when(c1.getListaDeTramos()).thenReturn(tramos);
				// La lista de tramos de C1 == elMejor circuito 
				assertEquals(tramos, criterioMP.elMejor(terminal1, terminal2, circuitos));
			}
			
			@Test
			void elMejorCon2CircuitosIguales() {
				circuitos.add(c1);
				circuitos.add(c2);
				circuitos.add(c3);
				// Seteo precios de cada circuito, ya que el comparador del criterio compara por distancia.
				when(c1.getTiempoEntrePuertos(terminal1, terminal2)).thenReturn(800);
				when(c2.getTiempoEntrePuertos(terminal1, terminal2)).thenReturn(800);
				when(c3.getTiempoEntrePuertos(terminal1, terminal2)).thenReturn(900);
				// C1 es el tramo mas rapido, pero el metodo elMejor devuelve una lista de Tramos, por lo que cuando
				// le pregunten el tramo queremos que nos devuelva eso mismo.
				when(c1.getListaDeTramos()).thenReturn(tramos);
				// La lista de tramos de C1 == elMejor circuito 
				assertEquals(tramos, criterioMP.elMejor(terminal1, terminal2, circuitos));
			}
			
			@Test
			void elMejorCon1SoloCircuito() {
				circuitos.add(c1);
				when(c1.getTiempoEntrePuertos(terminal1, terminal2)).thenReturn(200);
				when(c1.getListaDeTramos()).thenReturn(tramos);
				assertEquals(tramos, criterioMP.elMejor(terminal1, terminal2, circuitos));
			}
			
			@Test 
			void elMejorConListaDeCircuitosVacia() {
				// Testeamos que devuelve una lista vacia.
				assertEquals(criterioMP.elMejor(terminal1, terminal2, circuitos), circuitos);
			}
}
