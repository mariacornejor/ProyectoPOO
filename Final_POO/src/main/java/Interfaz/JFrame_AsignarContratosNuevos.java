/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Aplicacion_de_Gestion.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ronald
 */
public class JFrame_AsignarContratosNuevos extends javax.swing.JFrame {

    private int opc;
    private int weekNumber;
    private HashMap<Integer,Integer> NumContrato_Codigoempleados; 
    private ArrayList<Integer> codigosEmpleadosValidos;    
    private ArrayList<Integer> codigosContratosDisponibles;
    private ArrayList<Calendario> datosCalendario;

    ///TODO: Si semanaVista< semanaActual!!! Entonces no es posible hacer modificaciones del contrato!!!
    /**
     * Creates new form JFrame_AsignarContratosNuevos
     * @param opc Modelo turnos semanales de los 4 disponibles para elegir 
     */
    public JFrame_AsignarContratosNuevos(int opc, int weekNumber) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Asignar contratos");
        
        
        
        this.jComboBox_changeEmpleados1.removeAllItems();
        this.jComboBox_changeEmpleados2.removeAllItems();
        
        
        this.opc = opc;
        this.weekNumber = weekNumber;
        this.datosCalendario = Gestion_Empresa.getInstance().get_Opciones_Calendarios_Semanales().get(opc);
        
        initData();
        
