package gui;

import javax.swing.JDesktopPane;
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

import core.Hospede;
import core.Login;
import core.colecoes.ColecaoDeLogins;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;


public class PainelUsuarios extends JInternalFrame {

  private static final long serialVersionUID = 58746164L;
  private CardLayout layout = new CardLayout();
  private JPanel panelExterno = new JPanel();
  private JDesktopPane painelPrincipal;
  private JButton btnCadastrarFuncionrio;
  private ColecaoDeLogins listaDeLogins;
  private PanelCadastrarFuncionario cadastraFuncionario;
  private PainelEditarUsuario PainelEditarUsuario;
  private JPanel panelFuncionariosCadastrados;
  private Login loginSelecionado;
  private JTable tableLogin;
  private JTextField textField;
  private JButton btnEditar;
  private JButton buttonPesquisa;
  private JButton buttonCancelaPesquisa;
  private List<Login> colecaoAtiva = new ArrayList<Login>();
 

  /**
   * Create the frame.
   */
  public PainelUsuarios(JDesktopPane painelPrincipal, ColecaoDeLogins listaDeLogins) {
    this.colecaoAtiva = listaDeLogins.getListaContasLogin();
    this.painelPrincipal = painelPrincipal;
    this.listaDeLogins = listaDeLogins;
    setClosable(true);

    cadastraFuncionario = new PanelCadastrarFuncionario(PainelUsuarios.this.listaDeLogins);
    cadastraFuncionario.setSize(new Dimension(500, 330));
    setBounds(100, 100, 714, 467);

    panelExterno.setBounds(23, 69, 655, 338);
    panelExterno.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    panelExterno.setLayout(layout);
    panelExterno.add(cadastraFuncionario, "cadastrarLogin");

    btnCadastrarFuncionrio = new JButton("Cadastrar");
    btnCadastrarFuncionrio.setIcon(new ImageIcon(PainelUsuarios.class.getResource("/resources/cadastrar.png")));
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

        if (loginSelecionado.equals(PainelUsuarios.this.listaDeLogins.getListaContasLogin().get(0))) {
          JOptionPane.showMessageDialog(null, "O Login do administrador não pode ser deletado!");
        }

        else if (PainelUsuarios.this.listaDeLogins.removeContaLogin(loginSelecionado)) {
          PainelUsuarios.this.listaDeLogins.removeContaLogin(loginSelecionado);
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

    btnEditar = new JButton("Editar");
    btnEditar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (PainelEditarUsuario == null || PainelEditarUsuario.isClosed()){
          PainelEditarUsuario = new PainelEditarUsuario(loginSelecionado);
          adicionaNoPainel(PainelEditarUsuario);
          PainelEditarUsuario.show();
        }else{
          PainelEditarUsuario.toFront();
        }
      }
    });

    textField = new JTextField();
    textField.setColumns(10);

    JLabel lblTodosOsUsurios = new JLabel("Usuários");
    lblTodosOsUsurios.setFont(new Font("Tahoma", Font.PLAIN, 13));

    buttonPesquisa = new JButton("");
    buttonPesquisa.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (!(textField.getText().isEmpty())) {
          List<Login> loginsPesquisados = new ArrayList<Login>();
          for (Login l: colecaoAtiva){
            if (l.getNome().toLowerCase().contains(textField.getText().toLowerCase())){
              loginsPesquisados.add(l);
            }
          }
          colecaoAtiva = loginsPesquisados;
          if (loginsPesquisados.size() == 0){
            JOptionPane.showMessageDialog(null, "Sem resultados.");
            desenhaTabela();
          }else{
            JOptionPane.showMessageDialog(null, (loginsPesquisados.size() >= 2) ? loginsPesquisados.size() + " usuários encontrados." : loginsPesquisados.size() + " usuário encontrado.");
            desenhaTabela();
          }
          buttonCancelaPesquisa.setEnabled(true);
        }
      }
    });
    buttonPesquisa.setIcon(new ImageIcon(PainelUsuarios.class.getResource("/resources/search.png")));

    buttonCancelaPesquisa = new JButton("");
    buttonCancelaPesquisa.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        textField.setText("");
        colecaoAtiva = PainelUsuarios.this.listaDeLogins.getListaContasLogin();
        desenhaTabela();
        buttonCancelaPesquisa.setEnabled(false);
      }
    });
    buttonCancelaPesquisa.setEnabled(false);
    buttonCancelaPesquisa.setToolTipText("Cancelar pesquisa( A tabela volta a ter todos os usuários).");
    buttonCancelaPesquisa.setIcon(new ImageIcon(PainelClientes.class.getResource("/resources/cross.png")));
    GroupLayout gl_panelFuncionariosCadastrados = new GroupLayout(panelFuncionariosCadastrados);
    gl_panelFuncionariosCadastrados.setHorizontalGroup(
        gl_panelFuncionariosCadastrados.createParallelGroup(Alignment.TRAILING)
        .addGroup(Alignment.LEADING, gl_panelFuncionariosCadastrados.createSequentialGroup()
            .addGap(23)
            .addGroup(gl_panelFuncionariosCadastrados.createParallelGroup(Alignment.LEADING)
                .addComponent(lblTodosOsUsurios)
                .addGroup(gl_panelFuncionariosCadastrados.createSequentialGroup()
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(btnEditar)
                    .addGap(18)
                    .addComponent(btnRemover, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 601, GroupLayout.PREFERRED_SIZE)
                    .addGroup(gl_panelFuncionariosCadastrados.createSequentialGroup()
                        .addComponent(buttonCancelaPesquisa, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(buttonPesquisa, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
                        .addGap(27))
        );
    gl_panelFuncionariosCadastrados.setVerticalGroup(
        gl_panelFuncionariosCadastrados.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_panelFuncionariosCadastrados.createSequentialGroup()
            .addGap(14)
            .addComponent(lblTodosOsUsurios)
            .addPreferredGap(ComponentPlacement.UNRELATED)
            .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
            .addGap(12)
            .addGroup(gl_panelFuncionariosCadastrados.createParallelGroup(Alignment.LEADING)
                .addComponent(buttonPesquisa, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                .addGroup(gl_panelFuncionariosCadastrados.createParallelGroup(Alignment.BASELINE)
                    .addComponent(textField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCancelaPesquisa, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_panelFuncionariosCadastrados.createParallelGroup(Alignment.LEADING)
                        .addComponent(btnRemover, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEditar))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
          //tableLogin.clearSelection();
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
    getContentPane().add(btnCadastrarFuncionrio);
    getContentPane().add(panelExterno);

    JButton btnCadastrados = new JButton("Cadastrados");
    btnCadastrados.setIcon(new ImageIcon(PainelUsuarios.class.getResource("/resources/clientes_icon.png")));
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

    Object[][] designTabela = new Object[colecaoAtiva.size()][5];

    for (int i = 0; i < colecaoAtiva.size(); i++) {
      Login loginAtual = colecaoAtiva.get(i);
      designTabela[i][0] = loginAtual.getNome();
      designTabela[i][1] = loginAtual.getLogin();
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
  public void adicionaNoPainel(JInternalFrame painel){
    painelPrincipal.add(painel);
  }
}
