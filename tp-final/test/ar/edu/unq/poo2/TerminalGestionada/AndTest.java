package ar.edu.unq.poo2.TerminalGestionada;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Criterio.And;
import Criterio.Condicion;
import Naviera.Viaje;

class AndTest {
	Condicion and1;
	Condicion and2;
	Condicion hoja1;
	Condicion hoja2;
	Condicion hoja3;
	Viaje v1;
	
	@BeforeEach
	void setUp() throws Exception {
		hoja1 = mock(Condicion.class);
		hoja2 = mock(Condicion.class);
		hoja3 = mock(Condicion.class);
		and1 = new And(hoja1, hoja2);
		and2 = new And(hoja3, and1);
		v1 = mock(Viaje.class);
	}

	@Test
	void andConDosHojasTrue() {
		when(hoja1.chequear(v1)).thenReturn(true);
		when(hoja2.chequear(v1)).thenReturn(true);
		assertTrue(and1.chequear(v1));
	}
	
	@Test
	void andConUnaHojaFalse() {
		when(hoja1.chequear(v1)).thenReturn(true);
		when(hoja2.chequear(v1)).thenReturn(false);
		assertFalse(and1.chequear(v1));
	}
	
	@Test 
	void andConHojaYAnd() {
		// El primer lado del or da false, pero el segundo lado (Or con hoja 1 y 2, da true por la hoja 2)
		when(hoja1.chequear(v1)).thenReturn(true);
		when(hoja2.chequear(v1)).thenReturn(true);
		when(hoja3.chequear(v1)).thenReturn(false);
		assertFalse(and2.chequear(v1));
	}

}
