package ar.edu.unq.poo2.TerminalGestionada;

import static org.mockito.ArgumentMatchers.contains;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unq.po2.TerminalPortuaria.Camion;
import ar.edu.unq.po2.TerminalPortuaria.Cliente;
import ar.edu.unq.po2.TerminalPortuaria.Conductor;
import ar.edu.unq.po2.TerminalPortuaria.Container;
import ar.edu.unq.po2.TerminalPortuaria.EmpresaTransportista;
import ar.edu.unq.po2.TerminalPortuaria.Naviera;
import ar.edu.unq.po2.TerminalPortuaria.TerminalPortuaria;
import ar.edu.unq.po2.TerminalPortuaria.Turno;
import ar.edu.unq.po2.TerminalPortuaria.Viaje;

public class TerminalGestionada extends TerminalPortuaria {
	private List<Naviera> navieras;
	private List<EmpresaTransportista> transportistas;
	private List<Container> cargasSinRetirar;
	private Criterio criterioElMejor;
	private List<Turno> turnos;
	
	public TerminalGestionada(Criterio criterioElMejor) {
		super();
		this.navieras = new ArrayList<Naviera>();
		this.transportistas = new ArrayList<EmpresaTransportista>();
		this.cargasSinRetirar = new ArrayList<Container>();
		this.criterioElMejor = criterioElMejor;
		this.turnos = new ArrayList<Turno>();
	}

	
	public void exportar(Viaje viaje, Cliente shipper, Camion coche, Conductor chofer, Container carga, TerminalPortuaria destino) throws Exception {
		this.validarExportacion(viaje, destino); //Chequea si se puede realizar la exportacion para que no haya errores de otras clases que expongan otros mensajes de error
		this.validarTransporte(coche, chofer);
		this.registrarExportacion();
		this.asignarTurno(viaje, shipper, coche, chofer);
	}
	
	private void validarTransporte(Camion coche, Conductor chofer) {
		 if (transportistas.stream().anyMatch(t -> t.tieneChofer(chofer) && t.tieneCamion(coche))) {
			throw new Exception("El chofer y camion no son validos."); 
		 }
	}


	private void validarExportacion(Viaje viaje, TerminalPortuaria destino) throws Exception {
		if (viaje.contienePuertos(this, destino)) {
			throw new Exception("El viaje seleccionado no esta dirigido a la terminal Destino seleccionada");
		}
	}


	private void asignarTurno(Viaje viaje, Cliente shipper, Camion coche, Conductor chofer) throws Exception {
		// Este metodo no va a tirar un error del circuito ya que se valido previamente.
		LocalDateTime fechaLlegadaViaje = viaje.fechaDeArriboAlPuerto(this);
		LocalDateTime fechaAAsignar = fechaLlegadaViaje.minus(12, ChronoUnit.HOURS);
		turnos.add(new Turno(chofer, coche, shipper, fechaAAsignar));
	}


	private void registrarExportacion() {
		// TODO Auto-generated method stub
		
	}
	
	public ingresarCarga(Camion coche, Conductor chofer) {
		
	}


	public List<Viaje> filtrarViajes(Condicion query) {
		return navieras.stream()
				.flatMap(naviera -> naviera.getViajes().stream()) // Aplica FlatMap para poder mapear navieras con sus viajes y que no quede una lista de listas, sino una lista de Viajes, sin discriminar por naviera
				.filter(viaje -> query.chequear(viaje))         // Filtra los viajes que cumplen con la query. Si bien la query tira exception
				.toList();
				
	}
	
	public void registrarNaviera(Naviera n) {
		navieras.add(n);
	}
	
	public void registrarEmpresaTransportista(EmpresaTransportista et) {
		transportistas.add(et);
	}
}
