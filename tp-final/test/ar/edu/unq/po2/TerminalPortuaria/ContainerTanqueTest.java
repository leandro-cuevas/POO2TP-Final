package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContainerTanqueTest {

	Container container;
	
	@BeforeEach
	void setUp() throws Exception {
		container = new ContainerTanque(20, 40, 60, 200);
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
