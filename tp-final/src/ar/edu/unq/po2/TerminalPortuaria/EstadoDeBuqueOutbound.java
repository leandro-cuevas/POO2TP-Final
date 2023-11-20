package ar.edu.unq.po2.TerminalPortuaria;

public class EstadoDeBuqueOutbound extends EstadoDeBuque {
	
	@Override
	public void comunicarConTerminal(Buque buque) {
		//Avisa que se partió.
		buque.avisarQueSePartio();
	}

	@Override
	protected boolean condicionParaPasarFase(Buque buque) {
	// Indica que el buque esta a menos de 50 km de distincia. Debe pasar a la siguiente fase.
		return buque.getDistanciaDeLaTerminal() < 50;
	}

	
}
