package core;

import gui.Main;

import java.text.ParseException;
import java.util.Calendar;

import org.joda.time.DateTime;
import org.joda.time.Period;

public class Estrategia {
	
	public static final TipoDeEstrategia ACRESCIMO = TipoDeEstrategia.ACRESCIMO;
	public static final TipoDeEstrategia DECRESCIMO = TipoDeEstrategia.DECRESCIMO;
	private DateTime inicioPeriodo;
	private DateTime finalPeriodo;
	private Period periodo;
	private double modificador;
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
	public Estrategia(DateTime inicioPeriodo, DateTime finalPeriodo, double modificador, TipoDeEstrategia tipoDeEstrategia) throws IllegalArgumentException{
		if (modificador < 0 || modificador > 100){
			throw new IllegalArgumentException("Modificador em formato inválido (menor que 0 ou maior que 100).");
		}
		this.inicioPeriodo = inicioPeriodo;
		this.finalPeriodo = finalPeriodo;
		periodo = new Period(inicioPeriodo, finalPeriodo);
		this.modificador = modificador;
		this.tipoDeEstrategia = tipoDeEstrategia;
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
	public Estrategia(Calendar inicioPeriodo, Calendar finalPeriodo, double modificador, TipoDeEstrategia tipoDeEstrategia) throws IllegalArgumentException{
		this(new DateTime(inicioPeriodo), new DateTime(finalPeriodo), modificador, tipoDeEstrategia);
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
	public Estrategia(String dataInicio, String dataFinal, double modificador, TipoDeEstrategia tipoDeEstrategia) throws Exception{
		this(Main.converteParaCalendar(dataInicio), Main.converteParaCalendar(dataFinal), modificador, tipoDeEstrategia);
	}
	public DateTime getInicioPeriodo() {
		return inicioPeriodo;
	}
	public void setInicioPeriodo(DateTime inicioPeriodo) {
		this.inicioPeriodo = inicioPeriodo;
	}
	public DateTime getFinalPeriodo() {
		return finalPeriodo;
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
	public void setPeriodo(Period periodo) {
		this.periodo = periodo;
	}
	public double getModificador() {
		return modificador;
	}
	public void setModificador(double modificador) {
		this.modificador = modificador;
	}
	/**
	 * Método que retorna o multiplicador final (levando em conta o tipoDeEstrategia) para ser usado no cálculo dos contratos.
	 * @return
	 * O multiplicador final.
	 */
	public double getModificadorPreco(double preco){
		return ((modificador / 100 * tipoDeEstrategia.getMultiplicador()) * preco);
	}
	
}
