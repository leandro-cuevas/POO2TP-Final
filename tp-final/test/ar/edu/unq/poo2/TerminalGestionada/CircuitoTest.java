package ar.edu.unq.poo2.TerminalGestionada;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Naviera.Circuito;
import Naviera.Tramo;
import TerminalGestionada.TerminalPortuaria;

class CircuitoTest {
	
	TerminalPortuaria laPlata;
	TerminalPortuaria quilmes;
	TerminalPortuaria retiro;
	TerminalPortuaria tigre;
	TerminalPortuaria parana;
	Tramo laPlataQuilmes;
	Tramo quilmesRetiro;
	Tramo retiroTigre;
	Tramo tigreParana;
	Circuito circuito;
	

	@BeforeEach
	void setUp() throws Exception {
		laPlata = mock(TerminalPortuaria.class);
		quilmes = mock(TerminalPortuaria.class);
		retiro = mock(TerminalPortuaria.class);
		tigre = mock(TerminalPortuaria.class);
		parana = mock(TerminalPortuaria.class);
		laPlataQuilmes = mock(Tramo.class);
		quilmesRetiro = mock(Tramo.class);
		retiroTigre = mock(Tramo.class);
		tigreParana = mock(Tramo.class);
		circuito = new Circuito();
		
		//Cuando pida el origen y el destino, respondera como indica su nombre.
		//Se le adjudican precios, distancias y tiempos.
		when(laPlataQuilmes.getOrigen()).thenReturn(laPlata);
		when(laPlataQuilmes.getDestino()).thenReturn(quilmes);
		when(laPlataQuilmes.getTiempoDeDuracionEnHoras()).thenReturn(10);
		when(laPlataQuilmes.getDistancia()).thenReturn(200d);
		when(laPlataQuilmes.getPrecio()).thenReturn(1000d);
		when(quilmesRetiro.getOrigen()).thenReturn(quilmes);
		when(quilmesRetiro.getDestino()).thenReturn(retiro);
		when(quilmesRetiro.getTiempoDeDuracionEnHoras()).thenReturn(12);
		when(quilmesRetiro.getDistancia()).thenReturn(300d);
		when(quilmesRetiro.getPrecio()).thenReturn(1000d);
		when(retiroTigre.getOrigen()).thenReturn(retiro);
		when(retiroTigre.getDestino()).thenReturn(tigre);
		when(retiroTigre.getTiempoDeDuracionEnHoras()).thenReturn(15);
		when(retiroTigre.getDistancia()).thenReturn(500d);
		when(retiroTigre.getPrecio()).thenReturn(1000d);
		when(tigreParana.getOrigen()).thenReturn(tigre);
		when(tigreParana.getDestino()).thenReturn(parana);

	}

	@Test
	void agregarFuncionaCorrectamente() throws Exception {

		//Como está vacía la lista de tramos, el primero no tiene problema.
		circuito.agregarTramo(laPlataQuilmes);
		//Para el segundo y el tercero, debe coincidir origen y destino.
		circuito.agregarTramo(quilmesRetiro);
		circuito.agregarTramo(retiroTigre);
		circuito.eliminarUltimoTramo();
		assertEquals(2, circuito.getListaDeTramos().size()); 
	}
	
	@Test
	void agregarLanzaExceptionCorrectamente() throws Exception{
		
		//Como está vacía la lista de tramos, el primero no tiene problema.
		circuito.agregarTramo(quilmesRetiro);
		//Para el segundo, debe lanzar la excepction.
		assertThrowsExactly (Exception.class,()->{circuito.agregarTramo(laPlataQuilmes);}, "El tramo agregado rompe el invariante de representación");
	}
	
