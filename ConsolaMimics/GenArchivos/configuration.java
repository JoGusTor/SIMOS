/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GenArchivos;

import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author zero
 */
public class configuration {
        AbrirGuardar ag=new AbrirGuardar();
        FileWriter fichero = null;
        PrintWriter pw = null;
        public configuration(String ruta){
            escribeConfiguration(ruta);
        }
        
        
        private void escribeConfiguration(String archivo){
                try{
                    fichero = new FileWriter(archivo);
                    pw = new PrintWriter(fichero);

                    /*for (int i = 0; i < 10; i++)
                        pw.println("Linea " + i);*/
                    pw.println( "**********************************************************************************\n" +
                                "****** This file provides input data to the program MIMICS_1_5.FTN   *************\n" +
                                "******     Data supplied here pertain to the configuration of        *************\n" +
                                "******                   the input parameters.                       *************\n" +
                                "**********************************************************************************\n" +
                                "1234567890123456789012345678901234567890123456789012345678901234567890123456789012\n" +
                                "**********************************************************************************\n" +
                                "----- Output file configurations (create indicated output file: 0 = NO, 1= YES) --\n" +
                                "-----   POL = polarimetric.out,        PHASE = forest_phase_mats.out -------------\n" +
                                "-----   KAPPA = forest_kappa_mats.out, TRANS = forest_trans_mats.out -------------\n" +
                                "----------------------------------------------------------------------------------\n" +
                                "----- POL = I -- PHASE = I -- KAPPA = I --  TRANS = I ----------------------------\n" +
                                "----- POL = 0 -- PHASE = 0 -- KAPPA = 0 --  TRANS = 1 ----------------------------\n" +
                                "----------------------------------------------------------------------------------\n" +
                                "**********************************************************************************\n" +
                                "----------------------------------------------------------------------------------\n" +
                                "----- Crown characteristics formatting -------------------------------------------\n" +
                                "-----   T   =   trunks  present           (0 = NO, 1= YES) -----------------------\n" +
                                "-----   B_1 =  primary branches present   (0 = NO, 1= YES) -----------------------\n" +
                                "-----   B_2 =  secondary branches present (0 = NO, 1= YES) -----------------------\n" +
                                "-----   L   =    leaves present           (0 = NO, 1= YES) -----------------------\n" +
                                "-----   N   =   needles present           (0 = NO, 1= YES) -----------------------\n" +
                                "----------------------------------------------------------------------------------\n" +
                                "----- T = I ------- B_1 = I ---- B_2 = I -------- L = I -------  N = I -----------\n" +
                                "----- T = 1 ------- B_1 = 1 ---- B_2 = 1 -------- L = 1 -------  N = 0 -----------\n" +
                                "----------------------------------------------------------------------------------\n" +
                                "**********************************************************************************\n" +
                                "----------------------------------------------------------------------------------\n" +
                                "----- Format for dielectric data -------------------------------------------------\n" +
                                "----- FORM = 0 ,moisture input(dry density assumed to be that of leafy vegetation)\n" +
                                "----- FORM = 1 ,moisture input(dry density given) (NOT ALLOWED FOR SOIL)     -----\n" +
                                "----- FORM = 2 ,dielectric specified by user in the dielectric look-up table -----\n" +
                                "----- SPECIFY DATA TYPE FOR EACH CANOPY CONSTITUENT ------------------------------\n" +
                                "----------------------------------------------------------------------------------\n" +
                                "----------------------- BRANCHES -------------------------------------------------\n" +
                                "----- TRUNKS ---- PRIMARY --- SECONDARY ------- LEAVES --- NEEDLES ---- GROUND ---\n" +
                                "----- FORM = I -- FORM = I --- FORM = I ------ FORM = I -- FORM = I -- FORM = I --\n" +
                                "----- FORM = 2 -- FORM = 2  -- FORM = 2 ------ FORM = 2 -- FORM = 0 -- FORM = 0 --\n" +
                                "----------------------------------------------------------------------------------\n" +
                                "**********************************************************************************\n" +
                                "----------------------------------------------------------------------------------\n" +
                                "----- Size Integration Formatting ------------------------------------------------\n" +
                                "----- PDF(a,b) = PDF specified in size                                  ----------\n" +
                                "-----  (cylinder length and diameter or disk thickness and diameter)    ---------- \n" +
                                "-----  (0=default input file values, 1=lookup in PSDF subroutine,       ----------\n" +
                                "-----                                2=read from histogram data file)   ----------\n" +
                                "----------------------- BRANCHES -------------------------------------------------\n" +
                                "----- TRUNKS ---- PRIMARY --- SECONDARY ------- LEAVES --- NEEDLES ---------------\n" +
                                "--- PDF(a,b)=I  PDF(a,b)=I - PDF(a,b)=I ---- PDF(a,b)=I  PDF(a,b)=I --------------\n" +
                                "--- PDF(a,b)=2  PDF(a,b)=0 - PDF(a,b)=0 ---- PDF(a,b)=0  PDF(a,b)=0 --------------\n" +
                                "----------------------------------------------------------------------------------\n" +
                                "**********************************************************************************\n" +
                                "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n" +
                                "**********************************************************************************\n" +
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
