package lindenmayerv1.pkg6;

import java.awt.Color;

import javax.media.j3d.Appearance;  
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Cylinder;
import java.util.ArrayList;

public class Cilindro {

    private Cylinder cilindro;
    private double[] pos;
    private double radio;
    private double altura;
    private TransformGroup tg;
    private Color3f color;
   /*
    *Para construir el cilindro se toman en cuenta 2 puntos, radio, color, 3 angulos, 3 vectores para las posiciones y por ultimo
    *La ultima operacion realizada -___-'que no recuerdo para que sirve XD
    */
    public Cilindro(double[] punto1, double[] punto2, double radio, int col, ArrayList<Angulo> listaTrans,
                    double vectorH[], double vectorL[], double vectorU[], char ultOp) {
        
        pos = new double[3];
        pos[0] = (punto2[0] + punto1[0]) / 2;
        pos[1] = (punto2[1] + punto1[1]) / 2;
        pos[2] = (punto2[2] + punto1[2]) / 2;
        this.radio = radio;
        this.altura = (double) Math.sqrt(Math.pow(punto2[0] - punto1[0], 2)
                + Math.pow(punto2[1] - punto1[1], 2)
                + Math.pow(punto2[2] - punto1[2], 2));
        
        cilindro = new Cylinder((float) radio, (float) this.altura);
        tg = new TransformGroup();
        Transform3D rotacion = new Transform3D();
        
        Transform3D rotacion2 = new Transform3D();
        Transform3D rotacion3 = new Transform3D();
        //Se crea el cilindro por default si no se ha realizado una operacion anterior
        
        if (ultOp == 'i') {
            System.out.println("sadsadd");
            rotacion.rotY(Math.toRadians(0));
            rotacion2.rotX(Math.toRadians(0));
            rotacion.mul(rotacion2);
            rotacion2.rotZ(Math.toRadians(0));
            rotacion.mul(rotacion2);
        }
        
        for(int i=0;i<listaTrans.size();i++){
        //Si no esta por default se rota tantos grados en z
         
                //System.out.println(listaTrans.get(i).getAngulo());
                //System.out.println(listaTrans.get(i).getId());
               
            if (listaTrans.get(i).getAngulo()!=90 && listaTrans.get(i).getId()=='u') {
                System.out.println("Entro aqui en rotacion z");
                System.out.println(listaTrans.get(i).getAngulo());
                System.out.println(listaTrans.get(i).getId());
                Transform3D rotacion1 = new Transform3D();
                rotacion1.rotZ(Math.toRadians(listaTrans.get(i).getAngulo() ));
                rotacion.mul(rotacion1);
                System.out.println("Hizo la operacion");
            }else if(listaTrans.get(i).getAngulo()==90 && listaTrans.get(i).getId()=='u'){
                
                Transform3D rotacion1 = new Transform3D();
                rotacion1.rotZ(Math.toRadians(-90));
                rotacion.mul(rotacion1);
                
            }
        
        
        //Aqui esta lo interesante porque se combina la transformacion anterior con esta
            if (listaTrans.get(i).getAngulo()!=0 && listaTrans.get(i).getId()=='l') {
                System.out.println("Entro aqui en rotacion x");
                rotacion2.rotX(Math.toRadians(listaTrans.get(i).getAngulo() ));
                rotacion.mul(rotacion2);
            }
        
            if (listaTrans.get(i).getAngulo()!=0 && listaTrans.get(i).getId()=='h') {
                System.out.println("Entro aqui en rotacion Y");
                
                System.out.println(listaTrans.get(i).getAngulo());
                System.out.println(listaTrans.get(i).getId());
                rotacion3.rotY(Math.toRadians(listaTrans.get(i).getAngulo() -90));
                rotacion.mul(rotacion3);
            }
        }
        
        
        
        //Y la anterior con esta :D
        
        //Posicionamos el objeto donde debe ir
        Vector3f vector = new Vector3f((float) pos[0], (float) pos[1], (float) pos[2]);
        rotacion.setTranslation(vector);

        tg.setTransform(rotacion);
        //Cuestiones de revision de pruebas 
        //System.out.println("altura: " + this.altura + ", posiciones: ->" + this.pos[0] + ", " + this.pos[1] + ", " + this.pos[2]);
        Appearance app = new Appearance();
        
        //Colores disponibles
        switch (col) {
            case 1:
                color = new Color3f(Color.green); // brown light
                break;
            case 2:
                color = new Color3f(new Color(165, 42, 42)); // brown light
                break;
            case 3:
                color = new Color3f(Color.blue);
                break;
            case 4:
                color = new Color3f(Color.pink);
                break;
            case 5:
                color = new Color3f(Color.yellow);
                break;
            case 6:
                color = new Color3f(Color.WHITE);
                break;
        }
        //Aplicamos colores y luz
        cilindro.setAppearance(app);
        Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
        Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
        app.setMaterial(new Material(color, black, color, white, 70f));
        tg.addChild(cilindro);
    }


    public Transform3D traslacion() {
        Transform3D traslacion = new Transform3D();
        Vector3f vector = new Vector3f((float) pos[0], (float) pos[1], (float) pos[2]);
        traslacion.setTranslation(vector);

        return traslacion;
    }

    public Transform3D rotacion(double angulo, double x, double y, double z) {
        double[] temp = new double[3];
        temp[0] = pos[0];
        temp[1] = pos[1];
        temp[2] = pos[2];
        Transform3D transformacion = new Transform3D();
        System.out.println("alrededor del eje: " + x + ", " + y + ", " + z);
        transformacion.setRotation(new AxisAngle4d(x, y, z, Math.toRadians(45)));

        return transformacion;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    public void setAltura(double altura) {
        this.altura = altura;

    }

    public double[] getPos() {
        return pos;
    }

    public double getRadio() {
        return radio;
    }

    public TransformGroup getTg() {
        return tg;
    }

    public double getAltura() {
        return altura;
    }

}
