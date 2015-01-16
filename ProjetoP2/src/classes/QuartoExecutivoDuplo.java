package classes;

public class QuartoExecutivoDuplo extends Quarto {
	/**
	 * Construtor de um quarto do tipo Executivo Duplo.
	 * @param numero Numero do quarto.
	 * @param diarias Diarias no hotel.
	 * @throws Exception Caso o numero/numeroHospedes/diarias seja menor que zero.
	 */
	public QuartoExecutivoDuplo(int numero, int diarias) throws Exception {
		super(numero, 3, diarias, 385.0);
	}

}
