package ar.edu.unq.po2.TerminalPortuaria;

abstract class Container {
	
	protected int ancho;
	
	protected int largo;
	
	protected int peso;
	
	public Container(int ancho, int largo, int peso) {
		this.ancho = ancho;
		this.largo = largo;
		this.peso = peso;
	}

	public int getConsumo() {
		return 0;
	}

	public int getAncho() {
		return ancho;
	}

	public int getLargo() {
		return largo;
	}

	public int getPeso() {
		return peso;
	}
}
