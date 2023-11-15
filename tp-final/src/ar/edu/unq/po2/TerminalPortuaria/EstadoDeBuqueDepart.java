package ar.edu.unq.po2.TerminalPortuaria;

public class EstadoDeBuqueDepart extends EstadoDeBuque {
	@Override
	public void comunicarConTerminal(Buque buque) {
		buque.avisarQueSePartio();
	}

	@Override
	public void activarGPS(Buque buque) {
		if (buque.getDistanciaDeLaTerminal() > 1) {
			this.comunicarConTerminal(buque);
			this.cambiarFase(buque);
		}

	}

}
