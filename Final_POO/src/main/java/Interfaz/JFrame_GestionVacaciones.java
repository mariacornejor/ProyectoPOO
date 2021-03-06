/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Aplicacion_de_Gestion.Contrato;
import Aplicacion_de_Gestion.Contrato_Vacaciones;
import Aplicacion_de_Gestion.Empleado;
import Aplicacion_de_Gestion.Gestion_Empresa;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ronald
 */
public class JFrame_GestionVacaciones extends javax.swing.JFrame {

    int semanaActual;
    int cantVacaciones[];
    String textoVacio = "---";
    /**
     * Creates new form JFrame_GestionVacaciones
     */
    public JFrame_GestionVacaciones( int semanaActual) {
        initComponents();
        
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Gestionar Vacaciones");
        this.semanaActual = semanaActual;
        this.jLabel_semanaActual.setText("Semana Actual "+semanaActual);
        RefreshTableSemanas();
        RefreshTableCantSemanas();
        
        this.jComboBox_Empleados.removeAllItems();
        for(Empleado e: Gestion_Empresa.getInstance().getEmpleados())
        {
            jComboBox_Empleados.addItem(e.getCod_empleado() + " - "+e.getNombreCompleto());
        }
    }

    private void RefreshTableSemanas()
    {
        DefaultTableModel model  = (DefaultTableModel) this.jTable1.getModel();
        for(int i=0;i<model.getRowCount();)
            model.removeRow(0);
        
        ///Calculo datos de los empleados con vacaciones
        HashMap<Integer,Integer> hNumSemana_CodigoEmpleado = new HashMap<>();
        for(Empleado e: Gestion_Empresa.getInstance().getEmpleados())
        {
            for(Contrato c : e.getContratos())
            {
                if(c instanceof Contrato_Vacaciones)
                {
                    hNumSemana_CodigoEmpleado.put(c.getNum_semana(), e.getCod_empleado());
                }
            }
        }
        
        
        
        ///Lleno la tabla
        for(int i=1 ; i<=52; i++)
        {
            Object rowData[] = new Object[3];
            if(hNumSemana_CodigoEmpleado.containsKey(i))
            {
                rowData[0] = i;
                rowData[1] = hNumSemana_CodigoEmpleado.get(i);
                rowData[2] = Gestion_Empresa.getInstance().getEmpleado(hNumSemana_CodigoEmpleado.get(i)).getNombreCompleto();
            }
            else
            {
                rowData[0] = i;
                rowData[1] = textoVacio;
                rowData[2] = textoVacio;
                
            }
            model.addRow(rowData);
        }
        
    }
    private void RefreshTableCantSemanas()
    {
        DefaultTableModel model  = (DefaultTableModel) this.jTable_cantVacaciones.getModel();
        for(int i=0;i<model.getRowCount();)
            model.removeRow(0);
        
        cantVacaciones = new int[Gestion_Empresa.getInstance().getEmpleados().size()];
        for(int i=0 ; i<Gestion_Empresa.getInstance().getEmpleados().size(); i++)
        {
            cantVacaciones[i] = 0;
        }
        int pos = 0;
        for(Empleado e: Gestion_Empresa.getInstance().getEmpleados())
        {
            for(Contrato c : e.getContratos())
            {
                if(c instanceof Contrato_Vacaciones)
                {
                    cantVacaciones[pos]++;
                }
            }
            pos++;
        }
        
        pos=0;
        for(Empleado e: Gestion_Empresa.getInstance().getEmpleados())
        {
            Object rowData[] = new Object[3];
            rowData[0] = e.getCod_empleado();
            rowData[1] = e.getNombreCompleto();
            rowData[2] = cantVacaciones[pos];
            model.addRow(rowData);
            pos++;
        }        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel_semanaActual = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox_Empleados = new javax.swing.JComboBox<>();
        jButton_AsignarVacaciones = new javax.swing.JButton();
        jButton_EliminarVacaciones = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabelNombreSeleccionado = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_cantVacaciones = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);

        jPanel3.setBackground(new java.awt.Color(39, 40, 61));

