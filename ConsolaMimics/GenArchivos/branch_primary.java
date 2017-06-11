/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GenArchivos;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import javax.swing.JFrame;

/**
 *
 * @author zero
 */
public class branch_primary {
        AbrirGuardar ag=new AbrirGuardar();
        FileWriter fichero = null;
        PrintWriter pw = null;
        
         DecimalFormat f1=new DecimalFormat("0.0000");
         DecimalFormat ft=new DecimalFormat("0");
            DecimalFormat f2=new DecimalFormat("00.00");
            DecimalFormat f3=new DecimalFormat("00.000");
            DecimalFormat f4=new DecimalFormat("000.0000");
            DecimalFormat pdf=new DecimalFormat("00");
            float mgbrstrt;
            float mgbrstp;
            float mgbrdelt;
            int t1;
            
            float rhostrt;
            float rhostp;
            float rhodelt;
            float t2;

            float brnstrt1;
            float brnstp1;
            float brndelt1;
            float t3;

            float brnstrt2;
            float brnstp2;
            float brndelt2;
            float t4;

            float brnstrt3;
            float brnstp3;
            float brndelt3;
            float t5;
            
            float pdf_T;
        public branch_primary(float mg1,float mg2,float mg3,int T1,float rh1,float rh2,float rh3,int T2,float br1, float br2,float br3,int T3,float br4,float br5, float br6,int T4,float br7,float br8, float br9,int T5,float pdf,String ruta){
            mgbrstrt=mg1;
            mgbrstp=mg2;
            mgbrdelt=mg3;
            t1=T1;
            
            rhostrt=rh1;
            rhostp=rh2;
            rhodelt=rh3;
            t2=T2;

            brnstrt1=br1;
            brnstp1=br2;
            brndelt1=br3;
            t3=T3;

            brnstrt2=br4;
            brnstp2=br5;
            brndelt2=br6;
            t4=T4;

            brnstrt3=br7;
            brnstp3=br8;
            brndelt3=br9;
            t5=T5;
            
            pdf_T=pdf;
            escribeBranch_Primary(ruta);       
        }    
            
