package core;

import gui.Main;

import java.util.Calendar;

public abstract class Servico{
	
	private Calendar data;
	/**
	 * Construtor de serviço
	 * @param data Data de criação do serviço.
	 */
	public Servico() {
		this.data = Calendar.getInstance();
	}
	/**
	 * Calcula o preço total do serviço.
	 * @return O preço final.
	 */
	public abstract double calculaPrecoTotal();
	/**
	 * Retorna um Calendar com a data de criação do serviço.
	 * @return Um Calendar com a data de criação do serviço.
	 */
	public Calendar getData() {
		return data;
	}
	/**
	 * Retorna a hora de entrada do serviço.
	 * @return A hora de entrada do serviço.
	 */
	public int getHoraEntrada(){
		return getData().get(Calendar.HOUR_OF_DAY);
	}
	/**
	 * Retorna os minutos da hora de Entrada/Inicio do servico
	 * @return Um int com os minutos
	 */
	public int getMinutosEntrada(){
		return getData().get(Calendar.MINUTE);
	}
	/**
	 * Retorna a data de inicio do serviço.
	 * @return A data de inicio do serviço.
	 */
	public String getInicioServico(){
		String dataFormatada = Main.getFormatodata().format(data.getTime());
		return dataFormatada;	
	}
	/**
	 * Uma String com o tipo do serviço.
	 * @return String com o tipo do serviço.
	 */
	public abstract String getTipo();
	
}
