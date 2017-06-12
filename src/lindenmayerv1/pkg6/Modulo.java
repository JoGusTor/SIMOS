package lindenmayerv1.pkg6;


public class Modulo {
	private char id;
	//private int numeroParams;
	//private Dbl params[];
	
	
	/*
	 * Almacena el id (simbolos) y los parametros que llevara cada modulo, esto depende de
	 * lo que tenga dentro la clase wrapper Dbl
	 */
	public Modulo(char id, int numeroParams) {
		this.id = id;
		
	}
	
	/*
	 * Sobrecarga de metodo constructor para hacer una copia de un modulo
	 */
	public Modulo(Modulo modulo){
		this.id=modulo.id;
		
	}
	
	//getter para el simbolo
	public char obtieneId(){
		return id;
	}
	
	//setter para el simbolo
	public void setId(char id){
		this.id= id;
	}
	

	//revisa si 2 modulos son iguales
	public boolean equals(Modulo m){
		return(this.id == m.id );
	}
	
}
