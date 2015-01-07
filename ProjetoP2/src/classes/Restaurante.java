package classes;

	public class Restaurante extends Servico {

		private boolean isCobertura;
		private double preco;
		/**
		 * Construtor da classe restaurante, onde registra o preco da conta.
		 * @param data Data do uso do servico.
		 * @param isCobertura Se usou o restaurante da cobertura.
		 * @param preco Preco da conta
		 * @throws Exception Caso o preco informado seja menor que zero.
		 */
		public Restaurante(boolean isCobertura, double preco) throws Exception {
			super();
			if (preco < 0){
				throw new Exception("O preco da conta nao pode ser menor que zero.");
			}
			this.isCobertura = isCobertura;
			this.preco = preco;
		}
	/**
	 * Retorna se é o restaurante da cobertura.
	 * @return O tipo do restaurante.
	 */
	public boolean getIsCobertura() {
		return isCobertura;
	}
	
	@Override
	public double calculaPrecoTotal() {
		return preco;
	}
	
}
