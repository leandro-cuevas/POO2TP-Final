package ar.edu.unq.poo2.TerminalGestionada;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComparadorCircuitoDistanciaTest {
	ComparadorCircuito comparador;
	Circuito circ1;
	Circuito circ2;
	@BeforeEach
	void setUp() throws Exception {
		comparador = new ComparadorCircuitoDistancia();
		circ1 = mock(Circuito.class);
		circ2 = mock(Circuito.class);
	}

	@Test
	void elMenorCircuito() {
		when(circ1.getDistancia()).thenReturn(80.0);
		when(circ2.getDistancia()).thenReturn(70.0);
		assertEquals(1, comparador.comparar(circ1, circ2));	
	} // El numero positivo indica que el primer elemento es mayor al segundo elemento. 

	@Test
	void circuitosIguales() {
		when(circ1.getDistancia()).thenReturn(80.0);
		when(circ2.getDistancia()).thenReturn(80.0);
		assertEquals(0, comparador.comparar(circ1, circ2));	
	} // El numero 0 indica que el primer elemento es "igual" al segundo. "Igual" ya que no indica que los objetos son 
	// los mismos, sino que tienen el mismo valor en el atributo que se esta comparando

	@Test
	void elSegundoEsMayor() {
		when(circ1.getDistancia()).thenReturn(80.0);
		when(circ2.getDistancia()).thenReturn(90.0);
		assertEquals(-1, comparador.comparar(circ1, circ2));	
	}
}

