/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicacion_de_Gestion;

import Aplicacion_de_Gestion.tipo_turno;
import Comparadores.CriterioApellidos;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
/**
 *
 * @author Ronald
 */
public class GenerarPdf {
    public void GenerarReporteTurnosMensual(int mes,int anio)
    {
        int semanaInicio=0;
        int semanaFin=0;
        
        int SemanaEnMes[] = {4,4,5,4,4,5,4,4,5};
        
        for(int i=0;i<=mes;i++)
            semanaFin += SemanaEnMes[i];
        
        semanaInicio = semanaFin - SemanaEnMes[mes] + 1;
        
        semanaInicio += (2022-anio)*52;
        semanaFin += (2022-anio)*52;
        
        String meses[] = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
        Document document = new Document();
        try
        {
            String filename = String.format("Reporte Turnos Mensual %d_%s.pdf",anio, meses[mes]);
           PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
           
           document.open();
           int numSemanaImpreso = 0;
           Chapter capitulo = new Chapter(String.format("%s %d", meses[mes],anio), 1);
                 
           for(int semana = semanaInicio ; semana<=semanaFin ; semana++)
           {
                numSemanaImpreso++;
                capitulo.add(new Paragraph(String.format("- Semana %d ", numSemanaImpreso)));
                
                ArrayList<Calendario> datosCalendario = Gestion_Empresa.getInstance().Generar_Calendario_ArrayList(semana);
                if(datosCalendario.size()==21)
                {   
                    
                    ///HEADER
                    PdfPCell cell;
                    String headers[] = {"Lunes","Martes","Miércoles","Jueves","Viernes","Sábado","Domingo"};
                    PdfPTable table = new PdfPTable(headers.length);
                    for (String dia : headers) {
                        cell = new PdfPCell(new Phrase(dia));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);
                    }
                    int counter =0;
                    for(int i=0;i<3;i++)
                    {
                        for(int j=0;j<7;j++)
                        {
                            ///Get data from singleton
                            int codigoEmpleado = datosCalendario.get(counter++).getCod_empleado();
                            String nombre = codigoEmpleado + " - " + Gestion_Empresa.getInstance().getEmpleado(codigoEmpleado).getNombreCompleto();
                            ///Add to table
                            cell = new PdfPCell(new Phrase(nombre));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);
                        }
                    }
                    //capitulo.add(Chunk.NEWLINE);
                    capitulo.add(table);
                }
                else
                {
                    capitulo.add(new Paragraph("No hay datos para generar este calendario de turnos"));
                    
                }
               
           }
           document.add(capitulo);
           
           
           document.close();
           writer.close();
           
           Gestion_Empresa.getInstance().AgregarComentHistorial("Informe generado: "+ filename, true); 
           
