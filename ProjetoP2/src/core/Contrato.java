package core;

import gui.Main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.joda.time.DateTime;

public class Contrato implements Serializable{

  private static final long serialVersionUID = -1853311162234683521L;
  private List<Quarto> listaQuartosAlugados = new ArrayList<Quarto>();
  private List<Hospede> listaHospedes = new ArrayList<Hospede>();
  private List<Servico> listaServicos = new ArrayList<Servico>();
  private int numeroDiarias;
  private Hospede hospedePrincipal = null;
  private List<Estrategia> estrategiasDoContrato = new ArrayList<Estrategia>();

  private Calendar dataCheckIn;
  private Calendar dataCheckOut = Calendar.getInstance();
  private String status = "ABERTO";
  private double precoQueFoiPago;
  private String cartaoDeCredito;

  /**
   * Um setter para modificar a estrategia de preco usada no contrato.
   * @param estrategiasDoContrato 
   * Uma lista de estrategias para o contrato
   */
  public void setEstrategiasDoContrato(List<Estrategia> estrategiasDoContrato) {
    this.estrategiasDoContrato = estrategiasDoContrato;
  }

  /**
   * Construtor da classe Contrato.
   * @param listaQuartosAlugados Um List de Quarto com o(s) quarto(s) alugado(s).
   * @param listaHospedes Um List de Hospede com o(s) hóspede(s) ligado(s) ao contrato.
   * @param numeroDiarias O número de diárias.
   * @throws IllegalArgumentException
   */
  public Contrato(List<Quarto> listaQuartosAlugados, List<Hospede> listaHospedes, int numeroDiarias) throws IllegalArgumentException{
    if (listaQuartosAlugados == null || numeroDiarias <= 0 || listaQuartosAlugados.size() == 0) {
      throw new IllegalArgumentException ("Dados inválidos. Tente novamente.");
    }
    if (listaHospedes == null || listaHospedes.size() == 0) {
      throw new IllegalArgumentException ("Lista de hospedes invalida.");
    }
    this.listaQuartosAlugados.addAll(listaQuartosAlugados);
    this.listaHospedes.addAll(listaHospedes);
    this.numeroDiarias = numeroDiarias;
    this.dataCheckIn = Calendar.getInstance();
    dataCheckOut.setTime(dataCheckIn.getTime());
    dataCheckOut.add(Calendar.DAY_OF_YEAR, numeroDiarias);
  }
  /**
   * Segundo construtor de Contrato, com uma data de check-in (para reservas).
   * @param dataCheckIn
   * Dia que se espera que haja o check-in
   * @param listaQuartosAlugados Um List<Quarto> com o(s) quarto(s) alugado(s).
   * @param listaHospedes Um List<Hospede> com o(s) hóspede(s) ligado(s) ao contrato.
   * @param numeroDiarias O número de diárias.
   */
  public Contrato(Calendar dataCheckIn, List<Quarto> listaQuartosAlugados, List<Hospede> listaHospedes, int numeroDiarias) throws IllegalArgumentException{
    if (listaQuartosAlugados == null || listaHospedes == null || numeroDiarias <= 0 
        || listaQuartosAlugados.size() == 0 || listaHospedes.size() == 0
        ){
      throw new IllegalArgumentException ("Dados inválidos. Tente novamente.");
    }
    this.listaQuartosAlugados.addAll(listaQuartosAlugados);
    this.listaHospedes.addAll(listaHospedes);
    this.numeroDiarias = numeroDiarias;
    this.dataCheckIn = dataCheckIn;
    dataCheckOut.setTime(dataCheckIn.getTime());
    dataCheckOut.add(Calendar.DAY_OF_YEAR, numeroDiarias);
    status = "RESERVA";
  }
  /**
   * Getter da lista de quartos alugados.
   * @return Um List<Quarto> com os quartos alugados.
   */
  public List<Quarto> getListaQuartosAlugados() {
    return listaQuartosAlugados;
  }
  /**
   * Getter da lista de hóspedes ligados ao contrato.
   * @return Um List<Hospede> com todos os hóspedes ligados ao contrato.
   */
  public List<Hospede> getListaHospedes() {
    return listaHospedes;
  }
  /**
   * Getter da lista de serviços ligados ao contrato.
   * @return Um List<Servico> com todos os serviços ligados ao contrato.
   */
  public List<Servico> getListaServicos() {
    return listaServicos;
  }
  /**
   * Getter do número de diárias do contrato.
   * @return O número de diárias do contrato.
   */
  public int getNumeroDiarias() {
    return numeroDiarias;
  }
  /**
   * Informa o numero do cartao de credito usado no contrato.
   * @return O numero do cartao
   */
  public String getCartaoDeCredito() {
    return cartaoDeCredito;
  }
  /**
   * Modifica o numero do cartao de credito usado no contrato.
   * @param cartao Uma string com o numero do cartao
   */
  public void setCartaoDeCredito (String cartao){
    this.cartaoDeCredito = cartao;
  }
  /**
   * Setter do objeto referente ao hóspede principal do contrato.
   * @param hospede O hóspede que deseja ser marcado como o principal do contrato.
   */
  public void setHospedePrincipal(Hospede hospede){
    hospedePrincipal = hospede;
  }
  /**
   * Getter do objeto Hospede referente ao hóspede principal do contrato.
   * @return Um objeto Hospede com o hóspede principal do contrato.
   */
  public Hospede getHospedePrincipal(){
    return hospedePrincipal;
  }
  /**
   * Método que calcula o preço final a ser pago por tudo relevante ao contrato.
   * @return O preço final a ser pago.
   */
  public double calculaPrecoFinal(){
    if (status.equals("FECHADO")){
      return precoQueFoiPago;
    }
    return (this.CalculaPrecoQuartos() + this.CalculaPrecoServicos());
  }
  /**
   * Método para mudar o status do contrato de "ABERTO" para "FECHADO".
   */
  public void fechaContrato(){
    precoQueFoiPago = calculaPrecoFinal();
    status = "FECHADO";
    this.dataCheckOut = Calendar.getInstance();

  }
  /**
   * Getter do Calendar com a data de check in.
   * @return Um Calendar com a data de check in.
   */
  public Calendar getDataCheckIn(){
    return dataCheckIn;
  }
  /**
   * Retorna uma String com a data de checkIn.
   * @return Uma String com a data de checkIn.
   */
  public String getDataCheckInToString() {
    String dataFormatada = Main.getFormatodata().format(dataCheckIn.getTime());
    return dataFormatada;
  }
  /**
   * Getter do Calendar com a data de check out.
   * @return Um Calendar com a data de check out.
   */
  public Calendar getDataCheckOut(){
    return dataCheckOut;
  }
  /**
   * Retorna uma String com a data de checkOut.
   * @return Uma String com a data de checkOut.
   */
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
   * Calcula o preço total de todos os serviços ligados ao contrato.
   * @return O custo total dos serviços.
   */
  public double CalculaPrecoServicos() {
    double servicosPreco = 0;
    for(Servico i: listaServicos){
      servicosPreco += i.calculaPrecoTotal();
    }
    return servicosPreco;
  }
  /**
   * Calcula o preço total de todos os quartos ligados ao contrato.
   * @return O custo total dos quartos.
   */
  public double CalculaPrecoQuartos(){
    double quartosPreco = 0;
    if (estrategiasDoContrato.size() == 0){ //Se não houverem estratégias no contrato, o cálculo é BEM mais simples
      for (Quarto i : listaQuartosAlugados) {
        quartosPreco += i.getPrecoDiaria() * getNumeroDiarias();
      }
    }else{
      DateTime inicioContrato = new DateTime(getDataCheckIn()).withTimeAtStartOfDay();
      for (Quarto i: listaQuartosAlugados){
        
        DateTime inicio = inicioContrato;
        DateTime parada = inicioContrato.plusDays(numeroDiarias);
        DateTime dia = inicio;
        // Itera por cada dia do período.
        while (dia.compareTo(parada) < 0) {
          
          boolean quartoTemEstrategia = false; //Boolean que vai servir para checar se um quarto já passou por um cálculo de preço com estrategia.
          
          for (Estrategia estrategia : estrategiasDoContrato) {
            if (estrategia.periodoContemIntervalo(dia) && quartoTemEstrategia == false) {
              quartoTemEstrategia = true;
              quartosPreco += i.getPrecoDiaria() + (estrategia.getModificadorPreco(i.getPrecoDiaria()));
            }
          }if (!quartoTemEstrategia){ //Se o quarto, no dia específico, não estiver dentro de nenhuma estratégia, adiciona-se o preço normal dele.
            quartosPreco += i.getPrecoDiaria();
          }
          // Próximo
          dia = dia.plusDays(1);
        }
      }
    }
    return quartosPreco;
  }
  /**
   * Cria uma String com todos os serviços ligados ao contrato.
   * @return Uma String formatada com o toString() de todos os serviços.
   */
  private String ServicosFinal() {
    String servicosToString = "";
    for(Servico i: listaServicos){
      servicosToString += i.toString();
      servicosToString += "\n";
    }
    return servicosToString;
  }
  /**
   * Cria uma String com todos os quartos ligados ao contrato.
   * @return Uma String formatada com o toString() de todos os quartos.
   */
  private String QuartosFinal() {
    String quartosToString = "";
    for(Quarto i: listaQuartosAlugados){
      quartosToString += i.toString();
      quartosToString += "\n";
    }
    return quartosToString;
  }
  /**
   * Faz com que determinados hospedes estejam ligados ao contrato.
   * @param hospedes Uma List<Hospedes> com os hospedes a serem ligados ao contrato
   */
  public void setContratoEmHospede(List<Hospede> hospedes) {
    for(Hospede hospede: hospedes){
      hospede.setContratoLigado(this);
    }
  }
  /**
   * Inicia o contrato e calcula a data de checkOut com base nas diarias do contrato.
   */
  public void fazCheckIn(){
    status = "ABERTO";
    dataCheckIn = Calendar.getInstance();
    dataCheckOut.setTime(dataCheckIn.getTime());
    dataCheckOut.add(Calendar.DAY_OF_YEAR, numeroDiarias);
  }
  /**
   * Retorna a lista de estratégias vigentes no contrato.
   * @return
   * A lista de estratégias vigentes no contrato.
   */
  public List<Estrategia> getEstrategiasDoContrato() {
    return estrategiasDoContrato;
  }
  public String getDescricaoEstrategias(){
    String retorno = "";
    for (int i = 0; i < estrategiasDoContrato.size(); i++) {
      retorno += estrategiasDoContrato.get(i).getDescricao();
      if (i < estrategiasDoContrato.size() - 1){
        retorno += ", ";
      }
    }return retorno;
  }
  /**
   * Adicionando estratégias no contrato.
   * @param estrategia
   * Uma estratégia a ser adicionada.
   */
  public void adicionaEstrategiaNoContrato (Estrategia estrategia){
    estrategiasDoContrato.add(estrategia);
  }

