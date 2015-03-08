package core;

public class QuartoExecutivoTriplo extends Quarto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 489932188553239448L;

	/**
	 * Construtor de um quarto do tipo Executivo Triplo.
	 * @param numero Número do quarto.
	 * @throws IllegalArgumentException Caso o número/numeroHospedes/diárias seja menor que zero.
	 */
	public QuartoExecutivoTriplo(int numero) throws IllegalArgumentException {
		super(numero, 3, 440.0);
	}
	
	@Override
	public String toString() {
		return "Serviço --- Quarto Executivo Triplo ---" +
				super.toString() +
				"\nPreço da diária -> " + this.getPrecoDiaria();
	}

	@Override
	public String getTipo() {
		return "Quarto Executivo Triplo";
	}

}
