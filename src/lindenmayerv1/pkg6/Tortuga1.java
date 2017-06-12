package lindenmayerv1.pkg6;

import java.util.ArrayList;
import javax.media.j3d.TransformGroup;

public class Tortuga1 {

    private double[] punto2;
    private Cilindro linea;
    private TransformGroup transformacion;
    private Estado edo;
    private char ultOp;

    
    
    //Tortuga lleva un paso y coordenadas, aqui iniciamos a la tortuga
    //y todo el sistema de coordenadas 
    public Tortuga1(double d, double x, double y, double z, double radio) {

        punto2 = new double[3];

        punto2[0] = 0;
        punto2[1] = 0;
        punto2[2] = 0;
        
        
        transformacion = new TransformGroup();
        edo = new Estado();
        edo.setCoordenada(x, y, z);
        edo.setPaso(d);
        
        //Inicializamos todos los angulos a 90 grados
        edo.setAngulox(90);
        edo.setAnguloy(0);
        edo.setAnguloz(90);
        
        edo.setColor(1);
        edo.setRadio(radio);
        //Sistema de coordenadas
        edo.setVectorH(0, 1, 0);
        edo.setVectorL(1, 0, 0);
        edo.setVectorU(0, 0, 1);
        edo.setUltOp('i');
        edo.setListaTrans(new ArrayList<Angulo>());

    }

    public void setTortuga(Estado edo) {
        this.edo = edo;
    }

    public void setColor(int color) {
        this.edo.setColor(color);
    }

    // mueve adelante y dibuja linea
    public TransformGroup mueveAdelante() {

        
        punto2[0] = edo.getCoordenada()[0] + edo.getPaso() * edo.getVectorH()[0];
        punto2[1] = edo.getCoordenada()[1] + edo.getPaso() * edo.getVectorH()[1];
        punto2[2] = edo.getCoordenada()[2] + edo.getPaso() * edo.getVectorH()[2];

        linea = new Cilindro(edo.getCoordenada(), punto2, edo.getRadio(), edo.getColor(),edo.getListaTrans(),
                             edo.getVectorH(), edo.getVectorL(), edo.getVectorU(), edo.getUltOp()); 

        edo.setCoordenada(punto2[0],
                          punto2[1],
                          punto2[2]);

        transformacion = new TransformGroup();

        transformacion = linea.getTg();

        return transformacion;
    }
    
    


    public TransformGroup giraPosU(double angulo) {

        double[] tempH = new double[3];
        double[] tempL = new double[3];
        double[] tempU = new double[3];
        
        for (int i = 0; i < edo.getVectorH().length; i++) {
                        tempH[i] = edo.getVectorH()[i];
                        tempL[i] = edo.getVectorL()[i];
                        tempU[i] = edo.getVectorU()[i];
        }
       
        edo.setVectorH((tempH[0] * Math.cos(Math.toRadians(angulo)))
                     - (tempL[0] * (Math.sin(Math.toRadians(angulo)))),
                       (tempH[1] * Math.cos(Math.toRadians(angulo)))
                     - (tempL[1] * (Math.sin(Math.toRadians(angulo)))),
                       (tempH[2] * Math.cos(Math.toRadians(angulo)))
                     - (tempL[2] * (Math.sin(Math.toRadians(angulo))))
        );

        edo.setVectorL((tempH[0] * Math.sin(Math.toRadians(angulo)))
                      + tempL[0] * (Math.cos(Math.toRadians(angulo))),
                       (tempH[1] * Math.sin(Math.toRadians(angulo)))
                      + tempL[1] * (Math.cos(Math.toRadians(angulo))),
                       (tempH[2] * Math.sin(Math.toRadians(angulo)))
                      + tempL[2] * (Math.cos(Math.toRadians(angulo)))
        );
        edo.setUltOp('u');
        
        edo.setAnguloz(edo.getAnguloz()+angulo);
        //System.out.println(edo.getAnguloz());
        edo.agregarTrans(new Angulo('u', angulo));
        
        
        
        return transformacion;
    }

