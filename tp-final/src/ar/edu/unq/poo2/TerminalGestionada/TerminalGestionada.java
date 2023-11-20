package ar.edu.unq.poo2.TerminalGestionada;

import java.awt.Point;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.po2.TerminalPortuaria.*;


public class TerminalGestionada extends TerminalPortuaria {
	private List<Naviera> navieras;
	private List<EmpresaTransportista> transportistas;
	private Criterio criterioElMejor;
	private List<Turno> turnos;
	private Point coordenada;
	private int costoPorContainerPequenio;
	private int costoPorContainerGrande;
	private int costoPorKw;
	private double costoDePesado;
	private double costoPorEstadia;
	private List<Orden> ordenes;
	private List<OrdenExportacion> ordenesExportadas;
	
	public TerminalGestionada(Criterio criterioElMejor, int x, int y) {
		super();
		this.navieras = new ArrayList<Naviera>();
		this.transportistas = new ArrayList<EmpresaTransportista>();
		this.criterioElMejor = criterioElMejor;
		this.turnos = new ArrayList<Turno>();
		this.coordenada = new Point(x, y);
		this.ordenes = new ArrayList<Orden>();
		this.ordenesExportadas = new ArrayList<OrdenExportacion>();
	}

	public void arriboInminenteDelBuque(Buque buque) {
		/* Ante este aviso, la terminal enviará un mail a todos los consignees
		que estén esperando ese buque (orden de importación con ese viaje) avisando
		que su carga está llegando */
		this.ordenesParaViaje(buque).stream().forEach(o -> o.getCliente().avisarProntaLlegada(buque.getViaje().fechaDeArriboAlPuerto(this)));
	}

	public void elBuqueHaPartido(Buque buque) {
		/* La terminal enviará un mail a todos los shippers cuyas órdenes de
		exportación estén asociadas a ese viaje, avisando que su carga ya ha salido
		de la terminal */
		ordenesExportadas.stream().forEach(o->o.getCliente().avisarExportacion());
		ordenesExportadas.clear();
	}

	public void arriboElBuque(Buque buque) throws Exception {
		/* Ver si hace falta este método. Porque si no se avisa que el buque ya llegó,
		no está claro cómo la terminal lo sabría */
		buque.recibirOrdenInicioDeTrabajo();
		this.importarCargas(buque);     
		this.exportarCargas(buque);
		buque.depart();
		
	}

	private List<Orden> ordenesParaViaje(Buque buque) {
		return ordenes.stream().filter(o-> o.tieneMismoViaje(buque.getViaje())).toList();
	}
		
	/// VALIDACIONES
	
	private void validarTurno(Turno turno, LocalDateTime now) throws Exception{
		// Planteamos la duracion entre la fecha del turno y la hora en la que se quiere ingresar
		int duracionEntreTurnoYAhora = (int) Duration.between(turno.getDiaYHora(), now).toHours(); 
		// En caso de NO ser mayor a -3 horas (es decir, llego 3 horas temprano) O NO ser menor o igual a 3 horas (es decir, llego 3 horas tarde)
		// lanza la excepcion
		if (!( duracionEntreTurnoYAhora >= -3) || !(duracionEntreTurnoYAhora <= 3)) {
			throw new Exception("El ingreso que quiere realizar difiere en mas de 3 horas al turno otorgado. Verifique su horario.");
		}
	}


	private void validarCocheyChofer(Camion coche, Conductor chofer, Turno turno) throws Exception {
		if (!turno.esCamion(coche) || !turno.esChofer(chofer)) {
			throw new Exception("El transporte indicado en la terminal no es el indicado por el Shipper.");
		}
	}

	private void validarTransporte(Camion coche, Conductor chofer) throws Exception{
		// En caso de que ninguna empresa transportista tenga al chofer y al camion indicados, suelta la excepcion.
		if (!transportistas.stream().anyMatch(t -> t.tieneChofer(chofer) && t.tieneCamion(coche))) {
			throw new Exception("El chofer y camion no son validos."); 
		} 
	}
	
	
	private void validarExportacion(Viaje viaje, TerminalPortuaria destino) throws Exception {
		// En caso de el viaje elegido NO contenga a la terminal y al puerto destino en ESE orden suelta excepcion.
		if (!viaje.contienePuertos(this, destino)) {
			throw new Exception("El viaje seleccionado no esta dirigido a la terminal Destino seleccionada");
		}
	}

	
	/// EXPORTACIONES

