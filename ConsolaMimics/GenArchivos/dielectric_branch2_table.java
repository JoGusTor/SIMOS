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
public class dielectric_branch2_table {
        AbrirGuardar ag=new AbrirGuardar();
        FileWriter fichero = null;
        PrintWriter pw = null;
        
        DecimalFormat f1 = new DecimalFormat("00");
        DecimalFormat t = new DecimalFormat("000.000");
        
        float values;
        float epsilon_r1;
        float epsilon_r2;
        public dielectric_branch2_table(int numParametros,float epsilon1[],float epsilon2[],String ruta){
            values = numParametros;
            String cad="";
            for(int i=0;i<values;i++){
                epsilon_r1=epsilon1[i];
                epsilon_r2=epsilon2[i];
                cad+="---   Epsilon_r  = "+t.format(epsilon_r1)+"  -j "+t.format(epsilon_r2)+" -------------------------------------------\n";
                        
            }
            escribeDielectricBranch2Table(ruta,cad);
        }
        
        public dielectric_branch2_table(String ruta){
            values = 1;
            epsilon_r1=31.86111f;
            epsilon_r2=8.9301121f;
            String cad = "---   Epsilon_r  = "+t.format(epsilon_r1)+"  -j "+t.format(epsilon_r2)+" -------------------------------------------\n";
            escribeDielectricBranch2Table(ruta,cad);
        }
        
        
        private void escribeDielectricBranch2Table(String archivo,String cadena){
                try{
                    fichero = new FileWriter(archivo);
                    pw = new PrintWriter(fichero);

                    /*for (int i = 0; i < 10; i++)
                        pw.println("Linea " + i);*/
                    pw.println( "**********************************************************************************\n" +
                                "1234567890123456789012345678901234567890123456789012345678901234567890123456789012\n" +
                                "**********************************************************************************\n" +
                                "*This file provides an input list secondary branch relative dielectric constants *\n" +
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
