package ar.edu.unq.po2.TerminalPortuaria;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.poo2.TerminalGestionada.Electricidad;
import ar.edu.unq.poo2.TerminalGestionada.Lavado;
import ar.edu.unq.poo2.TerminalGestionada.Pesado;

class OrdenExportacionTest {
	
	//DOC
	
	Viaje viaje1;
	Viaje viaje2;
	Container container1; 
	Container container2;
	Conductor chofer; 
	Camion camion;
	TerminalPortuaria terminalOrigen;
	TerminalPortuaria terminalDestino; 
	Cliente shipper1;
	Cliente shipper2;
	LocalDateTime f1;
	LocalDateTime f2;
	Lavado lavado;
	Electricidad electro;
	Pesado pesado;
		
	//SUT 
		
	OrdenExportacion orden;
		
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
		shipper1 = mock(Cliente.class);
		shipper2 = mock(Cliente.class);
		f1 = LocalDateTime.of(2023, 12, 20, 12, 0);
		f2 = LocalDateTime.of(2023, 12, 16, 12, 0);
		
		//SUT
		
		orden = new OrdenExportacion(viaje1, container1, chofer, camion, terminalDestino, terminalOrigen, shipper1);	
	}

	@Test
	void fechaDeArriboAlPuerto() {
		//Testea que delega bien el mensaje al viaje.
		when(viaje1.fechaDeArriboAlPuerto(terminalOrigen)).thenReturn(f2); 
		assertEquals(f2, orden.fechaSalida());
	}

	@Test
	void gettersYSetters() {
		//Testea los getters para atributos seteados en el constructor
		assertEquals(shipper1, orden.getCliente());
		assertEquals(container1, orden.getContainer());
		assertEquals(terminalDestino, orden.getDestino());
		//Testea el getter de fecha de retirada, debe devolver null antes de setearla.
		assertEquals(null, orden.fechaRetirada());
		orden.setFechaRetirada(f1);
		assertEquals(f1, orden.fechaRetirada());
		//Testea 
		assertEquals(chofer, orden.getChofer());
		//Testea el getter de cargaDepositada que se instancie en false, al llamar al setter se setea en true.
		assertFalse(orden.isCargaDepositada());
		orden.setCargaDepositada();
		assertTrue(orden.isCargaDepositada());
	}
	
	@Test
	void costosDeServicioYViaje() throws Exception {
		//Testea el costo de los servicios mockeados.
		when(viaje1.fechaDeArriboAlPuerto(terminalOrigen)).thenReturn(f1);
		orden.setFechaRetirada(f2);
		when(lavado.getCostoDeServicio(96)).thenReturn(200d);
		when(pesado.getCostoDeServicio(96)).thenReturn(300d);
		when(electro.getCostoDeServicio(96)).thenReturn(400d);
		orden.agregarServicio(pesado);
		orden.agregarServicio(electro);
		orden.agregarServicio(lavado);
		assertEquals(900, orden.getCostosDeServicios());
		verify(viaje1, never()).getCircuitoRecorrido().getPrecioEntrePuertos(terminalOrigen, terminalDestino);
	}
}
