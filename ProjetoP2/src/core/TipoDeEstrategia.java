package core;

public enum TipoDeEstrategia {
	
	ACRESCIMO (1, "Acréscimo"), DECRESCIMO (-1, "Decréscimo");
	private int multiplicador;
	private String descricao;
	
	private TipoDeEstrategia(int multiplicador, String descricao){
		this.multiplicador = multiplicador;
		this.descricao = descricao;
	}
	
	public int getMultiplicador() {
		return multiplicador;
	}
	@Override
	public String toString(){
		return descricao;
	}
}
