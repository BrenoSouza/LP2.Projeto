package core.colecoes;

import java.util.ArrayList;
import java.util.List;

import core.Estrategia;

public class ColecaoDeEstrategias {
	
	private List<Estrategia> listaEstrategias = new ArrayList<Estrategia>();
	/**
	 * Método que retorna o tamanho da lista de estratégias.
	 * @return
	 * O tamanho da lista de estratégias.
	 */
	public int getListaEstrategiasTamanho(){
		return listaEstrategias.size();
	}
	/**
	 * Método para adicionar uma estratégia a lista de estratégias.
	 * @param estrategia
	 * Uma estratégia a ser adicionada.
	 * @throws Exception
	 * Se a estratégia for null, já existir na lista de estratégias ou sobrepoe um intervalo de outra estratégia.
	 */
	public void adicionaEstrategia(Estrategia estrategia) throws Exception{
		if (estrategia == null || listaEstrategias.contains(estrategia)){
			throw new Exception("A estratégia não foi adicionado");
		}
		for (Estrategia outraEstrategia: listaEstrategias){
			if (outraEstrategia.sobrepoe(estrategia)){
				throw new Exception("A estratégia inclui um período referente a outra estratégia existente");
			}
		}
		listaEstrategias.add(estrategia);
	}
	/**
	 * Método de remoção de estratégias.
	 * @param estrategia
	 * A estratégia a ser removida.
	 * @return
	 * true se a estratégia tiver sido removida
	 */
	public boolean removeEstrategia(Estrategia estrategia){
		return listaEstrategias.remove(estrategia);
	}
	/**
	 * Método para checar se existe alguma estratégia em vigor.
	 * @return
	 * Um objeto Estrategia contendo a estratégia em vigor, null se não houver nenhuma estratégia em vigor.
	 */
	public Estrategia estrategiaAtual(){
		for (Estrategia estrategia: listaEstrategias){
			if (estrategia.periodoContemPresente()){
				return estrategia;
			}
		}return null;
	}
	public Estrategia get(int i){
		return listaEstrategias.get(i);
	}
}
