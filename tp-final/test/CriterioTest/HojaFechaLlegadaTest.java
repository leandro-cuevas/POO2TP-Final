package CriterioTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Criterio.Condicion;
import Criterio.HojaFechaLlegada;
import Naviera.Viaje;
import TerminalGestionada.TerminalPortuaria;

class HojaFechaLlegadaTest {
	// Tipamos como condicion ya que es como se va a ver para el resto del entorno
	LocalDateTime fecha1;
	LocalDateTime fecha2;
	LocalDateTime fecha3;
	Condicion hf;
	Viaje v1;
	TerminalPortuaria tp1;
	TerminalPortuaria tp2;
	@BeforeEach
	void setUp() throws Exception {
	fecha1 = LocalDateTime.of(2023, 9, 5, 12, 30);
	fecha2 = LocalDateTime.of(2023, 7, 2, 12, 30);
	fecha3 = LocalDateTime.of(2025, 1, 5, 12,30);
	tp1 = mock(TerminalPortuaria.class);
	tp2 = mock(TerminalPortuaria.class);
	hf = new HojaFechaLlegada(fecha1, tp1);
	v1 = mock(Viaje.class);
	}

	@Test
	void laFechaIngresadaEsMayorALaDelViaje() throws Exception {
		when(v1.contienePuertos(null, tp1)).thenReturn(true);
		when(v1.fechaDeArriboAlPuerto(tp1)).thenReturn(fecha2);
		assertTrue(hf.chequear(v1));
	}
	
	@Test
	void laFechaIngresadaEsIGUALALaDelViaje() throws Exception {
		when(v1.contienePuertos(null, tp1)).thenReturn(true);
		when(v1.fechaDeArriboAlPuerto(tp1)).thenReturn(fecha1);
		assertTrue(hf.chequear(v1));
	}
	
	@Test
	void laFechaEsMayorQueryFALSE() {
		when(v1.contienePuertos(null, tp1)).thenReturn(true);
		when(v1.fechaDeArriboAlPuerto(tp1)).thenReturn(fecha3);
		assertFalse(hf.chequear(v1));
	}
	
	@Test
	void noContieneElPuertoDaFALSE() {
		when(v1.contienePuertos(null, tp1)).thenReturn(false);
		assertFalse(hf.chequear(v1));
	}
}
