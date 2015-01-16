package classes;

import gui.Main;

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
	 * @param listaQuartosAlugados Um List<Quarto> com o(s) quarto(s) alugado(s).
	 * @param listaHospedes Um List<Hospede> com o(s) h�spede(s) ligado(s) ao contrato.
	 * @param numeroDiarias O n�mero de di�rias.
	 */
	public Contrato(List<Quarto> listaQuartosAlugados, List<Hospede> listaHospedes, int numeroDiarias) throws Exception{
		if (listaQuartosAlugados == null || listaHospedes == null || numeroDiarias <= 0 
//				|| listaQuartosAlugados.size() == 0 || listaHospedes.size() == 0 Partes comentadas apenas para testes mais f�ceis do PainelContrato
				){
			throw new Exception ("Dados inv�lidos. Tente novamente.");
		}
		this.listaQuartosAlugados.addAll(listaQuartosAlugados);
		this.listaHospedes.addAll(listaHospedes);
		this.numeroDiarias = numeroDiarias;
		this.dataCheckIn = Calendar.getInstance();
		dataCheckOut.setTime(dataCheckIn.getTime());
		dataCheckOut.add(Calendar.DAY_OF_YEAR, numeroDiarias);
	}
	/**
	 * Getter da lista de quartos alugados.
	 * @return Um List<Quarto> com os quartos alugados.
	 */
	public List<Quarto> getListaQuartosAlugados() {
		return listaQuartosAlugados;
	}
	/**
	 * Getter da lista de h�spedes ligados ao contrato.
	 * @return Um List<Hospede> com todos os h�spedes ligados ao contrato.
	 */
	public List<Hospede> getListaHospedes() {
		return listaHospedes;
	}
	/**
	 * Getter da lista de servi�os ligados ao contrato.
	 * @return Um List<Servico> com todos os servi�os ligados ao contrato.
	 */
	public List<Servico> getListaServicos() {
		return listaServicos;
	}
	/**
	 * Getter do n�mero de di�rias do contrato.
	 * @return O n�mero de di�rias do contrato.
	 */
	public int getNumeroDiarias() {
		return numeroDiarias;
	}
	/**
	 * Setter do objeto referente ao h�spede principal do contrato.
	 * @param hospede O h�spede que deseja ser marcado como o principal do contrato.
	 */
	public void setHospedePrincipal(Hospede hospede){
		hospedePrincipal = hospede;
	}
	/**
	 * Getter do objeto Hospede referente ao h�spede principal do contrato.
	 * @return Um objeto Hospede com o hospede principal do contrato.
	 */
	public Hospede getHospedePrincipal(){
		return hospedePrincipal;
	}
	/**
	 * M�todo que calcula o pre�o final a ser pago por tudo relevante ao contrato.
	 * @return O pre�o final a ser pago.
	 */
	public double calculaPrecoFinal(){
		return (this.CalculaPrecoQuartos() + this.CalculaPrecoServicos());
	}
	/**
	 * M�todo para mudar o status do contrato de "ABERTO" para "FECHADO"
	 */
	public void fechaContrato(){
		status = "FECHADO";
		this.dataCheckOut = Calendar.getInstance();
	}
	/**
	 * Getter do Calendar com a data de check in
	 * @return Um Calendar com a data de check in
	 */
	public Calendar getDataCheckIn(){
		return dataCheckIn;
	}
	
	public String getDataCheckInToString() {
		String dataFormatada = Main.getFormatodata().format(dataCheckIn.getTime());
		return dataFormatada;
	}
	/**
	 * Getter do Calendar com a data de check out
	 * @return Um Calendar com a data de check out
	 */
	public Calendar getDataCheckOut(){
		return dataCheckOut;
	}
	
	public String getDataCheckOutToString() {
		String dataFormatada = Main.getFormatodata().format(dataCheckOut.getTime());
		return dataFormatada;
	}
	/**
	 * Getter da string com o status do contrato.
	 * @return String com o status do contrato.
	 */
	public String getStatus(){
		return status;
	}
	/**
	 * Calcula o preco total de todos os servicos ligados ao contrato.
	 * @return O custo total dos servicos.
	 */
	public double CalculaPrecoServicos() {
		double servicosPreco = 0;
		for(Servico i: listaServicos){
			servicosPreco += i.calculaPrecoTotal();
		}
		return servicosPreco;
	}
	/**
	 * Calcula o preco total de todos os quartos ligados ao contrato.
	 * @return O custo total dos quartos.
	 */
	public double CalculaPrecoQuartos() {
		double quartosPreco = 0;
		for(Quarto i: listaQuartosAlugados){
			quartosPreco += i.calculaPrecoTotal();
		}
		return quartosPreco;
	}

	private String ServicosFinal() {
		String servicosToString = "";
		for(Servico i: listaServicos){
			servicosToString += i.toString();
			servicosToString += "\n";
		}
		return servicosToString;
	}
	
	private String QuartosFinal() {
		String quartosToString = "";
		for(Quarto i: listaQuartosAlugados){
			quartosToString += i.toString();
			quartosToString += "\n";
		}
		return quartosToString;
	}
	
	@Override
	public String toString() {
		return "--- Relatorio ---" + " CheckIn: " + this.getDataCheckInToString() + " | CheckOut: "+ this.getDataCheckOutToString() +
				"\nStatus -> " + this.getStatus() +
				"\n vvv Servicos vvv" +
				ServicosFinal() +
				"Custo total dos servicos: R$ " + this.CalculaPrecoServicos() +
				"\n vvv Quartos vvv" + 
				QuartosFinal() +
				"Custo total dos quartos: R$ " + this.CalculaPrecoQuartos() +
				"\nCusto final do contrato: R$ " + this.calculaPrecoFinal();
	}

}
