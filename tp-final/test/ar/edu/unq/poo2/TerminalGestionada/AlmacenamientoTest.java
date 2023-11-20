package ar.edu.unq.poo2.TerminalGestionada;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AlmacenamientoTest {
	Almacenamiento almacenamiento;
	double costoPorDia;
	
	@BeforeEach
	void setUp() throws Exception {
	// DOC y ord como Dummy.
	costoPorDia = 2d;
	// SUT
	almacenamiento = new Almacenamiento(costoPorDia);
	}

	@Test
	void costoDeServicio23horas() {
		// Como estuvo menos de 24 horas, el costo sera 0. Este caso no va a estar nunca, ya que el almacenamiento se aplica con 24 horas en adelante.
		assertEquals(0d, almacenamiento.getCostoDeServicio(23));
	}
	
	@Test
	void costoDeServicio24horas() {
		// Como estuvo 24 horas, el costo sera costo * 1 (cantidad de dias).
		assertEquals(2d, almacenamiento.getCostoDeServicio(24));
	}
	
	@Test
	void costoDeServicio49horas() {
		// Como estuvo 49 horas, el costo sera costo * 2 (cantidad de dias).
		assertEquals(4d, almacenamiento.getCostoDeServicio(49));
	}

}
