package CriterioTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Criterio.Condicion;
import Criterio.HojaFechaSalida;
import Naviera.Viaje;
import TerminalGestionada.TerminalPortuaria;

class HojaFechaSalidaTest {
	// Tipamos como condicion ya que es como se va a ver para el resto del entorno
	LocalDateTime fecha1;
	LocalDateTime fecha2;
	LocalDateTime fecha3;
	Condicion hf;
	Viaje v1;
	TerminalPortuaria tp1;
	@BeforeEach
	void setUp() throws Exception {
	fecha1 = LocalDateTime.of(2023, 11, 5, 12, 30);
	fecha2 = LocalDateTime.of(2023, 8, 2, 12, 30);
	fecha3 = LocalDateTime.of(2024, 11, 5, 12,30);
	tp1 = mock(TerminalPortuaria.class);
	hf = new HojaFechaSalida(fecha1, tp1);
	v1 = mock(Viaje.class);
	}

	@Test
	void laFechaIngresadaEsMayorALaDelViaje() {
		when(v1.contienePuertos(tp1, null)).thenReturn(true);
		when(v1.fechaDeArriboAlPuerto(tp1)).thenReturn(fecha2);
		assertTrue(hf.chequear(v1));
	}
	
	@Test
	void laFechaIngresadaEsIGUALALaDelViaje() {
		when(v1.contienePuertos(tp1, null)).thenReturn(true);
		when(v1.fechaDeArriboAlPuerto(tp1)).thenReturn(fecha1);
		assertTrue(hf.chequear(v1));
	}
	
	@Test
	void laFechaEsMayorQueryFALSE() {
		when(v1.contienePuertos(tp1, null)).thenReturn(true);
		when(v1.fechaDeArriboAlPuerto(tp1)).thenReturn(fecha3);
		assertFalse(hf.chequear(v1));
	}
	
	@Test
	void catcheaCorrectamente() {
		when(v1.contienePuertos(tp1, null)).thenReturn(false);
		// En caso de no poder catchear la exception, explota el test.
		assertFalse(hf.chequear(v1));
		
	}

}
