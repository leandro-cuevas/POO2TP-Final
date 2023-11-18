package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContainerDryTest {

	Container container;
	Cliente duenio;
	
	@BeforeEach
	void setUp() throws Exception {
		duenio = mock(Cliente.class);
		container = new ContainerDry(20, 40, 60, 200, duenio);
	}

	@Test
	void constructorTest() {
		assertEquals(20, container.getAncho());
		assertEquals(40, container.getLargo());
		assertEquals(60, container.getAltura());
		assertEquals(200, container.getPeso());
		assertEquals(0, container.getConsumo());
	}
	
	@Test
	void metrosCubicosTest() {
		assertEquals(48000, container.getMetrosCubicos());
	}
}
