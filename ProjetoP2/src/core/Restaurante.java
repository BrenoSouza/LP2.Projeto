package core;

public class Restaurante extends Servico {

	private boolean isCobertura;
	private double preco;
	/**
	 * Construtor da classe restaurante, onde registra o preço da conta.
	 * @param data Data do uso do serviço.
	 * @param isCobertura Se usou o restaurante da cobertura.
	 * @param preco Preço da conta
	 * @throws Exception Caso o preço informado seja menor que zero.
	 */
	public Restaurante(boolean isCobertura, double preco) throws Exception {
		super();
		if (preco < 0){
			throw new Exception("O preço da conta nao pode ser menor que zero.");
		}
		this.isCobertura = isCobertura;
		this.preco = preco;
	}
	/**
	 * Retorna se o restaurante é da cobertura ou não.
	 * @return O tipo do restaurante.
	 */
	public boolean getIsCobertura() {
		return isCobertura;
	}

	@Override
	public double calculaPrecoTotal() {
		return preco;
	}

	@Override
	public String toString() {
		return "Serviço --- Restaurante ---" +
				"\nHora -> " + this.getHoraEntrada() +
				"\nCobertura? " + (this.getIsCobertura() ? "Sim" : "Não") +
				"\nCusto final: " + this.calculaPrecoTotal();
	}
	public String getTipo() {
		return "Restaurante" + (getIsCobertura() ? "Cobertura" : "");
	}

}
