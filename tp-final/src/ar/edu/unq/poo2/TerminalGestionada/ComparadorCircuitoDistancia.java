package ar.edu.unq.poo2.TerminalGestionada;

public class ComparadorCircuitoDistancia implements ComparadorCircuito {
	@Override
	public int comparar(Circuito c1, Circuito c2) {
		return Double.compare(c1.getDistancia(), c2.getDistancia());
	} //Elegimos usar double ya que el tiempo probablemente sea un Double
}
