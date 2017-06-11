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
public class ground {
        AbrirGuardar ag=new AbrirGuardar();
        FileWriter fichero = null;
        PrintWriter pw = null;
        
        DecimalFormat f1=new DecimalFormat("0");
        DecimalFormat f2=new DecimalFormat("0.0000");
        DecimalFormat f3=new DecimalFormat("00.0000");
        DecimalFormat f4=new DecimalFormat("0000.000");
        DecimalFormat f5=new DecimalFormat("000.00");
        DecimalFormat f6=new DecimalFormat("00.00");
        DecimalFormat pdf=new DecimalFormat("00");
        
        float surface_type;
        float snow;

        float mvsstrt;
        float mvsstp;
        float mvsdelt;
        float t1;

        float rmsstrt;
        float rmsstp;
        float rmsdelt;
        float t2;

        float lsstrt;
        float lsstp;
        float lsdelt;
        float t3;

        float Sstrt;
        float Sstp;
        float Sdelt;
        float t4;

        float Cstrt;
        float Cstp;
        float Cdelt;
        float t5;

        float sltstrt;
        float sltstp;
        float sltdelt;
        float t6;

        float thsstrt;
        float thsstp;
        float thsdelt;
        float t7;

        float pdf_T;
        public ground(float sur,float sn,float mtrt,float mtop,float mdelt,int T1,float rtrt,float rtop,float rdelt,int T2,float ltrt,float ltop,float ldelt,int T3,float strt,float stop,float sdelt,int T4,float ctrt,float ctop,float cdelt,int T5,float sltrt,float sltop,float sldelt,int T6,float ttrt,float ttop,float tdelt,int T7,float pdf,String ruta){
        surface_type=sur;
        snow=sn;

        mvsstrt=mtrt;
        mvsstp=mtop;
        mvsdelt=mdelt;
        t1=T1;

        rmsstrt=rtrt;
        rmsstp=rtop;
        rmsdelt=rdelt;
        t2=T2;

        lsstrt=ltrt;
        lsstp=ltop;
        lsdelt=ldelt;
        t3=T3;

        Sstrt=strt;
        Sstp=stop;
        Sdelt=sdelt;
        t4=T4;

        Cstrt=ctrt;
        Cstp=ctop;
        Cdelt=cdelt;
        t5=T5;

        sltstrt=sltrt;
        sltstp=sltop;
        sltdelt=sldelt;
        t6=T6;

        thsstrt=ttrt;
        thsstp=ttop;
        thsdelt=tdelt;
        t7=T7;

        pdf_T=pdf;
            escribeGround(ruta);
        }
        
        
        public ground(String ruta){
        surface_type=1;
        snow=0;

        mvsstrt=0.1914f;
        mvsstp=0.1914f;
        mvsdelt=0.0200f;
        t1=0f;

        rmsstrt=0.9000f;
        rmsstp=0.9000f;
        rmsdelt=0.1000f;
        t2=0f;

        lsstrt=15.000f;
        lsstp=15.000f;
        lsdelt=1.000f;
        t3=0f;

        Sstrt=50.00f;
        Sstp=50.00f;
        Sdelt=10.00f;
        t4=0f;

        Cstrt=10.00f;
        Cstp=10.00f;
        Cdelt=10.00f;
        t5=0f;

        sltstrt=2.50f;
        sltstp=2.50f;
        sltdelt=2.50f;
        t6=0f;

        thsstrt=0.0000f;
        thsstp=0.0000f;
        thsdelt=0.3000f;
        t7=0f;

        pdf_T=03;
            escribeGround(ruta);
        }
        
        
        private void escribeGround(String archivo){
                try{
                    fichero = new FileWriter(archivo);
                    pw = new PrintWriter(fichero);

                    /*for (int i = 0; i < 10; i++)
                        pw.println("Linea " + i);*/
                    pw.println( "*************************************************************************************\n" +
                                "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345\n" +
                                "*************************************************************************************\n" +
                                "******** This file provides input data to the program MIMICS_1_5.FTN   **************\n" +
                                "********   Data supplied here pertain to the ground surface.           **************\n" +
                                "*************************************************************************************\n" +
                                "******* T = 0 to step the parameter value using the DELTA value  ********************\n" +
                                "******* T = 1 for parameter valuyes read from the table          ********************\n" +
                                "*************************************************************************************\n" +
                                "-------  Type of ground surface  ----------------------------------------------------\n" +
                                "-------     1 = soil, 2 = standing water, 3 = ice -----------------------------------\n" +
                                "-------  Underlying surface type  ----  Snow layer on top?  (0=no, 1=yes)  ----------\n" +
                                "-------  surface type = I         ----  snow = I  -----------------------------------\n" +
                                "-------  surface type = "+f1.format(surface_type)+"         ----  snow = "+f1.format(snow)+"  -----------------------------------\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "*******  Input data for soil surface ************************************************\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "------ soil volumetric moisture content - (waterwght in sample)/(volume of sample)---\n" +
                                "------ mvsstrt = starting value, mvsstp = ending value , mvsdelt = increment  , T ---\n" +
                                "------ mvsstrt = X.xxxx        , mvsstp = X.xxxx       , mvsdelt = X.xxxx     , I ---\n" +
                                "------ mvsstrt = "+f2.format(mvsstrt)+"        , mvsstp = "+f2.format(mvsstp)+"       , mvsdelt = "+f2.format(mvsdelt)+"     , "+f1.format(t1)+" ---\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "-------- rms height (centimeters) -- T = 1 for look-up table values , 0 otherwise ---\n" +
                                "------ rmsstrt = starting value, rmsstp = ending value , rmsdelt = increment  , T ---\n" +
                                "------ rmsstrt = XX.xxxx       , rmsstp = XX.xxxx      , rmsdelt = XX.xxxx    , I ---\n" +
                                "------ rmsstrt = "+f3.format(rmsstrt)+"       , rmsstp = "+f3.format(rmsstp)+"      , rmsdelt = "+f3.format(rmsdelt)+"    , "+f1.format(t2)+" ---\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "------ Gaussian Autocorrelation Parameters - Correlation length (centimeters) -------\n" +
                                "------  lsstrt = starting value,  lsstp = ending value ,  lsdelt = increment , T ----\n" +
                                "------  lsstrt = XXXX.xxx      ,  lsstp = XXXX.xxx     ,  lsdelt = XXXX.xxx  , I ----\n" +
                                "------  lsstrt = "+f4.format(lsstrt)+"      ,  lsstp = "+f4.format(lsstp)+"     ,  lsdelt = "+f4.format(lsdelt)+"  , "+f1.format(t3)+" ----\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "- data for percent(by weight) sand, silt and clay parameters (silt+sand+clay = 100) -\n" +
                                "-----------------------------------------(silt is assumed from the other two) -------\n" +
                                "------  percent sand ----------------------------------------------------------------\n" +
                                "------   Sstrt = starting value,   Sstp = ending value  ,  Sdelt = increment , T ----\n" +
                                "------   Sstrt = XXX.xx        ,   Sstp = XXX.xx        ,  Sdelt = XXX.xx    , I ----\n" +
                                "------   Sstrt = "+f5.format(Sstrt)+"        ,   Sstp = "+f5.format(Sstp)+"        ,  Sdelt = "+f5.format(Sdelt)+"    , "+f1.format(t4)+" ----\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "------  percent clay ----------------------------------------------------------------\n" +
                                "------   Cstrt = starting value,   Cstp = ending value  ,  Cdelt = increment , T ----\n" +
                                "------   Cstrt = XXX.xx        ,   Cstp = XXX.xx        ,  Cdelt = XXX.xx    , I ----\n" +
                                "------   Cstrt = "+f5.format(Cstrt)+"        ,   Cstp = "+f5.format(Cstp)+"        ,  Cdelt = "+f5.format(Cdelt)+"    , "+f1.format(t5)+" ----\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "*******  Input data for standing water surface **************************************\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "-------  salinity of standing water (parts per thousand (ppt))\n" +
                                "------ sltstrt = starting value, sltstp = ending value , sltdelt = increment , T ----\n" +
                                "------ sltstrt = XX.xx         , sltstp = XX.xx        , sltdelt = XX.xx     , I ----\n" +
                                "------ sltstrt = "+f6.format(sltstrt)+"         , sltstp = "+f6.format(sltstp)+"        , sltdelt = "+f6.format(sltdelt)+"     , "+f1.format(t6)+" ----\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "*******  Input data for snow surface ************************************************\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "------ snow layer depth (thickness) -- (meters) -------------------------------------\n" +
                                "------ thsstrt = starting value, thsstp = ending value , thsdelt = increment  , T ---\n" +
                                "------ thsstrt = X.xxxx        , thsstp = X.xxxx       , thsdelt = X.xxxx     , I ---\n" +
                                "------ thsstrt = "+f2.format(thsstrt)+"        , thsstp = "+f2.format(thsstp)+"       , thsdelt = "+f2.format(thsdelt)+"     , "+f1.format(t7)+" ---\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "*******  Input data for surface model ***********************************************\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "-------- Surface Scattering Model Descriptor ----------------------------------------\n" +
                                "-------- I_SUR = P.D.F. INDICATOR ---------------------------------------------------\n" +
                                "-------- I_SUR = II -----------------------------------------------------------------\n" +
                                "-------- I_SUR = "+pdf.format(pdf_T)+" -----------------------------------------------------------------\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "-------- Surface Scattering Model Descriptors ---------------------------------------\n" +
                                "-------- P.D.F. FUNCTION DESCRIPTIONS -----------------------------------------------\n" +
                                "-------- I_SUR =  1   Geometrical Optics model used\n" +
                                "-------- I_SUR =  2   Physical Optics model used\n" +
                                "-------- I_SUR =  3   Small Perturbation model used\n" +
                                "-------------------------------------------------------------------------------------\n" +
                                "*************************************************************************************\n" +
                                "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n" +
                                "*************************************************************************************\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "\n" +
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
