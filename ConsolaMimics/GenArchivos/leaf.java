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
public class leaf {
        AbrirGuardar ag=new AbrirGuardar();
        FileWriter fichero = null;
        PrintWriter pw = null;
        
        DecimalFormat f1=new DecimalFormat("0.0000");
         DecimalFormat ft=new DecimalFormat("0");
            DecimalFormat f2=new DecimalFormat("00.00");
            DecimalFormat f3=new DecimalFormat("0.00");
            DecimalFormat f4=new DecimalFormat("0000.00");
            
            float mglstrt;
            float mglstp;
            float mgldelt;
            float rhostrt;
            float rhostp;
            float rhodelt;
            float lthstrt;
            float lthstp;
            float lthdelt;
            float ldustrt;
            float ldustp;
            float ldudelt;
            float ldnstrt;
            float ldnstp;
            float ldndelt;
            float t1,t2,t3,t4,t5;
            float pdf_T;
        public leaf(float mtrt,float mstp,float mdelt,int T1,float rtrt,float rstp,float rdelt,float T2,float ltrt,float lstp,float ldelt,float T3,float ltrt2,float lstp2,float ldelt2,float T4,float lstrt3,float lstp3,float ldelt3,float T5,float pdf,String ruta){
            mglstrt=mtrt;
            mglstp=mstp;
            mgldelt=mdelt;
            t1=T1;
            rhostrt=rtrt;
            rhostp=rstp;
            rhodelt=rdelt;
            t2=T2;
            lthstrt=ltrt;
            lthstp=lstp;
            lthdelt=ldelt;
            t3=T3;
            ldustrt=ltrt2;
            ldustp=lstp2;
            ldudelt=ldelt2;
            t4=T4;
            ldnstrt=lstrt3;
            ldnstp=lstp3;
            ldndelt=ldelt3;
            t5=T5;
            pdf_T=pdf;
            escribeLeaf(ruta);
        }
        
        public leaf(String ruta){
            mglstrt=0.5796f;
            mglstp=0.5796f;
            mgldelt=0.2f;
            t1=0;
            rhostrt=0.0976f;
            rhostp=0.0976f;
            rhodelt=3;
            t2=0;
            lthstrt=0.1f;
            lthstp=0.1f;
            lthdelt=0.02f;
            t3=0;
            ldustrt=6.76f;
            ldustp=6.76f;
            ldudelt=2f;
            t4=0;
            ldnstrt=48.46f;
            ldnstp=48.46f;
            ldndelt=20f;
            t5=0;
            pdf_T=5;
            escribeLeaf(ruta);
        }
        
        
        private void escribeLeaf(String archivo){
                try{
                    fichero = new FileWriter(archivo);
                    pw = new PrintWriter(fichero);

                    /*for (int i = 0; i < 10; i++)
                        pw.println("Linea " + i);*/
                    pw.println( "*************************************************************************************\n" +
                                "******** This file provides input data to the program MIMICS_1_5.FTN         ********\n" +
                                "********   Data supplied here pertain to the forest canopy itself.           ********\n" +
                                "*************************************************************************************\n" +
                                "****** This file provides data pertaining to the leaf geometry.  ********************\n" +
                                "*************************************************************************************\n" +
                                "******* T = 0 to step the parameter value using the DELTA value  ********************\n" +
                                "******* T = 1 for parameter values read from the table           ********************\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "------ leaf gravimetric moisture content (0.0 to 1.0) - (wetwght-drywght)/(wetwght)--\n" +
                                "------ mglstrt = starting value, mglstp = ending value , mgldelt = increment  , T ---\n" +
                                "------ mglstrt = X.xxxx        , mglstp = X.xxxx       , mgldelt = X.xxxx     , I ---\n" +
                                "------ mglstrt = "+f1.format(mglstrt)+"        , mglstp = "+f1.format(mglstp)+"       , mgldelt = "+f1.format(mgldelt)+"     , "+ft.format(t1)+" ---\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "------ dry density of leaf material  (0.0 to 1.0)      ------------------------------\n" +
                                "----- rhostrt = starting value ,rhostp = ending value  ,rhodelt = increment   , T ---\n" +
                                "----- rhostrt = X.xxxx         ,rhostp = X.xxxx        ,rhodelt = X.xxxx      , I ---\n" +
                                "----- rhostrt = "+f1.format(rhostrt)+"         ,rhostp = "+f1.format(rhostp)+"        ,rhodelt = "+f1.format(rhodelt)+"      , "+ft.format(t2)+" ---\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "------ leaf thickness (centimeters) -------------------------------------------------\n" +
                                "------ lthstrt = starting value, lthstp = ending value , lthdelt = increment  , T ---\n" +
                                "------ lthstrt = X.xxxx        , lthstp = X.xxxx       , lthdelt = X.xxxx     , I ---\n" +
                                "------ lthstrt = "+f1.format(lthstrt)+"        , lthstp = "+f1.format(lthstp)+"       , lthdelt = "+f1.format(lthdelt)+"     , "+ft.format(t3)+" ---\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "------ leaf diameter (centimeters) --------------------------------------------------\n" +
                                "------ ldustrt = starting value, ldustp = ending value , ldudelt = increment  , T ---\n" +
                                "------ ldustrt = XX.xx         , ldustp = XX.xx        , ldudelt = X.xx       , I ---\n" +
                                "------ ldustrt = "+f2.format(ldustrt)+"         , ldustp = "+f2.format(ldustp)+"        , ldudelt = "+f3.format(ldudelt)+"       , "+ft.format(t4)+" ---\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "------ leaf density in crown (leaves per cubic meter)      --------------------------\n" +
                                "------ ldnstrt = starting value, ldnstp = ending value , ldndelt = increment , T ----\n" +
                                "------ ldnstrt = XXXX.xx       , ldnstp = XXXX.xx      , ldndelt = XXXX.xx   , I ----\n" +
                                "------ ldnstrt = "+f4.format(ldnstrt)+"       , ldnstp = "+f4.format(ldnstp)+"      , ldndelt = "+f4.format(ldndelt)+"   , "+ft.format(t5)+" ----\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "***************** LEAF DISTRIBUTION FUNCTION DATA  **********************************\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "-- P.D.F. DESCRIBING LEAF ORIENTATION IN THETAD      -------------------------------\n" +
                                "-------- PDF_T = P.D.F. INDICATOR ---------------------------------------------------\n" +
                                "-------- PDF_T = I ------------------------------------------------------------------\n" +
                                "-------- PDF_T = "+ft.format(pdf_T)+" ------------------------------------------------------------------\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "-------- P.D.F. FUNCTION DESCRIPTIONS -----------------------------------------------\n" +
                                "-------- PDF_T = 1 FOR UNIFORN DISTRIBUTION\n" +
                                "-------- PDF_T = 2  -- planophile     --- BETA DISTRIBUTION\n" +
                                "-------- PDF_T = 3  -- erectophile    --- BETA DISTRIBUTION\n" +
                                "-------- PDF_T = 4  -- plagiophile    --- BETA DISTRIBUTION\n" +
                                "-------- PDF_T = 5  -- extremophile   --- BETA DISTRIBUTION\n" +
                                "-------- PDF_T = 6  -- uniform        --- BETA DISTRIBUTION\n" +
                                "-------- PDF_T = 7  -- spherical      --- BETA DISTRIBUTION\n" +
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
