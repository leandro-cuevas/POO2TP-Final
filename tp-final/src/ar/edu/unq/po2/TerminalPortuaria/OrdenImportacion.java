package ar.edu.unq.po2.TerminalPortuaria;

public class OrdenImportacion extends Orden {
	
	private Cliente consignee;

	public OrdenImportacion(Viaje viaje, Container container, TerminalPortuaria terminalDestino, Cliente consignee) {
		super(viaje, container, terminalDestino);
		this.consignee = consignee;
	}
	
	public void setCamion(Camion c) {
		this.camion = c;
	}
	public void setChofer(Conductor c) {
		this.chofer = c;
	}

}
