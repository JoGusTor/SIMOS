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
public class sensor {
        AbrirGuardar ag=new AbrirGuardar();
        FileWriter fichero = null;
        PrintWriter pw = null;
        
        DecimalFormat f1=new DecimalFormat("00.00");
        DecimalFormat f2 = new DecimalFormat("00.0");
        DecimalFormat t = new DecimalFormat("0");
        
        float frqstrt;
        float frqstop;
        float frqdelt;
        float t1;
        float angstrt;
        float angstop;
        float angdelt;
        float t2;
        public sensor(float trt1,float top1,float delt1,int T1,float trt2,float top2,float delt2,int T2,String ruta){
            frqstrt=trt1;
            frqstop=top1;
            frqdelt=delt1;
            t1=T1;
            angstrt=trt2;
            angstop=top2;
            angdelt=delt2;
            t2=T2;
            escribeSensor(ruta);
        }
        
        public sensor(String ruta){
            frqstrt=1.25f;
            frqstop=1.25f;
            frqdelt=1f;
            t1=0f;
            angstrt=20f;
            angstop=25f;
            angdelt=5f;
            t2=0f;
            escribeSensor(ruta);
        }
        
        
        private void escribeSensor(String archivo){
                try{
                    fichero = new FileWriter(archivo);
                    pw = new PrintWriter(fichero);

                    /*for (int i = 0; i < 10; i++)
                        pw.println("Linea " + i);*/
                    pw.println( "*************************************************************************************\n" +
                                "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345\n" +
                                "*************************************************************************************\n" +
                                "****** This file provides input data to the program MIMICS_1_5.FTN   ****************\n" +
                                "******         Data supplied here pertain to the sensor.             ****************\n" +
                                "*************************************************************************************\n" +
                                "******* T = 0 to step the parameter value using the DELTA value  ********************\n" +
                                "******* T = 1 for parameter valuyes read from the table          ********************\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "----------  frequency of operation data (GHz) ---------------------------------------\n" +
                                "---   frqstrt = starting value, frqstop = ending value, frqdelt = increment  , T ----\n" +
                                "---   frqstrt = XX.xx         , frqstop = XX.xx       , frqdelt = XX.xx      , I ----\n" +
                                "---   frqstrt = "+f1.format(frqstrt)+"         , frqstop = "+f1.format(frqstop)+"       , frqdelt = "+f1.format(frqdelt)+"      , "+t.format(t1)+" ----\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "---------Angle of Measurement data (degrees)-----------------------------------------\n" +
                                "---   angstrt = starting value,  angstp = ending value, angdelt = increment  , T ----\n" +
                                "---   angstrt = XX.x          ,  angstp = XX.x        , angdelt = XX.x       , I ----\n" +
                                "---   angstrt = "+f2.format(angstrt)+"          ,  angstp = "+f2.format(angstop)+"        , angdelt = "+f2.format(angdelt)+"       , "+t.format(t2)+" ----\n" +
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
