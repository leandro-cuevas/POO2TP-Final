package ar.edu.unq.po2.TerminalPortuaria;

public class Buque {
	
	TerminalPortuaria terminal;
	
	EstadoDeBuque estado;

	public Buque(TerminalPortuaria terminal, EstadoDeBuque estado) {
		this.terminal = terminal;
		this.estado = estado;
	}

	public void setEstado(EstadoDeBuque estado) {
		this.estado = estado;
	}

	public int getDistanciaDeLaTerminal() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void activarGPS() {
		estado.activarGPS(this);
	}
	
	public void recibirOrdenInicioDeTrabajo() throws Exception {
		if (this.getDistanciaDeLaTerminal() > 0 && !estado.isHabilitadoParaSalir()) {
			throw new Exception("La nave no está en la terminal y no puede empezar a trabajar");
		} else estado.cambiarFase(this);
	}
	
	public void depart() throws Exception {
		
		estado.setHabilitadoParaSalir(true);
		if (this.getDistanciaDeLaTerminal() == 0 && !estado.isHabilitadoParaSalir()) {
			throw new Exception("La nave no está en la terminal y por tanto no puede ser Depart");
		} else estado.cambiarFase(this);
	}
	
	public void avisarArriboInminente() {
		terminal.arriboInminenteDelBuque(this);
	}

	public void avisarQueSeArribo() {
		terminal.arriboElBuque(this);
		
	}
	
	
	

}
