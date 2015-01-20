package classes;

public class QuartoLuxoTriplo extends Quarto {
	/**
	 * Construtor de um quarto do tipo Luxo Triplo.
	 * @param numero Número do quarto.
	 * @param diarias Diárias no hotel.
	 * @throws Exception Caso o número/numeroHospedes/diárias seja menor que zero.
	 */
	public QuartoLuxoTriplo(int numero) throws Exception {
		super(numero, 3, 620.0);
	}
	
	@Override
	public String toString() {
		return "Serviço --- Quarto Executivo triplo ---" +
				super.toString() +
				"\nPreço da diária -> " + this.getPrecoDiaria() +
				"\nCusto final -> " + this.calculaPrecoTotal();
	}

	@Override
	public String getTipo() {
		return "Quarto Luxo Triplo" + (getDiarias() != 0 ? (", " + getDiarias() + " diárias") : "");
	}

}
