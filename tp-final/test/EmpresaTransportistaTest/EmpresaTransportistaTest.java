package EmpresaTransportistaTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.mockito.Mock;

import EmpresaTransportista.Camion;
import EmpresaTransportista.Conductor;
import EmpresaTransportista.EmpresaTransportista;

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
		chofer2 = mock(Conductor.class);
		empresa = new EmpresaTransportista();
		
		//Defino las respuestas que voy a necesitar de los mocks
		when(camion1.isDisponible()).thenReturn(true);
		when(camion2.isDisponible()).thenReturn(false);
		when(chofer1.isDisponible()).thenReturn(false);
		when(chofer2.isDisponible()).thenReturn(true);
	}
	
	@Test
	void testAgregarChoferesyBusqueda() throws Exception{
		//Testea que la empresa lanza una excepción cuando no tiene choferes o no tiene
		//ninguno disponible. Testea los getters de chofer y también que devuelva al chofer
		//que está disponible cuando ya fue agregado a la empresa.
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
		//Testea que la empresa lanza una excepción cuando no tiene camiones o no tiene
		//ninguno disponible. Testea los getters de camión y también que devuelva al camión
		//que está disponible cuando ya fue agregado a la empresa.
		assertThrows (Exception.class,()->{empresa.camionDisponible();});
		empresa.addCamion(camion2);
		assertTrue(empresa.tieneCamion(camion2));
		assertFalse(empresa.tieneCamion(camion1));
		assertThrows (Exception.class,()->{empresa.camionDisponible();});
		empresa.addCamion(camion1);
		assertEquals(camion1, empresa.camionDisponible());
	}
	

}
