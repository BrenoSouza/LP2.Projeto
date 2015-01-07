package classes;

import java.util.Calendar;

public abstract class Servico {
	
	private Calendar data;
	private double preco;
	
	public Servico(Calendar data) throws Exception {
		
		this.data = data;	
	}
	
	public double calculaPrecoTotal() {
		// TODO m�todo todo
		return 1.1;
	}
	
	public Calendar getData() {
		return data;
	}
	
		
	public void setData(Calendar data) {
		this.data = data;
	}

}
