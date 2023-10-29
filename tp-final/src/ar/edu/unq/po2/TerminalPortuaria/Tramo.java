package ar.edu.unq.po2.TerminalPortuaria;

import java.sql.Time;

public class Tramo {
	
	private TerminalPortuaria origen;
	
	private TerminalPortuaria destino;
	
	private Time tiempoDeDuracion;
	
	private double precio;
	
	private double distancia;
	
	public Tramo(TerminalPortuaria origen, TerminalPortuaria destino, Time tiempoDeDuracion, double precio) {
		this.origen = origen;
		this.destino = destino;
		this.tiempoDeDuracion = tiempoDeDuracion;
		this.precio = precio;
	}

	public TerminalPortuaria getOrigen() {
		return origen;
	}

	public TerminalPortuaria getDestino() {
		return destino;
	}

	public Time getTiempoDeDuracion() {
		return tiempoDeDuracion;
	}

	public double getPrecio() {
		return precio;
	}

	public double getDistancia() {
		return distancia;
	}
	
	
	
	

}
