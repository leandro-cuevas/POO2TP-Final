package ar.edu.unq.poo2.TerminalGestionada;

public class ComparadorCircuitoPrecio implements ComparadorCircuito {

	@Override
	public int comparar(Circuito c1, Circuito c2) {
		return Double.compare(c1.getPrecio(), c2.getPrecio());
	}

}
