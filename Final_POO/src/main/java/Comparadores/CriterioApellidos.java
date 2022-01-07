/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparadores;

import Aplicacion_de_Gestion.Empleado;
import java.util.Comparator;

/**
 *
 * @author Ronald
 */
public class CriterioApellidos implements Comparator< Empleado> {

	@Override
	public int compare(Empleado e1, Empleado e2) {
            return e1.getApellido().compareTo(e2.getApellido());
	}
}
