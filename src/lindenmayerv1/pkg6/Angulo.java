/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lindenmayerv1.pkg6;

/**
 *
 * @author aeternum
 */
public class Angulo {
    private char id;
    private double angulo;
    

    Angulo(char op, double d) {
        this.id= op;
        this.angulo= d;
    }

    public char getId() {
        return id;
    }

    public void setId(char op) {
        this.id = op;
    }

    public double getAngulo() {
        return angulo;
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }
    
}
