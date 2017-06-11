/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GenArchivos;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author zero
 */
public class AbrirGuardar {
    public String abrir(){
        String ruta=null;
        JFileChooser selector=new JFileChooser("C:");
        selector.setDialogTitle("Seleccione el archivo");
        selector.isDirectorySelectionEnabled();
        //FileNameExtensionFilter filtro= new FileNameExtensionFilter("Automata & TXT", "adx", "txt");
        //selector.setFileFilter(filtro);
        int seleccion=selector.showOpenDialog(null);
        if(seleccion==JFileChooser.APPROVE_OPTION){
                File rutaSeleccionada=selector.getSelectedFile();
                ruta=rutaSeleccionada.getPath();
                System.out.println(rutaSeleccionada);
        }
        return ruta;
    }
    
    public String seleccionarCarpeta(){
        String ruta=null;
        JFileChooser selector=new JFileChooser("C:");
        selector.setDialogTitle("Seleccione el archivo");
        selector.isDirectorySelectionEnabled();
        selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int seleccion=selector.showOpenDialog(null);
        if(seleccion==JFileChooser.APPROVE_OPTION){
                File rutaSeleccionada=selector.getSelectedFile();
                ruta=rutaSeleccionada.getPath();
                System.out.println(rutaSeleccionada);
        }
        return ruta;
    }
    
    public String guardar(String origen){ //el origen puede ser "" osea cadena vacia
            String ruta=null;
            //Creamos el objeto JFileChooser
            JFileChooser selector=new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Mimics (.input)","input");
            selector.setFileFilter(filtro);
            //seleccionamos un archivo por defecto, vacio si no se pasa un nombre para el archivo
            selector.setSelectedFile(new File(origen));
            //Abrimos la ventana, guardamos la opcion seleccionada por el usuario
            int seleccion=selector.showSaveDialog(null);
            //Si el usuario, pincha en aceptar
            if(seleccion==JFileChooser.APPROVE_OPTION){
                File rutaSeleccionada=selector.getSelectedFile();
                if(rutaSeleccionada.exists()){
                    int confirmacion=JOptionPane.showConfirmDialog(selector,"¿Remplazar archivo?","Archivo Existente",JOptionPane.YES_NO_OPTION);
                    if(confirmacion == JOptionPane.YES_OPTION) {
                        ruta=rutaSeleccionada.getPath();
                    }
                }else{
                    ruta=rutaSeleccionada.getPath();
                }    
            }
            return ruta;
       }
    
    public String seleccionMultilesCarpetas(){
        String rutas=null;
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setMultiSelectionEnabled(true);
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
           File[] files = fc.getSelectedFiles();
           rutas="";
           for (int i=0; i<files.length; i++) {
              rutas+=(files[i].getAbsolutePath()+ ":;");
           }
       }   
       return rutas;
    }
}
