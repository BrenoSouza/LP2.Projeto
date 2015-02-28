package core;

public class QuartoExecutivoSimples extends Quarto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6606816465165418755L;

	/**
	 * Construtor de um quarto do tipo Executivo Simples.
	 * @param numero Número do quarto.
	 * @throws Exception Caso o número/numeroHospedes/diárias seja menor que zero.
	 */
	public QuartoExecutivoSimples(int numero) throws IllegalArgumentException {
		super(numero, 3, 360.0);
	}
	
	@Override
	public String toString() {
		return "Serviço --- Quarto Executivo Simples ---" +
				super.toString() +
				"\nPreço da diária -> " + this.getPrecoDiaria() +
				"\nCusto final -> " + this.calculaPrecoTotal();
	}

	@Override
	public String getTipo() {
		return "Quarto Executivo Simples";
	}

}
