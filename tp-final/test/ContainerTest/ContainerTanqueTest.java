package ContainerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Container.Container;
import Container.ContainerTanque;
import TerminalGestionada.TerminalPortuaria;

class ContainerTanqueTest {
	TerminalPortuaria terminal;
	TerminalPortuaria terminal2;
	
	Container container;
	
	@BeforeEach
	void setUp() throws Exception {
		terminal  = mock(TerminalPortuaria.class);
		terminal2 = mock(TerminalPortuaria.class);
		container = new ContainerTanque(20, 40, 60, 200);
	}

	@Test
	void constructorTest() {
		assertEquals(0, container.getConsumo());
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
