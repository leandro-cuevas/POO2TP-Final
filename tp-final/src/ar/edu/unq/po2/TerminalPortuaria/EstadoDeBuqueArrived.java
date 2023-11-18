package ar.edu.unq.po2.TerminalPortuaria;

public class EstadoDeBuqueArrived extends EstadoDeBuque {

	@Override
	public void comunicarConTerminal(Buque buque) throws Exception {
		buque.avisarQueSeArribo();

	}

	@Override
	public void activarGPS(Buque buque) throws Exception {
		this.comunicarConTerminal(buque);

	}

}
