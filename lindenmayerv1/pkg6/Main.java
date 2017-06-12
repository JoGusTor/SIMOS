/*Opensource
 */
package lindenmayerv1.pkg6;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Stack;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;

/**
 *
 * @author aeternum.  Arbol 3D
 */
public class Main{

    public static void main(String[] args) {
        Stack<Estado> pila = new Stack<Estado>();
        Dimension dim;
        
        /*
        Calculos necesarios propios del proyecto
        */
        
        
        
        
        
        double anguloGeneral = 90;
        double radioGeneral = 0.003;// aprox 
        double pasoGeneral = 0.05;
        // Se generan los modulos que se usaran para el sistema L
        Modulo w = new Modulo('w', 0); //Axioma
        Modulo F = new Modulo('F', 0); //Avanza o traza linea
        Modulo arriba = new Modulo('+', 0);//Gira derecha (sobre eje z)
        Modulo abajo = new Modulo('-', 0);//Gira izquierda (sobre eje z)
        Modulo izquierda = new Modulo('{', 0); //Gira abajo  (sobre eje y) &
        Modulo derecha = new Modulo('}', 0); //Gira arriba  (sobre eje y) ^
        Modulo atras = new Modulo('<', 0); //Gira atras (sobre eje x)\
        Modulo adelante = new Modulo('>', 0); //Gira adelante (sobre eje x)/
        Modulo gira180 = new Modulo('|', 0); //Gira 180 grados(sobre eje z)|
        Modulo abre = new Modulo('[', 0);//Abre rama
        Modulo cierra = new Modulo(']', 0);//Cierra rama
        Modulo X = new Modulo('X', 0); //simbolo mudo
        Modulo A = new Modulo('A', 0);//Rojo
        Modulo B = new Modulo('B', 0);//Azul
        Modulo C = new Modulo('C', 0);//Verde
        Modulo D = new Modulo('D', 0);//Amarillo
        Modulo L = new Modulo('L', 0);//Hoja (Leaf)
        Modulo S = new Modulo('S', 0);//Internodo
        Modulo h = new Modulo('h', 0);//Paso chiquito :P
        Modulo dismDiam = new Modulo('!', 0); //disminuye diametro
        /*Hoja 
        CadModulo p1Suc = new CadModulo();
        p1Suc.agregaModulo(L);

        //Produccion 1
        CadModulo p2Suc = new CadModulo();
        p2Suc.agregaModulo(abajo);     //B
        p2Suc.agregaModulo(F);
        p2Suc.agregaModulo(X);

        p2Suc.agregaModulo(arriba);
        p2Suc.agregaModulo(X);

        p2Suc.agregaModulo(abajo);
        p2Suc.agregaModulo(F);
        p2Suc.agregaModulo(X);

        p2Suc.agregaModulo(abajo);
        p2Suc.agregaModulo(gira180);
        p2Suc.agregaModulo(abajo);

        p2Suc.agregaModulo(F);
        p2Suc.agregaModulo(X);

        p2Suc.agregaModulo(arriba);
        p2Suc.agregaModulo(X);
        p2Suc.agregaModulo(arriba);

        p2Suc.agregaModulo(F);
        p2Suc.agregaModulo(X);

        CadModulo p3Suc = new CadModulo();

        p2Suc.agregaModulo(F);
        p2Suc.agregaModulo(X);*/

        CadModulo p1Suc = new CadModulo();
        p1Suc.agregaModulo(A); //Apex
        
        
        CadModulo p2Suc = new CadModulo();
             
        p2Suc.agregaModulo(abre);     
        
        p2Suc.agregaModulo(izquierda);
        p2Suc.agregaModulo(F);
        p2Suc.agregaModulo(L);     
        p2Suc.agregaModulo(dismDiam);     
        p2Suc.agregaModulo(A);     
        
        p2Suc.agregaModulo(cierra);     
        
        p2Suc.agregaModulo(adelante);
        p2Suc.agregaModulo(adelante);
        p2Suc.agregaModulo(adelante);
        p2Suc.agregaModulo(adelante);
        p2Suc.agregaModulo(adelante);
        
        p2Suc.agregaModulo(abre);   
        
        p2Suc.agregaModulo(F);
        p2Suc.agregaModulo(L);     
        p2Suc.agregaModulo(dismDiam);     
        p2Suc.agregaModulo(A);     
        
        p2Suc.agregaModulo(cierra);   
        
        p2Suc.agregaModulo(adelante);
        p2Suc.agregaModulo(adelante);
        p2Suc.agregaModulo(adelante);
        p2Suc.agregaModulo(adelante);
        p2Suc.agregaModulo(adelante);
        p2Suc.agregaModulo(adelante);
        p2Suc.agregaModulo(adelante);
        
        
        p2Suc.agregaModulo(abre);   
        
        p2Suc.agregaModulo(F);
        p2Suc.agregaModulo(L);     
        p2Suc.agregaModulo(dismDiam);     
        p2Suc.agregaModulo(A);     
        
        p2Suc.agregaModulo(cierra);   
        
        
        CadModulo p3Suc = new CadModulo();
        p3Suc.agregaModulo(S);   
        p3Suc.agregaModulo(adelante);
        p3Suc.agregaModulo(adelante);
        p3Suc.agregaModulo(adelante);
        p3Suc.agregaModulo(adelante);
        p3Suc.agregaModulo(adelante);
        p3Suc.agregaModulo(F);   
        
        CadModulo p4Suc = new CadModulo();
        p4Suc.agregaModulo(F);   
        p4Suc.agregaModulo(L);
        
        CadModulo p5Suc = new CadModulo();
        p5Suc.agregaModulo(abre);   
        
        p5Suc.agregaModulo(derecha);   
        p5Suc.agregaModulo(derecha);
        
        p5Suc.agregaModulo(abajo);
        p5Suc.agregaModulo(h);
        p5Suc.agregaModulo(arriba);
        p5Suc.agregaModulo(h);
        p5Suc.agregaModulo(arriba);
        p5Suc.agregaModulo(h);
        p5Suc.agregaModulo(abajo);
        p5Suc.agregaModulo(gira180);
        p5Suc.agregaModulo(abajo);
        p5Suc.agregaModulo(h);
        p5Suc.agregaModulo(arriba);
        p5Suc.agregaModulo(h);
        p5Suc.agregaModulo(arriba);
        p5Suc.agregaModulo(h);
        
        p5Suc.agregaModulo(cierra);

        // Producciones y las agregamos al gestor para indicar el Lsystem
        // completo
        Produccion p1 = new Produccion(w, p1Suc);
        Produccion p2 = new Produccion(A, p2Suc);
        Produccion p3 = new Produccion(F, p3Suc);
        Produccion p4 = new Produccion(S, p4Suc);
        Produccion p5 = new Produccion(L, p5Suc);
        GestorProducciones gestor = new GestorProducciones();
        gestor.agregarProduccion(p1);
        gestor.agregarProduccion(p2);
        gestor.agregarProduccion(p3);
        gestor.agregarProduccion(p4);
        gestor.agregarProduccion(p5);
        // Axioma, se declara como una cadena
         CadModulo axioma = new CadModulo();
        axioma.agregaModulo(w);
        // Se generan todas las iteraciones
        int iteraciones = 2;
        for (int i = 0; i < iteraciones; i++) {
            gestor.aplica(axioma);
        }
        // Creamos la cadena que contendra la ultima iteracion, o el resultado
        // del Lsystem

        char[] cadenaL = new char[axioma.numeroModulos()];

        // Guardamos
        for (int j = 0; j < axioma.numeroModulos(); j++) {
            cadenaL[j] = axioma.obtieneModulo(j).obtieneId();

        }
        // Creamos un arreglo de grupos de transformaciones del tamaño de la
        // cadena (arbol) creada por el numero de iteraciones que vamos a generar,
        // se tienen que añadir todas las generaciones a este transformgroup
        TransformGroup[] transformaciones = new TransformGroup[cadenaL.length];
       // Transform3D rotacion = new Transform3D();
        Tortuga1 torti = new Tortuga1(pasoGeneral, 0.0, 0.0, 0.0, radioGeneral);
       
        
        // Aqui interpretamos la cadena y generamos los graficos necesarios,
        // dependiendo de la gramatica
        for (int j = 0; j < cadenaL.length; j++) {

            System.out.println(cadenaL[j]);
            if (cadenaL[j] == 'A') {
                torti.setColor(3);
            }

            if (cadenaL[j] == 'B') {
                torti.setColor(2);
            }

            if (cadenaL[j] == 'C') {
                torti.setColor(3);
            }

            if (cadenaL[j] == 'D') {
                torti.setColor(4);
            }

            if (cadenaL[j] == 'F') {
                transformaciones[j] = torti.mueveAdelante();
                System.out.println(torti.getEdo().toString());
                System.out.println("");
            }
            
            if (cadenaL[j] == 'h') {
                torti.setPaso(pasoGeneral);
                transformaciones[j] = torti.mueveAdelante();
                System.out.println(torti.getEdo().toString());
                System.out.println("");
            }
           

            if (cadenaL[j] == '+') {
                torti.giraPosU(anguloGeneral);
                System.out.println(torti.getEdo().toString());
                System.out.println("");
            }

            if (cadenaL[j] == '-') {
                torti.giraNegU(anguloGeneral);
                System.out.println(torti.getEdo().toString());
                System.out.println("");
            }

            if (cadenaL[j] == '{') {
                torti.giraPosL(anguloGeneral);
                System.out.println(torti.getEdo().toString());
                System.out.println("");
            }

            if (cadenaL[j] == '}') {
                torti.giraNegL(anguloGeneral);
                System.out.println(torti.getEdo().toString());
                System.out.println("");
            }

            if (cadenaL[j] == '>') {
                torti.giraPosH(anguloGeneral);
                System.out.println(torti.getEdo().toString());
                System.out.println("");
            }

            if (cadenaL[j] == '<') {
                torti.giraNegH(anguloGeneral);
            }

            if (cadenaL[j] == '|') {
                torti.giraPosU(180);
            }

            if (cadenaL[j] == '[') {
                System.out.println("Se realiza un push");
                pila.push(torti.getEdo());
                System.out.println(torti.getEdo().toString());
            }

            if (cadenaL[j] == ']') {
                System.out.println("Se realiza un pop");
                torti.setTortuga(pila.pop());
            }
        }
        GenerarEscena1 Ge = new GenerarEscena1(transformaciones);

        JFrame f = new JFrame("Arbol: " + "");
        dim = f.getToolkit().getScreenSize();

        f.getContentPane().setLayout(new BorderLayout());
        f.getContentPane().add("Center", Ge);
        f.setSize(dim);
        Ge.init();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Ge.destroy();
        f.setVisible(true);
    }
}
