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
public class trunk_and_gross_canopy {
        AbrirGuardar ag=new AbrirGuardar();
        FileWriter fichero = null;
        PrintWriter pw = null;
        DecimalFormat f1=new DecimalFormat("0.0000");
         DecimalFormat ft=new DecimalFormat("0");
            DecimalFormat f2=new DecimalFormat("00.0000");
            DecimalFormat f3=new DecimalFormat("000.00");
            DecimalFormat f4=new DecimalFormat("0000.00");
            DecimalFormat fpdf=new DecimalFormat("00");
        
        float mgtrstrt;
        float mgtrstp; 
        float mgtrdelt;
        float rhostrt;
        float rhostp;
        float rhodelt;
        float denstrt; 
        float denstp; 
        float densdelt;
        float Hcstrt;
        float Hcstp; 
        float Hcdelt;
        float dtstrt;
        float dtstp;
        float dtdelt;
        float ltstrt; 
        float ltstp;
        float ltdelt;
        float sdtstrt;
        float sdtstp;
        float sdtdelt;
        float sltstrt;
        float sltstp ;
        float sltdelt;
        float t1,t2,t3,t4,t5,t6,t7,t8;
        float pdf_T;
        float pdf_S;
       
        public trunk_and_gross_canopy(float mtrt,float mstp,float mdelt,int T1,float rtrt,float rstp,float rdelt,int T2,float dtrt,float dstp,float ddelt,int T3,float htrt,float hstp,float hdelt,int T4,float dttrt,float dtdtp,float dtdel,int T5,float ltrt,float lstp,float ldelt,int T6,float strt,float sstp,float sdelt,int T7,float sltrt,float slstp,float sldelt,int T8,float pdf1,float pdf2,String ruta){
            mgtrstrt=mtrt;
            mgtrstp =mstp;
            mgtrdelt=mdelt;
            t1=T1;
            rhostrt=rtrt;
            rhostp=rstp;
            rhodelt=rdelt;
            t2=T2;
            denstrt =dtrt;
            denstp =dstp;
            densdelt=ddelt;
            t3=T3;
            Hcstrt=htrt;
            Hcstp =hstp;
            Hcdelt=hdelt;
            t4=T4;
            dtstrt=dttrt;
            dtstp =dtdtp;
            dtdelt=dtdel;
            t5=T5;
            ltstrt =ltrt;
            ltstp=lstp;
            ltdelt=ldelt;
            t6=T6;
            sdtstrt=strt;
            sdtstp=sstp;
            sdtdelt=sdelt;
            t7=T7;
            sltstrt=sltrt;
            sltstp =slstp;
            sltdelt=sldelt;
            t8=T8;
            pdf_T=pdf1;
            pdf_S=pdf2;
            escribeTrunkAndGrossCanopy(ruta);
        }
        
