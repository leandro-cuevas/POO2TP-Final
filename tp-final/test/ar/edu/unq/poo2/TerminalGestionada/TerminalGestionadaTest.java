package ar.edu.unq.poo2.TerminalGestionada;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unq.po2.TerminalPortuaria.*;


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
	Buque buque;
	LocalDateTime f1;
	LocalDateTime f2;
	
	// DOC de ingresarCarga
	
	Turno turno;
	
	//DOC de Servicios
	
	Lavado lavado;
	Electricidad electro;
	Pesado pesado;
	Almacenamiento almacenamiento;
	
	@BeforeEach
	void setUp() throws Exception {
	// Seteamos el SUT, sin criterio por el momento
	terminal  = new TerminalGestionada(null, 8, 20);
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
	buque    = mock(Buque.class);
	f1 = LocalDateTime.of(2023, 12, 20, 12, 0);
	f2 = LocalDateTime.of(2023, 12, 16, 12, 0);
	
	turno    = mock(Turno.class);
	
	lavado = mock(Lavado.class);
	pesado = mock(Pesado.class);
	electro = mock(Electricidad.class);
	almacenamiento = mock(Almacenamiento.class);
	
	}
	
	/////////////////////////////////////////
	// TESTEO PARA FILTRADO DE QUERY
	/////////////////////////////////////////
	
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
	
	/////////////////////////////////////////
	// TESTEO PARA METODO EXPORTAR
	/////////////////////////////////////////
	
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
		//Testea que se setee el la terminalDestino pasada por parámetro en la carga
		verify(carga, times(1)).setDestino(destino);		
		//Testea que se haya creado el turno
		assertEquals(1, terminal.getTurnos().size());
		assertTrue(terminal.getTurnos().get(0).esCamion(coche));
		assertEquals(terminal.getTurnos().get(0).getDiaYHora(), LocalDateTime.of(2023,12,12,22,00));
	}
	
	/////////////////////////////////////////
	// TESTEO PARA INGRESAR CARGA ///////////
	/////////////////////////////////////////
	
	@Test
	void ingresarCargaElTurnoDifiereEn4HorasMAS() {
		// Seteamos el DOC para el chofer, con un camion y un turno asignado (supuestamente ya asignado en Exportar)
		when(chofer.getCamion()).thenReturn(coche);
		when(chofer.getTurno()).thenReturn(turno);
		// Seteamos el diaYHora para el turno. ya asignado en Exportar normalmente
		when(turno.getDiaYHora()).thenReturn(LocalDateTime.of(2023, 12, 12, 17, 00));
		assertThrowsExactly(Exception.class, 
		() -> {terminal.ingresarCarga(chofer, LocalDateTime.of(2023, 12, 12, 22, 00));},    // Metodo que causa la excepcion, quiere entrar a las 22 cuando el turno era a las 17.
		"El ingreso que quiere realizar difiere en mas de 3 horas al turno otorgado. Verifique su horario."); // String del error
	}
	
	@Test
	void ingresarCargaElTurnoDifiereEn4HorasMENOS() {
		// Seteamos el DOC para el chofer, con un camion y un turno asignado (supuestamente ya asignado en Exportar)
		when(chofer.getCamion()).thenReturn(coche);
		when(chofer.getTurno()).thenReturn(turno);
		// Seteamos el diaYHora para el turno. ya asignado en Exportar normalmente
		when(turno.getDiaYHora()).thenReturn(LocalDateTime.of(2023, 12, 12, 17, 00));
		assertThrowsExactly(Exception.class, 
		() -> {terminal.ingresarCarga(chofer, LocalDateTime.of(2023, 12, 12, 13, 00));},    // Metodo que causa la excepcion, quiere entrar a las 22 cuando el turno era a las 17.
		"El ingreso que quiere realizar difiere en mas de 3 horas al turno otorgado. Verifique su horario."); // String del error
	}
	
	@Test
	void elChoferNoEsElingresadoPorElShipper() {
		// Seteamos el DOC para el chofer, con un camion y un turno asignado (supuestamente ya asignado en Exportar)
		when(chofer.getCamion()).thenReturn(coche);
		when(chofer.getTurno()).thenReturn(turno);
		// Seteamos el diaYHora para el turno. ya asignado en Exportar normalmente
		when(turno.getDiaYHora()).thenReturn(LocalDateTime.of(2023, 12, 12, 17, 00));
		when(turno.esCamion(coche)).thenReturn(true);
		when(turno.esChofer(chofer)).thenReturn(false);
		
		assertThrowsExactly(Exception.class, 
				() -> {terminal.ingresarCarga(chofer, LocalDateTime.of(2023, 12, 12, 16, 00));},    // Metodo que causa la excepcion, el chofer es invalido
				"El transporte indicado en la terminal no es el indicado por el Shipper."); // String del error
	}
	
	@Test
	void elCamionNoEsElingresadoPorElShipper() {
		// Seteamos el DOC para el chofer, con un camion y un turno asignado (supuestamente ya asignado en Exportar)
		when(chofer.getCamion()).thenReturn(coche);
		when(chofer.getTurno()).thenReturn(turno);
		// Seteamos el diaYHora para el turno. ya asignado en Exportar normalmente
		when(turno.getDiaYHora()).thenReturn(LocalDateTime.of(2023, 12, 12, 17, 00));
		when(turno.esCamion(coche)).thenReturn(false);
		when(turno.esChofer(chofer)).thenReturn(true);
		
		assertThrowsExactly(Exception.class, 
				() -> {terminal.ingresarCarga(chofer, LocalDateTime.of(2023, 12, 12, 16, 00));},    // Metodo que causa la excepcion, el chofer es invalido
				"El transporte indicado en la terminal no es el indicado por el Shipper."); // String del error
	}
	
	@Test 
	void sePuedeIngresarLaCargaCorrectamente() throws Exception {
		// Creamos una operacion de exportar 
		when(viaje1.contienePuertos(terminal, destino)).thenReturn(true);
		when(viaje1.fechaDeArriboAlPuerto(terminal)).thenReturn(LocalDateTime.of(2023,12,13,10,00));
		terminal.registrarEmpresaTransportista(empresaT);
		when(empresaT.tieneCamion(coche)).thenReturn(true);
		when(empresaT.tieneChofer(chofer)).thenReturn(true);
		terminal.exportar(viaje1, cliente, coche, chofer, carga, destino);
		// Obtenemos el turno para poder setearle al DOC del chofer que responda el turno correctamente
		Turno t2 = terminal.getTurnos().get(0);
		//Seteamos las respuestas que queremos para el chofer. 
		when(chofer.getCamion()).thenReturn(coche);
		when(chofer.getTurno()).thenReturn(t2);
		when(chofer.getCarga()).thenReturn(carga);
		terminal.ingresarCarga(chofer, LocalDateTime.of(2023,12,12,22,00));
		// Chequeamos que la carga haya ingresado corretamente, es decir, que la lista de Turnos este vacia.
		assertEquals(0, terminal.getTurnos().size());
	}
	
	@Test 
	void testGetterXY() {
		assertEquals(8, terminal.getX());
		assertEquals(20, terminal.getY());
	}
	
	/////////////////////////////////////////
	///////// TESTEO PARA IMPORTAR //////////
	/////////////////////////////////////////
	
	@Test 
	void testImportarYAvisosAClienteArriboInminente() {
		//Testea que se genera una orden y al recibir el arribo inminente, se le avisa al cliente.
		terminal.importar(viaje1, carga, cliente, terminal);
		when(buque.getViaje()).thenReturn(viaje1);
		when(viaje1.fechaDeArriboAlPuerto(terminal)).thenReturn(f1);
		terminal.arriboInminenteDelBuque(buque);
		verify(cliente, times(1)).avisarProntaLlegada(f1);		
	}
	
	@Test 
	void testImportarYAvisosAClienteArribo() throws Exception {
		terminal.exportar(viaje1, cliente, coche, chofer, carga, destino);
		terminal.ingresarCarga(chofer, f2);
		terminal.importar(viaje1, carga, cliente, terminal);
		when(buque.getViaje()).thenReturn(viaje1);
		terminal.arriboElBuque(buque);
		verify(buque, times(1)).recibirOrdenInicioDeTrabajo();
		verify(buque, times(1)).descargarContainer(carga);
		verify(cliente, times(1)).listoPararRetirar(terminal, carga);
	}
	
	
}
