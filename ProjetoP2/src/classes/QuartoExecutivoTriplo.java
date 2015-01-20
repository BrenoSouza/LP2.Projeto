package classes;

public class QuartoExecutivoTriplo extends Quarto {
	/**
	 * Construtor de um quarto do tipo Executivo Triplo.
	 * @param numero Número do quarto.
	 * @param diarias Diárias no hotel.
	 * @throws Exception Caso o número/numeroHospedes/diárias seja menor que zero.
	 */
	public QuartoExecutivoTriplo(int numero) throws Exception {
		super(numero, 3, 440.0);
	}
	
	@Override
	public String toString() {
		return "Serviço --- Quarto Executivo Triplo ---" +
				super.toString() +
				"\nPreço da diária -> " + this.getPrecoDiaria() +
				"\nCusto final -> " + this.calculaPrecoTotal();
	}

	@Override
	public String getTipo() {
		return "Quarto Executivo Triplo" + (getDiarias() != 0 ? (", " + getDiarias() + " diária(s)") : "");
	}

}
