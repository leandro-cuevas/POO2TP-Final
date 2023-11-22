package TerminalGestionadaTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Container.Container;
import EmpresaTransportista.Camion;
import EmpresaTransportista.Conductor;
import TerminalGestionada.Cliente;
import TerminalGestionada.Turno;

class TurnoTest {
	Turno t1;
	Turno t2;
	Conductor c1;
	Conductor c2;
	Camion cam1;
	Camion cam2;
	Cliente cliente;
	Cliente clienteFalso;
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
	clienteFalso = mock(Cliente.class);
	// SUT
	// Caso para turno de exportacion, donde ya contamos con el camion y el chofer
	t1 = new Turno(c1, cam1, fecha, carga);
	// Caso para turno de importacion, donde el cliente aun no nos informo quien es el camion y el chofer a designar
	t2 = new Turno(cliente, fecha, carga);
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
	void setterDeCamionYChofer() {
		t2.setCamion(cam2);
		t2.setChofer(c2);
		assertTrue(t2.esCamion(cam2));
		assertTrue(t2.esChofer(c2));
	}
	
	@Test
	void getterConductorYCamion() {
		t2.setCamion(cam2);
		t2.setChofer(c2);
		assertEquals(cam2, t2.getCamion());
		assertEquals(c2, t2.getConductor());
	}
}
