package classes;

import java.util.Calendar;

public class AluguelCarro extends Servico {

	private boolean isLuxo;
	private boolean isTanqueCheio;
	private boolean isSegurado;
	private int diarias;
	private final double DIARIA_LUXO = 100.00;
	private final double DIARIA_EXECUTIVO = 60.00;
	private int quantidadeCarros;
	private double preco;	
	
	public AluguelCarro(Calendar data, int diarias, boolean isLuxo, 
			boolean isTanqueCheio, boolean isSegurado, 
			int quantidadeCarros) throws Exception {
		super(data);
		
		if (quantidadeCarros < 0) {
			// TODO os casos de excess�o.
			throw new Exception("");
		}
		
		this.isLuxo = isLuxo;
		this.isSegurado = isSegurado;
		this.isTanqueCheio = isTanqueCheio;
		this.diarias = diarias;
		this.quantidadeCarros = quantidadeCarros;
		
	}

	public int getQuantidadeCarros() {
		return quantidadeCarros;
	}

	public void setQuantidadeCarros(int quantidadeCarros) {
		this.quantidadeCarros = quantidadeCarros;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public boolean isLuxo() {
		return isLuxo;
	}

	public boolean isTanqueCheio() {
		return isTanqueCheio;
	}

	public boolean isSegurado() {
		return isSegurado;
	}

	public void setDiarias(int diarias) {
		this.diarias = diarias;
	}
	
	public int getDiarias() {
		return diarias;
	}
	
	@Override
	public double calculaPrecoTotal() {
		// TODO o m�todo todo
		return 1.1;
	}
	
}
