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
public class dielectric_leaf_table {
        AbrirGuardar ag=new AbrirGuardar();
        FileWriter fichero = null;
        PrintWriter pw = null;
        
        DecimalFormat f1=new DecimalFormat("00");
        DecimalFormat t = new DecimalFormat("000.000");
        float values;
        float epsilon_r1;
        float epsilon_r2;
         public dielectric_leaf_table(int numParametros,float epsilon1[],float epsilon2[],String ruta){
            values = numParametros;
            String cad="";
            for(int i=0;i<values;i++){
                epsilon_r1=epsilon1[i];
                epsilon_r2=epsilon2[i];
                cad+="---   Epsilon_r  = "+t.format(epsilon_r1)+"  -j "+t.format(epsilon_r2)+" -------------------------------------------\n";
                        
            }
            escribeDielectricLeafTable(ruta,cad);
        }
        
        public dielectric_leaf_table(String ruta){
            values = 1;
            epsilon_r1=19.258741f;
            epsilon_r2=6.45874521f;
            String cad = "---   Epsilon_r  = "+t.format(epsilon_r1)+"  -j "+t.format(epsilon_r2)+" -------------------------------------------\n";
            escribeDielectricLeafTable(ruta,cad);
        }
        
        
        private void escribeDielectricLeafTable(String archivo,String cadena){
                try{
                    fichero = new FileWriter(archivo);
                    pw = new PrintWriter(fichero);

                    /*for (int i = 0; i < 10; i++)
                        pw.println("Linea " + i);*/
                    pw.println( "**********************************************************************************\n" +
                                "1234567890123456789012345678901234567890123456789012345678901234567890123456789012\n" +
                                "**********************************************************************************\n" +
                                "*** This file provides an input list for leaf relative dielectric constants  *****\n" +
                                "**********************************************************************************\n" +
                                "**********************************************************************************\n" +
                                "----------------------------------------------------------------------------------\n" +
                                "--- number of parameter values = XX ----------------------------------------------\n" +
                                "--- number of parameter values = "+f1.format(values)+" ----------------------------------------------\n" +
                                "---   Epsilon_r  = XXX.xxx  -j XXX.xxx -- one set for each value to be evaluated -\n" +
                                cadena+
                                "----------------------------------------------------------------------------------\n" +
                                "**********************************************************************************\n" +
                                "**********************************************************************************");

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
