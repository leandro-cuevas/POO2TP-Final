package ar.edu.unq.poo2.TerminalGestionada;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.Container;
import ar.edu.unq.po2.TerminalPortuaria.Orden;

class LavadoTest {
	Orden ord;
	int costoPequenio;
	int costoGrande;
	Container container;
	// SUT
	Lavado lavado;
	
	@BeforeEach
	void setUp() throws Exception {
		ord           = mock(Orden.class);
		costoPequenio = 30;
		costoGrande   = 50;
		container     = mock(Container.class);
		
		lavado = new Lavado(costoPequenio, costoGrande, ord);
	}

	@Test
	void testeoContainerCostoPequenio() {
		// Verifica que cuando el container no excede los 70 metros cubicos, le cobra el costo pequenio
		when(ord.getContainer()).thenReturn(container);
		when(container.getMetrosCubicos()).thenReturn(69);
		// Ademas, el costo es indistinto de las horas que estuvo en la terminal.
		assertEquals(30, lavado.getCostoDeServicio(20));
	}
	
	@Test
	void testeoContainer70M3() {
		// Verifica que cuando el container no excede los 70 metros cubicos, le cobra el costo pequenio
		when(ord.getContainer()).thenReturn(container);
		when(container.getMetrosCubicos()).thenReturn(70);
		// Ademas, el costo es indistinto de las horas que estuvo en la terminal.
		assertEquals(30, lavado.getCostoDeServicio(20));
	}
	
	@Test
	void testeoContainerCostoGrande() {
		// Verifica que cuando el container no excede los 70 metros cubicos, le cobra el costo pequenio
		when(ord.getContainer()).thenReturn(container);
		when(container.getMetrosCubicos()).thenReturn(71);
		// Ademas, el costo es indistinto de las horas que estuvo en la terminal.
		assertEquals(50, lavado.getCostoDeServicio(200));
	}
}