	@Test
	void tramoConOrigenYDestino() throws Exception { 
		//Agrego los tramos.
		circuito.agregarTramo(laPlataQuilmes);
		circuito.agregarTramo(quilmesRetiro);
		circuito.agregarTramo(retiroTigre);
		circuito.agregarTramo(tigreParana);
		//Sí contiene origen destino.
		assertTrue(circuito.contienePuertos(laPlata, quilmes));
		assertTrue(circuito.contienePuertos(laPlata, retiro));
		assertTrue(circuito.contienePuertos(laPlata, tigre));
		assertTrue(circuito.contienePuertos(quilmes, retiro));
		assertTrue(circuito.contienePuertos(quilmes, tigre));
		//Existe origen y no destino.
		assertFalse(circuito.contienePuertos(laPlata, laPlata));
		//Existen pero tigre está más adelante que la plata.
		assertFalse(circuito.contienePuertos(tigre, laPlata));
		//Existen pero tigre está más adelante que quilmes.
		assertFalse(circuito.contienePuertos(tigre, quilmes));
		//Existe destino pero no origen.
		assertFalse(circuito.contienePuertos(parana, quilmes));
		
	}
	
	@Test
	void tiempoEntreOrigenYDestino() throws Exception {
		//Agrego los tramos
		circuito.agregarTramo(laPlataQuilmes);
		circuito.agregarTramo(quilmesRetiro);
		circuito.agregarTramo(retiroTigre);
		//Comrpuebo el tiempo inicializado anteriormente.
		assertEquals(10, circuito.getTiempoEntrePuertos(laPlata, quilmes));
		assertEquals(12, circuito.getTiempoEntrePuertos(quilmes, retiro));
		assertEquals(15, circuito.getTiempoEntrePuertos(retiro, tigre));
		assertEquals(37, circuito.getTiempoEntrePuertos(laPlata, tigre));
		//Aca lanzo excepción.
		assertThrows(Exception.class,() -> {circuito.getTiempoEntrePuertos(tigre, laPlata);});
		//El metodo get tiempo suma el tiempo de punta a punta.
		assertEquals(37, circuito.getTiempo());
	}
	
	@Test
	void distanciaEntreOrigenYDestino() throws Exception {
		//Agrego los tramos
		circuito.agregarTramo(laPlataQuilmes);
		circuito.agregarTramo(quilmesRetiro);
		circuito.agregarTramo(retiroTigre);
		//Comrpuebo el tiempo inicializado anteriormente.
		assertEquals(200d, circuito.getDistanciaEntrePuertos(laPlata, quilmes));
		assertEquals(300d, circuito.getDistanciaEntrePuertos(quilmes, retiro));
		assertEquals(500d, circuito.getDistanciaEntrePuertos(retiro, tigre));
		assertEquals(1000d, circuito.getDistanciaEntrePuertos(laPlata, tigre));
		//Aca lanzo excepción.
		assertThrows (Exception.class,() -> {circuito.getDistanciaEntrePuertos(tigre, laPlata);});
		//El metodo get distancia suma la distancia de punta a punta.
		assertEquals(1000d, circuito.getDistancia());
	}
	
	@Test
	void precioEntreOrigenYDestino() throws Exception {
		//Agrego los tramos
		circuito.agregarTramo(laPlataQuilmes);
		circuito.agregarTramo(quilmesRetiro);
		circuito.agregarTramo(retiroTigre);
		//Compruebo el tiempo inicializado anteriormente.
		assertEquals(1000d, circuito.getPrecioEntrePuertos(laPlata, quilmes));
		assertEquals(1000d, circuito.getPrecioEntrePuertos(quilmes, retiro));
		assertEquals(1000d, circuito.getPrecioEntrePuertos(retiro, tigre));
		assertEquals(3000d, circuito.getPrecioEntrePuertos(laPlata, tigre));
		//Aca lanzo excepción.
		assertThrows (Exception.class,() -> {circuito.getPrecioEntrePuertos(tigre, laPlata);});
		//El metodo get precio suma los precios de punta a punta.
		assertEquals(3000d, circuito.getPrecio());
	}
	
	@Test
	void getOrigenDelCircuitoFunciona() throws Exception {
		//Agrego los tramos
		circuito.agregarTramo(laPlataQuilmes);
		circuito.agregarTramo(quilmesRetiro);
		circuito.agregarTramo(retiroTigre);
		assertEquals(laPlata, circuito.puertoOrigen());
	}
	
	@Test
	void cantidadDeTerminalesEntreDosPuertos() throws Exception {
		// Agrego los tramos
		circuito.agregarTramo(laPlataQuilmes);
		circuito.agregarTramo(quilmesRetiro);
		assertEquals(1, circuito.getCantidadDeTerminalesEntrePuertos(laPlata, retiro));
	}
	
}