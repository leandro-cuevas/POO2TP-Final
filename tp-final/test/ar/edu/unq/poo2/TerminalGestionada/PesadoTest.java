package ar.edu.unq.poo2.TerminalGestionada;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Orden.Orden;
import Servicios.Pesado;

class PesadoTest {
	Pesado pesado;
	double costo;
	
	@BeforeEach
	void setUp() throws Exception {
	// DOC
	costo  = 20d;
	// SUT
	pesado = new Pesado(costo);

	}

	@Test
	void costoEsIndistintoDeLasHoras() {
	// Testea que los costos del pesado sean indistintos de la cantidad de horas por parametro.	
		assertEquals(20d, pesado.getCostoDeServicio(20));
		assertEquals(20d, pesado.getCostoDeServicio(40));
		assertEquals(20d, pesado.getCostoDeServicio(0));
	}

}
