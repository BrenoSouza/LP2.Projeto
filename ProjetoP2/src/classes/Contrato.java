package classes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Contrato {

	private List<Quarto> listaQuartosAlugados = new ArrayList<Quarto>();
	private List<Hospede> listaHospedes = new ArrayList<Hospede>();
	private List<Servico> listaServicos = new ArrayList<Servico>();
	private int numeroDiarias;
	private Hospede hospedePrincipal = null;
	private Calendar dataCheckIn;
	private Calendar dataCheckOut;
	private String status = "ABERTO";
	/**
	 * Construtor da classe Contrato.
	 * @param listaQuartosAlugados
	 * Um List<Quarto> com o(s) quarto(s) alugado(s).
	 * @param listaHospedes
	 * Um List<Hospede> com o(s) h�spede(s) ligado(s) ao contrato.
	 * @param numeroDiarias
	 * O n�mero de di�rias.
	 */
	public Contrato(List<Quarto> listaQuartosAlugados, List<Hospede> listaHospedes, int numeroDiarias, Calendar dataCheckIn) throws Exception{
		if (listaQuartosAlugados == null || listaHospedes == null || numeroDiarias <= 0 || listaQuartosAlugados.size() == 0 || listaHospedes.size() == 0){
			throw new Exception ("Dados inv�lidos. Tente novamente.");
		}
		this.listaQuartosAlugados.addAll(listaQuartosAlugados);
		this.listaHospedes.addAll(listaHospedes);
		this.numeroDiarias = numeroDiarias;
		this.dataCheckIn = dataCheckIn;
		dataCheckOut = dataCheckIn;
		dataCheckOut.add(Calendar.DATE, numeroDiarias);
	}
	/**
	 * Getter da lista de quartos alugados.
	 * @return
	 * Um List<Quarto> com os quartos alugados.
	 */
	public List<Quarto> getListaQuartosAlugados() {
		return listaQuartosAlugados;
	}
	/**
	 * Getter da lista de h�spedes ligados ao contrato.
	 * @return
	 * Um List<Hospede> com todos os h�spedes ligados ao contrato.
	 */
	public List<Hospede> getListaHospedes() {
		return listaHospedes;
	}
	/**
	 * Getter da lista de servi�os ligados ao contrato.
	 * @return
	 * Um List<Servico> com todos os servi�os ligados ao contrato.
	 */
	public List<Servico> getListaServicos() {
		return listaServicos;
	}
	/**
	 * Getter do n�mero de di�rias do contrato.
	 * @return
	 * O n�mero de di�rias do contrato.
	 */
	public int getNumeroDiarias() {
		return numeroDiarias;
	}
	/**
	 * Setter do objeto referente ao h�spede principal do contrato.
	 * @param hospede
	 * O h�spede que deseja ser marcado como o principal do contrato.
	 */
	public void setHospedePrincipal(Hospede hospede){
		hospedePrincipal = hospede;
	}
	/**
	 * Getter do objeto Hospede referente ao h�spede principal do contrato.
	 * @return
	 * Um objeto Hospede com o hospede principal do contrato.
	 */
	public Hospede getHospedePrincipal(){
		return hospedePrincipal;
	}
	/**
	 * M�todo que calcula o pre�o final a ser pago por tudo relevante ao contrato.
	 * @return
	 * O pre�o final a ser pago.
	 */
	public double calculaPrecoFinal(){
		// TODO O m�todo todo;
		return 0.0;
	}
	/**
	 * M�todo para mudar o status do contrato de "ABERTO" para "FECHADO"
	 */
	public void fechaContrato(){
		status = "FECHADO";
	}
	/**
	 * Getter do Calendar com a data de check in
	 * @return
	 * Um Calendar com a data de check in
	 */
	public Calendar getDataCheckIn(){
		return dataCheckIn;
	}
	/**
	 * Getter do Calendar com a data de check out
	 * @return
	 * Um Calendar com a data de check out
	 */
	public Calendar getDataCheckOut(){
		return dataCheckOut;
	}

}
