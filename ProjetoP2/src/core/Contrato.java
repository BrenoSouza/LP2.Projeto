package core;

import gui.Main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;

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
   * @throws ParametrosInvalidosException
   */
  public Contrato(List<Quarto> listaQuartosAlugados, List<Hospede> listaHospedes, int numeroDiarias) throws ParametrosInvalidosException{
    if (listaQuartosAlugados == null || numeroDiarias <= 0 || listaQuartosAlugados.size() == 0) {
      throw new ParametrosInvalidosException ("Dados inválidos. Tente novamente.");
    }
    if (listaHospedes == null || listaHospedes.size() == 0) {
      throw new ParametrosInvalidosException ("Lista de hospedes invalida.");
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
  public Contrato(Calendar dataCheckIn, List<Quarto> listaQuartosAlugados, List<Hospede> listaHospedes, int numeroDiarias) throws ParametrosInvalidosException{
    if (listaQuartosAlugados == null || listaHospedes == null || numeroDiarias <= 0 
        || listaQuartosAlugados.size() == 0 || listaHospedes.size() == 0
        ){
      throw new ParametrosInvalidosException ("Dados inválidos. Tente novamente.");
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
   * Método que calcula o preço final a ser pago por tudo relevante ao contrato (com multa).
   * @return O preço final a ser pago.
   */
  public double calculaPrecoFinalComMulta(){
    return (this.CalculaPrecoQuartos() + this.CalculaPrecoServicos()) + getMulta();
  }
  /**
   * Método que calcula o preço final a ser pago por tudo relevante ao contrato (sem multa).
   * @return O preço final a ser pago.
   */
  public double calculaPrecoFinalSemMulta(){
    return (this.CalculaPrecoQuartos() + this.CalculaPrecoServicos());
  }
  /**
   * Método para mudar o status do contrato de "ABERTO" para "FECHADO".
   */
  public void fechaContrato(){
    status = "FECHADO";
    this.dataCheckOut = Calendar.getInstance();
    for (Quarto quarto: listaQuartosAlugados){
      quarto.setCamaExtra(false);
    }

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
    String dataFormatada = Main.converteParaString(dataCheckIn);
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
    String dataFormatada = Main.converteParaString(dataCheckOut);
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
   * Método que retorna uma String com a descrições das estratégias do contrato.
   * @return
   * uma String com a descrições das estratégias do contrato
   */
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
   * Calcula o preço total de todos os quartos ligados ao contrato.
   * @return O custo total dos quartos.
   */
  public double CalculaPrecoQuartos(){
    double quartosPreco = 0;
    if (estrategiasDoContrato.size() == 0){ //Se não houverem estratégias no contrato, o cálculo é BEM mais simples
      for (Quarto i : listaQuartosAlugados) {
        Reserva reservaSelecionada = null;
        for (Reserva reserva: i.getListaReservas()){
          if (equals(reserva.getContrato())){
            reservaSelecionada = reserva;
            break;
          }
        }if (reservaSelecionada == null){
          throw new RuntimeException("Na busca pelas reservas do quarto selecionado, a reserva referente ao contrato não foi encontrada.");
        }
        quartosPreco += i.getPrecoDiaria() * reservaSelecionada.getNumeroDias();
      }
    }else{
      for (Quarto i: listaQuartosAlugados){
        Reserva reservaSelecionada = null;
        for (Reserva reserva: i.getListaReservas()){
          
         if (reserva.getContrato().getHospedePrincipal().equals(getHospedePrincipal())){
            reservaSelecionada = reserva;         
          }
        }
        if (reservaSelecionada == null){
          throw new RuntimeException("Na busca pelas reservas do quarto selecionado, a reserva referente ao contrato não foi encontrada.");
        }
        DateTime inicio = reservaSelecionada.getDataCheckIn();
        DateTime parada = reservaSelecionada.getDataCheckOut();
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
      servicosToString += Main.quebraDeLinha;
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
      quartosToString += Main.quebraDeLinha;
    }
    return quartosToString;
  }
  public void adicionaQuarto(Quarto quarto) {
    listaQuartosAlugados.add(quarto);
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
  /**
   * Adicionando estratégias no contrato.
   * @param estrategia
   * Uma estratégia a ser adicionada.
   */
  public void adicionaEstrategiaNoContrato (Estrategia estrategia){
    estrategiasDoContrato.add(estrategia);
  }
  /**
   * Método que retorna os dias de atraso entre a data de checkOut e o presente.
   * @return
   * Os dias de atraso entre a data de checkOut e o presente
   */
  public int getDiasDeDiferenca(){
    DateTime presente = new DateTime().withTimeAtStartOfDay();
    
    DateTime checkOut = new DateTime(dataCheckOut).withTimeAtStartOfDay();
    
    if (Days.daysBetween(checkOut, presente).getDays() > 0){//Se o checkOut estiver atrasado.
      return Days.daysBetween(checkOut, presente).getDays();
    }else if (Days.daysBetween(presente, checkOut).getDays() > 0){//Se o checkOut estiver adiantado.
      return Days.daysBetween(presente, checkOut).getDays();
    }
    return 0;
  }
  /**
   * Método que retorna uma possível multa.
   * @return
   * A possível multa do contrato. 
   */
  public double getMulta(){
    if (getDiasDeDiferenca() <= 0){
      return 0.0;
    }
    return Math.abs((CalculaPrecoQuartos() * 0.3) * getDiasDeDiferenca()); //Em nenhum lugar da especificação do projeto dizia o valor da multa, então estipulei 30% do preço total dos quartos a cada dia fora do checkOut.
  }

  @Override
  public boolean equals(Object obj){
    if (obj == null || !(obj instanceof Contrato)){
      return false;
    }
    Contrato outroContrato = (Contrato) obj;
    boolean listasIguais = getListaHospedes().equals(outroContrato.getListaHospedes()) && getListaQuartosAlugados().equals(outroContrato.getListaQuartosAlugados());
    return listasIguais;

  }

  @Override
  public String toString() {
    return "--- Relatório ---" + " CheckIn: " + this.getDataCheckInToString() + " | CheckOut: "+ this.getDataCheckOutToString() +
        Main.quebraDeLinha  + "Status -> " + this.getStatus() +
        Main.quebraDeLinha  + " vvv Serviços vvv" +
        ServicosFinal() +
        "Custo total dos serviços: R$ " + this.CalculaPrecoServicos() +
        Main.quebraDeLinha  + " vvv Quartos vvv" + 
        QuartosFinal() +
        "Custo total dos quartos: R$ " + this.CalculaPrecoQuartos() +
        Main.quebraDeLinha  + "Custo final do contrato: R$ " + this.calculaPrecoFinalSemMulta();
  }

}