        public branch_primary(String ruta){
            mgbrstrt=0.444444f;
            mgbrstp=0.444f;
            mgbrdelt=0.3f;
            t1=0;
            
            rhostrt=0.5f;
            rhostp=0.5f;
            rhodelt=0.4f;
            t2=0;

            brnstrt1=2.00f;
            brnstp1=2.0f;
            brndelt1=0.555555f;
            t3=0;

            brnstrt2=0.50f;
            brnstp2=0.55f;
            brndelt2=3f;
            t4=0;

            brnstrt3=1.3555f;
            brnstp3=1.35f;
            brndelt3=1f;
            t5=0;
            
            pdf_T=12f;
            escribeBranch_Primary(ruta);       
        }
        
        
        private void escribeBranch_Primary(String archivo){
                try{
                    fichero = new FileWriter(archivo);
                    pw = new PrintWriter(fichero);

                    /*for (int i = 0; i < 10; i++)
                        pw.println("Linea " + i);*/
                    pw.println( "*************************************************************************************\n" +
                                "******** This file provides input data to the program MIMICS_version_1_5.FTN ********\n" +
                                "********   Data supplied here pertain to the forest canopy itself.           ********\n" +
                                "*************************************************************************************\n" +
                                "****** This file provides data pertaining to the geometry        ********************\n" +
                                "****** of the primary branches.                                  ********************\n" +
                                "*************************************************************************************\n" +
                                "******* T = 0 to step the parameter value using the DELTA value  ********************\n" +
                                "******* T = 1 for parameter values read from the table           ********************\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "------ branch gravimetric moisture content (0.0 to 1.0) -----------------------------\n" +
                                "----- mgbrstrt = starting value,mgbrstp = ending value ,mgbrdelt = increment  , T ---\n" +
                                "----- mgbrstrt = X.xxxx        ,mgbrstp = X.xxxx       ,mgbrdelt = X.xxxx     , I ---\n" +
                                "----- mgbrstrt = "+f1.format(mgbrstrt)+"        ,mgbrstp = "+f1.format(mgbrstp)+"       ,mgbrdelt = "+f1.format(mgbrdelt)+"     , "+ft.format(t1)+" ---\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "------ dry density of branch material  (0.0 to 1.0)     -----------------------------\n" +
                                "----- rhostrt = starting value ,rhostp = ending value  ,rhodelt = increment   , T ---\n" +
                                "----- rhostrt = X.xxxx         ,rhostp = X.xxxx        ,rhodelt = X.xxxx      , I ---\n" +
                                "----- rhostrt = "+f1.format(rhostrt)+"         ,rhostp = "+f1.format(rhostp)+"        ,rhodelt = "+f1.format(rhodelt)+"      , "+ft.format(t2)+" ---\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "------ branch length  (meters) ------------------------------------------------------\n" +
                                "------ brnstrt = starting value, brnstp = ending value , brndelt = increment  , T ---\n" +
                                "------ brnstrt = XX.xx         , brnstp = XX.xx        , brndelt = XX.xx      , I ---\n" +
                                "------ brnstrt = "+f2.format(brnstrt1)+"         , brnstp = "+f2.format(brnstp1)+"        , brndelt = "+f2.format(brndelt1)+"      , "+ft.format(t3)+" ---\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "------ branch diameter (centimeters) ------------------------------------------------\n" +
                                "------ brnstrt = starting value, brnstp = ending value , brndelt = increment  , T ---\n" +
                                "------ brnstrt = XX.xxx        , brnstp = XX.xxx       , brndelt = XX.xxx     , I ---\n" +
                                "------ brnstrt = "+f3.format(brnstrt2)+"        , brnstp = "+f3.format(brnstp2)+"       , brndelt = "+f3.format(brndelt2)+"     , "+ft.format(t4)+" ---\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "------ branch density in crown (branches per cubic meter)      ----------------------\n" +
                                "------ brnstrt = starting value, brnstp = ending value , brndelt = increment  , T ---\n" +
                                "------ brnstrt = XXX.xxxx      , brnstp = XXX.xxxx     , brndelt = XXX.xxxx   , I ---\n" +
                                "------ brnstrt = "+f4.format(brnstrt3)+"      , brnstp = "+f4.format(brnstp3)+"     , brndelt = "+f4.format(brndelt3)+"   , "+ft.format(t5)+" ---\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n" +
                                "*************************************************************************************\n" +
                                "***************** BRANCH DISTRIBUTION FUNCTION DATA  ********************************\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "-- P.D.F. DESCRIBING BRANCH ORIENTATION IN THETAD      ------------------------------\n" +
                                "-------- PDF_T = P.D.F. INDICATOR ---------------------------------------------------\n" +
                                "-------- PDF_T = II -----------------------------------------------------------------\n" +
                                "-------- PDF_T = "+pdf.format(pdf_T)+" -----------------------------------------------------------------\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "-------- P.D.F. FUNCTION DESCRIPTIONS -----------------------------------------------\n" +
                                "-------- PDF_T = 1 FOR UNIFORN DISTRIBUTION\n" +
                                "-------- PDF_T = 2 FOR (SIN(2*THETA))**4\n" +
                                "-------- PDF_T = 3 FOR (SIN(THETA))**2\n" +
                                "-------- PDF_T = 4 FOR (SIN(THETA))**3\n" +
                                "-------- PDF_T = 5 FOR (SIN(THETA))**4\n" +
                                "-------- PDF_T = 6 FOR (SIN(THETA))**5\n" +
                                "-------- PDF_T = 7 FOR (SIN(THETA))**6\n" +
                                "-------- PDF_T = 8 FOR (SIN(THETA))**7\n" +
                                "-------- PDF_T = 9 FOR (SIN(THETA))**8\n" +
                                "-------- PDF_T =10 FOR (SIN(THETA))**9\n" +
                                "-------- PDF_T =11 FOR (SIN(THETA-30 degrees))**9\n" +
                                "-------- PDF_T =12 FOR (SIN(THETA+60 degrees))**9\n" +
                                "-------- PDF_T =13 FOR (SIN(THETA))**20\n" +
                                "-------- PDF_T =14 FOR (SIN(THETA+30 degrees))**20\n" +
                                "-------- PDF_T =17 FOR (SIN(THETA+64 degrees))**16  -- (skipped 15 and 16)\n" +
                                "-------- PDF_T =18 FOR (SIN(THETA+41 degrees))**8\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n" +
                                "*************************************************************************************\n" +
                                "");

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