	public void exportar(Viaje viaje, Cliente shipper, Camion coche, Conductor chofer, Container carga, TerminalPortuaria destino) throws Exception {
		this.validarExportacion(viaje, destino);  //Chequea si se puede realizar la exportacion para que no haya errores de otras clases que expongan otros mensajes de error.
		this.validarTransporte(coche, chofer);    // Chequea si el camion y el conductor elegidos por el shipper pertenecen a las empresas transportistas de la terminal.
		this.generarOrdenExportacion(viaje, carga, chofer, coche, destino, shipper);              // 
		this.asignarTurno(viaje, shipper, coche, chofer, carga); // Asigna un turno a la lista de turnos de la terminal con los datos asignados.
	}

	private void asignarTurno(Viaje viaje, Cliente shipper, Camion coche, Conductor chofer, Container carga) throws Exception {
		// Este metodo no va a tirar un error del circuito ya que se valido previamente.
		LocalDateTime fechaLlegadaViaje = viaje.fechaDeArriboAlPuerto(this);         
		LocalDateTime fechaAAsignar = fechaLlegadaViaje.minus(12, ChronoUnit.HOURS); // Le resta 12 horas a la fecha de arribo a la terminal, para hacier eficiente todo el tiempo que la carga este en la terminal
		turnos.add(new Turno(chofer, coche, shipper, fechaAAsignar, carga));
		// SETEAMOS A LA CARGA EL VIAJE QUE TENDRA, YA QUE CUANDO UN BUQUE VENGA A RETIRAR CARGAS,
		// SE LLEVARA LAS QUE CONTENGAN SU VIAJE.
	}
	
	private void generarOrdenExportacion(Viaje viaje, Container carga, Conductor chofer, Camion camion,
			TerminalPortuaria destino, Cliente shipper) {
		carga.setDestino(destino);
		ordenes.add(new OrdenExportacion(viaje, carga, chofer, camion, destino, this, shipper));
	}

	public void ingresarCarga(Conductor chofer, LocalDateTime diaYHora) throws Exception{
		validarTurno(chofer.getTurno(), diaYHora);   // Chequea que el ingreso no difiera en mas de 3 horas al turno otorgado
		validarCocheyChofer(chofer.getCamion(), chofer, chofer.getTurno()); 		// Chequea que el coche y el chofer que quieren ingresar, sean los asignados en el turno. 
		int indexTurno = turnos.indexOf(chofer.getTurno());
		turnos.remove(indexTurno);
		Orden ordenDeCarga = ordenes.stream().filter(ord -> ord.esContainer(chofer.getCarga())).findFirst().get();
		ordenDeCarga.setCargaDepositada();
		this.realizarServicioDePesado(ordenDeCarga);
	}
	
	private void exportarCargas(Buque buque) {
		List<Orden> ordenesParaExportar = ordenes.stream().filter(o->o.tieneMismoViaje(buque.getViaje()) && o.isCargaDepositada()).toList();
		ordenesParaExportar.stream().forEach(o-> manejarExportaciones(buque, o));
	}
	
	private void manejarExportaciones(Buque buque, Orden orden) {
		Container carga = orden.getContainer();
		carga.setDestino(orden.getDestino());
		buque.cargarContainer(carga);
		ordenesExportadas.add((OrdenExportacion) orden);
		ordenes.remove(orden);		
	}

	/// IMPORTACIONES /////////////////////
	
	public void importar(Viaje viaje, Container carga, Cliente consignee, TerminalPortuaria terminalOrigen) {
		//Genera una orden de importación
		ordenes.add(new OrdenImportacion(viaje, carga, this, consignee, terminalOrigen));
	}

	private void importarCargas(Buque buque) {
		this.ordenesParaViaje(buque).stream().forEach(o->manejarImportaciones(buque, o));
	}
	
	private void manejarImportaciones(Buque buque, Orden orden) {
		Cliente consignee = orden.getCliente();
		buque.descargarContainer(orden.getContainer());
		orden.setCargaDepositada();
		this.avisarConsignee(consignee, orden.getContainer());
	}
	
	private void avisarConsignee(Cliente consignee, Container carga) {
		// Crea un turno para el consignee duenio de la carga que arribo. El turno tiene 24 horas de duracion
		LocalDateTime diaYHora = LocalDateTime.now().plus(24, ChronoUnit.HOURS);
		Turno turno = new Turno(consignee, diaYHora, carga);
		turnos.add(turno);
		consignee.listoPararRetirar(this, carga);
	}
	
	// RETIRAR IMPORTACIONES ///////////////////
	
	public void avisarTransporteParaRetiro(Cliente consignee, Conductor chofer, Camion camion) {
		OrdenImportacion ordenDeConsignee = (OrdenImportacion) ordenes.stream().filter(orden -> orden.esDeCliente(consignee)).findFirst().get();
		Turno turnoDeConsignee = turnos.stream().filter(t -> t.esDeCliente(consignee)).findFirst().get();
		ordenDeConsignee.setCamion(camion);
		ordenDeConsignee.setChofer(chofer);
		turnoDeConsignee.setCamion(camion);
		turnoDeConsignee.setChofer(chofer);
	}
	
