package ar.edu.unq.poo2.TerminalGestionada;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.Container;
import ar.edu.unq.po2.TerminalPortuaria.Orden;

class ElectricidadTest {
	Electricidad electricidad;
	double costoPorKW;
	Orden ord;
	
	Container container;
	Container containerReefer;
	
	@BeforeEach
	void setUp() throws Exception {
	ord        		= mock(Orden.class);
	costoPorKW 		= 20d;
	container       = mock(Container.class);
	containerReefer = mock(Container.class);
	// SUT
	electricidad = new Electricidad(costoPorKW, ord);
	// Planteamos respuestas esperadas para el mock orden.
	when(container.getConsumo()).thenReturn(0);
	when(containerReefer.getConsumo()).thenReturn(10);
	}

	@Test
	void costoDeElectricidad20HorasReefer() {
	// En caso de ser un container reefer, quien puede responder su costo distinto de 0, el costo se calcula correctamente
	when(ord.getContainer()).thenReturn(containerReefer);
	assertEquals(4000, electricidad.getCostoDeServicio(20));
	}
	
	@Test
	void costoDeElectricidad20HorasNoRefeer() {
	// En caso de no ser un container reefer, el costo del container sera 0, por lo que el calculo dara siempre 0
	when(ord.getContainer()).thenReturn(container);
	assertEquals(0, electricidad.getCostoDeServicio(20));
	}

}
