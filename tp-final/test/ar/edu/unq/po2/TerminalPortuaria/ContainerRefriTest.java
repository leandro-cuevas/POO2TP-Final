package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContainerRefriTest {
	TerminalPortuaria terminal;
	TerminalPortuaria terminal2;

	Container container;
	
	@BeforeEach
	void setUp() throws Exception {
		terminal  = mock(TerminalPortuaria.class);
		terminal2 = mock(TerminalPortuaria.class);
		container = new ContainerRefri(20, 40, 60, 200, 25);
	}

	@Test
	void constructorTest() {
		assertEquals(20, container.getAncho());
		assertEquals(40, container.getLargo());
		assertEquals(60, container.getAltura());
		assertEquals(200, container.getPeso());
		assertEquals(25, container.getConsumo());
	}
	
	@Test
	void metrosCubicosTest() {
		assertEquals(48000, container.getMetrosCubicos());
	}
	
	@Test 
	void setterDeDestino() {
		container.setDestino(terminal);
		assertTrue(container.finDelRecorrido(terminal));
		assertFalse(container.finDelRecorrido(terminal2));
	}
}
