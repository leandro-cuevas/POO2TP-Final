package ar.edu.unq.po2.TerminalPortuaria;

public class EstadoDeBuqueArrived extends EstadoDeBuque {

	@Override
	public void comunicarConTerminal(Buque buque) throws Exception {
		//Avisa que se arribó a la terminal.
		buque.avisarQueSeArribo();

	}

	@Override
	public void activarGPS(Buque buque) throws Exception {
		//La activación del gps se comporta de distinta manera según el estado del buque.
		this.comunicarConTerminal(buque);

	}

}
