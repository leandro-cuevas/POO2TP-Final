package EmpresaTransportistaTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.mockito.Mock;

import Container.Container;
import EmpresaTransportista.Camion;

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
	void testCargaYDescargaCamion() {
		//Testea la carga y descarga del camión, utilizando el getter
		camion.cargar(carga);
		assertEquals(carga,camion.getCarga());
		camion.descargar();
		assertEquals(null,camion.getCarga());
	}
	
	@Test
	void testDisponibilidadCamion() {
		//Testea la disponibilidad del camión antes y después de ser asignado un turno.
		assertTrue(camion.isDisponible());
		camion.asignarTurno();
		assertFalse(camion.isDisponible());
	}

}
