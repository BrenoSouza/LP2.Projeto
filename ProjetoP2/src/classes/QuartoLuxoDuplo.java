package classes;

public class QuartoLuxoDuplo extends Quarto {
	/**
	 * Construtor de um quarto do tipo Luxo Duplo.
	 * @param numero Numero do quarto.
	 * @param diarias Diarias no hotel.
	 * @throws Exception Caso o numero/numeroHospedes/diarias seja menor que zero.
	 */
	public QuartoLuxoDuplo(int numero, int diarias) throws Exception {
		super(numero, 3, diarias, 570.0);
	}
	
	@Override
	public String toString() {
		return "Servico --- Quarto Luxo Duplo ---" +
				super.toString() +
				"\nPreco da diaria -> " + this.getPrecoDiaria() +
				"\nCusto final -> " + this.calculaPrecoTotal();
	}

}
