package classes;

public class QuartoLuxoSimples extends Quarto {
	/**
	 * Construtor de um quarto do tipo Luxo Simples.
	 * @param numero Número do quarto.
	 * @param diarias Diárias no hotel.
	 * @throws Exception Caso o número/numeroHospedes/diárias seja menor que zero.
	 */
	public QuartoLuxoSimples(int numero) throws Exception {
		super(numero, 3, 520.0);
	}
	
	@Override
	public String toString() {
		return "Serviço --- Quarto Luxo Simples ---" +
				super.toString() +
				"\nPreço da diária -> " + this.getPrecoDiaria() +
				"\nCusto final -> " + this.calculaPrecoTotal();
	}

	@Override
	public String getTipo() {
		return "Quarto Luxo Simples, " + getDiarias() + " diárias";
	}

}