        RefreshTableCalendarData();
        
    }

    
    private void initData() {
        
        this.NumContrato_Codigoempleados = new HashMap();
        
        ///Inicializo los codigos de empleado
        this.codigosEmpleadosValidos = new ArrayList<>();
        for(Empleado e: Gestion_Empresa.getInstance().getEmpleados())
        {
            if(e.getestado_empleado() && !(e.getContrato(weekNumber) instanceof Contrato_Vacaciones)) //Empleado activo y no tiene vacaciones esta semana
            {
                codigosEmpleadosValidos.add(e.getCod_empleado());
            }
        }
        
        ///Inicializo los codigos de contratos
        codigosContratosDisponibles = new ArrayList<>();
        int cant=6;
        if(opc == 3)
            cant = 5;
        
        for(int i=0;i<cant;i++)
            codigosContratosDisponibles.add(i);
        
        RefreshComboBoxEmpleados();
        RefreshComboBoxContratos();
        
    }
    
    public void RefreshComboBoxEmpleados()
    {
        this.jComboBox_Empleados.removeAllItems();
        for(int CodigoEmpleado : this.codigosEmpleadosValidos)
        {
            Empleado e = Gestion_Empresa.getInstance().getEmpleado(CodigoEmpleado);
            String ItemDescripcion = String.format("%d - %s", CodigoEmpleado,e.getNombreCompleto()) ;
            this.jComboBox_Empleados.addItem(ItemDescripcion);
        }
    }
    
    public void RefreshComboBoxContratos()
    {
        this.jComboBox_NumeroContratos.removeAllItems();
        for(int CodigoContrato : this.codigosContratosDisponibles)
        {
            this.jComboBox_NumeroContratos.addItem(String.valueOf(CodigoContrato));
        }
    }
    
    public void RefreshTableCalendarData()
    {
        DefaultTableModel model = (DefaultTableModel)this.jTable_calendarios0.getModel();
        for(int i=0;i<model.getRowCount();)
            model.removeRow(0);
        
        
                
        int counter=0;
        if(datosCalendario.size()==21)
        {
            for(int i=0;i<3;i++)
            {
                Object rowData[]= new Object[7];
                for(int j=0;j<7;j++)
                {
                    rowData[j] = datosCalendario.get(counter++).getCod_empleado();
                }
                model.addRow(rowData);
            }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_calendarios0 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jComboBox_Empleados = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox_NumeroContratos = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jComboBox_changeEmpleados1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox_changeEmpleados2 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton_ConfirmarCambio = new javax.swing.JButton();
        jButton_saveCalendar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable_calendarios0.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_calendarios0.setColumnSelectionAllowed(true);
        jTable_calendarios0.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable_calendarios0);
        jTable_calendarios0.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Empleado", "Código", "#Contrato", "#Horas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Asignar Contratos a Empleados");

        jComboBox_Empleados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Empleado:");

        jLabel2.setText("Contrato:");

        jComboBox_NumeroContratos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Asignar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Asignar manualmente");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(10, 10, 10)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBox_NumeroContratos, 0, 170, Short.MAX_VALUE)
                            .addComponent(jComboBox_Empleados, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_Empleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_NumeroContratos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jComboBox_changeEmpleados1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Empleado1 :");

        jLabel6.setText("Empleado 2:");

        jComboBox_changeEmpleados2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton2.setText("Cambio");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Cambiar Contratos");

        jButton_ConfirmarCambio.setText("Confirmar");
        jButton_ConfirmarCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ConfirmarCambioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(10, 10, 10)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox_changeEmpleados1, 0, 83, Short.MAX_VALUE)
                            .addComponent(jComboBox_changeEmpleados2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(93, 93, 93)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_ConfirmarCambio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_changeEmpleados1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_changeEmpleados2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jButton_ConfirmarCambio))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jButton_saveCalendar.setText("Guardar Calendario Semanal");
        jButton_saveCalendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_saveCalendarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_saveCalendar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addComponent(jButton_saveCalendar)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(this.codigosContratosDisponibles.size() >0)
        {
            int indexEmpleado = this.jComboBox_Empleados.getSelectedIndex() ;
            int indexContrato = this.jComboBox_NumeroContratos.getSelectedIndex();

            int codigoEmpleado = this.codigosEmpleadosValidos.get( indexEmpleado);
            int codigoContrato = this.codigosContratosDisponibles.get ( indexContrato );

            this.NumContrato_Codigoempleados.put(codigoContrato,codigoEmpleado);


            
            ///Ahora los quito y agrego a la tabla
            this.codigosEmpleadosValidos.remove(indexEmpleado);
            this.codigosContratosDisponibles.remove(indexContrato);

            RefreshComboBoxContratos();
            RefreshComboBoxEmpleados();
            
            int cantHoras = 0;
            for(int i=0; i<this.datosCalendario.size();i++)
                if(this.datosCalendario.get(i).getCod_empleado() == codigoContrato)
                    cantHoras += 8;
            
            Object rowData[] = new Object[4];
            rowData[0] = Gestion_Empresa.getInstance().getEmpleado(codigoEmpleado).getNombreCompleto() ;
            rowData[1] = codigoEmpleado;
            rowData[2] =codigoContrato ; 
            rowData[3] = cantHoras;
            ((DefaultTableModel)this.jTable1.getModel()).addRow(rowData);

        }
        else
        {
            JOptionPane.showMessageDialog(null, "Ya has asignado todos los contratos","Contratos asignados",JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(this.jTable1.getRowCount() < 2)
        {
            JOptionPane.showMessageDialog(null, "Necesitas tener más contratos asignados para poder hacer un cambios","Operación Inválida",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        this.jComboBox_changeEmpleados1.setEnabled(true);
        this.jComboBox_changeEmpleados2.setEnabled(true);
        this.jButton_ConfirmarCambio.setEnabled(true);
        
        ///Inicializo los combobox
        this.jComboBox_changeEmpleados1.removeAllItems();
        this.jComboBox_changeEmpleados2.removeAllItems();
        
        DefaultTableModel model = ((DefaultTableModel)this.jTable1.getModel());
        for(int row=0; row<model.getRowCount() ; row++)
        {
            this.jComboBox_changeEmpleados1.addItem(String.valueOf(model.getValueAt(row, 1)));
            this.jComboBox_changeEmpleados2.addItem(String.valueOf(model.getValueAt(row, 1)));
        }
        
        

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton_ConfirmarCambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ConfirmarCambioActionPerformed
        // TODO add your handling code here:
        int index1,index2;
        index1 = this.jComboBox_changeEmpleados1.getSelectedIndex();
        index2 = this.jComboBox_changeEmpleados2.getSelectedIndex();
        if(index1 != index2)
        {
            //Cambio valido
        }
        else
        {
            
        }
        
        this.jComboBox_changeEmpleados1.setEnabled(false);
        this.jComboBox_changeEmpleados2.setEnabled(false);
        this.jButton_ConfirmarCambio.setEnabled(false);
        
    }//GEN-LAST:event_jButton_ConfirmarCambioActionPerformed

    private void jButton_saveCalendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_saveCalendarActionPerformed
        // TODO add your handling code here:
        if(this.codigosContratosDisponibles.size()!=0)
        {
            JOptionPane.showMessageDialog(null, "Es necesario asignar todos los contratos primero", "Datos faltantes", JOptionPane.INFORMATION_MESSAGE);
            return;
        }   
        NumContrato_Codigoempleados = new HashMap<>();
        
        for(int row =0 ;row < this.jTable1.getRowCount() ; row++)
        {
            int codigoEmpleado = (int)jTable1.getValueAt(row, 1);
            int numContrato = (int)jTable1.getValueAt(row, 2);
            NumContrato_Codigoempleados.put(numContrato,codigoEmpleado);
        }
        
        Gestion_Empresa.getInstance().Asignar_Contratos_Automaticamente(NumContrato_Codigoempleados, weekNumber, opc);
        JOptionPane.showMessageDialog(null, "Actualiza el calendario", "Contratos generados con éxito", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
        
        
    }//GEN-LAST:event_jButton_saveCalendarActionPerformed

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
            java.util.logging.Logger.getLogger(JFrame_AsignarContratosNuevos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrame_AsignarContratosNuevos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrame_AsignarContratosNuevos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame_AsignarContratosNuevos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrame_AsignarContratosNuevos(0,1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton_ConfirmarCambio;
    private javax.swing.JButton jButton_saveCalendar;
    private javax.swing.JComboBox<String> jComboBox_Empleados;
    private javax.swing.JComboBox<String> jComboBox_NumeroContratos;
    private javax.swing.JComboBox<String> jComboBox_changeEmpleados1;
    private javax.swing.JComboBox<String> jComboBox_changeEmpleados2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable_calendarios0;
    // End of variables declaration//GEN-END:variables


    
}
