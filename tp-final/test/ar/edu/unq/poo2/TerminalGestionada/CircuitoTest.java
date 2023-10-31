package ar.edu.unq.poo2.TerminalGestionada;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.TerminalPortuaria;
import ar.edu.unq.po2.TerminalPortuaria.Tramo;

class CircuitoTest {
	
	TerminalPortuaria laPlata;
	TerminalPortuaria quilmes;
	TerminalPortuaria retiro;
	TerminalPortuaria tigre;
	Tramo laPlataQuilmes;
	Tramo quilmesRetiro;
	Tramo retiroTigre;
	Circuito circuito;
	

	@BeforeEach
	void setUp() throws Exception {
		laPlata = mock(TerminalPortuaria.class);
		quilmes = mock(TerminalPortuaria.class);
		retiro = mock(TerminalPortuaria.class);
		tigre = mock(TerminalPortuaria.class);
		laPlataQuilmes = mock(Tramo.class);
		quilmesRetiro = mock(Tramo.class);
		retiroTigre = mock(Tramo.class);
		circuito = new Circuito();
		
		//Cuando pida el origen y el destino, respondera como indica su nombre.
		when(laPlataQuilmes.getOrigen()).thenReturn(laPlata);
		when(laPlataQuilmes.getDestino()).thenReturn(quilmes);
		when(quilmesRetiro.getOrigen()).thenReturn(quilmes);
		when(quilmesRetiro.getDestino()).thenReturn(retiro);
		when(retiroTigre.getOrigen()).thenReturn(retiro);
		when(retiroTigre.getDestino()).thenReturn(tigre);
	}

	@Test
	void agregarFuncionaCorrectamente() throws Exception {

		//Como está vacía la lista de tramos, el primero no tiene problema.
		circuito.agregarTramo(laPlataQuilmes);
		//Para el segundo y el tercero, debe coincidir origen y destino.
		circuito.agregarTramo(quilmesRetiro);
		circuito.agregarTramo(retiroTigre);
	}
	
	@Test
	void agregarLanzaExceptionCorrectamente() throws Exception{
		try {
		//Como está vacía la lista de tramos, el primero no tiene problema.
		circuito.agregarTramo(quilmesRetiro);
		//Para el segundo, debe lanzar la excepction.
		circuito.agregarTramo(laPlataQuilmes);
		} catch (Exception e) {
			//Agarro la exception y la verifico.
			assertEquals("El tramo agregado rompe el invariante de representación", e.getMessage());
		}
	}

}
