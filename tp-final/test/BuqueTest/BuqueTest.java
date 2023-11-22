package BuqueTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Buque.Buque;
import Buque.EstadoDeBuque;
import Container.Container;
import Naviera.Viaje;
import TerminalGestionada.TerminalGestionada;

class BuqueTest {

	TerminalGestionada terminal;
	EstadoDeBuque estado;
	EstadoDeBuque otroEstado;
	Buque buque;
	Container container;
	Container container2;
	Container container3;
	Container container4;
	
	Viaje viaje1;
	
	@BeforeEach
	void setUp() throws Exception {
		terminal   = mock(TerminalGestionada.class);
		estado 	   = mock(EstadoDeBuque.class);
		otroEstado = mock(EstadoDeBuque.class);
		container  = mock(Container.class);
		container2 = mock(Container.class);
		container3 = mock(Container.class);
		container4 = mock(Container.class);
		buque      = new Buque(terminal, estado);
		viaje1     = mock(Viaje.class);
	}

	@Test
	void estaEnViajeTest() {
		//Por defecto no lo está.
		assertFalse(buque.estaEnViaje());
		//Se asigna un viaje.
		buque.asignarViaje(viaje1);
		//Ahora es verdadero.
		assertTrue(buque.estaEnViaje());
		// Y tiene el viaje asignado correctamente
		assertEquals(viaje1, buque.getViaje());
	}
	
	@Test
	void setEstadoYGpsTest() throws Exception {
		//Pruebo el setter.
		buque.setEstado(otroEstado);
		//Activo GPS.
		buque.activarGPS();
		//Debe llamarse una vez a activar GPS del estado del buque.
		verify(otroEstado, times(1)).activarGPS(buque);
	}
	
	@Test
	void distanciaDeTerminalTest() {
		//Armo el mock.
		when(terminal.getX()).thenReturn(3d);
		when(terminal.getY()).thenReturn(4d);
		//El método Point.distance me devuelve la hipotenuusa entre dos puntos.
		//El buque está en 0,0 y la terminal en 3,4 para que la hipotenusa me de 5.
		assertEquals(5, buque.getDistanciaDeLaTerminal());
	}
	
	@Test
	void recibirOrdenInicioDeTrabajoTest() throws Exception {
		//Armo el mock.
		when(terminal.getX()).thenReturn(3d);
		when(terminal.getY()).thenReturn(4d);
		//Como no está en la terminal no puede hacerlo.
		assertThrowsExactly(Exception.class,()->{buque.recibirOrdenInicioDeTrabajo();}, "La nave no está en la terminal o no puede empezar a trabajar") ;
		//Ahora está en la misma ubicación
		when(terminal.getX()).thenReturn(0d);
		when(terminal.getY()).thenReturn(0d);
		//Se cumplen las condiciones y funciona.
		buque.recibirOrdenInicioDeTrabajo();
		verify(estado, times(1)).cambiarFase(buque);
	}
	
	@Test
	void departTest() throws Exception{
		//Esta en la terminal y es igual a la ubicación del buque.
		when(terminal.getX()).thenReturn(0d);
		when(terminal.getY()).thenReturn(0d);
		//Ejecuto depart
		buque.depart();
		//Como funciona, se realiza una vez el cambio de fase del buque.
		verify(estado, times(1)).cambiarFase(buque);
	}
	
	@Test
	void avisosATerminal() throws Exception {
		//Aviso los distintos mensajes a terminarl.
		buque.avisarArriboInminente();
		buque.avisarQueSeArribo();
		buque.avisarQueSePartio();
		//Verifico que cada uno es invocado una vez.
		verify(terminal, times(1)).arriboInminenteDelBuque(buque);
		verify(terminal, times(1)).arriboElBuque(buque);
		verify(terminal, times(1)).elBuqueHaPartido(buque);
	}
	
	@Test
	void cargaContainerTest() {
		//Cargo
		buque.cargarContainer(container);
		//Descargo
		buque.descargarContainer(container);
		//No tengo containers cargados.
		assertEquals(0,buque.getContainers().size());
	}
	
	@Test 
	void comunicarConLaTerminalDelegado() throws Exception {
		// Testea que delegue bien el mensaje comunicar con la terminal a su estado
		buque.comunicarConLaTerminal();
		buque.comunicarConLaTerminal();
		verify(estado, times(2)).comunicarConTerminal(buque);
	}
	
	@Test 
	void cargasParaTerminalT1() {
		// Seteamos el destino de todos los containers.	
		when(container.finDelRecorrido(terminal)).thenReturn(true);
		when(container2.finDelRecorrido(terminal)).thenReturn(true);
		when(container3.finDelRecorrido(terminal)).thenReturn(false);
		when(container4.finDelRecorrido(terminal)).thenReturn(false);
		// Cargamos el buque con todos los containers, para la terminal y para la terminal2.
		buque.cargarContainer(container);
		buque.cargarContainer(container2);
		buque.cargarContainer(container3);
		buque.cargarContainer(container4);
		// Verificamos que el size de las cargas para la terminal es 2.
		assertEquals(2, buque.containersParaDescargar(terminal).size());
	}
}
