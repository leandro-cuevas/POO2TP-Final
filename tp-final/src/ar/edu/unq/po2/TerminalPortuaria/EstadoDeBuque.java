package ar.edu.unq.po2.TerminalPortuaria;

abstract class EstadoDeBuque {
	
	protected EstadoDeBuque siguiente;
	
	private boolean habilitadoParaSalir; 
	
	public EstadoDeBuque() {
		this.siguiente = null;
		this.habilitadoParaSalir = false;
	}

	abstract public void comunicarConTerminal(Buque buque) throws Exception;
	
	abstract public void activarGPS(Buque buque) throws Exception;
		
	public void cambiarFase(Buque buque) {
		//Cambia la fase del buque a su siguiente estado.
		buque.setEstado(siguiente);
	}
	
	public void setSiguiente(EstadoDeBuque siguiente) {
		// Setter del siguiente estado, ya que al instanciarse no sabe el siguiente.
		this.siguiente = siguiente;
	}

	public boolean isHabilitadoParaSalir() {
		//Retorna si el buque está habilitado para salir.
		//Esto quiere decir que ya se realizaron los trabajos sobre el mismo.
		return habilitadoParaSalir;
	}

	public void setHabilitadoParaSalir(boolean habilitadoParaSalir) {
		//Setter de la habilitación para salir.
		this.habilitadoParaSalir = habilitadoParaSalir;
	}

}
