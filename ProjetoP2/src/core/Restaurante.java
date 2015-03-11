package core;

import java.io.Serializable;

public class Restaurante extends Servico implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1277741509068732041L;
	private boolean isCobertura;
	private double preco;
	/**
	 * Construtor da classe restaurante, onde registra o preço da conta.
	 * @param isCobertura Se usou o restaurante da cobertura.
	 * @param preco Preço da conta
	 * @throws ParametrosInvalidosException Caso o preço informado seja menor que zero.
	 */
	public Restaurante(boolean isCobertura, double preco) throws ParametrosInvalidosException {
		super();
		if (preco < 0){
			throw new ParametrosInvalidosException("O preço da conta nao pode ser menor que zero.");
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
				System.getProperty("line.separator") + "Hora -> " + this.getHoraEntrada() +
				System.getProperty("line.separator") + "Cobertura? " + (this.getIsCobertura() ? "Sim" : "Não") +
				System.getProperty("line.separator") + "Custo final: " + this.calculaPrecoTotal();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Restaurante)){
			return false;
		}Restaurante outroRestaurante = (Restaurante) obj;
		return toString().equals(outroRestaurante.toString());
	}
	/**
	 * Método que retorna uma String com uma breve descrição da instância de restaurante.
	 */
	public String getTipo() {
		return "Restaurante" + (getIsCobertura() ? "Cobertura" : "");
	}
	/**
	 * Modifica o atributo preço de Restaurante.
	 * @param preco O novo preço da classe
	 */
	public void setPreco(double preco) {
		this.preco = preco;
	}
	/**
	 * Modifica o atributo que informa se foi utilizado o restaurante da cobertura.
	 * @param isCobertura True = Restaurante cobertura / False = Restaurante normal
	 */
	public void setCobertura(boolean isCobertura) {
		this.isCobertura = isCobertura;
	}

}