        public trunk_and_gross_canopy(String ruta){
            mgtrstrt=0.5f;
            mgtrstp =0.5f;
            mgtrdelt=0.1f;
            t1=0;
            rhostrt=0.5f;
            rhostp=0.5f;
            rhodelt=0.1f;
            t2=0;
            denstrt =0.128f;
            denstp =0.128f;
            densdelt=0.1f;
            t3=0;
            Hcstrt=6.9f;
            Hcstp =6.9f;
            Hcdelt=2f;
            t4=0;
            dtstrt=14.5f;
            dtstp =14.5f;
            dtdelt=10;
            t5=0;
            ltstrt =14.16f;
            ltstp=14.16f;
            ltdelt=1f;
            t6=0;
            sdtstrt=4f;
            sdtstp=4f;
            sdtdelt=10f;
            t7=0;
            sltstrt=5f;
            sltstp =5f;
            sltdelt=1f;
            t8=0;
            pdf_T=4;
            pdf_S=0;
            escribeTrunkAndGrossCanopy(ruta);
        }
        
        
        private void escribeTrunkAndGrossCanopy(String archivo){
                try{
                    fichero = new FileWriter(archivo);
                    pw = new PrintWriter(fichero);

                    /*for (int i = 0; i < 10; i++)
                        pw.println("Linea " + i);*/
                    pw.println( "*************************************************************************************\n" +
                                "******** This file provides input data to the program MIMICS_1_5.FTN         ********\n" +
                                "********   Data supplied here pertain to the forest canopy itself.           ********\n" +
                                "*************************************************************************************\n" +
                                "****** This file provides data pertaining to the                       **************\n" +
                                "****** tunk geometry and gross canopy characteristics                  **************\n" +
                                "*************************************************************************************\n" +
                                "******* T = 0 to step the parameter value using the DELTA value  ********************\n" +
                                "******* T = 1 for parameter values read from the table           ********************\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "------ trunk gravimetric moisture content (0.0 to 1.0) ------------------------------\n" +
                                "----- mgtrstrt = starting value,mgtrstp = ending value ,mgtrdelt = increment  , T ---\n" +
                                "----- mgtrstrt = X.xxxx        ,mgtrstp = X.xxxx       ,mgtrdelt = X.xxxx     , I ---\n" +
                                "----- mgtrstrt = "+f1.format(mgtrstrt)+"        ,mgtrstp = "+f1.format(mgtrstp)+"       ,mgtrdelt = "+f1.format(mgtrdelt)+"     , "+ft.format(t1)+" ---\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "------ dry density of trunk material  (0.0 to 1.0)     ------------------------------\n" +
                                "----- rhostrt = starting value ,rhostp = ending value  ,rhodelt = increment   , T ---\n" +
                                "----- rhostrt = X.xxxx         ,rhostp = X.xxxx        ,rhodelt = X.xxxx      , I ---\n" +
                                "----- rhostrt = "+f1.format(rhostrt)+"         ,rhostp = "+f1.format(rhostp)+"        ,rhodelt = "+f1.format(rhodelt)+"      , "+ft.format(t2)+" ---\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "--------  Canopy density (trees per square meter)  ----------------------------------\n" +
                                "------ denstrt = starting value, denstp = ending value, densdelt = increment  , T ---\n" +
                                "------ denstrt = X.xxxx        , denstp = X.xxxx      , densdelt = X.xxxx     , I ---\n" +
                                "------ denstrt = "+f1.format(denstrt)+"        , denstp = "+f1.format(denstp)+"      , densdelt = "+f1.format(densdelt)+"     , "+ft.format(t3)+" ---\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "--------  crown thickness (meters)   ------------------------------------------------\n" +
                                "------  Hcstrt = starting value , Hcstp = ending value ,  Hcdelt = increment  , T ---\n" +
                                "------  Hcstrt = XX.xxxx        , Hcstp = XX.xxxx      ,  Hcdelt = XX.xxxx    , I ---\n" +
                                "------  Hcstrt = "+f2.format(Hcstrt)+"        , Hcstp = "+f2.format(Hcstp)+"      ,  Hcdelt = "+f2.format(Hcdelt)+"    , "+ft.format(t4)+" ---\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "--  trunk diameter data (centimeters) (also is the mean value MU for Normal Dist)  --\n" +
                                "------  dtstrt = starting value , dtstp = ending value ,  dtdelt = increment  , T ---\n" +
                                "------  dtstrt = XXX.xx         , dtstp = XXX.xx       ,  dtdelt = XXX.xx     , I ---\n" +
                                "------  dtstrt = "+f3.format(dtstrt )+"         , dtstp = "+f3.format(dtstp)+"       ,  dtdelt = "+f3.format(dtdelt)+"     , "+ft.format(t5)+" ---\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "----   trunk length data (meters) (also is the mean value MU for Normal Dist.)  ----- \n" +
                                "------  ltstrt = starting value , ltstp = ending value ,  ltdelt = increment  , T ---\n" +
                                "------  ltstrt = XXX.xx         , ltstp = XXX.xx       ,  ltdelt = XXX.xx     , I ---\n" +
                                "------  ltstrt = "+f3.format(ltstrt)+"         , ltstp = "+f3.format(ltstp)+"       ,  ltdelt = "+f3.format(ltdelt)+"     , "+ft.format(t6)+" ---\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "--  trunk diameter standard deviation (sigma) data (for Normal Dist) (centimeters) -- \n" +
                                "------ sdtstrt = starting value ,sdtstp = ending value , sdtdelt = increment  , T ---\n" +
                                "------ sdtstrt = XXX.xx         ,sdtstp = XXX.xx       , sdtdelt = XXX.xx     , I ---\n" +
                                "------ sdtstrt = "+f3.format(sdtstrt)+"         ,sdtstp = "+f3.format(sdtstp)+"       , sdtdelt = "+f3.format(sdtdelt)+"     , "+ft.format(t7)+" ---\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "--  trunk length standard deviation (sigma) data (for Normal Dist.) data (meters)  --\n" +
                                "------ sltstrt = starting value ,sltstp = ending value , sltdelt = increment  , T ---\n" +
                                "------ sltstrt = XXX.xx         ,sltstp = XXX.xx       , sltdelt = XXX.xx     , I ---\n" +
                                "------ sltstrt = "+f3.format(sltstrt)+"         ,sltstp = "+f3.format(sltstp)+"       , sltdelt = "+f3.format(sltdelt)+"     , "+ft.format(t8)+" ---\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n" +
                                "*************************************************************************************\n" +
                                "*************************************************************************************\n" +
                                "***************** TRUNK DISTRIBUTION FUNCTION DATA  *********************************\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "-- P.D.F. DESCRIBING TRUNK ORIENTATION IN THETAT      -------------------------------\n" +
                                "-------- PDF_T = P.D.F. INDICATOR ---------------------------------------------------\n" +
                                "-------- PDF_T = II -----------------------------------------------------------------\n" +
                                "-------- PDF_T = "+fpdf.format(pdf_T)+" -----------------------------------------------------------------\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "-- P.D.F. DESCRIBING TRUNK SIZE PARAMETERS    ---------------------------------------\n" +
                                "-------- PDF_S = P.D.F. INDICATOR ---------------------------------------------------\n" +
                                "-------- PDF_S = II -----------------------------------------------------------------\n" +
                                "-------- PDF_S = "+fpdf.format(pdf_S)+" -----------------------------------------------------------------\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "-------- P.D.F. FUNCTION DESCRIPTIONS -----------------------------------------------\n" +
                                "-------- PDF_T =  1 FOR VERTICAL TRUNKS\n" +
                                "-------- PDF_T =  2 FOR COS(THETA_c)**8\n" +
                                "-------- PDF_T =  3 FOR COS(THETA_c)**6\n" +
                                "-------- PDF_T =  4 FOR 8*EXP(-8*THETA_c)\n" +
                                "-------- PDF_S =  0 FOR SPECIFIED SINGLE VALUE SIZE\n" +
                                "-------- PDF_S =  1 FOR HISTOGRAM DATA\n" +
                                "-- 2,3,4 below are not yet entirely implemented\n" +
                                "-------- PDF_S =  2 FOR NORMAL DISTRIBUTION IN DIAMETER WITH SPECIFIED MEAN AND SIGMA\n" +
                                "-------- PDF_S =  3 FOR NORMAL DISTRIBUTION IN HEIGHT WITH SPECIFIED MEAN AND SIGMA\n" +
                                "-------- PDF_S =  4 FOR NORMAL DIST IN DIAM AND HGHT WITH SPECIFIED MEANS AND SIGMAS\n" +
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
