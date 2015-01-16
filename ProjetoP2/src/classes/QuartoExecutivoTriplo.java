package classes;

public class QuartoExecutivoTriplo extends Quarto {
	/**
	 * Construtor de um quarto do tipo Executivo Triplo.
	 * @param numero Numero do quarto.
	 * @param diarias Diarias no hotel.
	 * @throws Exception Caso o numero/numeroHospedes/diarias seja menor que zero.
	 */
	public QuartoExecutivoTriplo(int numero, int diarias) throws Exception {
		super(numero, 3, diarias, 440.0);
	}
	
	@Override
	public String toString() {
		return "Servico --- Quarto Executivo Triplo ---" +
				super.toString() +
				"\nPreco da diaria -> " + this.getPrecoDiaria() +
				"\nCusto final -> " + this.calculaPrecoTotal();
	}

}
