package CriterioTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Criterio.Condicion;
import Criterio.Or;
import Naviera.Viaje;

class OrTest {
	Condicion or1;
	Condicion or2;
	Condicion hoja1;
	Condicion hoja2;
	Viaje v1;

	@BeforeEach
	void setUp() throws Exception {
	hoja1 = mock(Condicion.class);
	hoja2 = mock(Condicion.class);
	or1 = new Or(hoja1, hoja2);
	or2 = new Or(hoja1, or1);
	v1 = mock(Viaje.class);
	}

	
	@Test
	void orConDosHojasTrue() {
		when(hoja1.chequear(v1)).thenReturn(true);
		when(hoja2.chequear(v1)).thenReturn(true);
		assertTrue(or1.chequear(v1));
	}
	
	@Test
	void orConDosHojasFalse() {
		when(hoja1.chequear(v1)).thenReturn(false);
		when(hoja2.chequear(v1)).thenReturn(false);
		assertFalse(or1.chequear(v1));
	}
	
	@Test 
	void orConHojaYOr() {
		// El primer lado del or da false, pero el segundo lado (Or con hoja 1 y 2, da true por la hoja 2)
		when(hoja1.chequear(v1)).thenReturn(false);
		when(hoja2.chequear(v1)).thenReturn(true);
		assertTrue(or2.chequear(v1));
	}
}