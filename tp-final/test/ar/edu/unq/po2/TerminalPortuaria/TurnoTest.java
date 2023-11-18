package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TurnoTest {
	Turno t1;
	Conductor c1;
	Conductor c2;
	Camion cam1;
	Camion cam2;
	Cliente cliente;
	Container carga;
	LocalDateTime fecha;
	
	@BeforeEach
	void setUp() throws Exception {
	// DUMMYS
	c1 = mock(Conductor.class);
	c2 = mock(Conductor.class);
	cam1 = mock(Camion.class);
	cam2 = mock(Camion.class);
	carga = mock(Container.class);
	fecha = LocalDateTime.of(2020, 12, 20, 10, 00);
	cliente = mock(Cliente.class);
	// SUT
	t1 = new Turno(c1, cam1, cliente, fecha, carga);
	}

	@Test
	void consultaSiEsChoferTRUE() {
		assertTrue(t1.esChofer(c1));
	}
	
	@Test
	void consultaSiEsChoferFALSE() {
		assertFalse(t1.esChofer(c2));
	}
	
	@Test
	void consultaSiEsCamionTRUE() {
		assertTrue(t1.esCamion(cam1));
	}
	
	@Test
	void consultaSiEsCamionFALSE() {
		assertFalse(t1.esCamion(cam2));
	}
	
	@Test
	void getterFecha() {
		assertEquals(t1.getDiaYHora(), fecha);
	}
	
	@Test
	void getterCamion() {
		assertEquals(t1.getCamion(), cam1);
	}

}
