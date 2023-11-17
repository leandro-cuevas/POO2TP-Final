package ar.edu.unq.po2.TerminalPortuaria;

public abstract class Container {
	
	private int ancho;
	
	private int largo;
	
	private int altura;
	
	private int peso;
	
	private Viaje viaje;
	
	private Cliente dueño;
	
	private TerminalPortuaria terminalDestino;
	
	public Container(int ancho, int largo, int altura, int peso) {
		this.ancho = ancho;
		this.largo = largo;
		this.altura = altura;
		this.peso = peso;
		this.viaje = null;
		this.dueño = null;
		this.terminalDestino = null;
	}

	public int getConsumo() {
		//Es un hook por defecto.
		return 0;
	}

	public int getAncho() {
		//El ancho del container.
		return ancho;
	}

	public int getLargo() {
		//El largo del container.
		return largo;
	}

	public int getPeso() {
		//El peso del container.
		return peso;
	}
	
	public int getAltura() {
		//La altura del container.
		return altura;
	}
	
	public int getMetrosCubicos() {
		//Los metros cúbicos del container.
		return altura * ancho * largo;
	}
	
	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}
	
	public void setDestino(TerminalPortuaria destino) {
		this.terminalDestino = destino;
	}
	
	public boolean finDelRecorrido(TerminalPortuaria terminal) {
		return this.terminalDestino == terminal;
	}
}
