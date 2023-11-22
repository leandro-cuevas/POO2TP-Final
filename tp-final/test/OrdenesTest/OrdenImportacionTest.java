package OrdenesTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Container.Container;
import EmpresaTransportista.Camion;
import EmpresaTransportista.Conductor;
import Naviera.Circuito;
import Naviera.Viaje;
import Orden.OrdenImportacion;
import Servicios.Electricidad;
import Servicios.Lavado;
import Servicios.Pesado;
import TerminalGestionada.Cliente;
import TerminalGestionada.TerminalPortuaria;
import TerminalGestionada.Turno;

class OrdenImportacionTest {
	
	//DOC
	
	Viaje viaje1;
	Viaje viaje2;
	Circuito circuito;
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
	Lavado lavado;
	Electricidad electro;
	Pesado pesado;
	Turno turno;
	
	//SUT 
	
	OrdenImportacion orden;
	
	@BeforeEach
	
	void setUp() throws Exception {
		
		//DOC
		
		viaje1 = mock(Viaje.class);
		viaje2 = mock(Viaje.class);
		circuito = mock(Circuito.class);
		container1 = mock(Container.class);
		container2 = mock(Container.class);
		chofer = mock(Conductor.class);
		camion = mock(Camion.class);
		terminalOrigen = mock(TerminalPortuaria.class);
		terminalDestino = mock(TerminalPortuaria.class);
		consignee1 = mock(Cliente.class);
		consignee2 = mock(Cliente.class);
		f1 = LocalDateTime.of(2023, 12, 20, 12, 0);
		f2 = LocalDateTime.of(2023, 12, 16, 12, 0);
		lavado = mock(Lavado.class);
		pesado = mock(Pesado.class);
		electro = mock(Electricidad.class);
		turno = mock(Turno.class);
		
		//SUT
		
		orden = new OrdenImportacion(viaje1, container1, terminalDestino, terminalOrigen, consignee1);
	}

	@Test
	void gettersYSetters() {
		orden.setTurno(turno);
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
		when(turno.getCamion()).thenReturn(camion);
		assertEquals(camion, orden.getCamion());
		//Testea el getter de chofer, debe devolver null antes de setearlo.
		assertEquals(null, orden.getChofer());
		orden.setChofer(chofer);
		when(turno.getConductor()).thenReturn(chofer);
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
	
	@Test
	void costosDeServicioYViaje() throws Exception {
		//Testea el costo de los servicios mockeados.
		when(viaje1.getCircuitoRecorrido()).thenReturn(circuito);
		when(circuito.getPrecioEntrePuertos(terminalOrigen, terminalDestino)).thenReturn(1000d);
		when(viaje1.fechaDeArriboAlPuerto(terminalDestino)).thenReturn(f1);
		orden.setFechaRetirada(f2);
		when(lavado.getCostoDeServicio(96)).thenReturn(200d);
		when(pesado.getCostoDeServicio(96)).thenReturn(300d);
		when(electro.getCostoDeServicio(96)).thenReturn(400d);
		orden.agregarServicio(pesado);
		orden.agregarServicio(electro);
		orden.agregarServicio(lavado);
		assertEquals(1900, orden.getCostosDeServicios());		
	}
		
}
