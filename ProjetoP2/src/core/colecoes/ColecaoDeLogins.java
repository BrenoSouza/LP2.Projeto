package core.colecoes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import core.Hospede;
import core.Login;

public class ColecaoDeLogins implements Serializable{

	private static final long serialVersionUID = 202762263316350339L;
	
	private List<Login> listaContasLogin = new ArrayList<Login>();
	
	
	/**
	 * Retorna a lista de Contas da colecao
	 * @return Um List<ContaLogin>
	 */
	public List<Login> getListaContasLogin() {
		return listaContasLogin;
	}
	/**
	 * Informa o tamanho da lista da colecao/numero de Contas
	 * @return Um int com o tamanho da lista
	 */
	public int getTamanhoListaLogin() {
		return this.getListaContasLogin().size();
	}
	/**
	 * Adiciona um ContaLogin na colecao
	 * @param ContaLogin A conta a ser adicionada
	 * @throws Exception Caso atinga as exceções da classe ContaLogin
	 */
	public void adicionaContaLogin(Login ContaLogin) throws Exception{
		if (ContaLogin == null || listaContasLogin.contains(ContaLogin)){
			throw new Exception("O ContaLogin não foi adicionado.");
		}
		listaContasLogin.add(ContaLogin);
	}
	
	/**
	 * Remove uma Conta da colecao
	 * @param ContaLogin A conta a ser removida
	 * @return True - se foi removido / False - se houve algum problema
	 */
	public boolean removeContaLogin(Hospede ContaLogin){
		return listaContasLogin.remove(ContaLogin);
	}
	
	public boolean verificaLoginESenha(String login, String senha) {
		for (int i = 0; i < listaContasLogin.size(); i++) {
			if (listaContasLogin.get(i).getLogin().equals(login)) {
				if (listaContasLogin.get(i).getSenha().equals(senha)) {
					return true;
				}
				return false;
			}
		}
		return false;
	}
	
}
