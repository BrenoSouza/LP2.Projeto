package core;

public class QuartoLuxoDuplo extends Quarto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1698751127129978306L;

	/**
	 * Construtor de um quarto do tipo Luxo Duplo.
	 * @param numero Número do quarto.
	 * @throws IllegalArgumentException Caso o número/numeroHospedes/diárias seja menor que zero.
	 */
	public QuartoLuxoDuplo(int numero) throws IllegalArgumentException {
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
		return "Quarto Luxo Duplo";
	}

}
