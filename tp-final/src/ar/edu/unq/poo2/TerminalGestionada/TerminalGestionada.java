package ar.edu.unq.poo2.TerminalGestionada;

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
	private Map<Cliente, Turno> turnos;
	
	public TerminalGestionada(Criterio criterioElMejor) {
		super();
		this.navieras = new ArrayList<Naviera>();
		this.transportistas = new ArrayList<EmpresaTransportista>();
		this.cargasSinRetirar = new ArrayList<Container>();
		this.criterioElMejor = criterioElMejor;
		this.turnos = new HashMap<Cliente, Turno>();
	}

	
	public void exportar(Viaje viaje, Cliente shipper, Camion coche, Conductor chofer, Container carga, TerminalPortuaria destino) {
		
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
