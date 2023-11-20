package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrdenImportacionTest {
	
	//DOC
	
	Viaje viaje1;
	Viaje viaje2;
	Container container1; 
	Container container2;
	Conductor chofer; 
	Camion camion;
	TerminalPortuaria terminalOrigen;
	TerminalPortuaria terminalDestino; 
	Cliente consignee1;
	Cliente consignee2;
	LocalDateTime f1;
	LocalDateTime f2;
	
	//SUT 
	
	OrdenImportacion orden;
	
	@BeforeEach
	
	void setUp() throws Exception {
		
		//DOC
		
		viaje1 = mock(Viaje.class);
		viaje2 = mock(Viaje.class);
		container1 = mock(Container.class);
		container2 = mock(Container.class);
		chofer = mock(Conductor.class);
		camion = mock(Camion.class);
		terminalOrigen = mock(TerminalPortuaria.class);
		terminalDestino = mock(TerminalPortuaria.class);
		consignee1 = mock(Cliente.class);
		consignee2 = mock(Cliente.class);
		f1 = LocalDateTime.of(2023, 12, 20, 12, 0);
		f2 = LocalDateTime.of(2023, 8, 20, 12, 0);
		
		//SUT
		
		orden = new OrdenImportacion(viaje1, container1, terminalDestino, consignee1, terminalOrigen);
	}

	@Test
	void gettersYSetters() {
		//Testea los getters para atributos seteados en el constructor
		assertEquals(consignee1, orden.getCliente());
		assertEquals(container1, orden.getContainer());
		assertEquals(terminalDestino, orden.getDestino());
		//Testea el getter de fecha de retirada, debe devolver null antes de setearla.
		assertEquals(null, orden.fechaRetirada());
		orden.setFechaRetirada(f1);
		assertEquals(f1, orden.fechaRetirada());
		//Testea el getter de camion, debe devolver null antes de setearlo.
		assertEquals(null, orden.getCamion());
		orden.setCamion(camion);
		assertEquals(camion, orden.getCamion());
		//Testea el getter de chofer, debe devolver null antes de setearlo.
		assertEquals(null, orden.getChofer());
		orden.setChofer(chofer);
		assertEquals(chofer, orden.getChofer());
		//Testea el getter de cargaDepositada que se instancie en false, al llamar al setter se setea en true.
		assertFalse(orden.isCargaDepositada());
		orden.setCargaDepositada();
		assertTrue(orden.isCargaDepositada());
	}
	
	@Test
	void evaluadores() {
		//Testea los métodos que evalúan la igualdad de los objetos.
		assertTrue(orden.tieneMismoViaje(viaje1));
		assertFalse(orden.tieneMismoViaje(viaje2));
		assertTrue(orden.esDeCliente(consignee1));
		assertFalse(orden.esDeCliente(consignee2));
		assertTrue(orden.esContainer(container1));
		assertFalse(orden.esContainer(container2));		
	}
		
}
