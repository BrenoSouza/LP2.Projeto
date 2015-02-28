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
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Restaurante)){
			return false;
		}Restaurante outroRestaurante = (Restaurante) obj;
		return toString().equals(outroRestaurante.toString());
	}
	public String getTipo() {
		return "Restaurante" + (getIsCobertura() ? "Cobertura" : "");
	}
	/**
	 * Modifica o atributo preço de Restaurante
	 * @param preco O novo preço da classe
	 */
	public void setPreco(double preco) {
		this.preco = preco;
	}
	/**
	 * Modifica o atributo que informa se foi utilizado o restaurante da cobertura
	 * @param isCobertura True = Restaurante cobertura / False = Restaurante normal
	 */
	public void setCobertura(boolean isCobertura) {
		this.isCobertura = isCobertura;
	}

}
