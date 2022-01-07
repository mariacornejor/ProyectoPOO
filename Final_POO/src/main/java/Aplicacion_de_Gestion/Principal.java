/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacion_de_Gestion;

import java.util.HashMap;

public class Principal {
    
    public Principal() {

        Empleado emp1 = new Empleado("17463971A", "Alma", "Swift", "674821998", "1990-06-06", "ES 12 1756 1866 15 2834510863", "Santander");
        Empleado emp2 = new Empleado("22748614D", "Jorge", "Gomez", "647262553", "2000-12-25", "ES 12 5636 1993 12 3023713562", "BBVA");
        Empleado emp3 = new Empleado("92568301V", "Lucia", "Vegas", "674969402", "1971-10-18", "ES 16 2014 1866 16 2343506463", "Santander");
	Empleado emp4 = new Empleado("12201438M", "Santiago", "Ross", "624157606", "1998-01-16", "ES 19 1195 1866 19 1053086952", "LiberBank");
	Empleado emp5 = new Empleado("73793113S", "Lana", "Fuentes", "692613093", "1997-11-04", "ES 11 1756 4622 11 5208503966", "BBVA");
        Empleado emp6 = new Empleado("83333071R", "Ronald", "Ris", "692613093", "1999-12-25", "ES 11 1756 4622 55 5208503966", "Santander");

	/*
	
        for (int i=15; i<20; i++) {
            emp1.AgregarComentarioHistorial("cambió turno "+(Math.random()*100), (((int)(Math.random()*100))%2==0));
            emp2.AgregarComentarioHistorial("cambió turno "+(Math.random()*100), (((int)(Math.random()*100))%2==0));
            emp3.AgregarComentarioHistorial("cambió turno "+(Math.random()*100), (((int)(Math.random()*100))%2==0));
            emp4.AgregarComentarioHistorial("cambió turno "+(Math.random()*100), (((int)(Math.random()*100))%2==0));
            emp5.AgregarComentarioHistorial("cambió turno "+(Math.random()*100), (((int)(Math.random()*100))%2==0));
            emp6.AgregarComentarioHistorial("cambió turno "+(Math.random()*100), (((int)(Math.random()*100))%2==0));

        }
        
        emp1.getHistorial_empleado().setCant_contrato_vacaciones(1);
        emp1.getHistorial_empleado().setCant_contrato_24h(5);
        emp1.getHistorial_empleado().setCant_contrato_32h(7);
        emp1.getHistorial_empleado().setCant_contrato_40h(2);
        
        emp2.getHistorial_empleado().setCant_contrato_vacaciones(4);
        emp2.getHistorial_empleado().setCant_contrato_24h(12);
        emp2.getHistorial_empleado().setCant_contrato_32h(3);
        emp2.getHistorial_empleado().setCant_contrato_40h(2);
        
        emp3.getHistorial_empleado().setCant_contrato_vacaciones(4);
        emp3.getHistorial_empleado().setCant_contrato_24h(9);
        emp3.getHistorial_empleado().setCant_contrato_32h(0);
        emp3.getHistorial_empleado().setCant_contrato_40h(22);
        
        emp4.getHistorial_empleado().setCant_contrato_vacaciones(5);
        emp4.getHistorial_empleado().setCant_contrato_24h(7);
        emp4.getHistorial_empleado().setCant_contrato_32h(8);
        emp4.getHistorial_empleado().setCant_contrato_40h(15);
        
        emp5.getHistorial_empleado().setCant_contrato_vacaciones(6);
        emp5.getHistorial_empleado().setCant_contrato_24h(3);
        emp5.getHistorial_empleado().setCant_contrato_32h(12);
        emp5.getHistorial_empleado().setCant_contrato_40h(7);
        
        emp6.getHistorial_empleado().setCant_contrato_vacaciones(1);
        emp6.getHistorial_empleado().setCant_contrato_24h(5);
        emp6.getHistorial_empleado().setCant_contrato_32h(7);
        emp6.getHistorial_empleado().setCant_contrato_40h(2);
        
        */
        
        
        Gestion_Empresa.getInstance().AgregarEmpleado(emp1);
        Gestion_Empresa.getInstance().AgregarEmpleado(emp2);
        Gestion_Empresa.getInstance().AgregarEmpleado(emp3);
        Gestion_Empresa.getInstance().AgregarEmpleado(emp4);
        Gestion_Empresa.getInstance().AgregarEmpleado(emp5);
        Gestion_Empresa.getInstance().AgregarEmpleado(emp6);
        
        HashMap<Integer, Integer> NumContrato_Codigoempleados = new HashMap<>();
        int ContractNumber = 0;
        for(Empleado e: Gestion_Empresa.getInstance().getEmpleados())
        {
            NumContrato_Codigoempleados.put(ContractNumber, e.getCod_empleado());
            ContractNumber++;
        }
        Gestion_Empresa.getInstance().Asignar_Contratos_Automaticamente(NumContrato_Codigoempleados,1,0);
        Gestion_Empresa.getInstance().Asignar_Contratos_Automaticamente(NumContrato_Codigoempleados,2,1);
        Gestion_Empresa.getInstance().Asignar_Contratos_Automaticamente(NumContrato_Codigoempleados,3,1);
        Gestion_Empresa.getInstance().Asignar_Contratos_Automaticamente(NumContrato_Codigoempleados,4,0);
        Gestion_Empresa.getInstance().Asignar_Contratos_Automaticamente(NumContrato_Codigoempleados,5,0);
        Gestion_Empresa.getInstance().Asignar_Contratos_Automaticamente(NumContrato_Codigoempleados,6,2);
        Gestion_Empresa.getInstance().Asignar_Contratos_Automaticamente(NumContrato_Codigoempleados,7,0);
        Gestion_Empresa.getInstance().Asignar_Contratos_Automaticamente(NumContrato_Codigoempleados,8,1);
        Gestion_Empresa.getInstance().Asignar_Contratos_Automaticamente(NumContrato_Codigoempleados,9,1);
        Gestion_Empresa.getInstance().Asignar_Contratos_Automaticamente(NumContrato_Codigoempleados,10,0);
        Gestion_Empresa.getInstance().Asignar_Contratos_Automaticamente(NumContrato_Codigoempleados,11,0);
        Gestion_Empresa.getInstance().Asignar_Contratos_Automaticamente(NumContrato_Codigoempleados,12,2);
        Gestion_Empresa.getInstance().Asignar_Contratos_Automaticamente(NumContrato_Codigoempleados,13,0);
        

        
        

        

    }
    
    public static void main (String[] args) {
	Gestion_Empresa.getInstance().Generador_Contratos_Algoritmo();
	Gestion_Empresa.getInstance().Generar_Calendario_ArrayList(40);
	Gestion_Empresa.getInstance().Imprimir_Calendario_Semanal();	
    }
    
}