  @Override
  public boolean equals(Object obj){
    if (obj == null || !(obj instanceof Contrato)){
      return false;
    }
    Contrato outroContrato = (Contrato) obj;
    boolean datasIguais = dataCheckIn.equals(outroContrato.getDataCheckIn()) && dataCheckOut.equals(outroContrato.getDataCheckOut());

    boolean hospedePrincipalIgual;

    if (hospedePrincipal == null || outroContrato.getHospedePrincipal() == null){
      // Existe a possibilidade que hospedePrincipal seja null, então...
      hospedePrincipalIgual = hospedePrincipal == outroContrato.getHospedePrincipal();
    }else{
      hospedePrincipalIgual = hospedePrincipal.equals(outroContrato.getHospedePrincipal());
    }	  

    boolean dadosIguais = numeroDiarias == outroContrato.getNumeroDiarias() && cartaoDeCredito.equals(outroContrato.getCartaoDeCredito());
    boolean listasIguais = listaHospedes.equals(outroContrato.getListaHospedes()) && listaQuartosAlugados.equals(outroContrato.getListaQuartosAlugados()) && listaServicos.equals(outroContrato.getListaHospedes());

    return (datasIguais && hospedePrincipalIgual && dadosIguais && listasIguais);

  }

  @Override
  public String toString() {
    return "--- Relatório ---" + " CheckIn: " + this.getDataCheckInToString() + " | CheckOut: "+ this.getDataCheckOutToString() +
        "\nStatus -> " + this.getStatus() +
        "\n vvv Serviços vvv" +
        ServicosFinal() +
        "Custo total dos serviços: R$ " + this.CalculaPrecoServicos() +
        "\n vvv Quartos vvv" + 
        QuartosFinal() +
        "Custo total dos quartos: R$ " + this.CalculaPrecoQuartos() +
        "\nCusto final do contrato: R$ " + this.calculaPrecoFinal();
  }

}
