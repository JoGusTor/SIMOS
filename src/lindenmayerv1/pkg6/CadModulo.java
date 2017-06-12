package lindenmayerv1.pkg6;

import java.util.Vector;


public class CadModulo {
	private Vector<Modulo> modulos= new Vector<Modulo>();
	
	//Constructor de modulo, lo unico que hace es reservar los recursos necesarios
	public CadModulo(){
	}
	
	//Constructor por copia de una cadena
	public CadModulo(CadModulo cm){
		agregaModulo(cm);	
	}
	
	// Constructor que agrega una porcion de una cadena de modulos 
	public CadModulo(int desde, int hasta, CadModulo cm){
		if(desde>hasta)
			hasta^=desde^=hasta^=desde;
		for(Modulo m:cm.modulos.subList(desde, hasta)){
			agregaModulo(m);
		}
	}
	
	//Agrega un modulo "nuevo" a la lista
	public void agregaModulo(Modulo m){
		modulos.add(new Modulo(m));
	}
	
	//Agrega una cadena de modulos
	public void agregaModulo(CadModulo cm){
		for(Modulo m:cm.modulos){
			agregaModulo(m);
		}
	}
	
	//Borra un modulo en cierta posicion
	public void borraModulo(int pos){
		modulos.remove(pos);
	}
	
	//Inserta un modulo en cierta posicion
	public void insertaModulo(int pos, Modulo m){
		modulos.add(pos, new Modulo(m));
	}
	
	//Inserta una cadena de modulos a partir de cierta posicion
	public void insertaModulo(int pos, CadModulo cm){
		int i=0;
		for(Modulo m: cm.modulos){
			insertaModulo(pos+i,m);
			i++;
		}
	}
	
	//Reemplaza un solo modulo en cierta posicion
	public void reemplazaModulo(int pos, Modulo m){
		borraModulo(pos);
		insertaModulo(pos,m);
	}
	
	
	//Reemplaza una cadena de  modulos a partir de cierta posicion
	public void reemplazaModulo(int pos, CadModulo cm){
		borraModulo(pos);
		insertaModulo(pos,cm);
	}
	
	//regresa numero de modulos
	public int numeroModulos(){
		return modulos.size();
	}
	
	//getter para un modulo en dada posicion
	public Modulo obtieneModulo(int pos){
		return modulos.get(pos);
	}
	
	public void setModulo(int pos, Modulo m){
		reemplazaModulo(pos, m);
	}
	
	public boolean compara(CadModulo cm){
		if(numeroModulos()!=cm.numeroModulos())
			return false;
		for(int i=0;i<numeroModulos();i++){
			if(!obtieneModulo(i).equals(cm.obtieneModulo(i)))
				return false;
		}	
		return true;
	}	
}
