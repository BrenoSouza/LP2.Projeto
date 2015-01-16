package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import classes.Contrato;
import classes.Hospede;
import classes.Quarto;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PainelContratos extends JInternalFrame {
	private final JScrollPane scrollPanePrincipal = new JScrollPane();
	private JTable tableContratos;
	private List<Contrato> listaContratos = new ArrayList<Contrato>();
	private final SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
	private JTable table;
	private Contrato contratoSelecionado = null;
	private JButton btnVisualizar;
	private JButton btnEditar;
	private JButton btnNovo;


	/**
	 * Create the frame.
	 */
	public PainelContratos(List<Contrato> listaContratos){
		try{
		Contrato teste = new Contrato(new ArrayList<Quarto>(), new ArrayList<Hospede>(), 5, Calendar.getInstance());
		listaContratos.add(teste);
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		this.listaContratos = listaContratos;
		setResizable(true);
		setFrameIcon(new ImageIcon(PainelContratos.class.getResource("/resources/contrato_icon.png")));
		setTitle("Contratos");
		setClosable(true);
		setBounds(100, 0, 752, 450);
		
		btnVisualizar = new JButton("Visualizar");
		btnVisualizar.setEnabled(false);
		
		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		
		btnNovo = new JButton("Novo");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPanePrincipal, GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnVisualizar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 229, Short.MAX_VALUE)
							.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(220)
							.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPanePrincipal, GroupLayout.PREFERRED_SIZE, 345, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVisualizar)
						.addComponent(btnNovo)
						.addComponent(btnEditar))
					.addContainerGap())
		);

		tableContratos = new JTable();
//		tableContratos.addFocusListener(new FocusAdapter() {  M�todo que deseleciona a sele��o se o usu�rio clicar fora da tabela, ainda em constru��o.
//			@Override
//			public void focusLost(FocusEvent arg0) {
//				tableContratos.getSelectionModel().clearSelection();
//			}
//		});
		// IN�CIO DE CONSTRU��O DA TABELA
		// designTabela = o conte�do da tabela em si, preenchida atrav�s de um loop for.
				Object[][] designTabela = new Object[listaContratos.size()][5];
				for (int i = 0; i < listaContratos.size(); i++){
					Contrato contratoAtual = listaContratos.get(i);
					// Para preencher a primeira linha da tabela, com o nome do H�spede Principal.
					if (contratoAtual.getHospedePrincipal() == null){
						designTabela[i][0] = "N�o especificado";
					}else{
						designTabela[i][0] = contratoAtual.getHospedePrincipal().getNome();
					}
					// Para conseguir uma String formatada com a data do checkin, atrav�s de um m�todo na classe Main.
					String dataFormatadaCheckIn = "";
					try{
						dataFormatadaCheckIn = Main.converteParaString(contratoAtual.getDataCheckIn());
					}catch (Exception e){
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					designTabela[i][1] = dataFormatadaCheckIn;
					// Para conseguir uma String formatada com a estimada data do checkout.
					String dataFormatadaCheckOut = "";
					try{
						dataFormatadaCheckOut = Main.converteParaString(contratoAtual.getDataCheckOut());
					}catch (Exception e){
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					designTabela[i][2] = dataFormatadaCheckOut;
					// Para colocar na tabela o total de despesas do contrato.
					designTabela[i][3] = contratoAtual.calculaPrecoFinal();
					// Para colocar na tabela o status do contrato.
					designTabela[i][4] = contratoAtual.getStatus();
			// FIM DE CONSTRU��O DE TABELA.
			
		}
				//GAMBIARRA PARA QUE O USU�RIO N�O POSSA EDITAR OS DADOS DA TABELA
				@SuppressWarnings("serial")
				DefaultTableModel modeloTabela = new DefaultTableModel(designTabela, new String[] {
						"H�spede principal", "Data de Check-In", "Data de Check-Out", "Despesas Atuais", "Status"
				}) {

					@Override
				    public boolean isCellEditable(int row, int column) {
				        //Esse m�todo pegaria um �ndice para ver se o usu�rio pode editar certa parte da tabela. Como n�o � necess�rio no nosso uso, ele sempre vai retornar false
				        return false;
				    }
				};

	
		tableContratos.setModel(modeloTabela); // USANDO O MODELO ALTERADO PELA 'GAMBIARRA'
		tableContratos.setRowSelectionAllowed(true); // Quando der clique, selecionar toda a linha, e n�o s� uma c�lula
		//CRIANDO UMA A��O PRA QUANDO UMA LINHA FOR SELECIONADA
				ListSelectionModel modeloSelecaoLinha = tableContratos.getSelectionModel(); // SINGLE_SELECTION = Selecionar s� uma op��o de vez
				
				modeloSelecaoLinha.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				modeloSelecaoLinha.addListSelectionListener(new ListSelectionListener() {
					//Necessita ser esse nome de m�todo para funcionar
					public void valueChanged(ListSelectionEvent e) {
						int[] indiceSelecionado = tableContratos.getSelectedRows(); // getSelectedRows() retorna uma array de int com os �ndices da lista dos objetos selecionados. Como nessa tabela s� se seleciona uma op��o de cada vez, sempre ter� s� um elemento essa array.
						if (indiceSelecionado.length <= 0){
							contratoSelecionado = null;
						}else{
							// Aqui � uma gambiarra mais complicada: java n�o permite que eu use o listaContratos (ou qualquer outra vari�vel n�o final) dentro de um m�todo do construtor, como � esse. Para solucionar isso, optei pela gambiarra de s� usar esse �ndice em um m�todo fora do construtor, setContratoSelecionado, que consegue usar as vari�veis sem problemas.
							setContratoSelecionado(indiceSelecionado[0]);
						}atualizaBotoes();
						
					}
				});
		//O event acima se refere a como o programa vai lidar quando o usu�rio clica em uma linha da tabela.
		
		scrollPanePrincipal.setViewportView(tableContratos);
		
		
		scrollPanePrincipal.setRowHeaderView(table);
		getContentPane().setLayout(groupLayout);

	}
	public void setContratoSelecionado(int indice){
		// Fim da gambiarra. Como estou em outro m�todo, posso usar vari�veis n�o finais a vontade sem problema.
		contratoSelecionado = listaContratos.get(indice); // Lembrando que a tabela est� na mesma ordem que a listaContratos, ent�o os �ndices s�o os mesmos.
	}
	public void atualizaBotoes(){
		if (contratoSelecionado == null){
			btnNovo.setEnabled(true);
			btnEditar.setEnabled(false);
			btnVisualizar.setEnabled(false);
		}else{
			btnNovo.setEnabled(false);
			btnEditar.setEnabled(true);
			btnVisualizar.setEnabled(true);
		}
	}
}
