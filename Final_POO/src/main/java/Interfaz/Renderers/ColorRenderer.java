/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz.Renderers;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Ronald
 */

 public class ColorRenderer extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
            ArrayList<Color> colores = new ArrayList<>();
            colores.add(Color.orange);
            colores.add(Color.LIGHT_GRAY);
            colores.add(Color.BLUE);
            colores.add(Color.MAGENTA);
            colores.add(Color.cyan);
            colores.add(Color.green);
            
            
            ///TODO Check excepcions if necessary
            int numberSelected = Integer.parseInt((String)table.getModel().getValueAt(row, column));
            setBackground(colores.get(numberSelected));
            
            return this;
        }
    }