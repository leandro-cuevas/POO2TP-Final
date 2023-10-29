package ar.edu.unq.poo2.TerminalGestionada;

import java.util.Date;
import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.TerminalPortuaria;
import ar.edu.unq.po2.TerminalPortuaria.Tramo;

public class Circuito {
	
	private List<Tramo> listaDeTramos;
	
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

	public double getTiempoEntrePuertos(TerminalPortuaria origen, TerminalPortuaria destino) throws Exception {
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
	
	public int compararCon(Circuito c, ComparadorCircuito comp) {
		//Dado un circuito y un comparador de circuito, chequea si este objeto es menor al parámetro según la lógica del ComparadorCircuito
		return comp.comparar(this, c);
	}
	public List<Tramo> getListaDeTramos() {
		//Getter de la lista de tramos.
		return this.listaDeTramos;
	}

}
