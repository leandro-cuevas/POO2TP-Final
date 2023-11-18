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
		/* TODO Ante este aviso, la terminal enviará un mail a todos los consignees
		que estén esperando ese buque (orden de importación con ese viaje) avisando
		que su carga está llegando */
		List<Container> cargasParaAca = buque.containersParaDescargar(this);
		cargasParaAca.stream().forEach(c -> c.getDuenio().avisarProntaLlegada());
	}

	public void elBuqueHaPartido(Buque buque) {
		/* TODO la terminal enviará un mail a todos los shippers cuyas órdenes de
		exportación estén asociadas a ese viaje, avisando que su carga ya ha salido
		de la terminal */
		ordenesExportadas.stream().forEach(o->o.getShipper().avisarExportacion());
		ordenesExportadas.clear();
	}

	public void arriboElBuque(Buque buque) throws Exception {
		/* TODO Ver si hace falta este método. Porque si no se avisa que el buque ya llegó,
		no está claro cómo la terminal lo sabría */
		buque.recibirOrdenInicioDeTrabajo();
		this.importarCargas(buque);
		this.exportarCargas(buque);
		buque.depart();
		
	}

	private void asignarTurno(Viaje viaje, Cliente shipper, Camion coche, Conductor chofer, Container carga) throws Exception {
		// Este metodo no va a tirar un error del circuito ya que se valido previamente.
		LocalDateTime fechaLlegadaViaje = viaje.fechaDeArriboAlPuerto(this);         
		LocalDateTime fechaAAsignar = fechaLlegadaViaje.minus(12, ChronoUnit.HOURS); // Le resta 12 horas a la fecha de arribo a la terminal, para hacier eficiente todo el tiempo que la carga este en la terminal
		turnos.add(new Turno(chofer, coche, shipper, fechaAAsignar, carga));
		// SETEAMOS A LA CARGA EL VIAJE QUE TENDRA, YA QUE CUANDO UN BUQUE VENGA A RETIRAR CARGAS,
		// SE LLEVARA LAS QUE CONTENGAN SU VIAJE.
	}
	
	public void ingresarCarga(Conductor chofer, LocalDateTime diaYHora) throws Exception{
		validarTurno(chofer.getTurno(), diaYHora);   // Chequea que el ingreso no difiera en mas de 3 horas al turno otorgado
		validarCocheyChofer(chofer.getCamion(), chofer, chofer.getTurno()); 		// Chequea que el coche y el chofer que quieren ingresar, sean los asignados en el turno. 
		//TODO cambiar a ordenes cargasSinRetirar.add(turno.getCarga());
		int indexTurno = turnos.indexOf(chofer.getTurno());
		turnos.remove(indexTurno);
		//TODO cambiar a ordenes cargasSinRetirar.add(chofer.getCarga());
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

	private void generarOrdenExportacion(Viaje viaje, Container carga, Conductor chofer, Camion camion,
			TerminalPortuaria destino, Cliente shipper) {
		ordenes.add(new OrdenExportacion(viaje, carga, chofer, camion, destino, this, shipper));
	}

	public void exportarCargas(Buque buque) {
		List<Orden> ordenesParaExportar = ordenes.stream().filter(o->o.tieneMismoViaje(buque.getViaje())).toList();
		ordenesParaExportar.stream().forEach(o-> manejarExportaciones(buque, o));
	}
	
	public void manejarExportaciones(Buque buque, Orden orden) {
		Container carga = orden.getContainer();
		carga.setDestino(orden.getDestino());
		buque.cargarContainer(carga);
		ordenesExportadas.add((OrdenExportacion) orden);
		ordenes.remove(orden);		
	}
	


	/// IMPORTACIONES
	
	public void importarCargas(Buque buque) {
		List<Container> cargasParaAca = buque.containersParaDescargar(this);
		cargasParaAca.stream().forEach(c->manejarImportaciones(buque, c));
	}
	
	public void manejarImportaciones(Buque buque, Container carga) {
		buque.descargarContainer(carga);
		this.generarOrdenImportacion(buque, carga);
	}
	
	
	private void generarOrdenImportacion(Buque buque, Container carga) {
		ordenes.add(new OrdenImportacion(buque.getViaje(), carga , this, carga.getDuenio()));
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
	

	public List<Viaje> filtrarViajes(Condicion query) {
		return navieras.stream()
				.flatMap(naviera -> naviera.getViajes().stream()) // Aplica FlatMap para poder mapear navieras con sus viajes y que no quede una lista de listas, sino una lista de Viajes, sin discriminar por naviera
				.filter(viaje -> query.chequear(viaje))         // Filtra los viajes que cumplen con la query. Si bien la query tira exception
				.toList();
		
	}
	

	private void realizarServicioElectrico(Container c) {
		//Creo el servicio eléctrico
		Electricidad electricidad = new Electricidad(this.costoPorKw, ordenDelContainer(c));
		//Busco la orden del container.
		//Le agrego este servicio
		ordenDelContainer(c).agregarServicio(electricidad);
	}
	
	private void realizarServicioDePesado(Container c) {
		//Creo el servicio de pesado
		Pesado pesado = new Pesado(costoDePesado, ordenDelContainer(c));
		//Busco la orden del container.
		//Le agrego este servicio
		ordenDelContainer(c).agregarServicio(pesado);

	}
	
	private void realizarServicioDeAlmacenamientoExcedente(Container c) {
		//Creo el servicio de pesado
		Almacenamiento almacenamiento = new Almacenamiento(costoPorEstadia, ordenDelContainer(c));
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
