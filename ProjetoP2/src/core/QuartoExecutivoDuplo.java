package core;

public class QuartoExecutivoDuplo extends Quarto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1586467347214023176L;

	/**
	 * Construtor de um quarto do tipo Executivo Duplo.
	 * @param numero Número do quarto.
	 * @throws IllegalArgumentException Caso o número/numeroHospedes/diárias seja menor que zero.
	 */
	public QuartoExecutivoDuplo(int numero) throws IllegalArgumentException {
		super(numero, 3, 385.0);
	}
	
	@Override
	public String toString() {
		return "Serviço --- Quarto Executivo Duplo ---" +
				super.toString() +
				"\nPreço da diária -> " + this.getPrecoDiaria();
	}

	@Override
	public String getTipo() {
		return "Quarto Executivo Duplo" /*+ (getDiarias() != 0 ? (", " + "atualmente "+ getDiarias() + " diária(s)") : */;
	}

}
