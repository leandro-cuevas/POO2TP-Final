package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Container.Container;
import Container.ContainerDry;
import TerminalGestionada.TerminalPortuaria;

class ContainerDryTest {
	TerminalPortuaria terminal;
	TerminalPortuaria terminal2;
	
	Container container;
	
	
	@BeforeEach
	void setUp() throws Exception {
		terminal  = mock(TerminalPortuaria.class);
		terminal2 = mock(TerminalPortuaria.class);
		container = new ContainerDry(20, 40, 60, 200);
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
