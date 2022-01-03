package Aplicacion_de_Gestion;

import java.util.ArrayList;

public class Empleado {
	private int cod_empleado;
	private boolean estado_empleado;
	private String dni, nombre, apellido, telefono;
	private String fecha_nacimiento;
	private String num_cuenta, banco_cuenta;
	
	private Historial_Empleado historial_empleado;
	private ArrayList<Historial_Cambios> historial_cambios_empleado; 
	private ArrayList<Contrato> contratos; 
	
	public Empleado(String dni, String nombre, String apellido, String telefono, String fecha_nacimiento, String num_cuenta, String banco_cuenta) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.fecha_nacimiento = fecha_nacimiento;
		this.banco_cuenta = banco_cuenta;
		this.num_cuenta = num_cuenta;
		
		this.historial_empleado =  new Historial_Empleado();
		this.historial_cambios_empleado = new ArrayList<Historial_Cambios>(); 
		this.contratos = new ArrayList<Contrato>();
	}
	
	public void CambioContrato(Contrato nuevo_contrato) {
		Contrato cSobreescribir = null;
		for(Contrato c: this.contratos)
		{
			if(c.getNum_semana() == nuevo_contrato.getNum_semana())
			{
				cSobreescribir = c;
				break;
			}
		}
		if(cSobreescribir != null)
		{
			cSobreescribir.getTurnos().clear();
			this.contratos.remove(cSobreescribir);
		}
		this.contratos.add(nuevo_contrato);
	}
	///Se usara en el algoritmos de generar turnos
	public boolean CambioTurno(Turno turno_eliminar, Turno turno_agregar, int num_semana) {
		///TODO 
		Contrato cModificar = null;
		for(Contrato c: this.contratos)
		{
			if(c.getNum_semana() == num_semana)
			{
				cModificar = c;
				break;
			}
		}
		if(cModificar==null)
			return false; ///TODO ALERT
		///TODO estoy mas dormido que despierto, a dormir
		if(cModificar.Hacer_Cambio_Turno(turno_agregar, turno_eliminar))
		{
			return true;
		}
		return true;
	}
	
	public void AgregarComentarioHistorial(String comentario, boolean estado) {
		String status = "Denegado";
		if(estado)
			status = "Aceptado";
		this.historial_cambios_empleado.add(new Historial_Cambios(comentario, status));
	}
	
	public void Mover_Contrato_al_Historial(int num_semana) {
		for(Contrato c: this.getContratos())
		{
			if(c.getNum_semana() == num_semana)
			{
				if(c instanceof Contrato_24h)
					this.historial_empleado.setCant_contrato_24h( this.historial_empleado.getCant_contrato_24h() + 1 );
				else if( c instanceof Contrato_32h)
					this.historial_empleado.setCant_contrato_32h( this.historial_empleado.getCant_contrato_32h() + 1 );
				else if( c instanceof Contrato_40h)
					this.historial_empleado.setCant_contrato_40h( this.historial_empleado.getCant_contrato_40h() + 1 );
				else if( c instanceof Contrato_Vacaciones)
				{
					this.historial_empleado.setCant_dias_vacaciones( this.historial_empleado.getCant_dias_vacaciones() + 7); //7 dias
					this.historial_empleado.setCant_contrato_vacaciones( this.historial_empleado.getCant_contrato_vacaciones() + 1);
				}
				
				if(c.finde_libre)
					this.historial_empleado.setCant_dias_vacaciones( this.historial_empleado.getCant_dias_vacaciones() + 1);// 2  dias que cuentan como vacaciones
				
				this.AgregarComentarioHistorial("Contrato de "+c.cant_horas_contrato+"h - concluido", true);
				return;
			}
		}
	}
	
	
	public Contrato getContrato(int numSemana)
	{
		for(Contrato c: this.contratos )
			if(c.num_semana == numSemana)
				return c;
		return null;
	}
	
	///GETTERS Y SETTERS	

	public int getCod_empleado() {
		return cod_empleado;
	}

	public void setCod_empleado(int cod_empleado) {
		this.cod_empleado = cod_empleado;
	}

	public boolean isEstado_empleado() {
		return estado_empleado;
	}

	public void setEstado_empleado(boolean estado_empleado) {
		this.estado_empleado = estado_empleado;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}


	public String getNum_cuenta() {
		return num_cuenta;
	}

	public void setNum_cuenta(String num_cuenta) {
		this.num_cuenta = num_cuenta;
	}

	public String getBanco_cuenta() {
		return banco_cuenta;
	}

	public void setBanco_cuenta(String banco_cuenta) {
		this.banco_cuenta = banco_cuenta;
	}

	public Historial_Empleado getHistorial_empleado() {
		return historial_empleado;
	}

	public void setHistorial_empleado(Historial_Empleado historial_empleado) {
		this.historial_empleado = historial_empleado;
	}

	public ArrayList<Historial_Cambios> getHistorial_cambios_empleado() {
		return historial_cambios_empleado;
	}

	public void setHistorial_cambios_empleado(ArrayList<Historial_Cambios> historial_cambios_empleado) {
		this.historial_cambios_empleado = historial_cambios_empleado;
	}

	public ArrayList<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(ArrayList<Contrato> contratos) {
		this.contratos = contratos;
	}
}
