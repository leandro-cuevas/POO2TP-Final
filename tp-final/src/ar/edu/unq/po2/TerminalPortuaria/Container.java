package ar.edu.unq.po2.TerminalPortuaria;

public abstract class Container {
	
	private int ancho;
	
	private int largo;
	
	private int altura;
	
	private int peso;
		
	private Cliente duenio;
	
	private TerminalPortuaria terminalDestino;
	
	public Container(int ancho, int largo, int altura, int peso, Cliente consignee) {
		this.ancho = ancho;
		this.largo = largo;
		this.altura = altura;
		this.peso = peso;
		this.terminalDestino = null;
		this.duenio = consignee;
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
	

	public void setDestino(TerminalPortuaria destino) {
		this.terminalDestino = destino;
	}
	
	public boolean finDelRecorrido(TerminalPortuaria terminal) {
		return this.terminalDestino == terminal;
	}

	public Cliente getDuenio() {
		return duenio;
	}
}
