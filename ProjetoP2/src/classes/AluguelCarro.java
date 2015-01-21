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
	
	/**
	 * Construtor do Serviço de Alguel de Carros.
	 * @param diarias Quantidade de diárias do aluguel de Carros.
	 * @param isLuxo Se o carro escolhido é de Luxo.
	 * @param isTanqueCheio Se o tanque do carro está cheio.
	 * @param isSegurado Se o carro está com seguro.
	 * @throws Exception Se o número de diárias for menor que 1.
	 */
	
	public AluguelCarro(int diarias, boolean isLuxo, boolean isTanqueCheio, boolean isSegurado) throws Exception {
		super();
		if (diarias < 1) {
			throw new Exception("Parametros inválidos!");
		}
		
		this.isLuxo = isLuxo;
		this.isSegurado = isSegurado;
		this.isTanqueCheio = isTanqueCheio;
		this.diarias = diarias;
		
	}

	/**
	 * Getter Se o carro é de luxo.
	 * @return Se o tipo do carro é de luxo.
	 */
	public boolean isLuxo() {
		return isLuxo;
	}

	/**
	 * Getter Se o tanque do carro é cheio.
	 * @return Se o tanque do carro está cheio.
	 */
	public boolean isTanqueCheio() {
		return isTanqueCheio;
	}

	/**
	 * Getter se o carro está assegurado.
	 * @return Se o carro esta assegurado.
	 */
	public boolean isSegurado() {
		return isSegurado;
	}

	/**
	 * O setter do número de diárias.
	 * @param diarias Um parâmetro com o número de diárias.
	 */
	public void setDiarias(int diarias) {
		this.diarias += diarias;
	}
	
	/**
	 * Getter do número de diarias.
	 * @return O número de dias que o carro será alugado.
	 * 
	 */
	public int getDiarias() {
		return diarias;
	}
	
	/**
	 * Calcula o preço total do servico.
	 * @return O preço final.
	 */
	
	public String getFim() {
		int diaInicio = super.getData().get(Calendar.DAY_OF_MONTH);
		super.getData().set(Calendar.DAY_OF_MONTH, diaInicio + getDiarias());
		return super.getInicioServico();
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
	
	@Override
	public String toString() {
		return "Serviço --- Aluguel de Carro ---" +
				"\nInício -> " + this.getInicioServico() +
				"\nFim -> " + this.getFim() +
				"\nExtras ->\n Tanque cheio? " + (this.isTanqueCheio() ? "Sim" : "Não") +
				"\n            Segurado? " + (this.isSegurado() ? "Sim" : "Não") +
				"\n            Luxo? " + (this.isLuxo() ? "Sim" : "Não") +
				"\nCusto final: " + this.calculaPrecoTotal();
	}

	@Override
	public String getTipo() {
		return "Aluguel de carro" + (isTanqueCheio() ? ", tanque cheio" : "") + (isLuxo() ? ", luxo" : "") + (isSegurado() ? ", segurado" : "");
	}
	
}
