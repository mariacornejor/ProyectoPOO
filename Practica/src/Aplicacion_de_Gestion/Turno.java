package Aplicacion_de_Gestion;

public class Turno {
	private int dia;
	private tipo_turno horario;
	
	public Turno(int dia, tipo_turno horario) {
		this.dia = dia;
		this.horario = horario;
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
