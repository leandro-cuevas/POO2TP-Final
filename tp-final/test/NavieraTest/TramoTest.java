package NavieraTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Naviera.Tramo;
import TerminalGestionada.TerminalPortuaria;

class TramoTest {
	
	TerminalPortuaria bsAs;
	TerminalPortuaria lp;
	Tramo buenosAiresLaPlata;
	Tramo laPlataBuenosAires;

	@BeforeEach
	void setUp() throws Exception {
		bsAs = mock(TerminalPortuaria.class);
		lp = mock(TerminalPortuaria.class);
		buenosAiresLaPlata = new Tramo(bsAs, lp, 24, 5000, 60);
		laPlataBuenosAires = new Tramo(lp, bsAs, 24, 5000, 60);
	}

	@Test
	void constructorTest() {
		assertEquals(bsAs, buenosAiresLaPlata.getOrigen());
		assertEquals(lp, buenosAiresLaPlata.getDestino());
		assertEquals(24, buenosAiresLaPlata.getTiempoDeDuracionEnHoras());
		assertEquals(5000, buenosAiresLaPlata.getPrecio());
		assertEquals(60, buenosAiresLaPlata.getDistancia());
	}

}
