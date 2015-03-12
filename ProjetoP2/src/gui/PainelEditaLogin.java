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
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;


public class PainelEditaLogin extends JInternalFrame {
  
  private static final long serialVersionUID = 58746164L;
  private CardLayout layout = new CardLayout();
  private JPanel panelExterno = new JPanel();
  private JButton btnCadastrarFuncionrio;
  private ColecaoDeLogins listaDeLogins;
  private PanelEditarLogin editaLogin = new PanelEditarLogin();
  private PanelCadastrarFuncionario cadastraFuncionario;
  private JPanel panelFuncionariosCadastrados;
  private JTable table;
  private Login loginSelecionado;
  private JTable tableLogin;
  
  
  /**
   * Create the frame.
   */
  public PainelEditaLogin(ColecaoDeLogins listaDeLogins) {
    this.listaDeLogins = listaDeLogins;
    setClosable(true);
    cadastraFuncionario = new PanelCadastrarFuncionario(PainelEditaLogin.this.listaDeLogins);

    cadastraFuncionario.setSize(new Dimension(500, 330));
    editaLogin.setSize(new Dimension(470, 320));
    setBounds(100, 100, 714, 467);
    panelExterno.setBounds(23, 69, 655, 338);
    panelExterno.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    panelExterno.setLayout(layout);
    panelExterno.add(editaLogin, "editaLogin");
    editaLogin.setLayout(null);
    panelExterno.add(cadastraFuncionario, "cadastrarLogin");
        
    JButton btnEditarDados = new JButton("Editar Dados");
    btnEditarDados.setIcon(new ImageIcon(PainelEditaLogin.class.getResource("/resources/editardados.png")));
    btnEditarDados.setBounds(429, 11, 161, 34);
    btnEditarDados.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
        layout.show(panelExterno, "editaLogin");
        
      }
    });
    
    btnCadastrarFuncionrio = new JButton("Cadastrar");
    btnCadastrarFuncionrio.setIcon(new ImageIcon(PainelEditaLogin.class.getResource("/resources/cadastrar.png")));
    btnCadastrarFuncionrio.setBounds(23, 11, 161, 34);
    btnCadastrarFuncionrio.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        layout.show(panelExterno, "cadastrarLogin");
        
      }
    });
    
    panelFuncionariosCadastrados = new JPanel();
    panelExterno.add(panelFuncionariosCadastrados, "cadastrados");
    
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
    
    JScrollPane scrollPane = new JScrollPane();
    
    JButton btnEditar = new JButton("Editar");
    GroupLayout gl_panelFuncionariosCadastrados = new GroupLayout(panelFuncionariosCadastrados);
    gl_panelFuncionariosCadastrados.setHorizontalGroup(
      gl_panelFuncionariosCadastrados.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_panelFuncionariosCadastrados.createSequentialGroup()
          .addGap(23)
          .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 601, GroupLayout.PREFERRED_SIZE)
          .addContainerGap(27, Short.MAX_VALUE))
        .addGroup(Alignment.TRAILING, gl_panelFuncionariosCadastrados.createSequentialGroup()
          .addGap(71)
          .addComponent(btnEditar)
          .addPreferredGap(ComponentPlacement.RELATED, 327, Short.MAX_VALUE)
          .addComponent(btnRemover, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
          .addGap(75))
    );
    gl_panelFuncionariosCadastrados.setVerticalGroup(
      gl_panelFuncionariosCadastrados.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_panelFuncionariosCadastrados.createSequentialGroup()
          .addGap(39)
          .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
          .addGap(18)
          .addGroup(gl_panelFuncionariosCadastrados.createParallelGroup(Alignment.BASELINE)
            .addComponent(btnRemover, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
            .addComponent(btnEditar))
          .addGap(41))
    );
    
    tableLogin = new JTable();
    scrollPane.setColumnHeaderView(tableLogin);
    panelFuncionariosCadastrados.setLayout(gl_panelFuncionariosCadastrados);
    
    ListSelectionModel modeloSelecaoLinha = tableLogin.getSelectionModel();
    modeloSelecaoLinha.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    modeloSelecaoLinha.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        int[] indiceLogin = tableLogin.getSelectedRows(); 
        if (indiceLogin.length <= 0){
          loginSelecionado = null;
        }else{
          tableLogin.clearSelection();
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
    btnCadastrados.setIcon(new ImageIcon(PainelEditaLogin.class.getResource("/resources/clientes_icon.png")));
    btnCadastrados.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        desenhaTabela();
        layout.show(panelExterno, "cadastrados");
        
      }
    });
    btnCadastrados.setBounds(231, 11, 161, 34);
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
    
    tableLogin.setModel(modeloTabela); 

    
    
  }
}
