package Buque;

public class EstadoDeBuqueInbound extends EstadoDeBuque {

	@Override
	public void comunicarConTerminal(Buque buque) {
		//Avisa que está cercano a la terminal.	
		buque.avisarArriboInminente();
	}

	@Override
	protected boolean condicionParaPasarFase(Buque buque) {
	// Indica si el buque esta a la misma distancia de la terminal. Debe pasar a la siguiente fase.
		return buque.getDistanciaDeLaTerminal() == 0;
	}

}
