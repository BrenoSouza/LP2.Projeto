package classes;

import java.util.Calendar;

	public class Restaurante extends Servico {

		private boolean isCobertura;
		private double preco;
		
		public Restaurante(Calendar data, boolean isCobertura, double preco) throws Exception {
			super(data);
			this.isCobertura = isCobertura;
			this.preco = preco;
		}
	
	@Override
	public double calculaPrecoTotal() {
		return preco;
	}
	
	public boolean getIsCobertura() {
		return isCobertura;
	}
	
}
