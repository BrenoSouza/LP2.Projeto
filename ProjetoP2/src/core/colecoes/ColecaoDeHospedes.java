package core.colecoes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import core.Hospede;
import core.ParametrosInvalidosException;

public class ColecaoDeHospedes implements Serializable{

	private static final long serialVersionUID = 4378311643213774791L;
	private List<Hospede> listaHospedes = new ArrayList<Hospede>();
	/**
	 * Retorna a lista de hospede da colecao.
	 * @return Uma lista de hóspedes
	 */
	public List<Hospede> getListaHospedes() {
		return listaHospedes;
	}
	/**
	 * Informa o tamanho da lista da colecao/numero de hospedes.
	 * @return Um int com o tamanho da lista
	 */
	public int getListaHospedeTamanho() {
		return this.getListaHospedes().size();
	}
	/**
	 * Adiciona um hospede na colecao.
	 * @param hospede O hospede a ser adicionado
	 * @throws Exception Caso atinga as exceções da classe hospede
	 */
	public void adicionaHospede(Hospede hospede) throws ParametrosInvalidosException{
		if (hospede == null || listaHospedes.contains(hospede)){
			throw new ParametrosInvalidosException("O hospede já existe.");
		}
		listaHospedes.add(hospede);
	}
	/**
	 * Adiciona uma lista de hospedes a colecao.
	 * @param hospedes Um List<hospede>
	 * @throws ParametrosInvalidosException Caso atinga as exceções da classe hospede
	 */
	public void adicionaListaHospedes(List<Hospede> hospedes) throws ParametrosInvalidosException{
		if (hospedes == null || hospedes.contains(null) || listaHospedes.containsAll(hospedes)){
			throw new ParametrosInvalidosException("Os hospedes não foram adicionados.");
		}
		listaHospedes.addAll(hospedes);
	}
	/**
	 * Remove um hospede da colecao.
	 * @param hospede O hospede a ser removido
	 * @return True - se foi removido / False - se houve algum problema
	 */
	public boolean removeHospede(Hospede hospede){
		return listaHospedes.remove(hospede);
	}
	/**
	 * Remove uma lista de hospedes da colecao.
	 * @param hospedes Um List<hospede>
	 * @return True - se removeu algum hospede da colecao / False - se não removeu nenhum hospede
	 */
	public boolean removeHospedes(List<Hospede> hospedes){
		boolean removeu = false;
		for (Hospede hospede: hospedes){
			if (listaHospedes.contains(hospede)){
				listaHospedes.remove(hospede);
				removeu = true;
			}
		}
		return removeu;
	}
	/**
	 * Informa o hospede localizado em certo indice da colecao.
	 * @param i Um inteiro informando o indice
	 * @return Um hospede indice i da colecao
	 */
	public Hospede getIndice(int i){
		return listaHospedes.get(i);
	}
	/**
	 * Pesquisa hospedes na colecao.
	 * @param cpf O cpf para localizar o hospede
	 * @return O hospede encontrado ou null se nada for encontrado
	 */
	public Hospede pesquisar(String cpf) {
		for (Hospede hospede: getListaHospedes()){
			if(hospede.getCpf().equals(cpf)){
				return hospede;
			}
		}
		return null;
	}
	/**
	 * Pesquisa hospedes que possuem contrato.
	 * @param status Contrato ABERTO ou FECHADO
	 * @return Um List<Hospedes> com os hospedes em um tipo de contrato desejado.
	 */
	public List<Hospede> pesquisaHospedeContrato(String status) {
		List<Hospede> pesquisados = new ArrayList<Hospede>();
		if (status == null) {
			for (Hospede h: listaHospedes) {
				if (h.getContratoLigado() == null) {
					pesquisados.add(h);
				}
			}
		}else{
			for (Hospede h: listaHospedes) {
				if (h.getContratoLigado() != null && h.getContratoLigado().getStatus().equals(status)){
					pesquisados.add(h);
				}
			}
		}
		return pesquisados;
	}

}
