package classes;

import java.util.Calendar;

public class AluguelCarro extends Servico {

	private boolean isLuxo;
	private boolean isTanqueCheio;
	private boolean isSegurado;
	private int diarias;
	private final double DIARIA_LUXO = 100.00;
	private final double DIARIA_EXECUTIVO = 60.00;
	private final double PRECO_TANQUE_CHEIO = 150.00;
	private final double PRECO_SEGURO = 100.00;
	private double preco;	
	
	public AluguelCarro(Calendar data, int diarias, boolean isLuxo, boolean isTanqueCheio, boolean isSegurado) throws Exception {
		super(data);
		
		if (diarias <= 0) {
			throw new Exception("Parametros invalidos!");
		}
		
		this.isLuxo = isLuxo;
		this.isSegurado = isSegurado;
		this.isTanqueCheio = isTanqueCheio;
		this.diarias = diarias;
		
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
		preco = 0.0;
		if (isTanqueCheio()){
			preco += PRECO_TANQUE_CHEIO;
		}if (isSegurado()){
			preco += PRECO_SEGURO;
		}if (isLuxo()){
			preco += (DIARIA_LUXO * diarias);
		}else{
			preco += (DIARIA_EXECUTIVO * diarias);
		}return preco;
		
	}
	
}
