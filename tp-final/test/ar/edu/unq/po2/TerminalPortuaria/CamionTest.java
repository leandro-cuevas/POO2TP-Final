package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.mockito.Mock;

class CamionTest {
	
	//DOC
	Container carga;
	
	//SUT
	Camion camion;
	
	@BeforeEach
	void setUp() throws Exception {
		carga = mock(Container.class);
		camion = new Camion();
	}
	
	@Test
	void testCargaCamion() {
		camion.cargar(carga);
		assertEquals(carga,camion.getCarga());
	}
	
	@Test
	void testDescargaCamion() {
		camion.cargar(carga);
		camion.descargar();
		assertEquals(null,camion.getCarga());
	}
	
	@Test
	void testDisponibilidadCamion() {
		assertTrue(camion.isDisponible());
		camion.asignarTurno();
		assertFalse(camion.isDisponible());
	}
	
	

}
