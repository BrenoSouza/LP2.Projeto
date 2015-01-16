package classes;

public class QuartoPresidencial extends Quarto {
	/**
	 * Construtor de um quarto do tipo Presidencial.
	 * @param numero Numero do quarto.
	 * @param diarias Diarias no hotel.
	 * @throws Exception Caso o numero/numeroHospedes/diarias seja menor que zero.
	 */
	public QuartoPresidencial(int numero, int diarias) throws Exception {
		super(numero, 4, diarias, 1200.0);
	}

}
