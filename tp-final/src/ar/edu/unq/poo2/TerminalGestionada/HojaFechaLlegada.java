package ar.edu.unq.poo2.TerminalGestionada;

import java.sql.Date;

public class HojaFechaLlegada implements Condicion {
	Date fecha;

	@Override
	public boolean chequear(Naviera naviera) {
		return fecha < naviera.getFechaDeLlegada();
	}
}
