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
		camiones.add(camion);
	}
	
	public void addConductor(Conductor chofer) {
		choferes.add(chofer);
	}
	
	public boolean tieneCamion(Camion camion) {
		return camiones.contains(camion);
	}
	
	public boolean tieneChofer(Conductor chofer) {
		return choferes.contains(chofer);
	}
	
	public Conductor choferDisponible() throws Exception {
		validarChoferDisponible();
		return choferes.stream().filter(c-> c.isDisponible()).findFirst().get();
	}

	private void validarChoferDisponible() throws Exception {
		if (!choferes.stream().anyMatch(c-> c.isDisponible())) {
			throw new Exception("No hay ningún chofer disponible");
		}
	}
	
	public Camion camionDisponible() throws Exception {
		validarCamionDisponible();
		return camiones.stream().filter(c-> c.isDisponible()).findFirst().get();
	}

	private void validarCamionDisponible() throws Exception {
		if (!camiones.stream().anyMatch(c-> c.isDisponible())) {
			throw new Exception("No hay ningún camion disponible");
		}
	}
}
