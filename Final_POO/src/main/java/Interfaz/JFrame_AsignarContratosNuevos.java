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
        
        this.jComboBox_changeEmpleados1.setEnabled(false);
        this.jComboBox_changeEmpleados2.setEnabled(false);
        this.jButton_ConfirmarCambio.setEnabled(false);
        
        
        
        
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
                    int codigoContrato = datosCalendario.get(counter++).getCod_empleado();
                    if(this.NumContrato_Codigoempleados.containsKey(codigoContrato))
                    {
                        int codigoEmpleado = this.NumContrato_Codigoempleados.get(codigoContrato);
                        rowData[j] = codigoContrato + " - "+ Gestion_Empresa.getInstance().getEmpleado(codigoEmpleado).getNombreCompleto();
                    }
                    else
                    {
                        rowData[j] = codigoContrato;
                    }
                }
                model.addRow(rowData);
            }
        }        
    }
    
    public void RefreshComboBoxCambioEmpleados()
    {
        
        ///Inicializo los combobox
        this.jComboBox_changeEmpleados1.removeAllItems();
        this.jComboBox_changeEmpleados2.removeAllItems();
        
        DefaultTableModel model = ((DefaultTableModel)this.jTable1.getModel());
        for(int row=0; row<model.getRowCount() ; row++)
        {
            this.jComboBox_changeEmpleados1.addItem(model.getValueAt(row, 1).toString() + " - "+ model.getValueAt(row, 0).toString());
            this.jComboBox_changeEmpleados2.addItem(model.getValueAt(row, 1).toString() + " - "+ model.getValueAt(row, 0).toString());
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_calendarios0 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox_Empleados = new javax.swing.JComboBox<>();
        jComboBox_NumeroContratos = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox_changeEmpleados1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton_ConfirmarCambio = new javax.swing.JButton();
        jComboBox_changeEmpleados2 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jButton_saveCalendar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabelNombreSeleccionado = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);

        jPanel3.setBackground(new java.awt.Color(39, 40, 61));

        jTable1.setBackground(new java.awt.Color(124, 135, 167));
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

        jTable_calendarios0.setBackground(new java.awt.Color(223, 223, 223));
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

        jLabel4.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(229, 229, 229));
        jLabel4.setText("Asignar manualmente");

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(229, 229, 229));
        jLabel1.setText("Empleado:");

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(229, 229, 229));
        jLabel2.setText("Contrato:");

        jComboBox_Empleados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox_NumeroContratos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Asignar");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(229, 229, 229));
        jLabel7.setText("Cambiar Contratos");

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(229, 229, 229));
        jLabel5.setText("Empleado1 :");

        jComboBox_changeEmpleados1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton2.setText("Cambio");
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton_ConfirmarCambio.setText("Confirmar");
        jButton_ConfirmarCambio.setBorderPainted(false);
        jButton_ConfirmarCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ConfirmarCambioActionPerformed(evt);
            }
        });

        jComboBox_changeEmpleados2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(229, 229, 229));
        jLabel6.setText("Empleado 2:");

        jButton_saveCalendar.setText("Guardar Calendario Semanal");
        jButton_saveCalendar.setBorderPainted(false);
        jButton_saveCalendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_saveCalendarActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(79, 92, 106));
        jPanel4.setRequestFocusEnabled(false);

        jLabelNombreSeleccionado.setFont(new java.awt.Font("Gill Sans MT", 1, 24)); // NOI18N
        jLabelNombreSeleccionado.setForeground(new java.awt.Color(229, 229, 229));
        jLabelNombreSeleccionado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelNombreSeleccionado.setText("Asignar contratos a empleados");

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

        jButton3.setText("Quitar empleado");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(10, 10, 10)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox_Empleados, 0, 120, Short.MAX_VALUE)
                                    .addComponent(jComboBox_NumeroContratos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(42, 42, 42)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(10, 10, 10)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox_changeEmpleados1, 0, 111, Short.MAX_VALUE)
                                    .addComponent(jComboBox_changeEmpleados2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton_ConfirmarCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(98, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_saveCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(125, 125, 125))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(9, 9, 9)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox_Empleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox_NumeroContratos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(8, 8, 8)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox_changeEmpleados1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox_changeEmpleados2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jButton_ConfirmarCambio))
                        .addGap(42, 42, 42)
                        .addComponent(jButton_saveCalendar))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            
            RefreshComboBoxCambioEmpleados();
            RefreshTableCalendarData();

        }
        else
        {
            JOptionPane.showMessageDialog(this, "Ya has asignado todos los contratos","Contratos asignados",JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(this.jTable1.getRowCount() < 2)
        {
            JOptionPane.showMessageDialog(this, "Necesitas tener más contratos asignados para poder hacer un cambios","Operación Inválida",JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        this.jComboBox_changeEmpleados1.setEnabled(true);
        this.jComboBox_changeEmpleados2.setEnabled(true);
        this.jButton_ConfirmarCambio.setEnabled(true);
        
        RefreshComboBoxCambioEmpleados();
        

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton_ConfirmarCambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ConfirmarCambioActionPerformed
        // TODO add your handling code here:

        int index1 = this.jComboBox_changeEmpleados1.getSelectedIndex();
        int index2 = this.jComboBox_changeEmpleados2.getSelectedIndex();
        
        if(index1 != index2)
        {
            //Cambio los contratos!
            DefaultTableModel model = ((DefaultTableModel)this.jTable1.getModel());
            
            String nombre1 = model.getValueAt(index1, 0).toString();
            int codigo1 = Integer.parseInt(model.getValueAt(index1, 1).toString());
            int key1 = Integer.parseInt(model.getValueAt(index1, 2).toString());
            int cantH1 = Integer.parseInt(model.getValueAt(index1, 3).toString());
            
            String nombre12 = model.getValueAt(index2, 0).toString();
            int codigo2 = Integer.parseInt(model.getValueAt(index2, 1).toString());
            int key2 = Integer.parseInt(model.getValueAt(index2, 2).toString());
            int cantH2 = Integer.parseInt(model.getValueAt(index2, 3).toString());
            
            
            for(int row=0; row<model.getRowCount() ; row++)
            {
                int codigoEmpleadoTabla = Integer.parseInt(model.getValueAt(row, 1).toString());
                if(codigoEmpleadoTabla == codigo1   || codigoEmpleadoTabla == codigo2)
                {
                    model.removeRow(row);
                    row--;
                }
            }
            
            Object rowData1[] = new Object[4];
            rowData1[0] = nombre12;
            rowData1[1] = codigo1;
            rowData1[2] = key2;
            rowData1[3] = cantH2;
            model.addRow(rowData1);
            
            rowData1 = new Object[4];
            rowData1[0] = nombre1;
            rowData1[1] = codigo2;
            rowData1[2] = key1;
            rowData1[3] = cantH1;
            model.addRow(rowData1);
            
            ///Cambios datos en el hashmap
            this.NumContrato_Codigoempleados.remove(key1);
            this.NumContrato_Codigoempleados.remove(key2);
            this.NumContrato_Codigoempleados.put(key1, codigo2);
            this.NumContrato_Codigoempleados.put(key2, codigo1);
            
            RefreshTableCalendarData();
            
            
            JOptionPane.showMessageDialog(this, "Contratos cambiados");
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Elige codigos de empleados distintos", "No es posible hacer el cambio", JOptionPane.INFORMATION_MESSAGE);
        }
        
        this.jComboBox_changeEmpleados1.setEnabled(false);
        this.jComboBox_changeEmpleados2.setEnabled(false);
        this.jButton_ConfirmarCambio.setEnabled(false);
        
    }//GEN-LAST:event_jButton_ConfirmarCambioActionPerformed

    private void jButton_saveCalendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_saveCalendarActionPerformed
        // TODO add your handling code here:
        if(this.codigosContratosDisponibles.size()!=0)
        {
            JOptionPane.showMessageDialog(this, "Es necesario asignar todos los contratos primero", "Datos faltantes", JOptionPane.INFORMATION_MESSAGE);
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
        JOptionPane.showMessageDialog(this, "Actualiza el calendario", "Contratos generados con éxito", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
        
        
    }//GEN-LAST:event_jButton_saveCalendarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow() ;
        if(row == -1)
        {
            JOptionPane.showMessageDialog(this, "Necesitas seleccionar un empleado de la tabla","Operación Inválida",JOptionPane.INFORMATION_MESSAGE);
        }
        
        int numContrato = Integer.parseInt(this.jTable1.getValueAt(row, 2).toString());
        int codEmpleado = NumContrato_Codigoempleados.get(numContrato);
        
        this.codigosContratosDisponibles.add(numContrato);
        this.codigosEmpleadosValidos.add(codEmpleado);
        
        NumContrato_Codigoempleados.remove(numContrato);
        DefaultTableModel model = (DefaultTableModel)this.jTable1.getModel();
        model.removeRow(row);              
        
        RefreshComboBoxContratos();
        RefreshComboBoxEmpleados();
        RefreshComboBoxCambioEmpleados();
        RefreshTableCalendarData();
                
    }//GEN-LAST:event_jButton3ActionPerformed

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
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton_ConfirmarCambio;
    private javax.swing.JButton jButton_saveCalendar;
    private javax.swing.JComboBox<String> jComboBox_Empleados;
    private javax.swing.JComboBox<String> jComboBox_NumeroContratos;
    private javax.swing.JComboBox<String> jComboBox_changeEmpleados1;
    private javax.swing.JComboBox<String> jComboBox_changeEmpleados2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelNombreSeleccionado;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable_calendarios0;
    // End of variables declaration//GEN-END:variables


    
}
