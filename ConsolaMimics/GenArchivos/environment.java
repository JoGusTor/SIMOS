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
public class environment {
        AbrirGuardar ag=new AbrirGuardar();
        FileWriter fichero = null;
        PrintWriter pw = null;
        
        DecimalFormat f1 = new DecimalFormat("000.0");
        DecimalFormat t = new DecimalFormat("0");
        float tstrt1;
        float tstop1;
        float tdelt1;
        float t1;
        float tstrt2;
        float tstop2;
        float tdelt2;
        float t2;
        float tstrt3;
        float tstop3;
        float tdelt3;
        float t3;
        public environment(float trt1,float top1,float delt1,int T1,float trt2,float top2,float delt2,int T2,float trt3,float top3,float delt3,int T3,String ruta){
        tstrt1=trt1;
        tstop1=top1;
        tdelt1=delt1;
        t1=T1;
        
        tstrt2=trt2;
        tstop2=top2;
        tdelt2=delt2;
        t2=T2;
        
        tstrt3=trt3;
        tstop3=top3;
        tdelt3=delt3;
        t3=T3;
        escribeEnvironment(ruta);
        }
        
        public environment(String ruta){
        tstrt1=23.254f;
        tstop1=23.254896f;
        tdelt1=1f;
        t1=0f;
        
        tstrt2=23f;
        tstop2=23.0f;
        tdelt2=1.0f;
        t2=0f;
        
        tstrt3=23.0f;
        tstop3=23f;
        tdelt3=1f;
        t3=0f;
        escribeEnvironment(ruta);
        }
        
        
        private void escribeEnvironment(String archivo){
                try{
                    fichero = new FileWriter(archivo);
                    pw = new PrintWriter(fichero);

                    /*for (int i = 0; i < 10; i++)
                        pw.println("Linea " + i);*/
                    pw.println( "*************************************************************************************\n" +
                                "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345\n" +
                                "*************************************************************************************\n" +
                                "****** This file provides input data to the program MIMICS_1_5.FTN   ****************\n" +
                                "******         Data supplied here pertain to the environment.        ****************\n" +
                                "*************************************************************************************\n" +
                                "******* T = 0 to step the parameter value using the DELTA value  ********************\n" +
                                "******* T = 1 for parameter valuyes read from the table          ********************\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "----------  temperature of soil (C)    ----------------------------------------------\n" +
                                "---     Tstrt = starting value,   Tstop = ending value,   Tdelt = increment  , T ----\n" +
                                "---     Tstrt = XXX.x         ,   Tstop = XXX.x       ,   Tdelt = XXX.x      , I ----\n" +
                                "---     Tstrt = "+f1.format(tstrt1)+"         ,   Tstop = "+f1.format(tstop1)+"       ,   Tdelt = "+f1.format(tdelt1)+"      , "+t.format(t1)+" ----\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "----------  temperature of standing water (C)    ------------------------------------\n" +
                                "---     Tstrt = starting value,   Tstop = ending value,   Tdelt = increment  , T ----\n" +
                                "---     Tstrt = XXX.x         ,   Tstop = XXX.x       ,   Tdelt = XXX.x      , I ----\n" +
                                "---     Tstrt = "+f1.format(tstrt2)+"         ,   Tstop = "+f1.format(tstop2)+"       ,   Tdelt = "+f1.format(tdelt2)+"      , "+t.format(t2)+" ----\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "----------  temperature of vegetation (C)   -----------------------------------------\n" +
                                "---     Tstrt = starting value,   Tstop = ending value,   Tdelt = increment  , T ----\n" +
                                "---     Tstrt = XXX.x         ,   Tstop = XXX.x       ,   Tdelt = XXX.x      , I ----\n" +
                                "---     Tstrt = "+f1.format(tstrt3)+"         ,   Tstop = "+f1.format(tstop3)+"       ,   Tdelt = "+f1.format(tdelt3)+"      , "+t.format(t3)+" ----\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n" +
                                "*************************************************************************************");

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