        jTable1.setBackground(new java.awt.Color(124, 135, 167));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Num Semana", "CodEmpleado", "Empleado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable1);

        jLabel_semanaActual.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel_semanaActual.setForeground(new java.awt.Color(229, 229, 229));
        jLabel_semanaActual.setText("Semana Actual 1");

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(229, 229, 229));
        jLabel1.setText("Empleado:");

        jComboBox_Empleados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton_AsignarVacaciones.setText("Asignar");
        jButton_AsignarVacaciones.setBorderPainted(false);
        jButton_AsignarVacaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AsignarVacacionesActionPerformed(evt);
            }
        });

        jButton_EliminarVacaciones.setText("Eliminar");
        jButton_EliminarVacaciones.setBorderPainted(false);
        jButton_EliminarVacaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EliminarVacacionesActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(79, 92, 106));
        jPanel4.setRequestFocusEnabled(false);

        jLabelNombreSeleccionado.setFont(new java.awt.Font("Gill Sans MT", 1, 24)); // NOI18N
        jLabelNombreSeleccionado.setForeground(new java.awt.Color(229, 229, 229));
        jLabelNombreSeleccionado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelNombreSeleccionado.setText("Gestionar vacaciones");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNombreSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelNombreSeleccionado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        jLabel8.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(229, 229, 229));
        jLabel8.setText("Eliminar semana de vacaci??n");

        jTable_cantVacaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Empleado", "Vacaciones Asignadas"
            }
        ));
        jScrollPane1.setViewportView(jTable_cantVacaciones);
        if (jTable_cantVacaciones.getColumnModel().getColumnCount() > 0) {
            jTable_cantVacaciones.getColumnModel().getColumn(0).setResizable(false);
            jTable_cantVacaciones.getColumnModel().getColumn(0).setPreferredWidth(20);
        }

        jLabel9.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(229, 229, 229));
        jLabel9.setText("Asignar vacaciones");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel_semanaActual)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton_EliminarVacaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jComboBox_Empleados, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(42, 42, 42)
                                    .addComponent(jButton_AsignarVacaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_semanaActual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox_Empleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jButton_AsignarVacaciones))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jButton_EliminarVacaciones)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_AsignarVacacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AsignarVacacionesActionPerformed

        int index = this.jComboBox_Empleados.getSelectedIndex();
        if(cantVacaciones[index] >=4)
        {
            JOptionPane.showMessageDialog(this, "Solo puedes tener 4 semanas de vacaciones","Operaci??n Inv??lida",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if(this.jTable1.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(this, "Necesitas seleccionar una semana de la tabla","Operaci??n Inv??lida",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String codigoStr = this.jTable1.getValueAt(this.jTable1.getSelectedRow(), 1).toString();
        if(!codigoStr.equals(textoVacio))
        {
            JOptionPane.showMessageDialog(this, "Alguien m??s ya tiene vacaciones esta semana","Operaci??n Inv??lida",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        ///Verifico si no tiene contrato esa semana
        Empleado e = Gestion_Empresa.getInstance().getEmpleados().get(index);
        Contrato c = e.getContrato(this.jTable1.getSelectedRow() +1);
        if(c!=null)
        {
            JOptionPane.showMessageDialog(this, "El empleado seleccionado ya tiene un contrato esa semana, no es posible asignar vacaciones","Operaci??n Inv??lida",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        int numSemana = this.jTable1.getSelectedRow() +1;
        if(numSemana <= this.semanaActual)
        {
            JOptionPane.showMessageDialog(this, "No es posible asignar vacaciones de semanas antiguas o actuales","Operaci??n Inv??lida",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        c = new Contrato_Vacaciones(numSemana);
        e.getContratos().add(c);
        e.AgregarComentarioHistorial("Vacaciones asignadas para la semana "+numSemana, rootPaneCheckingEnabled);
        JOptionPane.showMessageDialog(this, "Vacaciones asignadas");
        
        RefreshTableCantSemanas();
        RefreshTableSemanas();
        
    }//GEN-LAST:event_jButton_AsignarVacacionesActionPerformed

    private void jButton_EliminarVacacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EliminarVacacionesActionPerformed
        // TODO add your handling code here:
        if(this.jTable1.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(this, "Necesitas seleccionar una semana de la tabla","Operaci??n Inv??lida",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        String codigoStr = this.jTable1.getValueAt(this.jTable1.getSelectedRow(), 1).toString();
        if(codigoStr.equals(textoVacio))
        {
            JOptionPane.showMessageDialog(this, "Nadie tiene vacaciones esta semana","Operaci??n Inv??lida",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int codigo = (int)Integer.parseInt(codigoStr);
        
        int numSemanaSel = this.jTable1.getSelectedRow() + 1;
        if(numSemanaSel <= this.semanaActual)
        {
            JOptionPane.showMessageDialog(this, "No es posible quitar vacaciones de semanas antiguas o actuales","Operaci??n Inv??lida",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Empleado e = Gestion_Empresa.getInstance().getEmpleado(codigo);
        Contrato c = e.getContrato(numSemanaSel);
        e.getContratos().remove(c);
        JOptionPane.showMessageDialog(this, "Vacaciones eliminadas con exito");
        e.AgregarComentarioHistorial("Vacaciones eliminadas con exito - semana "+numSemanaSel,true);
        
        int selectedRow = jTable1.getSelectedRow();
        RefreshTableCantSemanas();
        RefreshTableSemanas();
        jTable1.setRowSelectionInterval(selectedRow,selectedRow);
    }//GEN-LAST:event_jButton_EliminarVacacionesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrame_GestionVacaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrame_GestionVacaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrame_GestionVacaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame_GestionVacaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrame_GestionVacaciones(1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_AsignarVacaciones;
    private javax.swing.JButton jButton_EliminarVacaciones;
    private javax.swing.JComboBox<String> jComboBox_Empleados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelNombreSeleccionado;
    private javax.swing.JLabel jLabel_semanaActual;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable_cantVacaciones;
    // End of variables declaration//GEN-END:variables
}
