package core;

public enum TipoDeEstrategia {
	
	ACRESCIMO (1), DECRESCIMO (-1);
	private int multiplicador;
	
	private TipoDeEstrategia(int multiplicador){
		this.multiplicador = multiplicador;
	}
	
	public int getMultiplicador() {
		return multiplicador;
	}
}
