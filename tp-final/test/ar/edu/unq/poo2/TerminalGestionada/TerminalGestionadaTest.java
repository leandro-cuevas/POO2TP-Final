package ar.edu.unq.poo2.TerminalGestionada;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.Camion;
import ar.edu.unq.po2.TerminalPortuaria.Cliente;
import ar.edu.unq.po2.TerminalPortuaria.Conductor;
import ar.edu.unq.po2.TerminalPortuaria.Container;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista;
import ar.edu.unq.po2.TerminalPortuaria.Naviera;
import ar.edu.unq.po2.TerminalPortuaria.TerminalPortuaria;
import ar.edu.unq.po2.TerminalPortuaria.Viaje;

class TerminalGestionadaTest {
	//Tipamos con terminalGestionada para poder probar nuestros propios metodos
	TerminalGestionada terminal;
	
	// DOC 
	Viaje viaje1;
	Viaje viaje2;
	Naviera n1;
	Naviera n2;
	Condicion queryMock;
	List<Viaje> viajes1 = new ArrayList<Viaje>();
	List<Viaje> viajes2= new ArrayList<Viaje>();
	
	// DOC de exportar
	Cliente cliente;
	Camion coche;
	Conductor chofer;
	Container carga;
	TerminalPortuaria destino; 
	EmpresaTransportista empresaT;
	
	@BeforeEach
	void setUp() throws Exception {
	// Seteamos el SUT, sin criterio por el momento
	terminal  = new TerminalGestionada(null, 8, 8);
	queryMock = mock(Condicion.class);
	n1        = mock(Naviera.class);
	n2        = mock(Naviera.class);	
	viaje1    = mock(Viaje.class);
	viaje2    = mock(Viaje.class);
	viajes1.add(viaje1);
	viajes2.add(viaje2);
	
	cliente  = mock(Cliente.class);
	coche    = mock(Camion.class);
	chofer   = mock(Conductor.class);
	carga    = mock (Container.class);
	destino  = mock(TerminalPortuaria.class);
	empresaT = mock(EmpresaTransportista.class);
	}

	@Test
	void testeoDeQueryMockeada() {
		// Registramos las navieras y ponemos el DOC para que responda como queremos
		terminal.registrarNaviera(n1);
		terminal.registrarNaviera(n2);
		when(n1.getViajes()).thenReturn(viajes1);
		when(n2.getViajes()).thenReturn(viajes2);
		// Cuando la query cheque el viaje v1, pasara el filtro, el v2 no
		when(queryMock.chequear(viaje1)).thenReturn(true);
		when(queryMock.chequear(viaje2)).thenReturn(false);
		// El retorno de filtrar viajes va a ser una lista con v1, igual al contenido de viaje1.
		assertEquals(viajes1, terminal.filtrarViajes(queryMock));
	}
	
	@Test 
	void testNoSePuedeExportarViajeINVALIDO() {
		when(viaje1.contienePuertos(terminal, destino)).thenReturn(false);
		assertThrowsExactly(Exception.class, () -> {terminal.exportar(viaje1, cliente, coche, chofer, carga, destino); }, "El viaje seleccionado no esta dirigido a la terminal Destino seleccionada");
	}
	
	@Test
	void testNoSePuedeExportarChoferINVALIDO() {
		when(viaje1.contienePuertos(terminal, destino)).thenReturn(true);
		terminal.registrarEmpresaTransportista(empresaT);
		when(empresaT.tieneCamion(coche)).thenReturn(true);
		when(empresaT.tieneChofer(chofer)).thenReturn(false);
		assertThrowsExactly(Exception.class, () -> {terminal.exportar(viaje1, cliente, coche, chofer, carga, destino); }, "El chofer y camion no son validos.");
	}
	
	@Test
	void testNoSePuedeExportarConductorINVALIDO() {
		when(viaje1.contienePuertos(terminal, destino)).thenReturn(true);
		terminal.registrarEmpresaTransportista(empresaT);
		when(empresaT.tieneCamion(coche)).thenReturn(false);
		when(empresaT.tieneChofer(chofer)).thenReturn(true);
		assertThrowsExactly(Exception.class, () -> {terminal.exportar(viaje1, cliente, coche, chofer, carga, destino); }, "El chofer y camion no son validos.");
	}
	
	@Test 
	void testExportarFuncionaCorrectamente() throws Exception {
		when(viaje1.contienePuertos(terminal, destino)).thenReturn(true);
		when(viaje1.fechaDeArriboAlPuerto(terminal)).thenReturn(LocalDateTime.of(2023,12,13,10,00));
		terminal.registrarEmpresaTransportista(empresaT);
		when(empresaT.tieneCamion(coche)).thenReturn(true);
		when(empresaT.tieneChofer(chofer)).thenReturn(true);
		terminal.exportar(viaje1, cliente, coche, chofer, carga, destino);
		assertEquals(1, terminal.getTurnos().size());
		assertEquals(coche, terminal.getTurnos().get(0).getCamion());
		assertEquals(terminal.getTurnos().get(0).getDiaYHora(), LocalDateTime.of(2023,12,12,22,00));
	}
}
