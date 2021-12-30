package Aplicacion_de_Gestion;
import java.util.ArrayList;

import Comparadores.CriterioCalendario;

public class Gestion_Empleado {
	
	///TODO: Hacer un Singleton de estos datos
	
	private int semana_actual;
	private int codigos_empleado;
	private String nom_empresa;
	private String direccion;
	private String telefono;
	///TODO ArrayList de las semanas/fechas cuyos contratos ya fueron generados! Asi no volver a generarlos
	private  ArrayList<Empleado> empleados; 
	private ArrayList<Calendario> calendario_Semana_Activa; 
	private ArrayList<Historial_Cambios> Historial_Cambios_Empresa;
	

	private ArrayList<ArrayList<Contrato>> lContratosSemanales; ///Backtrackin uses

	public Gestion_Empleado(int semana_actual, String nom_empresa, String direccion, String telefono) {
		this.semana_actual = semana_actual;
		this.nom_empresa = nom_empresa;
		this.direccion = direccion;
		this.telefono = telefono;
		this.codigos_empleado = 0; ///Para asisgnar a los empleados
		this.empleados = new ArrayList<Empleado>();
		this.calendario_Semana_Activa = new ArrayList<Calendario>();
		this.Historial_Cambios_Empresa = new ArrayList<Historial_Cambios>();
	}

