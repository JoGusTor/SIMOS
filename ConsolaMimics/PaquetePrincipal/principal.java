/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaquetePrincipal;

import GenArchivos.AbrirGuardar;
import GenArchivos.branch_primary;
import GenArchivos.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.swing.JFileChooser;

/**
 *
 * @author zero
 */
public class principal extends javax.swing.JFrame {

    /**
     * Creates new form principal
     */
    AbrirGuardar ag=new AbrirGuardar();
    String entrada=null;
    String salida=null;
    String mimics_source=null;
    String script=null;
    public principal() {
        initComponents();
        
      javax.swing.text.DefaultCaret caret = (javax.swing.text.DefaultCaret) jTextArea1.getCaret();
      caret.setUpdatePolicy(javax.swing.text.DefaultCaret.ALWAYS_UPDATE);
        this.archivoCofig();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        jFileChooser1 = new javax.swing.JFileChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem22 = new javax.swing.JMenuItem();

        jFileChooser1.setDialogType(javax.swing.JFileChooser.CUSTOM_DIALOG);
        jFileChooser1.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Carpeta de entrada (input_dir)");

        jLabel2.setText("Carpeta de salida (output_dir)");

        jLabel3.setText("No se ha seleccionado una carpeta");

        jLabel4.setText("No se ha seleccionado una carpeta");

        jMenu1.setText("Archivo");

        jMenuItem1.setText("Correr MIMICS");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem21.setText("Correr ciclo MIMICS");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem21);

        jMenu3.setText("Generador");

