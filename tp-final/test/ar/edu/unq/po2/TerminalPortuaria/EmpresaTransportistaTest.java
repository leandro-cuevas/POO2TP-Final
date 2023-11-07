package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.mockito.Mock;

class EmpresaTransportistaTest {
	
	//DOC
	Camion camion1;
	Camion camion2;
	Conductor chofer1;
	Conductor chofer2;
		
	//SUT
	EmpresaTransportista empresa;
		
	@BeforeEach
	void setUp() throws Exception {
		camion1 = mock(Camion.class);
		camion2 = mock(Camion.class);
		chofer1 =  mock(Conductor.class);
		chofer2 = new Conductor();
		empresa = new EmpresaTransportista();

		when(camion1.isDisponible()).thenReturn(true);
		when(camion2.isDisponible()).thenReturn(false);
		when(chofer1.isDisponible()).thenReturn(false);
		when(chofer2.isDisponible()).thenReturn(true);
	}
	
	@Test
	void testAgregarChoferesyBusqueda() throws Exception{
		assertThrows (Exception.class,()->{empresa.choferDisponible();});
		empresa.addConductor(chofer1);
		assertTrue(empresa.tieneChofer(chofer1));
		assertFalse(empresa.tieneChofer(chofer2));
		assertThrows (Exception.class,()->{empresa.choferDisponible();});
		empresa.addConductor(chofer2);
		assertEquals(chofer2, empresa.choferDisponible());

	}
	
	@Test
	void testAgregarCamionesyBusqueda() throws Exception{
		assertThrows (Exception.class,()->{empresa.camionDisponible();});
		empresa.addCamion(camion2);
		assertTrue(empresa.tieneCamion(camion2));
		assertFalse(empresa.tieneCamion(camion2));
		assertThrows (Exception.class,()->{empresa.camionDisponible();});
		empresa.addCamion(camion1);
		assertEquals(camion1, empresa.camionDisponible());
	}
	

}
