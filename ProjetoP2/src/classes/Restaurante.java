package classes;

import java.util.Calendar;

	public class Restaurante extends Servico {

		private boolean isCobertura;
		
		public Restaurante(Calendar data, Calendar horaEntrada,
				Calendar horaSaida, int diarias, boolean isCobertura)
				throws Exception {
			super(data, horaEntrada, horaSaida, diarias);
			
			this.isCobertura = isCobertura;
		}

		
	
	
}
