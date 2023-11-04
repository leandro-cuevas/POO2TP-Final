package ar.edu.unq.poo2.TerminalGestionada;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.TerminalPortuaria;
import ar.edu.unq.po2.TerminalPortuaria.Viaje;

class HojaPuertoDestinoTest {
	Condicion hf;
	Viaje v1;
	TerminalPortuaria tp1;
	TerminalPortuaria tp2;
	@BeforeEach
	void setUp() throws Exception {
	tp1 = mock(TerminalPortuaria.class);
	tp2 = mock(TerminalPortuaria.class);
	hf = new HojaPuertoDestino(tp1, tp2);
	v1 = mock(Viaje.class);
	}

	@Test
	void contieneLosPuertos() {
	when(v1.contienePuertos(tp1, tp2)).thenReturn(true);
	assertTrue(hf.chequear(v1));	
	}
	
	@Test
	void noContieneLosPuertos() {
	when(v1.contienePuertos(tp1, tp2)).thenReturn(false);
	assertFalse(hf.chequear(v1));	
	}

}
