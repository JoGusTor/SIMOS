package TT.entity;
// Generated 08-dic-2016 16:07:03 by Hibernate Tools 4.3.1



/**
 * EspecieCaracteristicas generated by hbm2java
 */
public class EspecieCaracteristicas  implements java.io.Serializable {


     private EspecieCaracteristicasId id;
     private ArbolEspecie arbolEspecie;
     private Copa copa;
     private Hojas hojas;
     private RamaPrimaria ramaPrimaria;
     private RamaSecundaria ramaSecundaria;
     private Tronco tronco;

    public EspecieCaracteristicas() {
    }

    public EspecieCaracteristicas(EspecieCaracteristicasId id, ArbolEspecie arbolEspecie, Copa copa, Hojas hojas, RamaPrimaria ramaPrimaria, RamaSecundaria ramaSecundaria, Tronco tronco) {
       this.id = id;
       this.arbolEspecie = arbolEspecie;
       this.copa = copa;
       this.hojas = hojas;
       this.ramaPrimaria = ramaPrimaria;
       this.ramaSecundaria = ramaSecundaria;
       this.tronco = tronco;
    }
   
    public EspecieCaracteristicasId getId() {
        return this.id;
    }
    
    public void setId(EspecieCaracteristicasId id) {
        this.id = id;
    }
    public ArbolEspecie getArbolEspecie() {
        return this.arbolEspecie;
    }
    
    public void setArbolEspecie(ArbolEspecie arbolEspecie) {
        this.arbolEspecie = arbolEspecie;
    }
    public Copa getCopa() {
        return this.copa;
    }
    
    public void setCopa(Copa copa) {
        this.copa = copa;
    }
    public Hojas getHojas() {
        return this.hojas;
    }
    
    public void setHojas(Hojas hojas) {
        this.hojas = hojas;
    }
    public RamaPrimaria getRamaPrimaria() {
        return this.ramaPrimaria;
    }
    
    public void setRamaPrimaria(RamaPrimaria ramaPrimaria) {
        this.ramaPrimaria = ramaPrimaria;
    }
    public RamaSecundaria getRamaSecundaria() {
        return this.ramaSecundaria;
    }
    
    public void setRamaSecundaria(RamaSecundaria ramaSecundaria) {
        this.ramaSecundaria = ramaSecundaria;
    }
    public Tronco getTronco() {
        return this.tronco;
    }
    
    public void setTronco(Tronco tronco) {
        this.tronco = tronco;
    }




}


