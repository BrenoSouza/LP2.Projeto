package core;

public class QuartoExecutivoDuplo extends Quarto {
	/**
	 * Construtor de um quarto do tipo Executivo Duplo.
	 * @param numero Número do quarto.
	 * @param diarias Diárias no hotel.
	 * @throws Exception Caso o número/numeroHospedes/diárias seja menor que zero.
	 */
	public QuartoExecutivoDuplo(int numero) throws Exception {
		super(numero, 3, 385.0);
	}
	
	@Override
	public String toString() {
		return "Serviço --- Quarto Executivo Duplo ---" +
				super.toString() +
				"\nPreço da diária -> " + this.getPrecoDiaria() +
				"\nCusto final -> " + this.calculaPrecoTotal();
	}

	@Override
	public String getTipo() {
		return "Quarto Executivo Duplo" /*+ (getDiarias() != 0 ? (", " + "atualmente "+ getDiarias() + " diária(s)") : */;
	}

}
