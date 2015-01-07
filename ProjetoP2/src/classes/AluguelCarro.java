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
	private Calendar data;
	private Calendar horaEntrada;
	private Calendar horaSaida;
	
	
	public AluguelCarro(Calendar data, Calendar horaEntrada,
			Calendar horaSaida, int diarias, boolean isLuxo, 
			boolean isTanqueCheio, boolean isSegurado, 
			int quantidadeCarros) throws Exception {
		super(data, horaEntrada, horaSaida, diarias);
		
		if (quantidadeCarros < 0) {
			throw new Exception("");
		}
		
		this.isLuxo = isLuxo;
		this.isSegurado = isSegurado;
		this.isTanqueCheio = isTanqueCheio;
		this.diarias = diarias;
		this.quantidadeCarros = quantidadeCarros;
		this.data = data;
		this.horaEntrada = horaEntrada;
		this.horaSaida = horaSaida;
		
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

	@Override
	public double calculaPrecoTotal() {
		return 1.1;
	}
	
}