	public void retirarImportacion(Conductor chofer, LocalDateTime diaYHora) throws Exception{
		Orden ordenDeConsignee = ordenes.stream().filter(o -> o.esDeCliente(chofer.getTurno().getCliente())).findFirst().get();
		validarCocheyChofer(chofer.getCamion(), chofer, chofer.getTurno()); 		// Chequea que el coche y el chofer que quieren ingresar, sean los asignados en el turno. 
		validarTurnoImp(chofer.getTurno(), diaYHora, ordenDeConsignee);   // Chequea que el ingreso no difiera en mas horas al turno otorgado, sino asigna almacenamiento excedente
		int indexTurno = turnos.indexOf(chofer.getTurno());
		turnos.remove(indexTurno);
		ordenDeConsignee.setFechaRetirada(LocalDateTime.now());
		
	}
	
	private void validarTurnoImp(Turno turno, LocalDateTime diaYHora, Orden orden) {
		// En caso de que la fecha del parametro sea mayor a la del turno, asigna el servicio.
		if (turno.getDiaYHora().compareTo(diaYHora) < 0) {
			orden.agregarServicio(new Almacenamiento(costoPorEstadia));
		}
	}

	public void registrarNaviera(Naviera n) {
		navieras.add(n);
	}
	
	public void registrarEmpresaTransportista(EmpresaTransportista et) {
		transportistas.add(et);
	}
	
	public double getX() {
		return this.coordenada.getX();
	}
	
	public double getY() {
		return this.coordenada.getY();
	}
	
	public List<Turno> getTurnos() {
		return turnos;
	}
	
	private Orden ordenDelContainer(Container c) {
		return ordenes.stream().filter(o-> o.getContainer() == c).findFirst().get();
	}

	
	public void retirarCarga(Conductor ch, Container c) {
		//Aca va todo lo demás
		Orden orden = ordenDelContainer(c);
		orden.setFechaRetirada(LocalDateTime.now());
		ordenes.remove(orden);
		
	}

	
	public void realizarLavadoDeContainer(Container c) {
		//Creo el servicio lavado con los costos.
		Lavado lavado = new Lavado (this.costoPorContainerPequenio, this.costoPorContainerGrande, ordenDelContainer(c));
		//Busco la orden del container.
		//Le agrego este servicio
		ordenDelContainer(c).agregarServicio(lavado);
	}
	
	////////////////////////////////////
	// SERVICIO DE FILTRADO DE VIAJES //
	////////////////////////////////////
	public List<Viaje> filtrarViajes(Condicion query) {
		return navieras.stream()
				.flatMap(naviera -> naviera.getViajes().stream()) // Aplica FlatMap para poder mapear navieras con sus viajes y que no quede una lista de listas, sino una lista de Viajes, sin discriminar por naviera
				.filter(viaje -> query.chequear(viaje))         // Filtra los viajes que cumplen con la query. Si bien la query tira exception
				.toList();	
	}
	

	public void realizarServicioElectrico(Container c) {
		//Creo el servicio eléctrico
		Electricidad electricidad = new Electricidad(this.costoPorKw, ordenDelContainer(c));
		//Busco la orden del container.
		//Le agrego este servicio
		ordenDelContainer(c).agregarServicio(electricidad);
	}
	
	public void realizarServicioDePesado(Orden orden) {
		//Creo el servicio de pesado
		Pesado pesado = new Pesado(costoDePesado);
		//Busco la orden del container.
		//Le agrego este servicio
		ordenDelContainer(orden.getContainer()).agregarServicio(pesado);

	}
	
	private void realizarServicioDeAlmacenamientoExcedente(Container c) {
		//Creo el servicio de almacenamiento excedente
		Almacenamiento almacenamiento = new Almacenamiento(costoPorEstadia);
		//Busco la orden del container.
		//Le agrego este servicio
		ordenDelContainer(c).agregarServicio(almacenamiento);
	}


	public void setCostoPorContainerPequenio(int costoPorContainerPequenio) {
		this.costoPorContainerPequenio = costoPorContainerPequenio;
	}

	public void setCostoPorContainerGrande(int costoPorContainerGrande) {
		this.costoPorContainerGrande = costoPorContainerGrande;
	}


	public void setCostoPorKw(int costoPorKw) {
		this.costoPorKw = costoPorKw;
	}
	
	public void setCostoDePesado(int costoDePesado) {
		this.costoDePesado =  costoDePesado;
	}
}
