package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TramoTest {
	
	TerminalPortuaria origen;
	TerminalPortuaria destino;
	Tramo buenosAiresLaPlata;

	@BeforeEach
	void setUp() throws Exception {
		origen = mock(TerminalPortuaria.class);
		destino = mock(TerminalPortuaria.class);
		buenosAiresLaPlata = new Tramo(origen, destino, 24, 5000, 60);
	}

	@Test
	void constructorTest() {
		assertEquals(origen, buenosAiresLaPlata.getOrigen());
		assertEquals(destino, buenosAiresLaPlata.getDestino());
		assertEquals(24, buenosAiresLaPlata.getTiempoDeDuracionEnHoras());
		assertEquals(5000, buenosAiresLaPlata.getPrecio());
		assertEquals(60, buenosAiresLaPlata.getDistancia());
	}

}
