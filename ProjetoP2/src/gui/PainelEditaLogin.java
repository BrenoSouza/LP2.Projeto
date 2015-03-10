package gui;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;

import java.awt.CardLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import core.Login;
import core.colecoes.ColecaoDeLogins;

import javax.swing.JTable;
import javax.swing.JScrollPane;


public class PainelEditaLogin extends JInternalFrame {
  
  private static final long serialVersionUID = 58746164L;
  private CardLayout layout = new CardLayout();
  private JPanel panelExterno = new JPanel();
  private JButton btnCadastrarFuncionrio;
  private ColecaoDeLogins listaDeLogins;
  private PanelEditarLogin editaLogin = new PanelEditarLogin();
  private PanelCadastrarFuncionario cadastraFuncionario;
  private JPanel panelFuncionariosCadastrados;
  private JPanel panel;
  private JScrollPane scrollPane;
  private JTable tableLogins;
  private Login loginSelecionado;
  
  
  /**
   * Create the frame.
   */
  public PainelEditaLogin(ColecaoDeLogins listaDeLogins) {
    this.listaDeLogins = listaDeLogins;
    setClosable(true);
    cadastraFuncionario = new PanelCadastrarFuncionario(PainelEditaLogin.this.listaDeLogins);

    cadastraFuncionario.setSize(new Dimension(500, 330));
    editaLogin.setSize(new Dimension(470, 320));
    setBounds(100, 100, 782, 505);
    panelExterno.setBounds(207, 51, 518, 370);
    panelExterno.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    panelExterno.setLayout(layout);
    panelExterno.add(editaLogin, "editaLogin");
    editaLogin.setLayout(null);
    panelExterno.add(cadastraFuncionario, "cadastrarLogin");
        
    JButton btnEditarDados = new JButton("Editar Dados");
    btnEditarDados.setBounds(32, 89, 125, 25);
    btnEditarDados.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
        layout.show(panelExterno, "editaLogin");
        
      }
    });
    
    btnCadastrarFuncionrio = new JButton("Cadastrar");
    btnCadastrarFuncionrio.setBounds(32, 198, 125, 25);
    btnCadastrarFuncionrio.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        layout.show(panelExterno, "cadastrarLogin");
        
      }
    });
    
    panelFuncionariosCadastrados = new JPanel();
    panelExterno.add(panelFuncionariosCadastrados, "cadastrados");
    panelFuncionariosCadastrados.setLayout(null);
    
    panel = new JPanel();
    panel.setBounds(36, 59, 444, 181);
    panelFuncionariosCadastrados.add(panel);
    panel.setLayout(null);
    
    scrollPane = new JScrollPane();
    scrollPane.setBounds(0, 0, 444, 181);
    panel.add(scrollPane);
    
    tableLogins = new JTable();
    scrollPane.setColumnHeaderView(tableLogins);
    
    //Configurações da Tabela
    
    scrollPane.setViewportView(tableLogins);
    
    JButton btnRemover = new JButton("Remover");
    btnRemover.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      
        if (loginSelecionado.equals(PainelEditaLogin.this.listaDeLogins.getListaContasLogin().get(0))) {
          JOptionPane.showMessageDialog(null, "O Login do administrador não pode ser deletado!");
        }
        
        else if (PainelEditaLogin.this.listaDeLogins.removeContaLogin(loginSelecionado)) {
          JOptionPane.showMessageDialog(null, "Login Removido!");
          desenhaTabela();
       }
       else {
        JOptionPane.showMessageDialog(null, "Login não foi removido!");
        desenhaTabela();

       }
      }

    });
    btnRemover.setBounds(201, 263, 117, 25);
    panelFuncionariosCadastrados.add(btnRemover);
    ListSelectionModel modeloSelecaoLinha = tableLogins.getSelectionModel();
    
    modeloSelecaoLinha.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
    modeloSelecaoLinha.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        int[] indiceLogin = tableLogins.getSelectedRows(); 
        if (indiceLogin.length <= 0){
          indiceLogin = null;
        }else{
          tableLogins.clearSelection();
          setLoginSelecionado(indiceLogin[0]);
        }
        
      }
    });
    
    // Fim configurações da tabela;
    
    GroupLayout gl_panelExterno = new GroupLayout(panelExterno);
    gl_panelExterno.setHorizontalGroup(
      gl_panelExterno.createParallelGroup(Alignment.LEADING)
        .addGap(0, 775, Short.MAX_VALUE)
    );
    gl_panelExterno.setVerticalGroup(
      gl_panelExterno.createParallelGroup(Alignment.LEADING)
        .addGap(0, 387, Short.MAX_VALUE)
    );
    getContentPane().setLayout(null);
    getContentPane().add(btnEditarDados);
    getContentPane().add(btnCadastrarFuncionrio);
    getContentPane().add(panelExterno);
    
    JButton btnCadastrados = new JButton("Cadastrados");
    btnCadastrados.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        desenhaTabela();
        layout.show(panelExterno, "cadastrados");
        
      }
    });
    btnCadastrados.setBounds(32, 318, 125, 25);
    getContentPane().add(btnCadastrados);

  }
  
  private void setLoginSelecionado(int indice) {
    loginSelecionado = listaDeLogins.getListaContasLogin().get(indice);
  }

  private void desenhaTabela() {
    
    Object [][] designTabela;
    if(listaDeLogins.getTamanhoListaLogin() == 0) {
      designTabela = new Object[0][2];
    }
    else {
      int tamanhoTabela = listaDeLogins.getTamanhoListaLogin();
      designTabela = new Object[tamanhoTabela][2];
      
      for (int i = 0; i < tamanhoTabela; i++) {
        Login loginAtual = listaDeLogins.getListaContasLogin().get(i);
        designTabela[i][0] = loginAtual.getNome();
        designTabela[i][1] = loginAtual.getLogin();
      }
    }
    
    @SuppressWarnings("serial")
    DefaultTableModel modeloTabela = new DefaultTableModel(designTabela, new String[] {
        "Nome", "Login" })     {

      @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
    tableLogins.setModel(modeloTabela); 

    
    
  }
}