    public TransformGroup giraNegU(double angulo) {

        double[] tempH = new double[3];
        double[] tempL = new double[3];
        double[] tempU = new double[3];
        for (int i = 0; i < edo.getVectorH().length; i++) {
            tempH[i] = edo.getVectorH()[i];
            tempL[i] = edo.getVectorL()[i];
            tempU[i] = edo.getVectorU()[i];
        }
        
        edo.setVectorH((tempH[0] * Math.cos(Math.toRadians(-angulo)))
                     - (tempL[0] * (Math.sin(Math.toRadians(-angulo)))),
                       (tempH[1] * Math.cos(Math.toRadians(-angulo)))
                     - (tempL[1] * (Math.sin(Math.toRadians(-angulo)))),
                       (tempH[2] * Math.cos(Math.toRadians(-angulo)))
                     - (tempL[2] * (Math.sin(Math.toRadians(-angulo))))
        );

        edo.setVectorL((tempH[0] * Math.sin(Math.toRadians(-angulo)))
                      + tempL[0] * (Math.cos(Math.toRadians(-angulo))),
                       (tempH[1] * Math.sin(Math.toRadians(-angulo)))
                      + tempL[1] * (Math.cos(Math.toRadians(-angulo))),
                       (tempH[2] * Math.sin(Math.toRadians(-angulo)))
                      + tempL[2] * (Math.cos(Math.toRadians(-angulo)))
        );
        edo.setUltOp('u');
        edo.setAnguloz(edo.getAnguloz()-angulo);
        //System.out.println(edo.getAnguloz());
        edo.agregarTrans(new Angulo('u', -angulo));
        return transformacion;
    }

    public TransformGroup giraPosL(double angulo) {

        double[] tempH = new double[3];
        double[] tempL = new double[3];
        double[] tempU = new double[3];
        for (int i = 0; i < edo.getVectorH().length; i++) {
            tempH[i] = edo.getVectorH()[i];
            tempL[i] = edo.getVectorL()[i];
            tempU[i] = edo.getVectorU()[i];
        }
        
        edo.setVectorH((tempH[0] * Math.cos(Math.toRadians(angulo)))
                      + tempU[0] * (Math.sin(Math.toRadians(angulo))),
                       (tempH[1] * Math.cos(Math.toRadians(angulo)))
                      + tempU[1] * (Math.sin(Math.toRadians(angulo))),
                       (tempH[2] * Math.cos(Math.toRadians(angulo)))
                      + tempU[2] * (Math.sin(Math.toRadians(angulo)))
        );  

        edo.setVectorU(-(tempH[0] * Math.sin(Math.toRadians(angulo)))
                       + tempU[0] * (Math.cos(Math.toRadians(angulo))),
                       -(tempH[1] * Math.sin(Math.toRadians(angulo)))
                       + tempU[1] * (Math.cos(Math.toRadians(angulo))),
                       -(tempH[2] * Math.sin(Math.toRadians(angulo)))
                       + tempU[2] * (Math.cos(Math.toRadians(angulo))));
        
        edo.setUltOp('l');
        edo.setAngulox(edo.getAngulox()+angulo);
        //System.out.println(edo.getAnguloz());
        edo.agregarTrans(new Angulo('l', angulo));
        return transformacion;
    }

    public TransformGroup giraNegL(double angulo) {



        double[] tempH = new double[3];
        double[] tempL = new double[3];
        double[] tempU = new double[3];
        for (int i = 0; i < edo.getVectorH().length; i++) {
            tempH[i] = edo.getVectorH()[i];
            tempL[i] = edo.getVectorL()[i];
            tempU[i] = edo.getVectorU()[i];
        }

        edo.setVectorH((tempH[0] * Math.cos(Math.toRadians(-angulo)))
                + tempU[0] * (Math.sin(Math.toRadians(-angulo))),
                (tempH[1] * Math.cos(Math.toRadians(-angulo)))
                + tempU[1] * (Math.sin(Math.toRadians(-angulo))),
                (tempH[2] * Math.cos(Math.toRadians(-angulo)))
                + tempU[2] * (Math.sin(Math.toRadians(-angulo)))
        );

        edo.setVectorU(-(tempH[0] * Math.sin(Math.toRadians(-angulo)))
                + tempU[0] * (Math.cos(Math.toRadians(-angulo))),
                -(tempH[1] * Math.sin(Math.toRadians(-angulo)))
                + tempU[1] * (Math.cos(Math.toRadians(-angulo))),
                -(tempH[2] * Math.sin(Math.toRadians(-angulo)))
                + tempU[2] * (Math.cos(Math.toRadians(-angulo))));
        
        
        edo.setUltOp('l');
        edo.setAngulox(edo.getAngulox()-angulo);
        //System.out.println(edo.getAnguloz());
        edo.agregarTrans(new Angulo('l', -angulo));
        
        
        return transformacion;
    }

