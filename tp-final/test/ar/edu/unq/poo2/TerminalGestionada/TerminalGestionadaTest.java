package ar.edu.unq.poo2.TerminalGestionada;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.Naviera;
import ar.edu.unq.po2.TerminalPortuaria.Viaje;

class TerminalGestionadaTest {
	//Tipamos con terminalGestionada para poder probar nuestros propios metodos
	TerminalGestionada terminal;
	Viaje v1;
	Viaje v2;
	Naviera n1;
	Naviera n2;
	Condicion queryMock;
//	Condicion orFeo;
//	Condicion andFeo;
//	Condicion hojaFechaSalida;
//	Condicion hojaFechaLlegada;
	List<Viaje> viaje1 = new ArrayList<Viaje>();
	List<Viaje> viaje2= new ArrayList<Viaje>();
	@BeforeEach
	void setUp() throws Exception {
	// Seteamos el SUT, sin criterio por el momento
	terminal = new TerminalGestionada(null);
	queryMock = mock(Condicion.class);
	n1 = mock(Naviera.class);
	n2 = mock(Naviera.class);	
	v1 = mock(Viaje.class);
	v2 = mock(Viaje.class);
	viaje1.add(v1);
	viaje2.add(v2);
	}

	@Test
	void testeoDeQueryMockeada() {
		// Registramos las navieras y ponemos el DOC para que responda como queremos
		terminal.registrarNaviera(n1);
		terminal.registrarNaviera(n2);
		when(n1.getViajes()).thenReturn(viaje1);
		when(n2.getViajes()).thenReturn(viaje2);
		// Cuando la query cheque el viaje v1, pasara el filtro, el v2 no
		when(queryMock.chequear(v1)).thenReturn(true);
		when(queryMock.chequear(v2)).thenReturn(false);
		// El retorno de filtrar viajes va a ser una lista con v1, igual al contenido de viaje1.
		assertEquals(viaje1, terminal.filtrarViajes(queryMock));
	}

}
