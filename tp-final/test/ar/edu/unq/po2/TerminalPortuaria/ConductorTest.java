package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.mockito.Mock;

class ConductorTest {
	
	//DOC
	Camion camion;
	Turno turno;
	Container carga;
	
	//SUT
	Conductor chofer;
	
	@BeforeEach
	void setUp() throws Exception {
		camion = mock(Camion.class);
		turno = mock(Turno.class);
		carga = mock(Container.class);
		chofer = new Conductor();
		
		when(camion.getCarga()).thenReturn(carga);
	}
	
	@Test
	void testDisponibilidad() {
		assertTrue(chofer.isDisponible());
		chofer.asignarTurno(camion,  turno);
		assertFalse(chofer.isDisponible());
	}
	
	@Test
	void testGetters() {
		chofer.asignarTurno(camion,  turno);
		assertEquals(carga, chofer.getCarga());
		assertEquals(camion, chofer.getCamion());
		assertEquals(turno, chofer.getTurno());
	}

}
