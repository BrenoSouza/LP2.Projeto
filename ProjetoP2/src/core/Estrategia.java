package core;

import gui.Main;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
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
	 * @param modificador.
	 * O modificador, de 0-100;
	 * @param tipoDeEstrategia
	 * Estrategia.ACRESCIMO ou Estrategia.DECRESCIMO.
	 * @throws IllegalArgumentException
	 * Se o modificador não estiver em formato correto. 
	 */
	public Estrategia(DateTime inicioPeriodo, DateTime finalPeriodo, double modificador, TipoDeEstrategia tipoDeEstrategia, String descricao) throws IllegalArgumentException{
		if (modificador < 0 || modificador > 100){
			throw new IllegalArgumentException("Modificador em formato inválido (menor que 0 ou maior que 100).");
		}if (descricao.isEmpty()){
			throw new IllegalArgumentException("Insira alguma descrição para a estratégia.");
		}if (inicioPeriodo.isAfter(finalPeriodo)){
			throw new IllegalArgumentException("Datas inválidas");
		}
		this.inicioPeriodo = inicioPeriodo;
		this.finalPeriodo = finalPeriodo;
		periodo = new Period(inicioPeriodo, finalPeriodo);
		this.modificador = modificador;
		this.tipoDeEstrategia = tipoDeEstrategia;
		this.descricao = descricao;
		this.intervalo = new Interval(inicioPeriodo, finalPeriodo);
	}
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
	 * @throws ParseException
	 * Se a data não estiver em formato correto.
	 * @throws IllegalArgumentException
	 * Se o modificador não estiver em formato correto.
	 */
	public Estrategia(String dataInicio, String dataFinal, double modificador, TipoDeEstrategia tipoDeEstrategia, String descricao) throws Exception{
		this(Main.converteParaCalendar(dataInicio), Main.converteParaCalendar(dataFinal), modificador, tipoDeEstrategia, descricao);
	}
	public DateTime getInicioPeriodo() {
		return inicioPeriodo;
	}
	public String getInicioPeriodoString(){
		atualizaIntervalo();
		DateTimeFormatter formatador = DateTimeFormat.forPattern("dd/MM");
		return formatador.print(inicioPeriodo);
		
	}
	public void setInicioPeriodo(DateTime inicioPeriodo) {
		this.inicioPeriodo = inicioPeriodo;
	}
	public DateTime getFinalPeriodo() {
		return finalPeriodo;
	}
	public String getFinalPeriodoString(){
		atualizaIntervalo();
		DateTimeFormatter formatador = DateTimeFormat.forPattern("dd/MM");
		return formatador.print(finalPeriodo);
	}
	public void setFinalPeriodo(DateTime finalPeriodo) {
		this.finalPeriodo = finalPeriodo;
	}
	public TipoDeEstrategia getTipoDeEstrategia() {
		return tipoDeEstrategia;
	}
	public Period getPeriodo() {
		return periodo;
	}
	public double getModificador() {
		return modificador;
	}
	public String getDescricao() {
		return descricao;
	}
	/**
	 * Método que retorna o multiplicador final (levando em conta o tipoDeEstrategia) para ser usado no cálculo dos contratos.
	 * @return
	 * O multiplicador final.
	 */
	public double getModificadorPreco(double preco){
		return ((modificador / 100 * tipoDeEstrategia.getMultiplicador()) * preco);
	}
	public boolean periodoContemPresente(){
		atualizaIntervalo();
		return intervalo.containsNow();
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
		return (this.inicioPeriodo.equals(outraEstrategia.getInicioPeriodo()) && this.finalPeriodo.equals(outraEstrategia.getFinalPeriodo()) && this.descricao.equals(outraEstrategia.getDescricao()) && this.modificador == outraEstrategia.getModificador());
	}
	public boolean sobrepoe(Estrategia outraEstrategia){
		this.atualizaIntervalo();
		outraEstrategia.atualizaIntervalo();
		return this.intervalo.overlaps(outraEstrategia.getIntervalo());
	}
	public boolean contratoSobrepoe(Contrato contrato){
		inicioPeriodo = new DateTime(getInicioPeriodo()).withYear(contrato.getDataCheckIn().get(Calendar.YEAR));
		
		finalPeriodo = new DateTime(getFinalPeriodo()).withYear(contrato.getDataCheckOut().get(Calendar.YEAR));
		
		intervalo = new Interval(inicioPeriodo, finalPeriodo);
		
		Interval intervaloContrato = new Interval(new DateTime(contrato.getDataCheckIn()), new DateTime(contrato.getDataCheckOut()));
		
		boolean retorno = intervalo.overlaps(intervaloContrato);
		atualizaIntervalo();
		return retorno;
	}
	private void atualizaIntervalo(){
		inicioPeriodo = new DateTime(getInicioPeriodo()).withYear(Calendar.getInstance().get(Calendar.YEAR));
		finalPeriodo = new DateTime(getFinalPeriodo()).withYear(Calendar.getInstance().get(Calendar.YEAR));
		intervalo = new Interval(inicioPeriodo, finalPeriodo);
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
