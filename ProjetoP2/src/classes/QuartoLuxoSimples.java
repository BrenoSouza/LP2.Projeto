package classes;

public class QuartoLuxoSimples extends Quarto {
	/**
	 * Construtor de um quarto do tipo Luxo Simples.
	 * @param numero Numero do quarto.
	 * @param diarias Diarias no hotel.
	 * @throws Exception Caso o numero/numeroHospedes/diarias seja menor que zero.
	 */
	public QuartoLuxoSimples(int numero, int diarias) throws Exception {
		super(numero, 3, diarias, 520.0);
	}
	
	@Override
	public String toString() {
		return "Servico --- Quarto Luxo Simples ---" +
				super.toString() +
				"\nPreco da diaria -> " + this.getPrecoDiaria() +
				"\nCusto final -> " + this.calculaPrecoTotal();
	}

}
