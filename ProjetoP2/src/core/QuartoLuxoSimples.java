package core;

public class QuartoLuxoSimples extends Quarto {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1031845293824403542L;

	/**
	 * Construtor de um quarto do tipo Luxo Simples.
	 * @param numero Número do quarto.
	 * @throws IllegalArgumentException Caso o número/numeroHospedes/diárias seja menor que zero.
	 */
	public QuartoLuxoSimples(int numero) throws IllegalArgumentException {
		super(numero, 3, 520.0);
	}
	
	@Override
	public String toString() {
		return "Serviço --- Quarto Luxo Simples ---" +
				super.toString() +
				"\nPreço da diária -> " + this.getPrecoDiaria();
	}

	@Override
	public String getTipo() {
		return "Quarto Luxo Simples";
	}

}
