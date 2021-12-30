/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comparadores;

import java.util.Comparator;

import Aplicacion_de_Gestion.Calendario;

public class CriterioCalendario implements Comparator< Calendario> {
    @Override
    public int compare(Calendario c1, Calendario c2) {
	int valorC1 = c1.getTurno().getDia()*3 + c1.getTurno().getHorario().ordinal();
	int valorC2 = c2.getTurno().getDia()*3 + c2.getTurno().getHorario().ordinal();
	
	if(valorC1 == valorC2)
            return 0;
	if(valorC1< valorC2)
            return -1;
	else
            return 1;
    }
        
}
