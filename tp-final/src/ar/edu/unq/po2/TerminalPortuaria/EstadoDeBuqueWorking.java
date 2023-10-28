package ar.edu.unq.po2.TerminalPortuaria;

public class EstadoDeBuqueWorking extends EstadoDeBuque {

	public EstadoDeBuqueWorking(EstadoDeBuque siguiente) {
		super(siguiente);
	}

	@Override
	public void comunicarConTerminal(Buque buque) {

	}

	@Override
	public void activarGPS(Buque buque) {

	}

}
