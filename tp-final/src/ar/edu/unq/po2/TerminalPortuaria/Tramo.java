package ar.edu.unq.po2.TerminalPortuaria;

import java.sql.Time;

public class Tramo {
	
	private TerminalPortuaria origen;
	
	private TerminalPortuaria destino;
	
	private int tiempoDeDuracionEnHoras;
	
	private double precio;
	
	private double distancia;
	
	public Tramo(TerminalPortuaria origen, TerminalPortuaria destino, int tiempoDeDuracionEnHoras, double precio, double distancia) {
		this.origen = origen;
		this.destino = destino;
		this.tiempoDeDuracionEnHoras = tiempoDeDuracionEnHoras;
		this.precio = precio;
		this.distancia = distancia;
	}

	public TerminalPortuaria getOrigen() {
		//Devuelve la terminal de origen.
		return origen;
	}

	public TerminalPortuaria getDestino() {
		//Devuelve la terminal de destino.
		return destino;
	}

	public int getTiempoDeDuracionEnHoras() {
		//Devuelve las horas de duración del viaje.
		return tiempoDeDuracionEnHoras;
	}

	public double getPrecio() {
		//Devuelve el precio.
		return precio;
	}

	public double getDistancia() {
		//Devuelve la distancia.
		return distancia;
	}
	
	
	
	

}
