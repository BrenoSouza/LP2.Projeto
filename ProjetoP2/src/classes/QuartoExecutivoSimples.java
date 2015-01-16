package classes;

public class QuartoExecutivoSimples extends Quarto {
	/**
	 * Construtor de um quarto do tipo Executivo Simples.
	 * @param numero Numero do quarto.
	 * @param diarias Diarias no hotel.
	 * @throws Exception Caso o numero/numeroHospedes/diarias seja menor que zero.
	 */
	public QuartoExecutivoSimples(int numero, int diarias) throws Exception {
		super(numero, 3, diarias, 360.0);
	}

}
