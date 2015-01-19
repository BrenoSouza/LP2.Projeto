package classes;

public class QuartoPresidencial extends Quarto {
	/**
	 * Construtor de um quarto do tipo Presidencial.
	 * @param numero Número do quarto.
	 * @param diarias Diárias no hotel.
	 * @throws Exception Caso o número/numeroHospedes/diárias seja menor que zero.
	 */
	public QuartoPresidencial(int numero) throws Exception {
		super(numero, 4, 1200.0);
	}
	
	@Override
	public String toString() {
		return "Serviço --- Quarto Presidencial ---" +
				super.toString() +
				"\nPreço da diária -> " + this.getPrecoDiaria() +
				"\nCusto final -> " + this.calculaPrecoTotal();
	}

	@Override
	public String getTipo() {
		return "Quarto Presidencial, " + getDiarias() + " diárias";
	}

}
