package classes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public abstract class Quarto extends Servico{
	private int numero, numeroHospedes, diarias;
	private double precoDiaria;
	private List<Hospede> listaHospedes = new ArrayList<Hospede>();
	private boolean camaExtra = false;
	/**
	 * O construtor do quarto.
	 * @param numero Numero do quarto.
	 * @param numeroHospedes Numero de hospedes.
	 * @param diarias Diarias no hotel.
	 * @param data Data de entrada.
	 * @param precoDiaria Preco da diaria
	 * @throws Exception Caso o numero/numeroHospedes/diarias seja menor que zero.
	 */
	public Quarto(int numero, int numeroHospedes, int diarias, Calendar data, double precoDiaria) throws Exception{
		super(data);
		if (numeroHospedes < 0 || diarias < 0 || numero < 0){
			throw new Exception("O numero de hospedes ou diarias ou numero do quarto nao pode ser menor que zero.");
		}
		this.numero = numero;
		this.numeroHospedes = numeroHospedes;
		this.diarias = diarias;
	}
	/**
	 * Retorna o numero do quarto.
	 * @return O numero do quarto.
	 */
	public int getNumero() {
		return numero;
	}
	/**
	 * Retorna o numero de hospedes.
	 * @return O numero de hospedes.
	 */
	public int getNumeroHospedes() {
		if (isCamaExtra()){
			return numeroHospedes + 1;
		}else{
			return numeroHospedes;
		}
	}
	/**
	 * Retorna o numero de diarias.
	 * @return O numero de diarias.
	 */
	public int getDiarias() {
		return diarias;
	}
	/**
	 * Retorna um List de hospedes.
	 * @return Um List<Hospedes>.
	 */
	public List<Hospede> getListaHospedes() {
		return listaHospedes;
	}
	/**
	 * Se foi solicitado cama extra.
	 * @return True - se tem cama extra/ False = se nao foi pedido cama extra.
	 */
	public boolean isCamaExtra() {
		return camaExtra;
	}
	/**
	 * Adiciona/remove uma cama extra no quarto.
	 * @param camaExtra True - adiciona uma cama no quarto/ False - remove a cama extra do quarto.
	 */
	public void setCamaExtra(boolean camaExtra) {
		this.camaExtra = camaExtra;
	}
	/**
	 * Adiciona hospedes no quarto.
	 * @param hospede O hospede a ser adicionado.
	 */
	public void adicionaHospede(Hospede hospede) throws Exception{
		if (hospede == null){
			throw new Exception("Nao é um hospede valido.");
		}
		listaHospedes.add(hospede);
	}
	/**
	 * O preco da diaria do quarto.
	 * @return O preco da diaria.
	 */
	public double getPrecoDiaria() {
		return precoDiaria;
	}
	/**
	 * Remove um hospede do quarto.
	 * @param hospede O hospede a ser removido.
	 */
	public void removeHospede(Hospede hospede) throws Exception{
		if (hospede == null){
			throw new Exception("Nao é um hospede valido.");
		}
		listaHospedes.remove(hospede);
	}
	@Override
	public double calculaPrecoTotal(){
		return getPrecoDiaria() * getDiarias();
	}
	
}
