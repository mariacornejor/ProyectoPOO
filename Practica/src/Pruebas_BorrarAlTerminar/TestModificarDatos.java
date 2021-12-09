package Pruebas_BorrarAlTerminar;

import java.util.ArrayList;

import javax.naming.directory.ModificationItem;

import Aplicacion_de_Gestion.Calendario;
import Aplicacion_de_Gestion.Turno;
import Aplicacion_de_Gestion.tipo_turno;

public class TestModificarDatos {

	public static void main(String[] args) {
		ConfirmarIgualObjectosDeDistintasInstancias();
		ModificarDatosInternosUsandoFor();
	}
	
	public static void ModificarDatosInternosUsandoFor()
	{

		ArrayList<Turno> turnos =  new ArrayList<Turno>();
		for(int i=0; i<5;i++)
		{
			Turno t =  new Turno(i, tipo_turno.morning);
			turnos.add(t);
		}

		for(int i=0; i<turnos.size();i++)
		{
			System.out.println(turnos.get(i).getDia() + " - " + turnos.get(i).getHorario().name());
		}
		Turno tTemp = null;
		for(Turno t : turnos) {
			t.setDia( t.getDia() + 1 );
			t.setHorario( tipo_turno.afternoon );
			tTemp = t; ///Referencia de memoria al objeto encontrado
		}
		tTemp.setHorario(tipo_turno.night); ///El cambio se hace tambien dentro de los datos del arraylist de turnos
		for(int i=0; i<turnos.size();i++)
		{
			System.out.println(turnos.get(i).getDia() + " - " + turnos.get(i).getHorario().name());
		}
		
		
		
	}
	
	public static void ConfirmarIgualObjectosDeDistintasInstancias()
	{
		System.out.println("Comprobando igualdad de dos intancias distintas con mismos valores");

		Turno tt1 = new Turno(0, tipo_turno.night);
		Turno tt2 = new Turno(0, tipo_turno.night);
		if(tt2.equals(tt1))
			System.out.println(true);
		else
			System.out.println(false);
		
		///Respuesta! FALSE!
		//No son iguales
		
		System.out.println("Comprobando igualdad de dos objetos donde uno sirvio de parametro del contructor del otro");
		Calendario cal =  new Calendario(0, 0, tt2);
		if(cal.getTurno().equals(tt2))
			System.out.println(true);
		else
			System.out.println(false);
			
	}

}
