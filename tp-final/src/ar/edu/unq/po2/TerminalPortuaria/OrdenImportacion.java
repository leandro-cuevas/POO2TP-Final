package ar.edu.unq.po2.TerminalPortuaria;

public class OrdenImportacion extends Orden {
	

	public OrdenImportacion(Viaje viaje, Container container, TerminalPortuaria terminalDestino, Cliente consignee) {
		super(viaje, container, terminalDestino, consignee);
		this.cargaDepositada = true;
	}
	
	public void setCamion(Camion c) {
		this.camion = c;
	}
	public void setChofer(Conductor c) {
		this.chofer = c;
	}

	public Camion getCamion() {
		return camion;
	}

}
