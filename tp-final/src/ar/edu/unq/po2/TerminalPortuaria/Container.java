package ar.edu.unq.po2.TerminalPortuaria;

public abstract class Container {
	
	private int ancho;
	
	private int largo;
	
	private int altura;
	
	private int peso;
			
	private TerminalPortuaria terminalDestino;
	
	public Container(int ancho, int largo, int altura, int peso) {
		this.ancho = ancho;
		this.largo = largo;
		this.altura = altura;
		this.peso = peso;
		this.terminalDestino = null;
	}

	public int getConsumo() {
		//Es un hook por defecto.
		return 0;
	}
	
	public int getMetrosCubicos() {
		//Los metros cúbicos del container.
		return altura * ancho * largo;
	}
	

	public void setDestino(TerminalPortuaria destino) {
		this.terminalDestino = destino;
	}
	
	public boolean finDelRecorrido(TerminalPortuaria terminal) {
		//Retorna si la terminal por parámetro es el final del recorrido del container.
		return this.terminalDestino == terminal;
	}
}