        jMenuItem4.setText("Branch Primary");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem5.setText("Branch Secondary");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem6.setText("Dielectric branch1 table");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuItem7.setText("Dielectric branch2 table");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem9.setText("Leaf");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem9);

        jMenuItem10.setText("Dielectric leaf table");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem10);

        jMenuItem11.setText("Ground");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem11);

        jMenuItem12.setText("Trunk and gross canopy");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem12);

        jMenuItem13.setText("Histogram trunk diam");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem13);

        jMenuItem14.setText("Dielectric trunk table");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem14);

        jMenuItem15.setText("Parameter nesting");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem15);

        jMenuItem16.setText("Parameter value table");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem16);

        jMenuItem17.setText("Sensor");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem17);

        jMenuItem18.setText("Environment");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem18);

        jMenuItem19.setText("Configuration");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem19);

        jMenuItem20.setText("Generar todos");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem20);

        jMenu1.add(jMenu3);

        jMenuItem8.setText("jMenuItem8");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Preferencias");

        jMenuItem2.setText("Ruta runit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Ruta source");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Configuración");

        jMenuItem22.setText("Configurar MIMICS");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem22);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4))
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)))
                        .addGap(0, 247, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
  
    private void archivoCofig(){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File ("./conf.adx");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String[] rutas;
         String linea;
         while((linea=br.readLine())!=null){
             if(linea.indexOf("mimics_source") == 0){
                 rutas=linea.split(":;");
                 mimics_source=rutas[1];
             }
             if(linea.indexOf("script") == 0){
                 rutas=linea.split(":;");
                 script=rutas[1];   
             }                
         }
         fr.close(); 
         br.close();
      }
      catch(Exception e){
         e.printStackTrace();
         this.jTextArea1.append(e+"\n");
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();
               br.close();
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
            this.jTextArea1.append(e2+"\n");
         }
      }
    }
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Thread t=new Thread(){
            public void run(){
                        if((entrada != null) && (mimics_source != null) && (script != null)){
                    try {  
                    String comando = String.format("%s %s %s",script,entrada,mimics_source);
                    Process p = Runtime.getRuntime().exec(comando);  
                    BufferedReader in = new BufferedReader(  
                                        new InputStreamReader(p.getInputStream()));  
                    String line = null;  
                    while ((line = in.readLine()) != null) {  
                        //System.out.println(line); 
                        jTextArea1.append(line+"\n");
                    }  
                } catch (IOException e) {  
                    jTextArea1.append(e+"\n");  
                }
               }else{
                    System.out.println("LLene los campos");
                    jTextArea1.append("Llene todos los campos\n");
               }
            };
        };
        t.start();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        /*int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
                script=file.getAbsolutePath();
                this.jTextArea1.append("Ruta guardada: "+script+"\n");
        } else {
            System.out.println("File access cancelled by user.");
            this.jTextArea1.append("File access cancelled by user.\n");
        }*/
        String nuevaRuta=ag.abrir();
        File archivo = null;
        File temporal = null;
        FileReader fr = null;   
        BufferedReader br = null;
        BufferedWriter bw = null;
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
         archivo = new File ("./conf.adx");
         temporal = new File ("./*conf.adx");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
         
         fw = new FileWriter(temporal);
         pw = new PrintWriter(fw);
         String[] rutas;
         String linea;
         while((linea=br.readLine())!=null){     
             if(linea.indexOf("script") == 0){
                 rutas=linea.split(":;");
                 pw.println("script:;"+nuevaRuta);
                 script=nuevaRuta;
             } else{
                 pw.println(linea);
             }
         }
        temporal.renameTo(archivo);
        fw.close();
        pw.close();
        fr.close(); 
        br.close();
        this.jTextArea1.append("Ruta guardada: "+script+"\n");
      }
      catch(Exception e){
         e.printStackTrace();
         this.jTextArea1.append(e+"\n");
      }finally{
         try{                    
            if( null != fr ){   
               fr.close(); 
               br.close();
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
            this.jTextArea1.append(e2+"\n");
         }
      }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        String nuevaRuta=ag.seleccionarCarpeta();
        File archivo = null;
        File temporal = null;
        FileReader fr = null;
        
        BufferedReader br = null;
        BufferedWriter bw = null;
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
         archivo = new File ("./conf.adx");
         temporal = new File ("./*conf.adx");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
         fw = new FileWriter(temporal);
         pw = new PrintWriter(temporal);
         String[] rutas;
         String linea;
         while((linea=br.readLine())!=null){     
             if(linea.indexOf("mimics_source") == 0){
                 rutas=linea.split(":;");
                 pw.println("mimics_source:;"+nuevaRuta);
                 mimics_source=nuevaRuta;
             } else{
                 pw.println(linea);
             }
         }
        //archivo.delete();
        temporal.renameTo(archivo);
        fw.close();
        pw.close();
        fr.close(); 
        br.close();
        this.jTextArea1.append("Ruta guardada: "+mimics_source+"\n");
      }
      catch(Exception e){
         e.printStackTrace();
         this.jTextArea1.append(e+"\n");
      }finally{
         try{                    
            if( null != fr ){   
               fr.close(); 
               br.close();
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
            this.jTextArea1.append(e2+"\n");
         }
      }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int returnVal = jFileChooser1.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser1.getSelectedFile();
                entrada=file.getAbsolutePath();
                this.jLabel3.setText(entrada);
        } else {
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int returnVal = jFileChooser1.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser1.getSelectedFile();
                salida=file.getAbsolutePath();
                this.jLabel4.setText(salida);
        } else {
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        Panelbranch_primary branch_P=new Panelbranch_primary();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        Panelbranch_secondary branch_S=new Panelbranch_secondary();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        Paneldielectric_branch1_table db1t=new Paneldielectric_branch1_table();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        Paneldielectric_branch2_table db2t=new Paneldielectric_branch2_table();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
       Panelleaf pleaf=new Panelleaf();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        Paneldielectric_leaf_table dlt=new Paneldielectric_leaf_table();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        new Panelground();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        new Paneltrunk_and_gross_canopy();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        new Panelhistogram_trunk_diam();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        new Paneldielectric_trunk_table();
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        String archivo="parameter_nesting.input";
        String RutaArchivo = ag.guardar(archivo);
        if(RutaArchivo != null){
            parameter_nesting pn=new parameter_nesting(RutaArchivo);
        }else{
            System.out.println("No se creo el archivo "+archivo);
        }
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        new Panelparameter_value_table();
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        new Panelsensor();
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        new Panelenvironment();
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        String archivo="configuration.input";
        String RutaArchivo = ag.guardar(archivo);
        if(RutaArchivo != null){
            configuration c=new configuration(RutaArchivo);
        }else{
            System.out.println("No se creo el archivo "+archivo);
        }
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        String RutaArchivo;
        String Ruta = ag.seleccionarCarpeta()+"/";
        if(Ruta != null){
            RutaArchivo=Ruta+"branch_primary.input";
            branch_primary bp=new branch_primary(RutaArchivo);
            RutaArchivo=Ruta+"branch_secondary.input";
            branch_secondary bs=new branch_secondary(RutaArchivo);
            RutaArchivo=Ruta+"dielectric_branch1_table.input";
            dielectric_branch1_table db1t=new dielectric_branch1_table(RutaArchivo);
            RutaArchivo=Ruta+"dielectric_branch2_table.input";
            dielectric_branch2_table db2t=new dielectric_branch2_table(RutaArchivo);
            
            
            RutaArchivo=Ruta+"leaf.input";
            leaf l=new leaf(RutaArchivo);
            RutaArchivo=Ruta+"dielectric_leaf_table.input";
            dielectric_leaf_table dlt=new dielectric_leaf_table(RutaArchivo);
            RutaArchivo=Ruta+"ground.input";
            ground g=new ground(RutaArchivo);
            RutaArchivo=Ruta+"trunk_and_gross_canopy.input";
            trunk_and_gross_canopy tagc=new trunk_and_gross_canopy(RutaArchivo);
            RutaArchivo=Ruta+"histogram_trunk_diam.input";
            histogram_trunk_diam htd=new histogram_trunk_diam(RutaArchivo);
            RutaArchivo=Ruta+"dielectric_trunk_table.input";
            dielectric_trunk_table dtt=new dielectric_trunk_table(RutaArchivo);
            RutaArchivo=Ruta+"parameter_nesting.input";
            parameter_nesting pn=new parameter_nesting(RutaArchivo);
            RutaArchivo=Ruta+"parameter_value_table.input";
            parameter_value_table pvt=new parameter_value_table(RutaArchivo);
            RutaArchivo=Ruta+"sensor.input";
            sensor s=new sensor(RutaArchivo);
            RutaArchivo=Ruta+"environment.input";
            environment e=new environment(RutaArchivo);
            RutaArchivo=Ruta+"configuration.input";
            configuration c=new configuration(RutaArchivo);
        }else{
            System.out.println("No se creo el archivo ");
        }
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        Panelground as=new Panelground();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        Thread t=new Thread(){
            public void run(){
                    String cadena=ag.seleccionMultilesCarpetas();
                    if(cadena != null ){
                        String[] carpetas=cadena.split(":;");         
                        System.out.println(carpetas.length+carpetas[0]);
                        for(int i=0;i<carpetas.length;i++){
                            if((entrada != null) && (mimics_source != null) && (script != null)){
                                try {  
                                String comando = String.format("%s %s %s",script,carpetas[i],mimics_source);
                                Process p = Runtime.getRuntime().exec(comando);  
                                BufferedReader in = new BufferedReader(  
                                                    new InputStreamReader(p.getInputStream()));  
                                String line = null;  
                                while ((line = in.readLine()) != null) {  
                                    //System.out.println(line); 
                                    jTextArea1.append(line+"\n");
                                }  
                                
                            } catch (IOException e) {  
                                jTextArea1.append(e+"\n");  
                            }
                           }else{
                                System.out.println("LLene los campos");
                                jTextArea1.append("Llene todos los campos\n");
                           }
                        }       
                        jTextArea1.append("Ciclo completo\n");
                        
                        
                        
                        FileWriter fichero = null;
                                PrintWriter pw = null;
                                try
                                {
                                    fichero = new FileWriter(carpetas[0]+"/Polarizaciones.adx");
                                    pw = new PrintWriter(fichero);
                                        for(int i=0;i<carpetas.length;i++){
                                            pw.print("-"+"3.53"+":-"+"14.44"+":-"+"14.43"+":-"+"7.97"+":");
                                            pw.println();
                                        }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                } finally {
                                   try {
                                   if (null != fichero)
                                      fichero.close();
                                   } catch (Exception e2) {
                                      e2.printStackTrace();
                                   }
                                }
                        
                        
                        
                        
                        
                        
                        
                    }
            };
        };
        t.start();
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        Configuracion PanelConf=new Configuracion();
    }//GEN-LAST:event_jMenuItem22ActionPerformed

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
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}