package core;

import gui.Main;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
public class Estrategia implements Comparable<Estrategia>, Serializable{
	
	private static final long serialVersionUID = -1724512552674869456L;
	public static final TipoDeEstrategia ACRESCIMO = TipoDeEstrategia.ACRESCIMO;
	public static final TipoDeEstrategia DECRESCIMO = TipoDeEstrategia.DECRESCIMO;
	private DateTime inicioPeriodo;
	private DateTime finalPeriodo;
	private Period periodo;
	private Interval intervalo;
	private double modificador;
	private String descricao;
	private TipoDeEstrategia tipoDeEstrategia;
	
	
	/**
	 * Construtor da classe Estrategia.
	 * @param inicioPeriodo
	 * Um DateTime contendo o início do período da estratégia.
	 * @param finalPeriodo
	 * Um DateTime contendo o final do período da estratégia.
	 * O modificador, de 0-100;
	 * @param tipoDeEstrategia
	 * Estrategia.ACRESCIMO ou Estrategia.DECRESCIMO.
	 * @param descricao
	 * Uma descrição da estratégia.
	 * @throws IllegalArgumentException
	 * Se o modificador não estiver em formato correto. 
	 */
	public Estrategia(DateTime inicioPeriodo, DateTime finalPeriodo, double modificador, TipoDeEstrategia tipoDeEstrategia, String descricao) throws IllegalArgumentException{
		if (modificador < 0 || modificador > 100){
			throw new IllegalArgumentException("Modificador em formato inválido (menor que 0 ou maior que 100).");
		}if (descricao.isEmpty()){
			throw new IllegalArgumentException("Insira alguma descrição para a estratégia.");
		}
		this.inicioPeriodo = inicioPeriodo;
		if (inicioPeriodo.isAfter(finalPeriodo)){ //No caso de uma estratégia que começa num ano e termina em outro.
      this.finalPeriodo = finalPeriodo.plusYears(1); 
    }else{
      this.finalPeriodo = finalPeriodo;
    }
		periodo = new Period(inicioPeriodo, finalPeriodo);
		this.modificador = modificador;
		this.tipoDeEstrategia = tipoDeEstrategia;
		this.descricao = descricao;
		this.intervalo = new Interval(this.inicioPeriodo, this.finalPeriodo);
	}
	/**
	 * Getter do Interval da estratégia.
	 * @return
	 * O Interval da estratégia
	 */
	public Interval getIntervalo() {
		atualizaIntervalo();
		return intervalo;
	}
	/**
	 * Construtor da classe Estrategia.
	 * @param inicioPeriodo
	 * Um Calendar contendo o início do período.
	 * @param finalPeriodo
	 * Um Calendar contendo o final do período.
	 * @param modificador
	 * O modificador, de 0-100;
	 * @param tipoDeEstrategia
	 * Estrategia.ACRESCIMO ou Estrategia.DECRESCIMO.
	 * @throws IllegalArgumentException
	 * Se o modificador não estiver em formato correto.
	 */
	public Estrategia(Calendar inicioPeriodo, Calendar finalPeriodo, double modificador, TipoDeEstrategia tipoDeEstrategia, String descricao) throws IllegalArgumentException{
		this(new DateTime(inicioPeriodo), new DateTime(finalPeriodo), modificador, tipoDeEstrategia, descricao);
	}
	/**
	 * Construtor da classe Estrategia.
	 * @param dataInicio
	 * Uma String no formato dd/mm/aaaa com o início do período.
	 * @param dataFinal
	 * Uma String no formato dd/mm/aaaa com o final do período.
	 * @param modificador
	 * O modificador, de 0-100;
	 * @param tipoDeEstrategia
	 * Estrategia.ACRESCIMO ou Estrategia.DECRESCIMO.
	 * @param descricao
	 * Uma curta descrição sobre a estratégia.
	 * @throws ParseException
	 * Se a data não estiver em formato correto.
	 * @throws IllegalArgumentException
	 * Se o modificador não estiver em formato correto.
	 */
	public Estrategia(String dataInicio, String dataFinal, double modificador, TipoDeEstrategia tipoDeEstrategia, String descricao) throws IllegalArgumentException, ParseException{
		this(Main.converteParaCalendar(dataInicio), Main.converteParaCalendar(dataFinal), modificador, tipoDeEstrategia, descricao);
	}
	/**
	 * Getter do inicio do periodo dado pela estratégia.
	 * @return
	 * O inicio do período dado pela estratégia.
	 */
	public DateTime getInicioPeriodo() {
		return inicioPeriodo;
	}
	/**
	 * Método para se ter o inicio do periodo formatado em String.
	 * @return
	 * O inicio do periodo dado pela estratégia, formatado em uma String no formato (dd/MM)
	 */
	public String getInicioPeriodoString(){
		atualizaIntervalo();
		DateTimeFormatter formatador = DateTimeFormat.forPattern("dd/MM");
		return formatador.print(inicioPeriodo);
		
	}
	/**
	 * Setter do inicio do periodo da estratégia.
	 * @param inicioPeriodo
	 * O início do período da estratégia.
	 */
	public void setInicioPeriodo(DateTime inicioPeriodo) {
		this.inicioPeriodo = inicioPeriodo;
	}
	/**
	 * Getter do final do período da estratégia.
	 * @return
	 * O final do período da estratégia.
	 */
	public DateTime getFinalPeriodo() {
		return finalPeriodo;
	}
	 /**
   * Método para se ter o final do periodo formatado em String.
   * @return
   * O final do periodo dado pela estratégia, formatado em uma String no formato (dd/MM)
   */
	public String getFinalPeriodoString(){
		atualizaIntervalo();
		DateTimeFormatter formatador = DateTimeFormat.forPattern("dd/MM");
		return formatador.print(finalPeriodo);
	}
	 /**
   * Setter do final do periodo da estratégia.
   * @param finalPeriodo
   * O final do período da estratégia.
   */
	public void setFinalPeriodo(DateTime finalPeriodo) {
		this.finalPeriodo = finalPeriodo;
	}
	/**
	 * Getter do Tipo de estratégia.
	 * @return
	 * Um TipoDeEstrategia (enum) relevante
	 */
	public TipoDeEstrategia getTipoDeEstrategia() {
		return tipoDeEstrategia;
	}
	/**
	 * Getter do objeto Period periodo relevante a estratégia.
	 * @return
	 * O objeto com o período da estratégia.
	 */
	public Period getPeriodo() {
		return periodo;
	}
	/**
	 * Getter do modificador da estratégia. 
	 * @return
	 * Um double na faixa de 1 - 100
	 */
	public double getModificador() {
		return modificador;
	}
	/**
	 * Getter da descrição da estratégia.
	 * @return
	 * A descrição da estratégia.
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * Método que retorna o multiplicador final (levando em conta o tipoDeEstrategia) para ser usado no cálculo dos contratos.
	 * @return
	 * O multiplicador final.
	 */
	public double getModificadorPreco(double preco){
		return (((modificador / 100.0) * tipoDeEstrategia.getMultiplicador()) * preco);
	}
	/**
	 * Método que verifica se o tempo presente está incluso na estratégia.
	 * @return
	 * True, se o presente estiver na estratégia.
	 */
	public boolean periodoContemPresente(){
		atualizaIntervalo();
		return intervalo.containsNow();
	}
	public boolean periodoContemIntervalo (ReadableInstant instante){
	  int ano = instante.get(DateTimeFieldType.year()); //O ano do instante a ser comparado.
	  
	  inicioPeriodo = new DateTime(getInicioPeriodo()).withYear(ano); //Setando o início do período da estratégia para o ano do instante.
    
    finalPeriodo = new DateTime(getFinalPeriodo()).withYear(ano); //Setando o final do período da estratégia para o ano do instante.
    
    if (finalPeriodo.isBefore(inicioPeriodo)){ 
      /*
       * Com esse ajuste, pode ser que o final do período fique antes do início, exemplo, se o ano do intervalo for 2018:
       * 21/12/2015 até 01/01/2016 -> 21/12/2018 até 01/01/2018.
       * Se esse for o caso, adiciona +1 no campo ano do final do período.
       */
     finalPeriodo = finalPeriodo.plusYears(1);
    }
    
    intervalo = new Interval(inicioPeriodo, finalPeriodo); //Re-criando o intervalo com as datas modificadas.
	  
	  return intervalo.contains(instante);
	}
	public int compareTo(Estrategia outraEstrategia){
		this.atualizaIntervalo();
		outraEstrategia.atualizaIntervalo();
		return this.inicioPeriodo.compareTo(outraEstrategia.getInicioPeriodo());
	}
	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Estrategia)){
			return false;
		}Estrategia outraEstrategia = (Estrategia) obj;
		atualizaIntervalo();
		outraEstrategia.atualizaIntervalo();
		return (this.getInicioPeriodoString().equals(outraEstrategia.getInicioPeriodoString()) && this.getFinalPeriodoString().equals(outraEstrategia.getFinalPeriodoString()) && this.descricao.equals(outraEstrategia.getDescricao()) && this.modificador == outraEstrategia.getModificador() && this.getTipoDeEstrategia() == outraEstrategia.getTipoDeEstrategia());
	}
	/**
	 * Método que verifica se duas estratégias se sobrepoem.
	 * @param outraEstrategia
	 * Outra estratégia
	 * @return
	 * True se ouver sobreposição.
	 */
	public boolean sobrepoe(Estrategia outraEstrategia){
		this.atualizaIntervalo();
		outraEstrategia.atualizaIntervalo();
		return this.intervalo.overlaps(outraEstrategia.getIntervalo());
	}
	public boolean contratoSobrepoe(Contrato contrato){
	  /*
	   * O objetivo todo de se definir uma estratégia envolve não colocar um ano nela. Ex: Não se diz que uma estratégia vai de 01/01/2015 até 02/02/2015, se diz que ela vai de 01/01 até 02/02, todo ano.
	   * Mas não é criar uma instância de Period só com uma data e mês, o seguinte "workaround" foi utilizado:
	   * - Ao se criar uma estratégia, ela fica com o ano que está no sistema.
	   * - Quando se quer comparar se um contrato está em um período definido por uma estratégia, o Period se "re-inicializa", mantendo o dia e mês (que nunca mudará) mas usando o ano do contrato que foi passado.
	   * No exemplo dado, se formos comparar a estratégia de 01/01 até 02/02 com um contrato de 2016, o Period vai ser "re-criado" com o ano de 2016.
	   */
		inicioPeriodo = new DateTime(getInicioPeriodo()).withYear(contrato.getDataCheckIn().get(Calendar.YEAR));
		
		finalPeriodo = new DateTime(getFinalPeriodo()).withYear(contrato.getDataCheckOut().get(Calendar.YEAR));
		
		if (finalPeriodo.isBefore(inicioPeriodo)){
     finalPeriodo = finalPeriodo.plusYears(1);
    }
		
		intervalo = new Interval(inicioPeriodo, finalPeriodo);
		
		// Abaixo se consegue um Interval com o checkIn e checkOut do contrato, para fins de comparação.
		
		Interval intervaloContrato = new Interval(new DateTime(contrato.getDataCheckIn()).withTimeAtStartOfDay(), new DateTime(contrato.getDataCheckOut()).withTimeAtStartOfDay());
				
		boolean retorno = intervalo.overlaps(intervaloContrato);
		
		atualizaIntervalo(); //Método que retorna o Interval para o ano do sistema.
		
		return retorno;
	}
	/**
	 * Método para uso interno, setando o ano do Interval para o ano do sistema, para fins de comparação.
	 */
	private void atualizaIntervalo(){
		inicioPeriodo = new DateTime(getInicioPeriodo()).withYear(Calendar.getInstance().get(Calendar.YEAR)); //Setando o início do período da estratégia para o ano do instante.
		finalPeriodo = new DateTime(getFinalPeriodo()).withYear(Calendar.getInstance().get(Calendar.YEAR)); //Setando o final do período da estratégia para o ano do instante.
		
		if (finalPeriodo.isBefore(inicioPeriodo)){
		  /*
       * Com esse ajuste, pode ser que o final do período fique antes do início, exemplo, se o ano do intervalo for 2018:
       * 21/12/2015 até 01/01/2016 -> 21/12/2018 até 01/01/2018.
       * Se esse for o caso, adiciona +1 no campo ano do final do período.
       */
      finalPeriodo = finalPeriodo.plusYears(1);
    }
		
		intervalo = new Interval(inicioPeriodo, finalPeriodo); //Re-criando o intervalo com as datas modificadas.
	}
	@Override
	public String toString(){
		String retorno = "";
		retorno += getDescricao();
		retorno += "\nDe " + getInicioPeriodoString() + " até " + getFinalPeriodoString();
		retorno += "\n" + getTipoDeEstrategia().toString() + " de " + getModificador() + "%";
		return retorno;
	}
	
}
