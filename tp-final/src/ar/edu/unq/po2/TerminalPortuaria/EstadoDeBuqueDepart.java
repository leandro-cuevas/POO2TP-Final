package ar.edu.unq.po2.TerminalPortuaria;

public class EstadoDeBuqueDepart extends EstadoDeBuque {
	@Override
	public void comunicarConTerminal(Buque buque) {
		//Avisa que se partió.
		buque.avisarQueSePartio();
	}

	@Override
	public void activarGPS(Buque buque) {
		//Si la distancia con la terminal es mayor a uno, avisa a la terminal y cambia de fase.
		if (buque.getDistanciaDeLaTerminal() > 1) {
			this.comunicarConTerminal(buque);
			this.cambiarFase(buque);
		}

	}

}
