package ar.edu.unq.po2.TerminalPortuaria;

class ContainerRefri extends Container {
	
	private int consumoPorHora;
	
	public ContainerRefri(int ancho, int largo, int altura, int peso, int consumoPorHora) {
		super(ancho, largo, altura, peso);
		this.consumoPorHora = consumoPorHora;
	}

	@Override 
	public int getConsumo() {
		//Getter del consumo.
		return consumoPorHora;
	}

	
}
