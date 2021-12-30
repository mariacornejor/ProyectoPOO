package Comparadores;
import java.util.Comparator;
import Aplicacion_de_Gestion.Turno;

public class CriterioTurnosDia implements Comparator<Turno> {

	@Override
	public int compare(Turno t1, Turno t2) {
		
		int valorC1 = t1.getDia()*3 + t1.getHorario().ordinal();
		int valorC2 = t2.getDia()*3 + t2.getHorario().ordinal();
	
		
		if(valorC1 == valorC2)
			return 0;
		if(valorC1< valorC2)
			return -1;
		else
			return 1;
		
	}
	

}
