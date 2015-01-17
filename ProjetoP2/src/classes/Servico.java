package classes;

import java.util.Calendar;
import gui.Main;

public abstract class Servico {
	
	private Calendar data;
	/**
	 * Construtor de servico
	 * @param data Data de criacao do servico.
	 */
	public Servico() {
		this.data = Calendar.getInstance();
	}
	/**
	 * Calcula o preco total do servico.
	 * @return O preco final.
	 */
	public abstract double calculaPrecoTotal();
	/**
	 * Retorna um Calendar com a data de criacao do servico.
	 * @return Um Calendar com a data de criacao do servico.
	 */
	public Calendar getData() {
		return data;
	}
	/**
	 * Retorna a hora de entrada do servico.
	 * @return A hora de entrada do servico.
	 */
	public int getHoraEntrada(){
		return getData().get(Calendar.HOUR_OF_DAY);
	}
	/**
	 * Retorna a data de inicio do servico.
	 * @return A data de inicio do servico.
	 */
	public String getInicioServico(){
		String dataFormatada = Main.getFormatodata().format(data);
		return dataFormatada;	
	}
	
}
