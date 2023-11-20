package ar.edu.unq.po2.TerminalPortuaria;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrdenExportacionTest {
	Viaje viaje;
	Container container; 
	Conductor chofer; 
	Camion camion;
	TerminalPortuaria terminalDestino;
	TerminalPortuaria terminalOrigen;
	Cliente shipper;
	LocalDateTime f1;
	LocalDateTime f2;
	//SUT
	OrdenExportacion orden;
	@BeforeEach
	void setUp() throws Exception {
		viaje = mock(Viaje.class);
		container = mock(Container.class);
		chofer = mock(Conductor.class);
		camion = mock(Camion.class);
		terminalDestino = mock(TerminalPortuaria.class);
		terminalOrigen = mock(TerminalPortuaria.class);
		shipper = mock(Cliente.class);
		f1 = LocalDateTime.of(2023, 12, 20, 12, 0);
		f2 = LocalDateTime.of(2023, 8, 20, 12, 0);
		//SUT
		orden = new OrdenExportacion(viaje, container, chofer, camion, terminalDestino, terminalOrigen, shipper);	
	}

	@Test
	void fechaDeArriboAlPuerto() {
		//Testea que delega bien el mensaje al viaje.
		when(viaje.fechaDeArriboAlPuerto(terminalDestino)).thenReturn(f1); // Esta fecha no la deberia devolver, ya que consulta por la destino, no por la origen
		when(viaje.fechaDeArriboAlPuerto(terminalOrigen)).thenReturn(f2); 
		assertEquals(f2, orden.fechaSalida());
	}

	@Test
	void getterDeCliente() {
		assertEquals(shipper, orden.getCliente());
	}
}
