/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacion_de_Gestion;

public class Calendario {
    private int cod_empleado;
    private int n_semana;
    private Turno turno;

    public Calendario(int cod_empleado, int n_semana, Turno turno ) {
        this.cod_empleado = cod_empleado;
	this.n_semana = n_semana;
	this.turno = turno;
    }

    ///GETTERS Y SETTERS

    public int getCod_empleado() {
        return cod_empleado;
    }

    public void setCod_empleado(int cod_empleado) {
        this.cod_empleado = cod_empleado;
    }

    public int getN_semana() {
        return 	n_semana;
    }

    public void setN_semana(int n_semana) {
	this.n_semana = n_semana;
    }

    public Turno getTurno() {
 	return turno;
    }

    public void setTurno(Turno turno) {
	this.turno = turno;
    }
        
}
