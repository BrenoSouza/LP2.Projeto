package classes;

import java.util.ArrayList;
import java.util.List;

public abstract class Quarto extends Servico implements Comparable<Quarto>{
	private int numero, numeroHospedes;
	private int diarias = 0;
	private double precoDiaria;
	private List<Hospede> listaHospedes = new ArrayList<Hospede>();
	private boolean camaExtra = false;
	private boolean isLivre = true;

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
		this.diarias = diarias;
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
	
}
