package Aplicacion_de_Gestion;

public class Turno extends Contrato {
	private int dia;
	private tipo_turno horario;
	
	public Turno() {
		// TODO Auto-generated constructor stub
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public tipo_turno getHorario() {
		return horario;
	}

	public void setHorario(tipo_turno horario) {
		this.horario = horario;
	}

}
