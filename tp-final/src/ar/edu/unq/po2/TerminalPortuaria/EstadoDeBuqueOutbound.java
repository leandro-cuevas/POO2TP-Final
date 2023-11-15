package ar.edu.unq.po2.TerminalPortuaria;

public class EstadoDeBuqueOutbound extends EstadoDeBuque {
	
	@Override
	public void comunicarConTerminal(Buque buque) {
		
	}

	@Override
	public void activarGPS(Buque buque) {
		if (buque.getDistanciaDeLaTerminal() < 50){
			this.cambiarFase(buque);
		}
	}
}
