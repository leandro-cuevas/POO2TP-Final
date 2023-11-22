package Buque;

abstract class EstadoDeBuque {
	
	protected EstadoDeBuque siguiente;
		
	public EstadoDeBuque() {
		this.siguiente = null;
	}

	public void comunicarConTerminal(Buque buque) throws Exception {}
	
	// METODO ESQUELETO DEL TEMPLATE
	public final void activarGPS(Buque buque) throws Exception {
		if(this.condicionParaPasarFase(buque)) {
			this.cambiarFase(buque);
			buque.comunicarConLaTerminal();
		}
	}
	// METODO A IMPLEMENTAR DEL ESQUELETO.	
	protected abstract boolean condicionParaPasarFase(Buque buque);

	public void cambiarFase(Buque buque) {
		//Cambia la fase del buque a su siguiente estado.
		buque.setEstado(siguiente);
	}
	
	public void setSiguiente(EstadoDeBuque siguiente) {
		// Setter del siguiente estado, ya que al instanciarse no sabe el siguiente.
		this.siguiente = siguiente;
	}
}