	public void AgregarEmpleado(Empleado empleado) {
		empleado.setCod_empleado(this.codigos_empleado);
		this.codigos_empleado++;
		empleado.setEstado_empleado(true);
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

	public boolean Cambio_Turno_Entre_Empleado(Calendario Calendario1, Calendario Calendario2) {
		///TODO 
		if(Calendario1.getN_semana() != Calendario2.getN_semana())
			return false; ///Los cambios de turnos solo son validos en la misma semana TODO print error alert
		
		Empleado e1 = null;
		Empleado e2 = null;
		for(Empleado e: this.empleados)
		{
			if(e.getCod_empleado() == Calendario1.getCod_empleado())
				e1 = e;
			if(e.getCod_empleado() == Calendario2.getCod_empleado())
				e2 = e;
			if(e1!=null && e2!= null)
				break;
		}

		if(e1==null && e2== null)///No existe alguno de los dos empleados TODO print alert
			return false;
		
		Contrato c1 = null;
		for(Contrato c: e1.getContratos())
		{
			if(c.getNum_semana() == Calendario1.getN_semana())
			{
				c1 = c;
				break;
			}
		}
		Contrato c2 = null;
		for(Contrato c: e2.getContratos())
		{
			if(c.getNum_semana() == Calendario2.getN_semana())
			{
				c2 = c;
				break;
			}
		}
		
		if(c1==null && c2==null) ///Si ambos contratos no existen, no hay cambio TODO print error Alert
			return false;
		
		///TODO Cuidado en caso de haber creado otra instancia de "Turno" y no usar la referencia
		if(!c1.getTurnos().contains(Calendario1.getTurno()) || !c2.getTurnos().contains(Calendario2.getTurno()))
			return false; //Me aseguro que los turnos pertenecen a los contratos que quiero hacer los cambios TODO print error alert
		
		
		//Hacer_Cambio_Turno(Turno Turno_IN, Turno Turno_OUT)
		boolean cambio1 = c1.Hacer_Cambio_Turno(Calendario2.getTurno(), Calendario1.getTurno());
		boolean cambio2 = c2.Hacer_Cambio_Turno(Calendario1.getTurno(), Calendario2.getTurno());
		
		if(cambio1 == true && cambio2==true) ///Turnos cambios correctamente
		{
			e1.AgregarComentarioHistorial("Cambio de turno",true);
			e2.AgregarComentarioHistorial("Cambio de turno",true);
			return true;
		}
		else //Deshacer los cambios en caso de que alguno de los 2 cambios sea invalido
		{
			
			c1.getTurnos().remove(Calendario1.getTurno());
			c1.getTurnos().remove(Calendario2.getTurno());
			c2.getTurnos().remove(Calendario1.getTurno());
			c2.getTurnos().remove(Calendario2.getTurno());
			
			c1.getTurnos().add(Calendario1.getTurno());
			c2.getTurnos().add(Calendario2.getTurno());
			
			e1.AgregarComentarioHistorial("Cambio de turno", false);
			e2.AgregarComentarioHistorial("Cambio de turno", false);
			return false;
		}
	}

	public boolean Cambio_Contrato_Empleados(int num_semana, int cod_empleado1, int cod_empleado2) {
		Empleado e1 = null;
		Empleado e2 = null;
		for(Empleado e: this.empleados)
		{
			if(e.getCod_empleado() == cod_empleado1)
				e1 = e;
			if(e.getCod_empleado() == cod_empleado2)
				e2 = e;
			if(e1!=null && e2!= null)
				break;
		}

		if(e1==null && e2== null)///No existe alguno de los dos empleados TODO print error alert
			return false;
		
		Contrato c1 = null;
		for(Contrato c: e1.getContratos())
		{
			if(c.getNum_semana() == num_semana)
			{
				c1 = c;
				break;
			}
		}
		Contrato c2 = null;
		for(Contrato c: e2.getContratos())
		{
			if(c.getNum_semana() == num_semana)
			{
				c2 = c;
				break;
			}
		}
		
		if(c1==null && c2==null) ///Si ambos contratos no existen, no hay cambio
			return false;
		
		///Es posible hacer un cambio de contratos si al menos un contrato existe
		if(c1 == null)
		{
			e1.getContratos().add(c2);
			e2.getContratos().remove(c2);
		}
		else if(c2 == null)
		{
			e1.getContratos().remove(c1);
			e2.getContratos().add(c1);
		}
		else ///Existen ambos contratos
		{
			e1.getContratos().remove(c1);
			e1.getContratos().add(c2);
			
			e2.getContratos().remove(c2);
			e2.getContratos().add(c1);
		}
		
		
		e1.AgregarComentarioHistorial("Contrato de la semana: "+num_semana+" cambiado con el empleado: "+cod_empleado2, true);
		e2.AgregarComentarioHistorial("Contrato de la semana: "+num_semana+" cambiado con el empleado: "+cod_empleado1, true);
		return true;
	}

	public void AgregarComentHistorial(String comentario, boolean estado) {
		String status = "Denegado";
		if(estado)
			status = "Aceptado";
		this.Historial_Cambios_Empresa.add(new Historial_Cambios(comentario, status));
	}

	public ArrayList<Calendario> Generar_Calendario_ArrayList(int semana) {
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
		///TODO 

	}


	private void Generar_Calendario_Semana(ArrayList<Contrato> lContratos )
	{
		
			this.calendario_Semana_Activa =  new ArrayList<Calendario>();
			int contractNumber = -1;
			for(Contrato con: lContratos)
			{
				contractNumber++;
				for(Turno t : con.getTurnos())
				{
					Calendario cal = new Calendario(contractNumber, 40, t);
					this.calendario_Semana_Activa.add(cal);
				}
			}
			//Imprimir_Calendario_Semanal();
		
	}

	public void Imprimir_Calendario_Semanal() {
		//Ordeno
		this.calendario_Semana_Activa.sort(new Comparadores.CriterioCalendario());
		//Imprimo
		String datos [][] = new String[3][7];
		for(int i=0; i<7;i++)
			for(int j=0;j<3;j++)
				datos[j][i] = "-";
		
		int dia=0;
		int horario;
		for(Calendario c: this.calendario_Semana_Activa)
		{
			dia = c.getTurno().getDia();
			horario = c.getTurno().getHorario().ordinal();
			datos[horario][dia] = String.valueOf(c.getCod_empleado());
		}
		for(int i=0; i<3;i++)
		{
			for(int j=0;j<7;j++)
			{
				System.out.print(" \t "+datos[i][j]);	
			}
			System.out.println();
		}
		System.out.println("\n-------------------------------------------------------------");
	}

	public void CerrarSemana() {
		for(Empleado e: this.empleados)
		{
			e.Mover_Contrato_al_Historial(this.semana_actual);
		}
		this.semana_actual++;

	}


	public ArrayList<Calendario> Generador_Contratos_Algoritmo() {
		///TODO Hacer algoritmo recursivo principal
		
		lContratosSemanales = new ArrayList<ArrayList<Contrato>>();
		
		System.out.println("EMPIEZA -------------------");
		Buscar_Posibles_Contratos(new ArrayList<Contrato>());
		System.out.println("TERMINA -------------------");
		///TODO Serializar resultados!!!
		
		for(ArrayList<Contrato> contratosSemana: lContratosSemanales)
		{
			Generar_Calendario_Semana(contratosSemana);
			Imprimir_Calendario_Semanal();
		}
		
		return this.calendario_Semana_Activa;

	}
	
	private Boolean Buscar_Posibles_Turnos(ArrayList<Contrato> contratosSemana, int dia,Boolean encontrado)
	{
		if(encontrado)
			return encontrado;
		
		if(dia==21) ///Sol encontrada
		{
			
			///Si llego aquí, es solución

			ArrayList<Contrato> solEncontrada = new ArrayList<Contrato>();
			contratosSemana.forEach(c -> solEncontrada.add(c));
			
			this.lContratosSemanales.add(solEncontrada);
			
			Generar_Calendario_Semana(solEncontrada);
			Imprimir_Calendario_Semanal();
			
			System.out.println("Solucion TURNOS " + contratosSemana.size());

			
			return true;
		}
			
		for(int c = 0 ; c<contratosSemana.size() && encontrado ==false ; c++)
		{
			
			if(contratosSemana.get(c).getTurnos().size()*8 < contratosSemana.get(c).cant_horas_contrato)
			{
				tipo_turno tipoturno = tipo_turno.morning; //por defecto, aunque siempre es sobreescrito
				switch(dia/7)
				{
				case 0: tipoturno = tipo_turno.morning; 
					break;
				case 1: tipoturno = tipo_turno.afternoon; 
					break;
				case 2: tipoturno = tipo_turno.night; 
					break;
				}
				
				Turno turnoTemp = new Turno(dia%7, tipoturno);
				contratosSemana.get(c).getTurnos().add(turnoTemp);
				
				if(contratosSemana.get(c).Validar_Turnos_Consecutivos_24h())
				{
					//Generar_Calendario_Semana(contratosSemana);
					//Imprimir_Calendario_Semanal();
					encontrado = Buscar_Posibles_Turnos(contratosSemana, dia+1,encontrado);
					
				}
				
				contratosSemana.get(c).getTurnos().remove(turnoTemp);
			}
				
			
		}
		return encontrado;
		
	}
		
		
			
	private void Buscar_Posibles_Contratos(ArrayList<Contrato> contratosSemana)
	{
		if(contratosSemana.size() >=7)
			return;
		
		int horas = 0;

		for(int k=0;k<contratosSemana.size();k++)
			horas += contratosSemana.get(k).cant_horas_contrato;
		
		
		if(horas == 168 )//Llego a una solucion
		{
			System.out.print("Sol Semana " + contratosSemana.size()+ " --  ");
			contratosSemana.forEach(c -> System.out.print(c.cant_horas_contrato+" "));
			System.out.println();
			Buscar_Posibles_Turnos(contratosSemana,0,false);
			return;
		}
		
		///Busco los posibles contratos validos
		for(int i=0;i<3;i++) 
		{
			int horasExtra=0;
			switch (i) 
			{
			case 0:
				contratosSemana.add(new Contrato_24h(40));
				horasExtra=24;
				break;
			case 1:
				contratosSemana.add(new Contrato_32h(40));
				horasExtra=32;
				break;
			case 2:
				contratosSemana.add(new Contrato_40h(40));
				horasExtra=40;
				break;
			}
			
			horas += horasExtra;
			if(horas <= 168 ) ///ALCANZABLE
				Buscar_Posibles_Contratos(contratosSemana);
				
			horas -= horasExtra;
			contratosSemana.remove(contratosSemana.size()-1);
		}
		
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
		return this.calendario_Semana_Activa;
	}

	public void setCalendario_Semana_Activa(ArrayList<Calendario> calendario_Semana_Activa) {
		this.calendario_Semana_Activa = new ArrayList<Calendario>(calendario_Semana_Activa);
	}

	public ArrayList<Historial_Cambios> getHistorial_Cambios_Empresa() {
		return Historial_Cambios_Empresa;
	}

	public void setHistorial_Cambios_Empresa(ArrayList<Historial_Cambios> historial_Cambios_Empresa) {
		Historial_Cambios_Empresa = historial_Cambios_Empresa;
	}

}
