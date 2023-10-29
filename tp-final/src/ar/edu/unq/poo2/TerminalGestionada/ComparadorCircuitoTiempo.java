package ar.edu.unq.poo2.TerminalGestionada;

public class ComparadorCircuitoTiempo implements ComparadorCircuito{

	@Override
	public int comparar(Circuito c1, Circuito c2) {
		return Double.compare(c1.getTiempo(), c2.getTiempo());
	} //Elegimos usar double ya que el tiempo probablemente sea un Double

}
