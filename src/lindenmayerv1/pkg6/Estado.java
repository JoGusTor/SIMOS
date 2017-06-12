package lindenmayerv1.pkg6;

import java.util.ArrayList;
import java.util.Arrays;

public class Estado {

    private double coordenada[];
    private double paso;
    private ArrayList<Angulo> listaTrans;
    private int color;
    private double radio;
    private char ultOp;
    private double[] vectorH;
    private double[] vectorU;
    private double[] vectorL;
    private double angulox;
    private double anguloy;
    private double anguloz;
    
    

   

    public Estado() {

    }

    public Estado(Estado edo) {
        this.coordenada = edo.getCoordenada();
        this.paso = edo.paso;
        this.color = edo.color;
        this.ultOp = edo.ultOp;
        this.vectorH = edo.vectorH;
        this.vectorL = edo.vectorL;
        this.vectorU = edo.vectorU;
        this.radio = edo.radio;
        this.listaTrans = edo.listaTrans;

    }

    public double[] getCoordenada() {
        return coordenada;
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    public void setCoordenada(double x, double y, double z) {

        this.coordenada = new double[3];
        this.coordenada[0] = x;
        this.coordenada[1] = y;
        this.coordenada[2] = z;

    }

    public int getColor() {
        return color;
    }

    public char getUltOp() {
        return ultOp;
    }

    public void setUltOp(char ultOp) {
        this.ultOp = ultOp;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public double getPaso() {
        return paso;
    }

    public void setPaso(double paso) {
        this.paso = paso;
    }

    public double[] getVectorH() {
        return vectorH;
    }

    public void setVectorH(double x, double y, double z) {

        this.vectorH = new double[3];
        this.vectorH[0] = x;
        this.vectorH[1] = y;
        this.vectorH[2] = z;
    }

    public double[] getVectorU() {
        return vectorU;
    }

    public void setVectorU(double x, double y, double z) {

        this.vectorU = new double[3];
        this.vectorU[0] = x;
        this.vectorU[1] = y;
        this.vectorU[2] = z;
    }

    public double[] getVectorL() {
        return vectorL;
    }

    public void setVectorL(double x, double y, double z) {

        this.vectorL = new double[3];
        this.vectorL[0] = x;
        this.vectorL[1] = y;
        this.vectorL[2] = z;
    }
    
     public double getAngulox() {
        return angulox;
    }

    public void setAngulox(double angulox) {
        this.angulox = angulox;
    }

    public double getAnguloy() {
        return anguloy;
    }

    public void setAnguloy(double anguloy) {
        this.anguloy = anguloy;
    }

    public double getAnguloz() {
        return anguloz;
    }

    public void setAnguloz(double anguloz) {
        this.anguloz = anguloz;
    }

    public ArrayList<Angulo> getListaTrans() {
        return listaTrans;
    }

    public void setListaTrans(ArrayList<Angulo> listaTrans) {
        this.listaTrans = listaTrans;
    }

    public void agregarTrans(Angulo angulo) {
        listaTrans.add(angulo);
        
        
    }

    @Override
    public String toString() {
        return "Estado{" + "coordenada=" + Arrays.toString(coordenada) + ", paso=" + paso  + ", color=" + color + ", radio=" + radio + ", ultOp=" + ultOp 
                         + ", vectorH=" + Arrays.toString(vectorH) + ", vectorU=" + Arrays.toString(vectorU) + ", vectorL=" + Arrays.toString(vectorL) + '}';
        
    }

}
