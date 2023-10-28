package ar.edu.unq.po2.TerminalPortuaria;

class ContainerRefri extends Container {
	
	private int consumoPorHora;
	
	public ContainerRefri(int consumoPorHora) {
		super(consumoPorHora, consumoPorHora, consumoPorHora);
		this.consumoPorHora = consumoPorHora;
	}

	@Override 
	public int getConsumo() {
		return consumoPorHora;
	}

	
}
