package lindenmayerv1.pkg6;


public class Produccion {
	private Modulo refPred;
	private CadModulo refSuc;

	
	//Constructor por parametros
	public Produccion(Modulo predecesor, CadModulo sucesor){
		
		refPred = new Modulo(predecesor);
		refSuc = new CadModulo(sucesor);
		
		
	
	}
	
	//Constructor por referencia
	public Produccion(Produccion prod){
		refPred= new Modulo(prod.refPred);
		refSuc = new CadModulo(prod.refSuc);
		
	}
	
	
	public boolean aplicaEnPos(int pos, CadModulo cadModPred, CadModulo cadModSuc){
		if(cadModPred.obtieneModulo(pos).equals(refPred)){
			cadModSuc.reemplazaModulo(pos, refSuc);
            return true;
        }
        return false;
	}	
	
}
