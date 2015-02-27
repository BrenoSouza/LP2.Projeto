package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.Interval;

public abstract class Quarto extends Servico implements Comparable<Quarto>, Serializable{
	private int numero, numeroHospedes;
	private int diarias = 0;
	private double precoDiaria;
	private List<Hospede> listaHospedes = new ArrayList<Hospede>();
	private boolean camaExtra = false;
	private boolean isLivre = true;
	private List<Reserva> listaReservas = new ArrayList<Reserva>();

	/**
	 * O construtor do quarto.
	 * @param numero Número do quarto.
	 * @param numeroHospedes Número de hospedes.
	 * @param data Data de entrada.
	 * @param precoDiaria Preço da diária
	 * @throws Exception Caso o número/numeroHospedes/diárias seja menor que zero.
	 */
	public Quarto(int numero, int numeroHospedes, double precoDiaria) throws Exception{
		super();
		if (numeroHospedes < 0 || diarias < 0 || numero < 0){
			throw new Exception("O número de hospedes ou diarias ou numero do quarto nao pode ser menor que zero.");
		}
		this.numero = numero;
		this.numeroHospedes = numeroHospedes;
		this.precoDiaria = precoDiaria;
	}
	/**
	 * Getter do parâmetro "isLivre"
	 * @return
	 * True se o quarto estiver desocupado
	 */
	public boolean isLivre() {
		return isLivre;
	}
	/**
	 * Setter do parâmetro "isLivre"
	 * @param isLivre
	 * True se o quarto for ser definido como "vago"
	 */
	public void setLivre(boolean isLivre) {
		if (isLivre){
			setDiarias(0);
		}
		this.isLivre = isLivre;
	}
	/**
	 * Getter de uma string revelando se o quarto está vago ou livre
	 * @return
	 * "VAGO" se o quarto estiver livre, "OCUPADO" caso contrário
	 */
	public String getEstado(){
		if (isLivre){
			return "VAGO";
		}else{
			return "OCUPADO";
		}
	}
	/**
	 * Setter da variável "diarias"
	 * @param diarias
	 * O número de diárias
	 */
	public void setDiarias(int diarias){
		if (diarias >= 0){
			this.diarias = diarias;
		}
	}
	/**
	 * Retorna o número do quarto.
	 * @return O número do quarto.
	 */
	public int getNumero() {
		return numero;
	}
	/**
	 * Retorna o número de hóspedes.
	 * @return O número de hóspedes.
	 */
	public int getNumeroHospedes() {
		if (isCamaExtra()){
			return numeroHospedes + 1;
		}else{
			return numeroHospedes;
		}
	}
	/**
	 * Retorna o número de diárias.
	 * @return O número de diárias.
	 */
	public int getDiarias() {
		return diarias;
	}
	/**
	 * Retorna um List de hóspede.
	 * @return Um List<Hospedes>.
	 */
	public List<Hospede> getListaHospedes() {
		return listaHospedes;
	}
	/**
	 * Se foi solicitado cama extra.
	 * @return True - se tem cama extra/ False = se não foi pedido cama extra.
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
	 * Adiciona hóspedes no quarto.
	 * @param hospede O hóspede a ser adicionado.
	 */
	public boolean adicionaHospede(Hospede hospede) throws Exception{
		if (hospede == null){
			throw new Exception("Nao é um hóspede válido.");
		}
		return listaHospedes.add(hospede);
	}
	/**
	 * O preço da diária do quarto.
	 * @return O preço da diária.
	 */
	public double getPrecoDiaria() {
		return precoDiaria;
	}
	/**
	 * Remove um hóspede do quarto.
	 * @param hospede O hóspede a ser removido.
	 */
	public boolean removeHospede(Hospede hospede) throws Exception{
		if (hospede == null){
			throw new Exception("Nao é um hóspede valido.");
		}
		return listaHospedes.remove(hospede);
	}
	@Override
	public double calculaPrecoTotal(){
		return getPrecoDiaria() * getDiarias();
	}
	
	@Override
	public String toString() {
		return "\nNúmero do quarto -> " + this.getNumero() +
				"\nNúmero de hóspedes -> " + this.getNumeroHospedes() +
				"\nDiárias -> " + this.getDiarias();
	}
	public int compareTo(Quarto q){
		return this.getNumero() - q.getNumero();
	}
	/**
	 * Método para que o quarto retorne ao seu padrão (Livre e com 0 diárias)
	 */
	public void setToLivre(){
		setDiarias(0);
		setLivre(true);
	}
	/**
	 * Método para que o quarto entre no estado de Vago
	 * @param diarias
	 * As diárias para o quarto
	 */
	public void setToOcupado(int diarias){
		setDiarias(diarias);
		setLivre(false);
	}
	/**
	 * Retorna uma List<Reserva> de quarto
	 * @return Uma List<Reserva>
	 */
	public List<Reserva> getListaReservas() {
		return listaReservas;
	}
	/**
	 * Adiciona uma reserva a lista de reservas
	 * @param reserva A reserva
	 */
	public void adicionaReserva(Reserva reserva){
		listaReservas.add(reserva);
	}
	/**
	 * Cria uma nova reserva e depois adiciona a lista de reservas.
	 * @param contrato Um contrato para criação de uma nova reserva.
	 */
	public void adicionaReserva(Contrato contrato){
		Reserva reserva = new Reserva(contrato);
		listaReservas.add(reserva);
	}
	/**
	 * Retira uma reserva da lista de reserva
	 * @param contrato O contrato ligado a reserva a ser removida
	 */
	public void retiraReserva(Contrato contrato){
		for (Reserva reserva: listaReservas){
			if (reserva.getContrato() == contrato){
				listaReservas.remove(reserva);
				break;
			}
		}
	}
	/**
	 * Checa se o quarto está livre para reserva ou não
	 * @param intervalo O intervalo de tempo que o quarto será utilizado
	 * @return True - se está livre para reserva / False - se está ocupado/reservado
	 */
	public boolean isLivreParaReserva(Interval intervalo){
		for (Reserva reserva: listaReservas){
			if (reserva.getIntervaloSobrepoe(intervalo)){
				return false;
			}
		}return true;
	}
	@Override
	public boolean equals (Object obj){
		if (!(obj instanceof Quarto)){
			return false;
		}
		Quarto outroQuarto = (Quarto) obj;
		return (this.getNumero() == outroQuarto.getNumero());
	}
	public int compareToEstado(Quarto q){
		return this.getEstado().compareTo(q.getEstado());
	}
}