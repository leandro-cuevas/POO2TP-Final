package ar.edu.unq.po2.TerminalPortuaria;

import java.util.ArrayList;
import java.util.List;

public class EmpresaTransportista {
	private List<Conductor> choferes;
	private List<Camion> camiones;
	
	public EmpresaTransportista() {
		choferes = new ArrayList<Conductor>();
		camiones = new ArrayList<Camion>();
	}
	
	public void addCamion(Camion camion) {
		//Agrega un camión a la empresa
		camiones.add(camion);
	}
	
	public void addConductor(Conductor chofer) {
		//Agrega un conductor a la empresa
		choferes.add(chofer);
	}
	
	public boolean tieneCamion(Camion camion) {
		//Responde si el camión pasado por parámetro pertenece a la empresa
		return camiones.contains(camion);
	}
	
	public boolean tieneChofer(Conductor chofer) {
		//Responde si el conductor pasado por parámetro pertenece a la empresa
		return choferes.contains(chofer);
	}
	
	public Conductor choferDisponible() throws Exception {
		//Si hay alguno disponible, devuelve un chofer que esté disponible.
		validarChoferDisponible();
		return choferes.stream().filter(c-> c.isDisponible()).findFirst().get();
	}

	private void validarChoferDisponible() throws Exception {
		//Valida que haya algún chofer disponible, sino lanza una excepción.
		if (!choferes.stream().anyMatch(c-> c.isDisponible())) {
			throw new Exception("No hay ningún chofer disponible");
		}
	}
	
	public Camion camionDisponible() throws Exception {
		//Si hay alguno disponible, devuelve un camión que esté disponible.
		validarCamionDisponible();
		return camiones.stream().filter(c-> c.isDisponible()).findFirst().get();
	}

	private void validarCamionDisponible() throws Exception {
		//Valida que haya algún camión disponible, sino lanza una excepción.
		if (!camiones.stream().anyMatch(c-> c.isDisponible())) {
			throw new Exception("No hay ningún camion disponible");
		}
	}
}
