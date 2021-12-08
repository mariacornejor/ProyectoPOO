package Aplicacion_de_Gestion;
import java.util.ArrayList;

public class Gestion_Empleado {
	
	///TODO: Hacer un Singleton de estos datos
	
	private int semana_actual;
	private int codigos_empleado;
	private String nom_empresa;
	private String direccion;
	private String telefono;
	private ArrayList<Empleado> empleados; 
	private ArrayList<Calendario> calendario_Semana_Activa; 
	private ArrayList<Historial_Cambios> Historial_Cambios_Empresa;

	public Gestion_Empleado(int semana_actual, String nom_empresa, String direccion, String telefono) {
		this.semana_actual = semana_actual;
		this.nom_empresa = nom_empresa;
		this.direccion = direccion;
		this.telefono = telefono;
		this.codigos_empleado = 0; ///Para asisgnar a los empleados
		ArrayList<Empleado> empleados = new ArrayList<Empleado>();
		ArrayList<Calendario> calendario_Semana_Activa = new ArrayList<Calendario>();
		ArrayList<Historial_Cambios> Historial_Cambios_Empresa = new ArrayList<Historial_Cambios>();
	}

	public void AgregarEmpleado(Empleado empleado) {
		empleado.setCod_empleado(this.codigos_empleado);
		this.codigos_empleado++;
		empleado.setEstado_empleado(true);
		empleado.setPuesto("Empleado");
		this.empleados.add(empleado);
	}

	public boolean Despedir_Empleado(Empleado Empleado_IN, Empleado Empleado_OUT) {
		Empleado e =null;
		for(Empleado ee: this.empleados)
		{
			if(ee.getCod_empleado() == Empleado_OUT.getCod_empleado())
			{
				e = ee;
				this.empleados.remove(e);
				break;
			}
		}
		if(e==null)
			return false;
		
		///Transfiero TODOS los contratos Proximos al otro empleado
		for(Contrato c : e.getContratos())
		{
			///TODO VALIDAR ULTIMA ENTREGAR usando Date
			if(c.num_semana > this.semana_actual)
			{
				Empleado_IN.getContratos().add(c);
			}
		}
		for(Contrato c : Empleado_IN.getContratos())
		{
			e.getContratos().remove(c);
		}
		e.setEstado_empleado(false); ///Despedido para que no se le asignen contratos 
		this.empleados.add(e); ///Agrego el empleado despedido, asi mantengo los datos de la semanas anteriores
		return true;
	}

	public void Cambio_Turno_Entre_Empleado(Calendario Calendario1, Calendario Calendario2) {

	}

	public boolean Cambio_Contrato_Empleado(int num_semana, int cod_empleado1, int cod_empleado2) {

	}

	public void AgregarComentHistorial(String comentario, boolean estado) {
		this.Historial_Cambios_Empresa.add(new Historial_Cambios(comentario, comentario));
	}

	public ArrayList<Calendario> Generar_Calendario(int semana) {
		this.calendario_Semana_Activa =  new ArrayList<Calendario>();
		for(Empleado e: this.empleados)
		{
			for(Contrato c : e.getContratos())
			{
				if(c.getNum_semana() == semana)
				{
					for(Turno t: c.getTurnos())
					{
						Calendario cal =  new Calendario(e.getCod_empleado(), semana, t);
						this.calendario_Semana_Activa.add(cal);
					}
				}
			}
		}
		
		return this.calendario_Semana_Activa;
	}

	public void Asignar_Vacaciones(int cod_empleado, int semana_vacaciones) {

	}

	public void Imprimir_Calendario_Semanal() {
		//Ordeno
		this.calendario_Semana_Activa.sort(new Comparadores.CriterioCalendario());
		//Imprimo
		int d=0;
		for(Calendario c: this.calendario_Semana_Activa)
		{
			System.out.print("Semana");
		}

	}

	public void CerrarSemana() {

	}

	public ArrayList<Calendario> Generar_Proximo_Contratos(int num_semana) {

	}


	///GETTERS Y SETTERS
	public int getSemana_actual() {
		return semana_actual;
	}

	public void setSemana_actual(int semana_actual) {
		this.semana_actual = semana_actual;
	}

	public String getNom_empresa() {
		return nom_empresa;
	}

	public void setNom_empresa(String nom_empresa) {
		this.nom_empresa = nom_empresa;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public ArrayList<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(ArrayList<Empleado> empleados) {
		this.empleados = empleados;
	}

	public ArrayList<Calendario> getCalendario_Semana_Activa() {
		return Calendario_Semana_Activa;
	}

	public void setCalendario_Semana_Activa(ArrayList<Calendario> calendario_Semana_Activa) {
		Calendario_Semana_Activa = calendario_Semana_Activa;
	}

	public ArrayList<Historial_Cambios> getHistorial_Cambios_Empresa() {
		return Historial_Cambios_Empresa;
	}

	public void setHistorial_Cambios_Empresa(ArrayList<Historial_Cambios> historial_Cambios_Empresa) {
		Historial_Cambios_Empresa = historial_Cambios_Empresa;
	}

}
