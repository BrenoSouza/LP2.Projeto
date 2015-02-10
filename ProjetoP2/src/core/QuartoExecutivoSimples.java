package core;

public class QuartoExecutivoSimples extends Quarto {
	/**
	 * Construtor de um quarto do tipo Executivo Simples.
	 * @param numero Número do quarto.
	 * @param diarias Diárias no hotel.
	 * @throws Exception Caso o número/numeroHospedes/diárias seja menor que zero.
	 */
	public QuartoExecutivoSimples(int numero) throws Exception {
		super(numero, 3, 360.0);
	}
	
	@Override
	public String toString() {
		return "Serviço --- Quarto Executivo Simples ---" +
				super.toString() +
				"\nPreço da diária -> " + this.getPrecoDiaria() +
				"\nCusto final -> " + this.calculaPrecoTotal();
	}

	@Override
	public String getTipo() {
		return "Quarto Executivo Simples" + (getDiarias() != 0 ? (", " + getDiarias() + " diária(s)") : "");
	}

}
