package TT.entity;
// Generated 08-dic-2016 16:07:03 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Tronco generated by hbm2java
 */
public class Tronco  implements java.io.Serializable {


     private Integer idTronco;
     private Double diametro;
     private Double longitud;
     private Double deDiametro;
     private Double deLongitud;
     private Set<EspecieCaracteristicas> especieCaracteristicases = new HashSet<EspecieCaracteristicas>(0);

    public Tronco() {
    }

    public Tronco(Double diametro, Double longitud, Double deDiametro, Double deLongitud, Set<EspecieCaracteristicas> especieCaracteristicases) {
       this.diametro = diametro;
       this.longitud = longitud;
       this.deDiametro = deDiametro;
       this.deLongitud = deLongitud;
       this.especieCaracteristicases = especieCaracteristicases;
    }
   
    public Integer getIdTronco() {
        return this.idTronco;
    }
    
    public void setIdTronco(Integer idTronco) {
        this.idTronco = idTronco;
    }
    public Double getDiametro() {
        return this.diametro;
    }
    
    public void setDiametro(Double diametro) {
        this.diametro = diametro;
    }
    public Double getLongitud() {
        return this.longitud;
    }
    
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
    public Double getDeDiametro() {
        return this.deDiametro;
    }
    
    public void setDeDiametro(Double deDiametro) {
        this.deDiametro = deDiametro;
    }
    public Double getDeLongitud() {
        return this.deLongitud;
    }
    
    public void setDeLongitud(Double deLongitud) {
        this.deLongitud = deLongitud;
    }
    public Set<EspecieCaracteristicas> getEspecieCaracteristicases() {
        return this.especieCaracteristicases;
    }
    
    public void setEspecieCaracteristicases(Set<EspecieCaracteristicas> especieCaracteristicases) {
        this.especieCaracteristicases = especieCaracteristicases;
    }




}


