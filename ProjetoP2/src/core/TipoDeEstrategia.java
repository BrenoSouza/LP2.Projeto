package core;

public enum TipoDeEstrategia {
	
	ACRESCIMO (1, "Acréscimo"), DECRESCIMO (-1, "Decréscimo");
	private int multiplicador;
	private String descricao;
	
	/**
	 * Construtor do Enum TipoDeEstrategia.
	 * @param multiplicador
	 * O multiplicador (1 ou -1)
	 * @param descricao
	 * A descrição (Acréscimo ou Decréscimo)
	 */
	private TipoDeEstrategia(int multiplicador, String descricao){
		this.multiplicador = multiplicador;
		this.descricao = descricao;
	}
	/**
	 * Getter do multiplicador do enum.
	 * @return
	 * O multiplicador do enum.
	 */
	public int getMultiplicador() {
		return multiplicador;
	}
	@Override
	public String toString(){
		return descricao;
	}
}
