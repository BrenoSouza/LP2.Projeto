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
//		tableContratos.addFocusListener(new FocusAdapter() {  Método que deseleciona a seleção se o usuário clicar fora da tabela, ainda em construção.
//			@Override
//			public void focusLost(FocusEvent arg0) {
//				tableContratos.getSelectionModel().clearSelection();
//			}
//		});
		// INÍCIO DE CONSTRUÇÃO DA TABELA
		// designTabela = o conteúdo da tabela em si, preenchida através de um loop for.
				Object[][] designTabela = new Object[listaContratos.size()][5];
				for (int i = 0; i < listaContratos.size(); i++){
					Contrato contratoAtual = listaContratos.get(i);
					// Para preencher a primeira linha da tabela, com o nome do Hóspede Principal.
					if (contratoAtual.getHospedePrincipal() == null){
						designTabela[i][0] = "Não especificado";
					}else{
						designTabela[i][0] = contratoAtual.getHospedePrincipal().getNome();
					}
					// Para conseguir uma String formatada com a data do checkin, através de um método na classe Main.
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
			// FIM DE CONSTRUÇÃO DE TABELA.
			
		}
				//GAMBIARRA PARA QUE O USUÁRIO NÃO POSSA EDITAR OS DADOS DA TABELA
				@SuppressWarnings("serial")
				DefaultTableModel modeloTabela = new DefaultTableModel(designTabela, new String[] {
						"Hóspede principal", "Data de Check-In", "Data de Check-Out", "Despesas Atuais", "Status"
				}) {

					@Override
				    public boolean isCellEditable(int row, int column) {
				        //Esse método pegaria um índice para ver se o usuário pode editar certa parte da tabela. Como não é necessário no nosso uso, ele sempre vai retornar false
				        return false;
				    }
				};

	
		tableContratos.setModel(modeloTabela); // USANDO O MODELO ALTERADO PELA 'GAMBIARRA'
		tableContratos.setRowSelectionAllowed(true); // Quando der clique, selecionar toda a linha, e não só uma célula
		//CRIANDO UMA AÇÃO PRA QUANDO UMA LINHA FOR SELECIONADA
				ListSelectionModel modeloSelecaoLinha = tableContratos.getSelectionModel(); // SINGLE_SELECTION = Selecionar só uma opção de vez
				
				modeloSelecaoLinha.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				modeloSelecaoLinha.addListSelectionListener(new ListSelectionListener() {
					//Necessita ser esse nome de método para funcionar
					public void valueChanged(ListSelectionEvent e) {
						int[] indiceSelecionado = tableContratos.getSelectedRows(); // getSelectedRows() retorna uma array de int com os índices da lista dos objetos selecionados. Como nessa tabela só se seleciona uma opção de cada vez, sempre terá só um elemento essa array.
						if (indiceSelecionado.length <= 0){
							contratoSelecionado = null;
						}else{
							// Aqui é uma gambiarra mais complicada: java não permite que eu use o listaContratos (ou qualquer outra variável não final) dentro de um método do construtor, como é esse. Para solucionar isso, optei pela gambiarra de só usar esse índice em um método fora do construtor, setContratoSelecionado, que consegue usar as variáveis sem problemas.
							setContratoSelecionado(indiceSelecionado[0]);
						}atualizaBotoes();
						
					}
				});
		//O event acima se refere a como o programa vai lidar quando o usuário clica em uma linha da tabela.
		
		scrollPanePrincipal.setViewportView(tableContratos);
		
		
		scrollPanePrincipal.setRowHeaderView(table);
		getContentPane().setLayout(groupLayout);

	}
	public void setContratoSelecionado(int indice){
		// Fim da gambiarra. Como estou em outro método, posso usar variáveis não finais a vontade sem problema.
		contratoSelecionado = listaContratos.get(indice); // Lembrando que a tabela está na mesma ordem que a listaContratos, então os índices são os mesmos.
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
