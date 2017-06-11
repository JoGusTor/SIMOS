/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GenArchivos;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;

/**
 *
 * @author zero
 */
public class histogram_trunk_diam {
        AbrirGuardar ag=new AbrirGuardar();
        FileWriter fichero = null;
        PrintWriter pw = null;
        
        DecimalFormat f1  =new DecimalFormat("00");
        DecimalFormat f2  = new DecimalFormat("000.00");
        DecimalFormat f3  = new DecimalFormat("0000.");

        float measurement;
        float midpoint;
        float trees;
        float deltaX;
         public histogram_trunk_diam(int numParametros,float pecho[],float arboles[],float delta[],String ruta){
            measurement = numParametros;
            String cad="";
            for(int i=0;i<measurement;i++){
                midpoint=pecho[i];
                trees=arboles[i];
                deltaX=delta[i];
                cad+="----    "+f2.format(midpoint)+" --------- "+f3.format(trees)+" ---- "+f2.format(deltaX)+" -----------------------------\n";
                        
            }
            escribeHistogramTrunkDiam(ruta,cad);
        }
        
        public histogram_trunk_diam(String ruta){
        measurement=2f;
        midpoint=14.5f;
        trees=80f;
        deltaX=3f;
        
        String cad=  "----    "+f2.format(midpoint)+" --------- "+f3.format(trees)+" ---- "+f2.format(deltaX)+" -----------------------------\n" +
                     "----    "+f2.format(midpoint)+" --------- "+f3.format(trees)+" ---- "+f2.format(deltaX)+" -----------------------------\n";
            escribeHistogramTrunkDiam(ruta,cad);
        }
        
        
        private void escribeHistogramTrunkDiam(String archivo,String cadena){
                try{
                    fichero = new FileWriter(archivo);
                    pw = new PrintWriter(fichero);

                    /*for (int i = 0; i < 10; i++)
                        pw.println("Linea " + i);*/
                    pw.println( "************************************************************************\n" +
                                "****  Trunk Diameter Size Distribution Histoogram Input File.     ******\n" +
                                "************************************************************************\n" +
                                "------------------------------------------------------------------------\n" +
                                "----  Trunk Diameter at Breast Height ----------------------------------\n" +
                                "------------------------------------------------------------------------\n" +
                                "----  Number of Measurement Bins = II  ---------------------------------\n" +
                                "----  Number of Measurement Bins = "+f1.format(measurement)+"  ---------------------------------\n" +
                                "------------------------------------------------------------------------\n" +
                                "----  DBH Midpoint  --- Number ---- Delta x ----------------------------\n" +
                                "----      (cm)      --- of trees --  (cm)   ----------------------------\n" +
                                "------------------------------------------------------------------------\n" +
                                "----    XXX.xx --------- XXXX. ---- XXX.xx -----------------------------\n" +
                                cadena+
                                "------------------------------------------------------------------------\n" +
                                "************************************************************************");

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                       try {
                       // asegurarnos que se cierra el fichero.
                       if (null != fichero)
                          fichero.close();
                       } catch (Exception e2) {
                          e2.printStackTrace();
                       }
                    }
        }
}
