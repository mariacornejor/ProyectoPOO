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
		
	
	public Contrato(Contrato c)
	{
		this.num_semana = c.num_semana;
		this.cant_horas_contrato = c.cant_horas_contrato;
		this.salario = c.salario;

		this.turnos =  new ArrayList<Turno>();
		for(Turno t : c.turnos)
			this.turnos.add(new Turno(t.getDia(),t.getHorario()));	
		
	}


	public boolean Validar_Turnos_Consecutivos_24h() {
		///Validar dias consecutivos y no menos de 24 horas entre turnos
//TODO
		if(this.turnos.size()==1)
			return true;
		
		
		
		turnos.sort(new CriterioTurnosDia());
		
		for(int i=0; i<this.turnos.size()-1 ;i++) {
			///Dias consecutivos de trabajo
			if((this.turnos.get(i).getDia()+1) != this.turnos.get(i+1).getDia()) { //dia consecutivo
				return false;
			}
			//24 horas minimas
			int turno1 = this.turnos.get(i).getDia()*3 + this.turnos.get(i).getHorario().ordinal();
			int turno2 = this.turnos.get(i+1).getDia()*3 + this.turnos.get(i+1).getHorario().ordinal();
			if(Math.abs((turno2-turno1)) < 3) { //no menos de 24h
				return false;
			}
		}
		
		return true;
		
		//TODO Borrar si no se usa al final
		/* 
		
		///validar dias consecutivos
		if(this.turnos.get(0).getDia() == 0 && (this.turnos.get(this.turnos.size()-1).getDia()+1)%7 == 0)
		{	///Ultimo dia y primeo de la semana, son consecutivos
			///pos del primero despues del break!?
			///Dias consecutivos de vacaciones!!!
			
			for(Turno t : this.turnos )
			{
				
			}
			
			
			
		}
		else ///Dias consecutivos solo entre semana
		{
			
			for(int i=0; i<this.turnos.size()-1 ;i++) {
				///Dias consecutivos de trabajo
				if((this.turnos.get(i).getDia()+1)%7 != this.turnos.get(i+1).getDia()%7) { //dia consecutivo
					return false;
				}
				//24 horas minimas
				int turno1 = this.turnos.get(i).getDia()*3 + this.turnos.get(i).getHorario().ordinal();
				int turno2 = this.turnos.get(i+1).getDia()*3 + this.turnos.get(i+1).getHorario().ordinal();
				if(Math.abs((turno2-turno1)) < 3) { //no menos de 24h
					return false;
				}
			}
		}
		
		return true;
		*/
	}
	
	
	public boolean VerificarValidezContrato()
	{
		if(Validar_Turnos_Consecutivos_24h())
			if(turnos.size()*8 == cant_horas_contrato) 
				return true;
		return false;
	}

	public boolean Hacer_Cambio_Turno(Turno Turno_IN, Turno Turno_OUT) {
		
		if(!this.turnos.contains(Turno_OUT))
			return false;
		
		this.turnos.remove(Turno_OUT);
		this.turnos.add(Turno_IN);
		if(Validar_Turnos_Consecutivos_24h())
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
