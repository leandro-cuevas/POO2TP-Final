package ar.edu.unq.po2.TerminalPortuaria;

public class TerminalPortuaria {

	public void arriboInminenteDelBuque(Buque buque) {
		/* TODO Ante este aviso, la terminal enviará un mail a todos los consignees
		que estén esperando ese buque (orden de importación con ese viaje) avisando
		que su carga está llegando */
	}

	public void elBuqueHaPartido(Buque buque) {
		/* TODO la terminal enviará un mail a todos los shippers cuyas órdenes de
		exportación estén asociadas a ese viaje, avisando que su carga ya ha salido
		de la terminal */
	}

	public void arriboElBuque(Buque buque) {
		/* TODO Ver si hace falta este método. Porque si no se avisa que el buque ya llegó,
		no está claro cómo la terminal lo sabría */
		
	}

}
