package core;

public class QuartoPresidencial extends Quarto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1963196564185146972L;

	/**
	 * Construtor de um quarto do tipo Presidencial.
	 * @param numero Número do quarto.
	 * @throws IllegalArgumentException Caso o número/numeroHospedes/diárias seja menor que zero.
	 */
	public QuartoPresidencial(int numero) throws IllegalArgumentException {
		super(numero, 4, 1200.0);
	}
	
	@Override
	public String toString() {
		return "Serviço --- Quarto Presidencial ---" +
				super.toString() +
				"\nPreço da diária -> " + this.getPrecoDiaria();
	}

	@Override
	public String getTipo() {
		return "Quarto Presidencial";
	}

}
