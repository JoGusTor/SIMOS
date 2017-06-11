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
public class parameter_value_table {
        AbrirGuardar ag=new AbrirGuardar();
        FileWriter fichero = null;
        PrintWriter pw = null;
         DecimalFormat f1=new DecimalFormat("00");
        DecimalFormat f2 = new DecimalFormat("0000.0000");
        float values;
        float parameter;
        float parameter2;
        float parameter3;
         public parameter_value_table(int numParametros,float parametro[],String ruta){
            values = numParametros;
            String cad="";
            for(int i=0;i<values;i++){
                parameter=parametro[i];
                cad+="---   Parameter = "+f2.format(parameter)+" - (?-Band) -------------------------------------------\n";
            }
            escribeParameterValueTable(ruta,cad);
        }
        
        public parameter_value_table(String ruta){
        values=3f;
        parameter=1.25f;
        parameter2=5.3f;
        parameter3=0.44f;
        String cad="---   Parameter = "+f2.format(parameter)+" - (L-Band) -------------------------------------------\n" +
                                "---   Parameter = "+f2.format(parameter2)+" - (C-Band) -------------------------------------------\n" +
                                "---   Parameter = "+f2.format(parameter3)+" - (P-Band) -------------------------------------------\n" ;
            escribeParameterValueTable(ruta,cad);
        }
        
        
        private void escribeParameterValueTable(String archivo,String cadena){
                try{
                    fichero = new FileWriter(archivo);
                    pw = new PrintWriter(fichero);

                    /*for (int i = 0; i < 10; i++)
                        pw.println("Linea " + i);*/
                    pw.println( "**********************************************************************************\n" +
                                "1234567890123456789012345678901234567890123456789012345678901234567890123456789012\n" +
                                "**********************************************************************************\n" +
                                "**** This file provides a parameter list for given inputs of the MIMICS model ****\n" +
                                "**********************************************************************************\n" +
                                "**********************************************************************************\n" +
                                "----------------------------------------------------------------------------------\n" +
                                "--- number of parameter values = XX ----------------------------------------------\n" +
                                "--- number of parameter values = "+f1.format(values)+" ----------------------------------------------\n" +
                                "---   Parameter = XXXX.xxxx -- one set for each parameter value to be evaluated --\n" +
//                                "---   Parameter = "+f2.format(parameter)+" - (L-Band) -------------------------------------------\n" +
//                                "---   Parameter = "+f2.format(parameter2)+" - (C-Band) -------------------------------------------\n" +
//                                "---   Parameter = "+f2.format(parameter3)+" - (P-Band) -------------------------------------------\n" +
                                cadena+
                                "----------------------------------------------------------------------------------\n" +
                                "**********************************************************************************\n" +
                                "----------------------------------------------------------------------------------\n" +
                                "--- number of parameter values = XX ----------------------------------------------\n" +
                                "--- number of parameter values =  3 ----------------------------------------------\n" +
                                "---   Parameter = XXXX.xxxx -- one set for each parameter value to be evaluated --\n" +
                                "---   Parameter =           ------------------------------------------------------\n" +
                                "---   Parameter =           ------------------------------------------------------\n" +
                                "---   Parameter =           ------------------------------------------------------\n" +
                                "----------------------------------------------------------------------------------\n" +
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
