package ar.edu.unq.po2.TerminalPortuaria;

public class EstadoDeBuqueInbound extends EstadoDeBuque {

	public EstadoDeBuqueInbound(EstadoDeBuque siguiente) {
		super(siguiente);
	}

	@Override
	public void comunicarConTerminal(Buque buque) {
		buque.avisarArriboInminente();

	}

	@Override
	public void activarGPS(Buque buque) {
		
		if (buque.getDistanciaDeLaTerminal() == 0) {
			this.comunicarConTerminal(buque);
			buque.setEstado(siguiente);
		}
	}

}
