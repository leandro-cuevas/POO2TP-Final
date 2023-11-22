package EmpresaTransportistaTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.mockito.Mock;

import Container.Container;
import EmpresaTransportista.Camion;
import EmpresaTransportista.Conductor;
import TerminalGestionada.Turno;

class ConductorTest {
	
	//DOC
	Camion camion;
	Camion camion2;
	Turno turno;
	Container carga;
	Turno t1;
	
	//SUT
	Conductor chofer;
	
	@BeforeEach
	void setUp() throws Exception {
		camion  = mock(Camion.class);
		turno   = mock(Turno.class);
		carga   = mock(Container.class);
		chofer  = new Conductor();
		camion2 = mock(Camion.class);
		t1      = mock(Turno.class);
		//Define la respuesta que se va a necesitar para el mock
		when(camion.getCarga()).thenReturn(carga);
	}
	
	@Test
	void testDisponibilidad() {
		//Testea que el chofer esté disponible antes de asignarle un turno, 
		//y luego de asignarlo testea que no esté disponible.
		assertTrue(chofer.isDisponible());
		chofer.asignarTurno(camion,  turno);
		assertFalse(chofer.isDisponible());
	}
	
	@Test
	void testGetters() {
		//Testea los getters del chofer, habiéndole asignado un turno.
		chofer.asignarTurno(camion,  turno);
		assertEquals(carga, chofer.getCarga());
		assertEquals(camion, chofer.getCamion());
		assertEquals(turno, chofer.getTurno());
	}
	
	@Test
	void setterDeCamion() {
		assertEquals(null, chofer.getCamion());
		chofer.setCamionManejado(camion2);
		assertEquals(camion2, chofer.getCamion());
		chofer.setCamionManejado(camion);
		assertEquals(camion, chofer.getCamion());
	}
	
	@Test
	void setterDeTurno() { 
		//Testea que en principio el chofer no tiene un turno, el setter se lo adjudica y lo puede devolver correctamente.
		assertEquals(null, chofer.getTurno());
		chofer.setTurno(t1);
		assertEquals(t1, chofer.getTurno());
	}
}
