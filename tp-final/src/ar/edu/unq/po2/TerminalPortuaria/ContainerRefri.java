package ar.edu.unq.po2.TerminalPortuaria;

class ContainerRefri extends Container {
	
	private int consumoPorHora;
	
	public ContainerRefri(int consumoPorHora) {
		super(consumoPorHora, consumoPorHora, consumoPorHora);
		this.consumoPorHora = consumoPorHora;
	}

	public int getConsumoPorHora() {
		return consumoPorHora;
	}

	
}
