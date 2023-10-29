import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.Tramo;
import ar.edu.unq.poo2.TerminalGestionada.Circuito;
import ar.edu.unq.poo2.TerminalGestionada.ComparadorCircuito;
import ar.edu.unq.poo2.TerminalGestionada.ComparadorCircuitoDistancia;
import ar.edu.unq.poo2.TerminalGestionada.ComparadorCircuitoPrecio;

public class CriterioMenorPrecio extends Criterio {
ComparadorCircuito comparador = new ComparadorCircuitoPrecio();
	
	@Override
	public List<Tramo> elMejor(List<Circuito> circuitos) {
		return circuitos.stream()
				.min((v1,v2) -> v1.compararCon(v2, comparador))
				.get()
				.getTramos();
	}
}
