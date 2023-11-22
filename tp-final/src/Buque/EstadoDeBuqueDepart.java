package Buque;

public class EstadoDeBuqueDepart extends EstadoDeBuque {

	@Override
	protected boolean condicionParaPasarFase(Buque buque) {
	// Indica si el buque esta mas de 1 km de distancia. Debe pasar a la siguiente fase.
		return buque.getDistanciaDeLaTerminal() > 1;
	}
	
	
}
