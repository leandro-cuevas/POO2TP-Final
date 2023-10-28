package ar.edu.unq.po2.TerminalPortuaria;

abstract class EstadoDeBuque {
	
	protected EstadoDeBuque siguiente;
	
	private boolean habilitadoParaSalir = false; 
	
	public EstadoDeBuque(EstadoDeBuque siguiente) {
		this.siguiente = siguiente;
	}

	abstract public void comunicarConTerminal(Buque buque);
	
	abstract public void activarGPS(Buque buque);
		
	public void cambiarFase(Buque buque) {
		buque.setEstado(siguiente);
		this.activarGPS(buque);
	}
	
	public void setSiguiente(EstadoDeBuque siguiente) {
		this.siguiente = siguiente;
	}

	public boolean isHabilitadoParaSalir() {
		return habilitadoParaSalir;
	}

	public void setHabilitadoParaSalir(boolean habilitadoParaSalir) {
		this.habilitadoParaSalir = habilitadoParaSalir;
	}

}
