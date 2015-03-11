package core.colecoes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import core.Contrato;
import core.ParametrosInvalidosException;
import core.Quarto;
import core.Reserva;
import core.TipoDeQuarto;

public class ColecaoDeQuartos implements Serializable {

	private static final long serialVersionUID = -589681074308782532L;
	private List<Quarto> listaQuartos = new ArrayList<Quarto>();
	/**
	 * Método para estabelecer a lista padrão com os quartos, nos seus status padrão.
	 */
	public void criaQuartos(){
		Quarto quartoParaAdicionar;
		int numeroQuarto = 1;
		try{
			for (int i = 0; i < 5; i++){ //Loop dos quartos presidenciais.
				quartoParaAdicionar = new Quarto(numeroQuarto, TipoDeQuarto.PRESIDENCIAL);
				listaQuartos.add(quartoParaAdicionar);
				numeroQuarto++;
			}for (int i = 0; i < 5; i++){ //Loop dos quartos luxo simples.
				quartoParaAdicionar = new Quarto(numeroQuarto, TipoDeQuarto.LUXOSIMPLES);
				listaQuartos.add(quartoParaAdicionar);
				numeroQuarto++;
			}for (int i = 0; i < 15; i++){ //Loop dos quartos luxo duplo.
				quartoParaAdicionar = new Quarto(numeroQuarto, TipoDeQuarto.LUXODUPLO);
				listaQuartos.add(quartoParaAdicionar);
				numeroQuarto++;
			}for (int i = 0; i < 20; i++){ //Loop dos quartos luxo triplo.
				quartoParaAdicionar = new Quarto(numeroQuarto, TipoDeQuarto.LUXOTRIPLO);
				listaQuartos.add(quartoParaAdicionar);
				numeroQuarto++;
			}for (int i = 0; i < 5; i++){ //Loop dos quartos executivo simples.
				quartoParaAdicionar = new Quarto(numeroQuarto, TipoDeQuarto.EXECUTIVOSIMPLES);
				listaQuartos.add(quartoParaAdicionar);
				numeroQuarto++;
			}for (int i = 0; i < 15; i++){ //Loop dos quartos executivo duplo.
				quartoParaAdicionar = new Quarto(numeroQuarto, TipoDeQuarto.EXECUTIVODUPLO);
				listaQuartos.add(quartoParaAdicionar);
				numeroQuarto++;
			}for (int i = 0; i < 20; i++){ //Loop dos quartos executivo triplo.
				quartoParaAdicionar = new Quarto(numeroQuarto, TipoDeQuarto.EXECUTIVOTRIPLO);
				listaQuartos.add(quartoParaAdicionar);
				numeroQuarto++;
			}

		}catch (ParametrosInvalidosException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	public List<Quarto> getListaQuartos() {
		return listaQuartos;
	}
/**
 * Método que retorna uma lista com os quartos vagos para aquele contrato.
 * @param contrato
 * Um contrato.
 * @return
 * Uma lista de quartos disponíveis para reserva para o contrato em questão.
 */
public List<Quarto> getListaQuartosVagosReserva(Contrato contrato){
		List<Quarto> listaQuartosVagos = new ArrayList<Quarto>();
		for (Quarto quarto: listaQuartos){
			if (quarto.isLivreParaReserva(new Reserva(contrato.getDataCheckIn(), contrato.getDataCheckOut()).getIntervalo())){
				listaQuartosVagos.add(quarto);
			}
		}return listaQuartosVagos;
	}
/**
 * Método que retorna uma lista com os quartos ocupados para aquele contrato.
 * @param contrato
 * Um contrato.
 * @return
 * Uma lista de quartos não disponíveis para reserva para o contrato em questão.
 */
	public List<Quarto> getListaQuartosOcupadosReserva(Contrato contrato){
		List<Quarto> listaQuartosOcupados = new ArrayList<Quarto>();
		for (Quarto quarto: listaQuartos){
			if (!quarto.isLivreParaReserva(new Reserva(contrato.getDataCheckIn(), contrato.getDataCheckOut()).getIntervalo())){
				listaQuartosOcupados.add(quarto);
			}
		}return listaQuartosOcupados;
	}
	/**
	 * Método para pesquisar um quarto pelo seu número.
	 * @param numeroQuarto
	 * O número do quarto
	 * @return
	 * O quarto especificado, pelo número. Se não houver, null.
	 */
	public Quarto pesquisarQuartoPorNumero(int numeroQuarto){
		for (Quarto quarto: listaQuartos){
			if (quarto.getNumero() == numeroQuarto){
				return quarto;
			}
		}return null;
	}
	/**
	 * Método que retorna um quarto via seu índice na lista.
	 * @param i
	 * O índice na lista.
	 * @return
	 * Quarto da lista no índice i
	 */
	public Quarto getIndice(int i){
		return listaQuartos.get(i);
	}
	/**
	 * Método que organiza os quartos pelo número, do maior para o menor.
	 */
	public void sortQuartosNumero(){
		Collections.sort(listaQuartos);
	}
}
