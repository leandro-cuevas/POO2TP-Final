package Buque;

public class EstadoDeBuqueWorking extends EstadoDeBuque {

	@Override
	protected boolean condicionParaPasarFase(Buque buque) {
	// Esto devuelve siempre falso ya que la orden para pasar de fase la recibe de la terminal. Entonces
	// nunca este estado debe cambiarse solo. El false implica que nunca se realice el if de activarGPS()
		return false;
	}

	

}
