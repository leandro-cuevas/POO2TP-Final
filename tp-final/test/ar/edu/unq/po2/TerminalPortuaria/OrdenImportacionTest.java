package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrdenImportacionTest {
	
	//DOC
	
	Viaje viaje;
	Container container; 
	Conductor chofer; 
	Camion camion;
	TerminalPortuaria terminalDestino; 
	Cliente consignee;
	LocalDateTime f1;
	LocalDateTime f2;
	
	//SUT 
	
	OrdenImportacion orden;
	
	@BeforeEach
	
	void setUp() throws Exception {
		
		//DOC
		
		viaje = mock(Viaje.class);
		container = mock(Container.class);
		chofer = mock(Conductor.class);
		camion = mock(Camion.class);
		terminalDestino = mock(TerminalPortuaria.class);
		consignee = mock(Cliente.class);
		f1 = LocalDateTime.of(2023, 12, 20, 12, 0);
		f2 = LocalDateTime.of(2023, 8, 20, 12, 0);
		
		//SUT
		
		orden = new OrdenImportacion(viaje, container, terminalDestino, consignee);
	}

	@Test
	void getterConsignee() {
		assertEquals(consignee, orden.getCliente());
	}
	
	@Test
	void setterCamion() {
	//Testea el setter de camion, en principio la orden de exportacion no lo posee
		assertEquals(null, orden.getCamion());
		orden.setCamion(camion);
		assertEquals(camion, orden.getCamion());
	}
	
	@Test
	void () {
	

}
