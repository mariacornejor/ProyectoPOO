package Aplicacion_de_Gestion;

public class Principal {

	public static void main (String[] args) {
		
		new Formulario().setVisible(true);
		
		Gestion_Empleado Empresa = new Gestion_Empleado(40,"RisBel","Avenida el Sol","678456321");

		Empleado emp1 = new Empleado("17463971A", "Alma", "Swift", "674821998", "06-06-1990", "ES 12 1756 1866 15 2834510863", "Santander");
		Empleado emp2 = new Empleado("22748614D", "Jorge", "Gomez", "647262553", "2000-12-25", "ES 12 5636 1993 12 3023713562", "BBVA");
		Empleado emp3 = new Empleado("92568301V", "Lucia", "Vegas", "674969402", "1971-10-18", "ES 16 2014 1866 16 2343506463", "Santander");
		Empleado emp4 = new Empleado("12201438M", "Santiago", "Ross", "624157606", "1998-01-16", "ES 19 1195 1866 19 1053086952", "LiberBank");
		Empleado emp5 = new Empleado("73793113S", "Lana", "Fuentes", "692613093", "1997-11-04", "ES 11 1756 4622 11 5208503966", "BBVA");
		
		emp1.getContratos().add(new Contrato_40h(40));  
		emp2.getContratos().add(new Contrato_40h(40));  
		emp3.getContratos().add(new Contrato_40h(40));  
		emp4.getContratos().add(new Contrato_24h(40)); 
		emp5.getContratos().add(new Contrato_24h(40)); 
		
		Empresa.getEmpleados().add(emp1);
		Empresa.getEmpleados().add(emp2);
		Empresa.getEmpleados().add(emp3);
		Empresa.getEmpleados().add(emp4);
		Empresa.getEmpleados().add(emp5);
		
		Empresa.Generador_Contratos_Algoritmo();
		Empresa.Generar_Calendario_ArrayList(40);
		Empresa.Imprimir_Calendario_Semanal();
		
		
		
		
	}

}