            try {
                
                Desktop.getDesktop().open(new File(filename));
            } catch (Exception e) {
            }
        } catch (DocumentException e)
        {
           e.printStackTrace();
        } catch (FileNotFoundException e)
        {
           e.printStackTrace();
        }
        
    }
    
    public void GenerarReporteCantTurnosAcumulados(int mes,int anio)
    {
        ArrayList<Empleado> empleados = new ArrayList<Empleado>(Gestion_Empresa.getInstance().getEmpleados());
        
        int semanaFin=0;
        
        int SemanaEnMes[] = {4,4,5,4,4,5,4,4,5};
        
        for(int i=0;i<=mes;i++)
            semanaFin += SemanaEnMes[i];
        
        semanaFin += (2022-anio)*52;
        
        int cantEmpleados = empleados.size();
        empleados.sort(new CriterioApellidos()); ///ORDENO los datos alfabeticamente
        
        int cantTurnosManana[] = new int[cantEmpleados];
        int cantTurnosTarde[] = new int[cantEmpleados];
        int cantTurnosNoche[] = new int[cantEmpleados];
        
        for(int i=0;i<cantEmpleados;i++)
        {
            cantTurnosManana[i]=0;
            cantTurnosTarde[i] = 0;
            cantTurnosNoche[i] = 0;
        }
        for(int i=0;i<cantEmpleados;i++)
        {
            Empleado e = empleados.get(i);
            for(Contrato c: e.getContratos())
            {
                if(c.getNum_semana()<=semanaFin)
                {
                    
                    for(Turno t : c.getTurnos())
                    {
                        switch(t.getHorario())
                        {
                            case morning: 
                                cantTurnosManana[i]++;
                                break;
                            case afternoon: 
                                cantTurnosTarde[i]++;
                                break;
                            case night: 
                                cantTurnosNoche[i]++;
                                break;
                        }
                    }
                }
            }
        }
        
        
        
        String meses[] = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
        String filename = String.format("Reporte Turnos Acumulados hasta %d_%s.pdf", anio, meses[mes]);
        Document document = new Document();
        try
        {
            
           PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
           
           document.open();
           
            
            ///HEADER
            PdfPCell cell;
            String headers[] = {"Apellido","Nombre","Codigo","# Mañana","# Tarde","# Noche","Total"};
            PdfPTable table = new PdfPTable(headers.length);
            for (String header : headers) {
                cell = new PdfPCell(new Phrase(header));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            for(int i=0;i<cantEmpleados;i++)
            {  
                cell = new PdfPCell(new Phrase(empleados.get(i).getApellido()));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(empleados.get(i).getNombre()));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(String.valueOf(empleados.get(i).getCod_empleado())));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(String.valueOf(cantTurnosManana[i])));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(String.valueOf(cantTurnosTarde[i])));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(String.valueOf(cantTurnosNoche[i])));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                int total = cantTurnosNoche[i] + cantTurnosTarde[i] + cantTurnosManana[i];
                cell = new PdfPCell(new Phrase(String.valueOf(total)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            
            
            
            
            
           Chapter capitulo = new Chapter(String.format("Reporte Turnos Acumulados hasta %d_%s", anio, meses[mes]), 1);
           document.add(capitulo);
           document.add(Chunk.NEWLINE);
           document.add(table);
           
           document.close();
           writer.close();
           
           Gestion_Empresa.getInstance().AgregarComentHistorial("Informe generado: "+ filename, true); 
            try {
                
                Desktop.getDesktop().open(new File(filename));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (DocumentException e)
        {
           e.printStackTrace();
        } catch (FileNotFoundException e)
        {
           e.printStackTrace();
        }
        
        
    }
   
    
    /**
 * Explanation of the method by which we read the JTable we pass as
 * parameter, and where we copy its content in a PDF passed 
 * as a parameter.
 * Método con el que leemos cualquier JTable que pasamos como
 * parámetro, y donde copiamos su contenido en un PDF que se pasa 
 * como parámetro. 
 *
 * @param jTable <code>JTable</code> 
 *      the JTable we are going to pass to PDF. 
 *      El JTable que vamos a pasar a PDF.
 * @param pdfNewFile <code>String</code> 
 *      pdf File we are going to write. 
 *      Fichero pdf en el que vamos a escribir. 
 * @param title <code>String</code> 
 *      title
 *      Título 
 */
public void utilJTableToPdf(JTable jTable, File pdfNewFile, String title){    
    try {
        // We create the document and set the file name.        
        // Creamos el documento e indicamos el nombre del fichero.
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(pdfNewFile));
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("No such file was found to generate the PDF (No se encontró el fichero para generar el pdf)" + fileNotFoundException);
        }
        document.open();
        // We add metadata to PDF
        // Añadimos los metadatos del PDF
        document.addTitle("Table export to PDF (Exportamos la tabla a PDF)");
        document.addSubject("Using iText (usando iText)");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Código Xules");
        document.addCreator("Código Xules");
          
        // First page (Primera página)
        Anchor anchor = new Anchor("Table export to PDF (Exportamos la tabla a PDF)");
        anchor.setName("Table export to PDF (Exportamos la tabla a PDF)");
  
        // Second parameter is the number of the chapter (El segundo parámetro es el número del capítulo).
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);
  
        Paragraph subPara = new Paragraph("Do it by Xules (Realizado por Xules)");
        Section subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("This is a simple example (Este es un ejemplo sencillo)"));
                      
        // Create the table (Creamos la tabla)
        PdfPTable table = new PdfPTable(jTable.getColumnCount()); 
           
        // Now we fill the rows of the PdfPTable (Ahora llenamos las filas de PdfPTable)
        PdfPCell columnHeader;
        // Fill table columns header 
        // Rellenamos las cabeceras de las columnas de la tabla.                
        for (int column = 0; column < jTable.getColumnCount(); column++) {                 
            columnHeader = new PdfPCell(new Phrase(jTable.getColumnName(column)));
            columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(columnHeader);
        }
        table.setHeaderRows(1);
        // Fill table rows (rellenamos las filas de la tabla).                
        for (int row = 0; row < jTable.getRowCount(); row++) {                
            for (int column = 0; column < jTable.getColumnCount(); column++) { 
                table.addCell(jTable.getValueAt(row, column).toString());
            }
        } 
        subCatPart.add(table);
        
        document.add(catPart);
        Chapter capitulo = new Chapter("Capitulo "+0, 0);
            for(int i=0; i<30;i++)
            {
                capitulo.add(new Paragraph("Tabla "+i));
                capitulo.add(table);
                
                if(i%10==0)
                {            
                    document.add(capitulo);
                    capitulo = new Chapter("Capitulo "+i, i);
                }
                    

            }
        
            
            document.add(capitulo);
        Chapter capitulo24 = new Chapter(new Paragraph("Nueva seccion"), 24); 
        capitulo24.add(0,table);
        document.add(capitulo24);
          
        document.close();
        JOptionPane.showMessageDialog(null, "Your PDF file has been generated!(¡Se ha generado tu hoja PDF!)",
                "RESULTADO", JOptionPane.INFORMATION_MESSAGE);
    } catch (DocumentException documentException) {
        System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);
        JOptionPane.showMessageDialog(null, "The file not exists (Se ha producido un error al generar un documento): " + documentException,
                "ERROR", JOptionPane.ERROR_MESSAGE);
    }     
               
}
    
    
    
    
}
