/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacion_de_Gestion;

public class Principal {
    public static Gestion_Empleado Empresa;
    
    public Principal() {
        Empresa = new Gestion_Empleado(40,"RisBel","Avenida el Sol","678456321");

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
	
        for (int i=15; i<20; i++) {
            emp1.AgregarComentarioHistorial("cambió turno "+(Math.random()*100), (((int)(Math.random()*100))%2==0));
            emp2.AgregarComentarioHistorial("cambió turno "+(Math.random()*100), (((int)(Math.random()*100))%2==0));
            emp3.AgregarComentarioHistorial("cambió turno "+(Math.random()*100), (((int)(Math.random()*100))%2==0));
            emp4.AgregarComentarioHistorial("cambió turno "+(Math.random()*100), (((int)(Math.random()*100))%2==0));
            emp5.AgregarComentarioHistorial("cambió turno "+(Math.random()*100), (((int)(Math.random()*100))%2==0));
        }
        
        emp1.getHistorial_empleado().setCant_contrato_vacaciones(1);
        emp1.getHistorial_empleado().setCant_contrato_24h(5);
        emp1.getHistorial_empleado().setCant_contrato_32h(7);
        emp1.getHistorial_empleado().setCant_contrato_40h(2);
        emp1.getHistorial_empleado().setCant_dias_vacaciones(8);
        
        emp2.getHistorial_empleado().setCant_contrato_vacaciones(4);
        emp2.getHistorial_empleado().setCant_contrato_24h(12);
        emp2.getHistorial_empleado().setCant_contrato_32h(3);
        emp2.getHistorial_empleado().setCant_contrato_40h(2);
        emp2.getHistorial_empleado().setCant_dias_vacaciones(13);
        
        emp3.getHistorial_empleado().setCant_contrato_vacaciones(4);
        emp3.getHistorial_empleado().setCant_contrato_24h(9);
        emp3.getHistorial_empleado().setCant_contrato_32h(0);
        emp3.getHistorial_empleado().setCant_contrato_40h(22);
        emp3.getHistorial_empleado().setCant_dias_vacaciones(31);
        
        emp4.getHistorial_empleado().setCant_contrato_vacaciones(5);
        emp4.getHistorial_empleado().setCant_contrato_24h(7);
        emp4.getHistorial_empleado().setCant_contrato_32h(8);
        emp4.getHistorial_empleado().setCant_contrato_40h(15);
        emp4.getHistorial_empleado().setCant_dias_vacaciones(21);
        
        emp5.getHistorial_empleado().setCant_contrato_vacaciones(6);
        emp5.getHistorial_empleado().setCant_contrato_24h(3);
        emp5.getHistorial_empleado().setCant_contrato_32h(12);
        emp5.getHistorial_empleado().setCant_contrato_40h(7);
        emp5.getHistorial_empleado().setCant_dias_vacaciones(13);
        
        Empresa.AgregarEmpleado(emp1);
        Empresa.AgregarEmpleado(emp2);
        Empresa.AgregarEmpleado(emp3);
        Empresa.AgregarEmpleado(emp4);
        Empresa.AgregarEmpleado(emp5);
    }
    
    public static void main (String[] args) {
	Empresa.Generador_Contratos_Algoritmo();
	Empresa.Generar_Calendario_ArrayList(40);
	Empresa.Imprimir_Calendario_Semanal();	
    }
    
}