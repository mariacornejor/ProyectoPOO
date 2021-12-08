package Aplicacion_de_Gestion;

import java.util.ArrayList;

import Comparadores.CriterioTurnosDia;

public class Contrato {
	protected int num_semana;
	protected int cant_horas_contrato;
	protected double salario;
	protected boolean finde_libre;
	protected ArrayList<Turno> turnos;
	
	public Contrato(int num_semana,int cant_horas_contrato,double salario) {
		this.num_semana =num_semana ;
		this.cant_horas_contrato =cant_horas_contrato ;
		this.salario =salario ;
		this.turnos =  new ArrayList<Turno>();
	}
	
	private boolean Validar_Turnos(ArrayList<Turno> Turno) {
		///TODO Validar dias consecutivos y no menos de 24 horas entre turnos
		if(turnos.size()*8 != cant_horas_contrato) {
			return false;
		}
		
		turnos.sort(new CriterioTurnosDia());
		for(int i=0; i<this.turnos.size()-1 ;i++) {
			if(this.turnos.get(i).getDia()+1 != this.turnos.get(i+1).getDia()) { //dia consecutivo
				return false;
			}
			
			int turno1 = this.turnos.get(i).getDia()*3 + this.turnos.get(i).getHorario().ordinal();
			int turno2 = this.turnos.get(i).getDia()*3 + this.turnos.get(i).getHorario().ordinal();
			if(Math.abs((turno2-turno1)) < 3) { //no menos de 24h
				return false;
			}
		}
		return true;
	}
	
	public boolean Hacer_Cambio(Turno Turno_IN, Turno Turno_OUT) {
		this.turnos.remove(Turno_OUT);
		this.turnos.add(Turno_IN);
		if(Validar_Turnos(this.turnos))
		{
			///Es un cambio valido
			return true;
		}
		else
		{
			///Cambio no valido, hay que deshacer el cambio
			this.turnos.remove(Turno_IN);
			this.turnos.add(Turno_OUT);
			return false;
		}	
	}

	///Getters y Setters
	public int getNum_semana() {
		return num_semana;
	}
	public void setNum_semana(int num_semana) {
		this.num_semana = num_semana;
	}

	public int getCant_horas_contrato() {
		return cant_horas_contrato;
	}
	public void setCant_horas_contrato(int cant_horas_contrato) {
		this.cant_horas_contrato = cant_horas_contrato;
	}

	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}

	public boolean isFinde_libre() {
		return finde_libre;
	}
	public void setFinde_libre(boolean finde_libre) {
		this.finde_libre = finde_libre;
	}
	
	public ArrayList<Turno> getTurnos() {
		return turnos;
	}
	public void setTurnos(ArrayList<Turno> turnos) {
		this.turnos = turnos;
	}

}
