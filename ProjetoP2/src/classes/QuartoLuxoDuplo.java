package classes;

public class QuartoLuxoDuplo extends Quarto {
	/**
	 * Construtor de um quarto do tipo Luxo Duplo.
	 * @param numero Número do quarto.
	 * @param diarias Diárias no hotel.
	 * @throws Exception Caso o número/numeroHospedes/diárias seja menor que zero.
	 */
	public QuartoLuxoDuplo(int numero) throws Exception {
		super(numero, 3, 570.0);
	}
	
	@Override
	public String toString() {
		return "Serviço --- Quarto Luxo Duplo ---" +
				super.toString() +
				"\nPreço da diária -> " + this.getPrecoDiaria() +
				"\nCusto final -> " + this.calculaPrecoTotal();
	}

	@Override
	public String getTipo() {
		return "Quarto Luxo Duplo" + (getDiarias() != 0 ? (", " + getDiarias() + " diária(s)") : "");
	}

}
