package Comparadores;
import java.util.Comparator;
import Aplicacion_de_Gestion.Turno;

public class CriterioTurnosDia implements Comparator<Turno> {

	@Override
	public int compare(Turno t1, Turno t2) {
		if (t1.getDia() < t2.getDia()) return -1;
		if (t1.getDia() > t2.getDia()) return 1;
		else return 0;
	}
	

}
