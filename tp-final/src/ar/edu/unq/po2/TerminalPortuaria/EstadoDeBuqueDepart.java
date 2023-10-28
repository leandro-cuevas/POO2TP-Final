package ar.edu.unq.po2.TerminalPortuaria;

public class EstadoDeBuqueDepart extends EstadoDeBuque {

	public EstadoDeBuqueDepart(EstadoDeBuque siguiente) {
		super(siguiente);
	}

	@Override
	public void comunicarConTerminal(Buque buque) {
		buque.terminal.elBuqueHaPartido(buque);
	}

	@Override
	public void activarGPS(Buque buque) {
		if (buque.getDistanciaDeLaTerminal() > 1) {
			this.comunicarConTerminal(buque);
			this.cambiarFase(buque);
		}

	}

}
