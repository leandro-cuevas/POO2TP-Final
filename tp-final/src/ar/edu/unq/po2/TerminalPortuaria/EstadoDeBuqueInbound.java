package ar.edu.unq.po2.TerminalPortuaria;

public class EstadoDeBuqueInbound extends EstadoDeBuque {

	@Override
	public void comunicarConTerminal(Buque buque) {
		//Avisa que está cercano a la terminal.
		buque.avisarArriboInminente();

	}

	@Override
	public void activarGPS(Buque buque) {
		//Si ya estoy a menos de 50, me comunico.
		if (buque.getDistanciaDeLaTerminal() < 50) {
			this.comunicarConTerminal(buque);
			buque.setEstado(siguiente);
		}
	}

}
