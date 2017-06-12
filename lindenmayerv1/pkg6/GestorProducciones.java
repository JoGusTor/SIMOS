package lindenmayerv1.pkg6;

import java.util.Vector;


public class GestorProducciones {
	private Vector<Produccion> producciones = new Vector<Produccion>();
	
	public GestorProducciones(){
	}
	
	public void agregarProduccion(Produccion prod){
		producciones.add(prod);
	}
	
	public void borraProduccion(int pos){
		producciones.remove(pos);
	}
	
	public Produccion obtieneProduccion(int pos){
		return producciones.get(pos);
	}
	
	public void setProduccion(int pos, Produccion prod){
		producciones.remove(pos);
		producciones.add(pos, prod);
	}
	
	public int numeroProducciones(){
		return producciones.size();
	}
	
	public boolean aplica(CadModulo cadMod){
		boolean alterado= false;
		CadModulo cadModPred= new CadModulo(cadMod);
		for(int i= cadMod.numeroModulos()-1;i>=0;i--){
			for(int j=0; j<producciones.size();j++){
				Produccion prod = producciones.get(j);
				alterado = prod.aplicaEnPos(i, cadModPred, cadMod);
				if(alterado){
					break;
				}
			}
			
		}
		
		return alterado;
	}
	
}