    public TransformGroup giraPosH(double angulo) {

        double[] tempH = new double[3];
        double[] tempL = new double[3];
        double[] tempU = new double[3];
        for (int i = 0; i < edo.getVectorH().length; i++) {
            tempH[i] = edo.getVectorH()[i];
            tempL[i] = edo.getVectorL()[i];
            tempU[i] = edo.getVectorU()[i];
        }

        edo.setVectorL((tempL[0] * Math.cos(Math.toRadians(angulo)))
                + tempU[0] * (Math.sin(Math.toRadians(angulo))),
                (tempL[1] * Math.cos(Math.toRadians(angulo)))
                + tempU[1] * (Math.sin(Math.toRadians(angulo))),
                (tempL[2] * Math.cos(Math.toRadians(angulo)))
                + tempU[2] * (Math.sin(Math.toRadians(angulo)))
        );

        edo.setVectorU(-(tempL[0] * Math.sin(Math.toRadians(angulo)))
                + tempU[0] * (Math.cos(Math.toRadians(angulo))),
                -(tempL[1] * Math.sin(Math.toRadians(angulo)))
                + tempU[1] * (Math.cos(Math.toRadians(angulo))),
                -(tempL[2] * Math.sin(Math.toRadians(angulo)))
                + tempU[2] * (Math.cos(Math.toRadians(angulo))));
        
        
        edo.setUltOp('h');
        edo.setAnguloy(edo.getAnguloy()+angulo);
        //System.out.println(edo.getAnguloz());
        edo.agregarTrans(new Angulo('h', angulo));
        
        
        
        return transformacion;
    }

    public TransformGroup giraNegH(double angulo) {
        double[] tempH = new double[3];
        double[] tempL = new double[3];
        double[] tempU = new double[3];
        for (int i = 0; i < edo.getVectorH().length; i++) {
            tempH[i] = edo.getVectorH()[i];
            tempL[i] = edo.getVectorL()[i];
            tempU[i] = edo.getVectorU()[i];
        }

        edo.setVectorL((tempL[0] * Math.cos(Math.toRadians(-angulo)))
                + tempU[0] * (Math.sin(Math.toRadians(-angulo))),
                (tempL[1] * Math.cos(Math.toRadians(-angulo)))
                + tempU[1] * (Math.sin(Math.toRadians(-angulo))),
                (tempL[2] * Math.cos(Math.toRadians(-angulo)))
                + tempU[2] * (Math.sin(Math.toRadians(-angulo)))
        );

        edo.setVectorU(-(tempL[0] * Math.sin(Math.toRadians(-angulo)))
                + tempU[0] * (Math.cos(Math.toRadians(-angulo))),
                -(tempL[1] * Math.sin(Math.toRadians(-angulo)))
                + tempU[1] * (Math.cos(Math.toRadians(-angulo))),
                -(tempL[2] * Math.sin(Math.toRadians(-angulo)))
                + tempU[2] * (Math.cos(Math.toRadians(-angulo))));
        
        
        
        edo.setUltOp('h');
        edo.setAnguloy(edo.getAnguloy()-angulo);
        //System.out.println(edo.getAnguloz());
        edo.agregarTrans(new Angulo('h', -angulo));
        
        return transformacion;
    }
    
    public void setRadio(double radio){
        edo.setRadio(radio);
    }
    
    public void setPaso(double paso){
        edo.setPaso(paso);
    }

    public Estado getEdo() {
        Estado copia = new Estado();
        
        copia.setColor(edo.getColor());
        
        copia.setUltOp(edo.getUltOp());
        copia.setListaTrans(edo.getListaTrans());
        
        copia.setCoordenada(edo.getCoordenada()[0],
                            edo.getCoordenada()[1],
                            edo.getCoordenada()[2]);
        copia.setPaso(this.edo.getPaso());
       
        copia.setVectorH(edo.getVectorH()[0],
                         edo.getVectorH()[1],
                         edo.getVectorH()[2]);

        copia.setVectorL(edo.getVectorL()[0],
                         edo.getVectorL()[1],
                         edo.getVectorL()[2]);

        copia.setVectorU(edo.getVectorU()[0],
                         edo.getVectorU()[1],
                         edo.getVectorU()[2]);
        
        copia.setRadio(edo.getRadio());


        return copia;
    }

}
