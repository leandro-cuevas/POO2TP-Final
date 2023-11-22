package Buque;

public class EstadoDeBuqueArrived extends EstadoDeBuque {

	@Override
	public void comunicarConTerminal(Buque buque) throws Exception {
		//Avisa que se arribó a la terminal.
		buque.avisarQueSeArribo();
	}

	@Override
	protected boolean condicionParaPasarFase(Buque buque) {
	// Esto devuelve siempre falso ya que la orden para pasar de fase la recibe de la terminal. Entonces
	// nunca este estado debe cambiarse solo. El false implica que nunca se realice el if de activarGPS()
		return false;
	}
}
