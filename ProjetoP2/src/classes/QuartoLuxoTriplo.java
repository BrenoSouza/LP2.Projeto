package classes;

public class QuartoLuxoTriplo extends Quarto {
	/**
	 * Construtor de um quarto do tipo Luxo Triplo.
	 * @param numero Numero do quarto.
	 * @param diarias Diarias no hotel.
	 * @throws Exception Caso o numero/numeroHospedes/diarias seja menor que zero.
	 */
	public QuartoLuxoTriplo(int numero, int diarias) throws Exception {
		super(numero, 3, diarias, 620.0);
	}
	
	@Override
	public String toString() {
		return "Servico --- Quarto Executivo triplo ---" +
				super.toString() +
				"\nPreco da diaria -> " + this.getPrecoDiaria() +
				"\nCusto final -> " + this.calculaPrecoTotal();
	}

}
