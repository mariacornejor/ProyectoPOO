package Aplicacion_de_Gestion;

public class Historial_Empleado {
	private int cant_contrato_24h;
	private int cant_contrato_32h;
	private int cant_contrato_40h;
	private int cant_contrato_vacaciones;
	private int cant_dias_vacaciones;
	
	public Historial_Empleado() {
		this.cant_contrato_24h = 0; 
		this.cant_contrato_32h = 0; 
		this.cant_contrato_40h = 0; 
		this.cant_contrato_vacaciones = 0;
		this.cant_dias_vacaciones = 0;
	}

	public int getCant_contrato_24h() {
		return cant_contrato_24h;
	}

	public void setCant_contrato_24h(int cant_contrato_24h) {
		this.cant_contrato_24h = cant_contrato_24h;
	}

	public int getCant_contrato_32h() {
		return cant_contrato_32h;
	}

	public void setCant_contrato_32h(int cant_contrato_32h) {
		this.cant_contrato_32h = cant_contrato_32h;
	}

	public int getCant_contrato_40h() {
		return cant_contrato_40h;
	}

	public void setCant_contrato_40h(int cant_contrato_40h) {
		this.cant_contrato_40h = cant_contrato_40h;
	}

	public int getCant_contrato_vacaciones() {
		return cant_contrato_vacaciones;
	}

	public void setCant_contrato_vacaciones(int cant_contrato_vacaciones) {
		this.cant_contrato_vacaciones = cant_contrato_vacaciones;
	}

	public int getCant_dias_vacaciones() {
		return cant_dias_vacaciones;
	}

	public void setCant_dias_vacaciones(int cant_dias_vacaciones) {
		this.cant_dias_vacaciones = cant_dias_vacaciones;
	}

}
