package ar.edu.unq.poo2.TerminalGestionada;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.TerminalPortuaria;
import ar.edu.unq.po2.TerminalPortuaria.Tramo;

public class Circuito {
	
	private List<Tramo> listaDeTramos;
	
	public Circuito() {
		this.listaDeTramos = new ArrayList<Tramo>();
	}

	public void agregarTramo(Tramo tramo) throws Exception {
		this.validarAgregarTramo(tramo);
		listaDeTramos.add(tramo);
	}
	
	private void validarAgregarTramo(Tramo tramo) throws Exception {
		if (!listaDeTramos.isEmpty() && !this.coincideConElUltimoAgregado(tramo)) {
			throw new Exception("El tramo agregado rompe el invariante de representación");
		}
	}
	
	public void eliminarUltimoTramo() {
		
		listaDeTramos.remove(listaDeTramos.size() - 1);
		
	}

	private boolean coincideConElUltimoAgregado(Tramo tramo) {
		Tramo ultimoAgregado = listaDeTramos.get(listaDeTramos.size() - 1);
		
		return ultimoAgregado.getDestino() == tramo.getOrigen();
				
	}

	private Tramo tramoConOrigen (TerminalPortuaria origen) {
		//Busca el tramo con el origen y lo devuelve. Debe existir.
		return listaDeTramos.stream()
				.filter(o-> o.getOrigen() == origen).
				findFirst().
				get();
	}
	
	private Tramo tramoConDestino(TerminalPortuaria destino) {
		//Busco el tramo con el origen y lo devuelve. Debe existir.
		return listaDeTramos.stream()
				.filter(d-> d.getDestino() == destino)
				.findFirst()
				.get();
	}
	
	public boolean contienePuertos(TerminalPortuaria origen, TerminalPortuaria destino) {
		//Dadas dos terminales portuarias, una de origen y una de destino, indica si existen.
		
		//Aplico stream a la lista.
		//Busco si hay algun con ese origen y alguno con ese destino.
		final boolean existenElOrigenYEldestino = listaDeTramos.stream()
													.anyMatch(o-> o.getOrigen() == origen)
												  && listaDeTramos.stream().
												  anyMatch(e-> e.getDestino() == destino);
						
		//Consigo el index del origen.
		final int indexDelOrigen = listaDeTramos.indexOf(this.tramoConOrigen(origen));
		
		//Consigo el index del destino.
		final int indexDelDestino = listaDeTramos.indexOf(this.tramoConDestino(destino));
		
		//El primer colaborador interno opera como short circuit, si ya es false, no va a tratar de buscar los index y por tanto no rompe.
		//Debo corroborar que el origen sea menor al destino porque sino signfica que el destino existe, pero que está antes en el recorrido.
		return existenElOrigenYEldestino && (indexDelOrigen < indexDelDestino);
	}
	
	private void validarExistenciaDePuertos(TerminalPortuaria origen, TerminalPortuaria destino) throws Exception {
		//Es una validación de que los puertos existen.
		if (!this.contienePuertos(origen, destino)){
			throw new Exception("No existe el origen o el destino");
		}
	}
	
	public double getPrecioEntrePuertos(TerminalPortuaria origen, TerminalPortuaria destino) throws Exception {
		//Dados un origen y un destino, me dice cuánto cuesta viajar entre esos lugares.
		
		//Primero valido la existencia.
		this.validarExistenciaDePuertos(origen, destino);
		
		//Consigo el index de origen.
		final int indexDelOrigen = listaDeTramos.indexOf(this.tramoConOrigen(origen));
		
		//Consigo el index de destino.
		final int indexDelDestino = listaDeTramos.indexOf(this.tramoConDestino(destino));
		
		//Utilizo subList con ambos index para obtener una lista que contienen la coleccion de tramos que me interesan.
		//A la misma le aplico stream.
		//Mapeo los doubles para conseguir los precios.
		//Los sumo.
		return listaDeTramos.subList(indexDelOrigen, indexDelDestino)
				.stream()
				.mapToDouble(e-> e.getPrecio())
				.sum();
	}

	public int getTiempoEntrePuertos(TerminalPortuaria origen, TerminalPortuaria destino) throws Exception {
		//Dados un origen y un destino, me dice cuánto tardo en viajar entre esos lugares.
		
		//Primero valido la existencia.
		this.validarExistenciaDePuertos(origen, destino);
		
		//Consigo el index de origen.
		final int indexDelOrigen = listaDeTramos.indexOf(this.tramoConOrigen(origen));
		
		//Consigo el index de destino.
		final int indexDelDestino = listaDeTramos.indexOf(this.tramoConDestino(destino));

		//Utilizo subList con ambos index para obtener una lista que contienen la coleccion de tramos que me interesan.
		//A la misma le aplico stream.
		//Mapeo los int para conseguir la duración de cada uno.
		//Los sumo.
		return listaDeTramos.subList(indexDelOrigen, indexDelDestino)
				.stream()
				.mapToInt(e-> e.getTiempoDeDuracionEnHoras())
				.sum();
	}
	
	public List<Tramo> getListaDeTramos() {
		//Getter de la lista de tramos.
		return this.listaDeTramos;
	}

	public double getDistancia() {
		//Retorna la distancia total del circuito.
		return listaDeTramos.stream()
				.mapToDouble(e-> e.getDistancia())
				.sum();
	}

	public double getPrecio() {
		// Retorna el precio por recorrer todo el circuito.
		return listaDeTramos.stream()
				.mapToDouble(e-> e.getPrecio())
				.sum();
	}

	public double getTiempo() {
		// Retorna el tiempo total de recorrer todo el circuito.
		return listaDeTramos.stream()
				.mapToInt(e-> e.getTiempoDeDuracionEnHoras())
				.sum();
	}

	public TerminalPortuaria puertoOrigen() {
		// Retorna el puerto de origen. El circuito debe tener al menos un tramo.
		return listaDeTramos.get(0).getOrigen();
	}

}
