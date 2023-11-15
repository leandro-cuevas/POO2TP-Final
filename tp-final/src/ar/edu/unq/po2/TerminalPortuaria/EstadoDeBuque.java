package ar.edu.unq.po2.TerminalPortuaria;

abstract class EstadoDeBuque {
	
	protected EstadoDeBuque siguiente;
	
	private boolean habilitadoParaSalir; 
	
	public EstadoDeBuque() {
		this.siguiente = null;
		this.habilitadoParaSalir = false;
	}

	abstract public void comunicarConTerminal(Buque buque);
	
	abstract public void activarGPS(Buque buque);
		
	public void cambiarFase(Buque buque) {
		buque.setEstado(siguiente);
	}
	
	// Setter del siguiente estado, ya que en principio no conoce a
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
