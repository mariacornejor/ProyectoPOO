package Aplicacion_de_Gestion;

import java.util.ArrayList;

public class Empleado {
	private int cod_empleado;
	private boolean estado_empleado;
	private String dni, nombre, apellido, telefono;
	private String fecha_nacimiento;
	private String num_cuenta, banco_cuenta;
	
	private Historial_Empleado historial_empleado;
	private ArrayList<Historial_Cambios> historia_cambios_empleado; 
	private ArrayList<Contrato> contratos; 
	
	public Empleado(String dni, String nombre, String apellido, String telefono, String fecha_nacimiento, String num_cuenta, String banco_cuenta) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.fecha_nacimiento = fecha_nacimiento;
		this.banco_cuenta = banco_cuenta;
		this.num_cuenta = num_cuenta;
		
		ArrayList<Historial_Cambios> historia_cambios_empleado = new ArrayList<Historial_Cambios>(); 
		ArrayList<Contrato> contratos = new ArrayList<Contrato>();
	}
	
	public Boolean CambioContrato(Contrato nuevo_contrato, int num_semana) {
		
	}
	
	public boolean CambioHorario(Turno turno_eliminar, Turno turno_agregar, int num_semana) {
		
	}
	
	public void AgregarComentHisorial(String comentario, boolean estado) {
		
	}
	
	public boolean Mover_Contrato_al_Historial(int num_semana) {
		
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

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
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

	public ArrayList<Historial_Cambios> getHistoria_cambios_empleado() {
		return historia_cambios_empleado;
	}

	public void setHistoria_cambios_empleado(ArrayList<Historial_Cambios> historia_cambios_empleado) {
		this.historia_cambios_empleado = historia_cambios_empleado;
	}

	public ArrayList<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(ArrayList<Contrato> contratos) {
		this.contratos = contratos;
	}
}
