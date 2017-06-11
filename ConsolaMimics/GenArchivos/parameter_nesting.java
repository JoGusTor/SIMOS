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
public class parameter_nesting {
        AbrirGuardar ag=new AbrirGuardar();
        FileWriter fichero = null;
        PrintWriter pw = null;
        public parameter_nesting(String ruta){
            escribeParameterNesting(ruta);
        }
        
        
        private void escribeParameterNesting(String archivo){
                try{
                    fichero = new FileWriter(archivo);
                    pw = new PrintWriter(fichero);

                    /*for (int i = 0; i < 10; i++)
                        pw.println("Linea " + i);*/
                    pw.println( "**********************************************************************\n" +
                                "1234567890123456789012345678901234567890123456789012345678901234567890\n" +
                                "**********************************************************************\n" +
                                "** INPUT FILE INDICATING THE NESTING ORDER OF THE CANOPY PARAMETERS **\n" +
                                "** ALSO LISTED IS THE NUMBER ASSIGNED TO EACH PARAMETER             **\n" +
                                "**   FOR IDENTIFICATION PURPOSES IN THE PROGRAM                     **\n" +
                                "**********************************************************************\n" +
                                "******** NOTE THAT LOWER NUMBERS ARE LOOPED THROUGH FIRST  ***********\n" +
                                "**********************************************************************\n" +
                                "----------------------------------------------------------------------\n" +
                                "PARAMETER            NESTING\n" +
                                " NUMBER               ORDER\n" +
                                "----------------------------------------------------------------------\n" +
                                "  1  THETA_DEGREES  =   1       radar look angle                    {SENSOR PARAMETERS\n" +
                                "  2  FREQ_GHZ       =   2       radar frequency\n" +
                                "  3  MV_SOIL        =   3       soil volumetric moisture            {GROUND PARAMETERS, also corresponds to soil dielectric\n" +
                                "  4  RMS_SOIL       =   4       surface RMS height\n" +
                                "  5  LS_SOIL        =   5       surface correlation length\n" +
                                "  6  SAND           =   6       soil % sand\n" +
                                "  7  CLAY           =   7       soil % clay\n" +
                                "  8  SALT           =   8       standing water salt content (ppt)\n" +
                                "  9  MG_TRUNK       =   9       gravimetric trunk moisture           {TRUNK PARAMETERS, also corresponds to trunk dielectric\n" +
                                " 10  DENSITY        =  10       canopy tree density\n" +
                                " 11  RHO_TRUNK      =  11       dry density of trunk material\n" +
                                " 12  CROWN_HGHT     =  12       crown layer height\n" +
                                " 13  TRUNK_DIAM     =  13       trunk diameter\n" +
                                " 14  TRUNK_HGHT     =  14       trunk layer height\n" +
                                " 15  MG_LEAF        =  15       gravimetric leaf moisture            {LEAF PARAMETERS, also corresponds to leaf dielectric\n" +
                                " 16  RHO_LEAF       =  16       dry density of leaf material\n" +
                                " 17  LEAF_DENS      =  17       leaf number density\n" +
                                " 18  LEAF_DIAM      =  18       leaf diameter\n" +
                                " 19  LEAF_TAU       =  19       leaf thickness\n" +
                                " 20  MG_NDL         =  20       gravimetric needle moisture          {NEEDLE PARAMETERS, also corresponds to needle dielectric\n" +
                                " 21  RHO_NDL        =  21       dry density of needle material\n" +
                                " 22  NDL_DENS       =  22       needle number density\n" +
                                " 23  NDL_DIAM       =  23       needle diameter\n" +
                                " 24  NDL_LNG        =  24       needle length\n" +
                                " 25  MG_BR1         =  25       gravimetric branch moisture          {PRIMARY BRANCH PARAMETERS, also corresponds to prim. branch dielectric\n" +
                                " 26  RHO_BR1        =  26       dry density of branch material       {PRIMARY BRANCH PARAMETERS\n" +
                                " 27  BR1_DENS       =  27       branch number density                {PRIMARY BRANCH PARAMETERS\n" +
                                " 28  BR1_DIAM       =  28       branch diameter                      {PRIMARY BRANCH PARAMETERS\n" +
                                " 29  BR1_LNG        =  29       branch length                        {PRIMARY BRANCH PARAMETERS\n" +
                                " 30   T_SOIL        =  30       temperature of soil                  {ENVIRONMENT PARAMETERS\n" +
                                " 31   T_VEG         =  31       temperature of vegetation\n" +
                                " 32   T_WATER       =  32       temperature of standing water\n" +
                                " 33  MG_BR2         =  33       gravimetric branch moisture          {SECONDARY BRANCH PARAMETERS, also corresponds to sec. branch dielectric\n" +
                                " 34  RHO_BR2        =  34       dry density of branch material       {SECONDARY BRANCH PARAMETERS\n" +
                                " 35  BR2_DENS       =  35       branch number density                {SECONDARY BRANCH PARAMETERS\n" +
                                " 36  BR2_DIAM       =  36       branch diameter                      {SECONDARY BRANCH PARAMETERS\n" +
                                " 37  BR2_LNG        =  37       branch length                        {SECONDARY BRANCH PARAMETERS\n" +
                                " 38  TRUNK_Dsig     =  38       trunk diameter sigma (standard dev.) {trunk statistics\n" +
                                " 39  TRUNK_Hsig     =  39       trunk height sigma (standard dev.)   {trunk statistics\n" +
                                " 40  T_SNOW         =  40       snow layer thickness                 {snow layer parameters\n" +
                                " 41  eps_SNOW       =  41       snow layer relative dielectric\n" +
                                "\n" +
                                "**********************************************************************\n" +
                                "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n" +
                                "**********************************************************************");

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
