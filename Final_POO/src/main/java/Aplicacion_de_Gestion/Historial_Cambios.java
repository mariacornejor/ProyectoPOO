/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Aplicacion_de_Gestion;

import java.util.Date;

public class Historial_Cambios {
    private String descripcion;
    private String estado;
    private Date fecha_cambio;
	
    public Historial_Cambios(String descripcion, String estado) {
	this.descripcion = descripcion;
	this.estado = estado;
	this.fecha_cambio = new Date();
    }

    ///SETTERS Y GETTERS
    public String getDescripcion() {
	return descripcion;
    }
        
    public String getEstado() {
	return estado;
    }

    public Date getFecha_cambio() {
	return fecha_cambio;
    }

}