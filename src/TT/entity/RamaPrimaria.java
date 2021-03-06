package TT.entity;
// Generated 08-dic-2016 16:07:03 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * RamaPrimaria generated by hbm2java
 */
public class RamaPrimaria  implements java.io.Serializable {


     private Integer idRp;
     private Double longitud;
     private Double diametro;
     private Double densidad;
     private Set<EspecieCaracteristicas> especieCaracteristicases = new HashSet<EspecieCaracteristicas>(0);

    public RamaPrimaria() {
    }

    public RamaPrimaria(Double longitud, Double diametro, Double densidad, Set<EspecieCaracteristicas> especieCaracteristicases) {
       this.longitud = longitud;
       this.diametro = diametro;
       this.densidad = densidad;
       this.especieCaracteristicases = especieCaracteristicases;
    }
   
    public Integer getIdRp() {
        return this.idRp;
    }
    
    public void setIdRp(Integer idRp) {
        this.idRp = idRp;
    }
    public Double getLongitud() {
        return this.longitud;
    }
    
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
    public Double getDiametro() {
        return this.diametro;
    }
    
    public void setDiametro(Double diametro) {
        this.diametro = diametro;
    }
    public Double getDensidad() {
        return this.densidad;
    }
    
    public void setDensidad(Double densidad) {
        this.densidad = densidad;
    }
    public Set<EspecieCaracteristicas> getEspecieCaracteristicases() {
        return this.especieCaracteristicases;
    }
    
    public void setEspecieCaracteristicases(Set<EspecieCaracteristicas> especieCaracteristicases) {
        this.especieCaracteristicases = especieCaracteristicases;
    }




}


