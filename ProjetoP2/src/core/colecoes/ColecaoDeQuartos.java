package core.colecoes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import core.Contrato;
import core.Quarto;
import core.QuartoExecutivoDuplo;
import core.QuartoExecutivoSimples;
import core.QuartoExecutivoTriplo;
import core.QuartoLuxoDuplo;
import core.QuartoLuxoSimples;
import core.QuartoLuxoTriplo;
import core.QuartoPresidencial;
import core.Reserva;

public class ColecaoDeQuartos {
	@SuppressWarnings("unused")
	private static final int VAGOS_PRIMEIRO = -1;
	@SuppressWarnings("unused")
	private static final int OCUPADOS_PRIMEIRO = 1;
	private List<Quarto> listaQuartos = new ArrayList<Quarto>();
	/**
	 * Método para estabelecer a lista padrão com os quartos, nos seus status padrão.
	 */
	public void criaQuartos(){
		Quarto quartoParaAdicionar;
		int numeroQuarto = 1;
		try{
			for (int i = 0; i < 5; i++){
				quartoParaAdicionar = new QuartoPresidencial(numeroQuarto);
				listaQuartos.add(quartoParaAdicionar);
				numeroQuarto++;
			}for (int i = 0; i < 5; i++){
				quartoParaAdicionar = new QuartoLuxoSimples(numeroQuarto);
				listaQuartos.add(quartoParaAdicionar);
				numeroQuarto++;
			}for (int i = 0; i < 15; i++){
				quartoParaAdicionar = new QuartoLuxoDuplo(numeroQuarto);
				listaQuartos.add(quartoParaAdicionar);
				numeroQuarto++;
			}for (int i = 0; i < 20; i++){
				quartoParaAdicionar = new QuartoLuxoTriplo(numeroQuarto);
				listaQuartos.add(quartoParaAdicionar);
				numeroQuarto++;
			}for (int i = 0; i < 5; i++){
				quartoParaAdicionar = new QuartoExecutivoSimples(numeroQuarto);
				listaQuartos.add(quartoParaAdicionar);
				numeroQuarto++;
			}for (int i = 0; i < 15; i++){
				quartoParaAdicionar = new QuartoExecutivoDuplo(numeroQuarto);
				listaQuartos.add(quartoParaAdicionar);
				numeroQuarto++;
			}for (int i = 0; i < 20; i++){
				quartoParaAdicionar = new QuartoExecutivoTriplo(numeroQuarto);
				listaQuartos.add(quartoParaAdicionar);
				numeroQuarto++;
			}

		}catch (Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	public List<Quarto> getListaQuartos() {
		return listaQuartos;
	}
	/**
	 * Método que retorna o número de quartos livres.
	 * @return
	 * O número de quartos livres.
	 */
	public int getNumeroQuartosLivres(){
		int numQuartosLivres = 0;
		for (Quarto quarto: listaQuartos){
			if (quarto.getEstado().equals("VAGO")){
				numQuartosLivres++;
			}
		}return numQuartosLivres;
	}
	
	/**
	 * Método que retorna o número de quartos ocupados.
	 * @return
	 * O número de quartos ocupados.
	 */
	public int getNumeroQuartosOcupados(){
		int numQuartosOcupados = 0;
		for (Quarto quarto: listaQuartos){
			if (quarto.getEstado().equals("OCUPADO")){
				numQuartosOcupados++;
			}
		}return numQuartosOcupados; 
	}
	/**
	 * Método que retorna uma List<Quarto> com todos os quartos vagos.
	 * @return
	 * List<Quarto> com todos os quartos vagos
	 */
	public List<Quarto> getListaQuartosVagos(){
		List<Quarto> listaQuartosVagos = new ArrayList<Quarto>();
		for (Quarto quarto: listaQuartos){
			if (quarto.getEstado().equals("VAGO")){
				listaQuartosVagos.add(quarto);
			}
		}return listaQuartosVagos;
	}
	public List<Quarto> getListaQuartosVagosReserva(Contrato contrato){
		List<Quarto> listaQuartosVagos = new ArrayList<Quarto>();
		for (Quarto quarto: listaQuartos){
			if (quarto.isLivreParaReserva(new Reserva(contrato.getDataCheckIn(), contrato.getDataCheckOut()).getIntervalo())){
				listaQuartosVagos.add(quarto);
			}
		}return listaQuartosVagos;
	}
	
	/**
	 * Método que retorna uma List<Quarto> com todos os quartos ocupados.
	 * @return
	 * List<Quarto> com todos os quartos ocupados.
	 */
	public List<Quarto> getListaQuartosOcupados(){
		List<Quarto> listaQuartosOcupados = new ArrayList<Quarto>();
		for (Quarto quarto: listaQuartos){
			if (quarto.getEstado().equals("OCUPADO")){
				listaQuartosOcupados.add(quarto);
			}
		}return listaQuartosOcupados;
	}
	public List<Quarto> getListaQuartosOcupadosReserva(Contrato contrato){
		List<Quarto> listaQuartosOcupados = new ArrayList<Quarto>();
		for (Quarto quarto: listaQuartos){
			if (!quarto.isLivreParaReserva(new Reserva(contrato.getDataCheckIn(), contrato.getDataCheckOut()).getIntervalo())){
				listaQuartosOcupados.add(quarto);
			}
		}return listaQuartosOcupados;
	}
	/**
	 * Método para pesquisar um quarto pelo seu número
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

	/**
	 * Método para ordenar os quartos em relação ao seu estado (Vago/Ocupado)
	 * @param modoDeOrdenacao
	 * ColecaoDeQuartos.VAGOS_PRIMEIRO ou ColecaoDeQuartos.OCUPADOS_PRIMEIRO
	 */
	public void sortQuartosStatus(int modoDeOrdenacao) {
		for (int fixo = 0; fixo < listaQuartos.size() - 1; fixo++) {
			int menor = fixo;
			//Se jogar um compareTo() entre as Strings "VAGO" e "OCUPADO", os quartos com "OCUPADO" ficariam na frente normalmente, daí o modificador pedido no construtor
			for (int i = menor + 1; i < listaQuartos.size(); i++){
				if ((listaQuartos.get(i).getEstado().compareTo(listaQuartos.get(menor).getEstado()) * modoDeOrdenacao) > 0){// Selecionando o que vai vir antes
					menor = i;
			}
	}
			if (menor != fixo) {
	                        Quarto q = listaQuartos.get(fixo);
	                        listaQuartos.set(fixo, listaQuartos.get(menor));
	                        listaQuartos.set(menor, q);
			}
		}
	}
}
