package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import core.Contrato;
import core.Login;
import core.colecoes.ColecaoDeContratos;
import core.colecoes.ColecaoDeEstrategias;
import core.colecoes.ColecaoDeHospedes;
import core.colecoes.ColecaoDeLogins;
import core.colecoes.ColecaoDeQuartos;

public class Main extends JFrame {

  private static final long serialVersionUID = 6565568846885435L;
  private JPanel contentPane;
  private JDesktopPane painelPrincipal = new JDesktopPane();
  private PainelClientes painelClientes;
  private PainelServicos painelServicos;
  private PainelContratos painelContratos;
  private PainelEstrategias painelEstrategias;
  private PainelRelatorio painelRelatorio;
  private List<Contrato> listaContratos;
  private ColecaoDeLogins listaDeLogins;
  private ColecaoDeContratos listaDeContratos;
  private ColecaoDeHospedes listaDeHospedes;
  private ColecaoDeQuartos listaDeQuartos;
  private ColecaoDeEstrategias listaDeEstrategias;
  private final static SimpleDateFormat FORMATO_DATA = new SimpleDateFormat("dd/MM/yyyy");
  URL url = this.getClass().getResource("/resources/arquivo.data");
  File arquivo = new File(url.toURI());
  private boolean primeiraVezInicializado = !arquivo.isFile() || arquivo.length() == 0;


  public static SimpleDateFormat getFormatodata() {
    return FORMATO_DATA;
  }

  public ColecaoDeHospedes getListaDeHospedes() {
    return listaDeHospedes;
  }

  public ColecaoDeQuartos getListaDeQuartos() {
    return listaDeQuartos;
  }

  public ColecaoDeLogins getListaDeLogins() {
    return listaDeLogins;
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
          Main frame = new Main();
          PainelLogin<Object> painelLogin = new PainelLogin<Object>(frame.getListaDeLogins(), frame);
          painelLogin.toFront();
          painelLogin.setVisible(true);			

        }catch (ReflectiveOperationException e){
          JOptionPane.showMessageDialog(null, e.getMessage() + "\nContate o operador do sistema.");
        }catch (UnsupportedLookAndFeelException e1){
          JOptionPane.showMessageDialog(null, e1.getMessage() + "\nContate o operador do sistema.");
        }catch (URISyntaxException e2){
          JOptionPane.showMessageDialog(null, e2.getMessage() + "\nContate o operador do sistema.");
        }
      }
    });
  }

  /**
   * Método de conversão string para Calendar.
   * @param data
   * Uma data no formato dd/mm/aaaa
   * @return
   * Um calendar com essa data
   * @throws ParseException
   * Se a string estiver em algum formato inválido
   */
  public static Calendar converteParaCalendar(String data) throws java.text.ParseException{
    Calendar dataDeRetorno = Calendar.getInstance();
    dataDeRetorno.set(Calendar.HOUR_OF_DAY, 0);
    dataDeRetorno.setTime(FORMATO_DATA.parse(data));
    return dataDeRetorno;

  }
  /**
   * Método de conversão de um Calendar para uma string.
   * @param data
   * O objeto Calendar
   * @return
   * Uma string com a data formatada do Calendar no formato dd/mm/aaaa
   */
  public static String converteParaString (Calendar data) {
    String dataFormatada = FORMATO_DATA.format(data.getTime());
    return dataFormatada;
  }

  public Main() throws URISyntaxException{
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent arg0) {
        try{

          List<Object> listaColecoes = new ArrayList<Object>();

          /*
           * Índices na listaColecoes:
           * listaDeLogins = 0
           * listaDeContratos = 1
           * listaDeHospedes = 2
           * listaDeQuartos = 3
           * listaDeEstrategias = 4
           */

          listaColecoes.add(listaDeLogins);
          listaColecoes.add(listaDeContratos);
          listaColecoes.add(listaDeHospedes);
          listaColecoes.add(listaDeQuartos);
          listaColecoes.add(listaDeEstrategias);

          FileOutputStream fOut = new FileOutputStream(arquivo);
          ObjectOutputStream objOut = new ObjectOutputStream(fOut);
          objOut.writeObject(listaColecoes);
          objOut.flush();
          objOut.close();
        }catch (IOException e){
          JOptionPane.showMessageDialog(null, e.getMessage());
        }
      }
    });
    setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/resources/hotel39.png")));
    setTitle("Hotel Riviera Campina - Administra\u00E7\u00E3o");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(0, 0, 1366, 700);
    toFront();

    inicializacao();
    this.listaContratos = listaDeContratos.getListaContratos();

    //MenuBar com opções para fechar janelas abertas e informações sobre o projeto
    JMenuBar menuBar = new JMenuBar();
    setJMenuBar(menuBar);

    JMenu mnOpo = new JMenu("Programa");
    menuBar.add(mnOpo);

    JMenuItem mntmOpo = new JMenuItem("Fechar janelas");
    mntmOpo.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        JInternalFrame[] listaPaineis = painelPrincipal.getAllFrames();
        for (JInternalFrame frame: listaPaineis){
          frame.dispose();
        }
      }
    });
    mnOpo.add(mntmOpo);

    JMenuItem mntmDeletarDados = new JMenuItem("Deletar dados");
    mntmDeletarDados.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        int escolha = JOptionPane.showOptionDialog(null, "Tem certeza de que deseja deletar todos os dados do hotel?\nEssa é uma operação irreversível.", "" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Sim", "Não" }, JOptionPane.NO_OPTION);
        if (escolha == JOptionPane.YES_OPTION) {
          try{
            apagaArquivo();
            dispose();
          }catch (URISyntaxException e){
            JOptionPane.showMessageDialog(null, e.getMessage() + "\nContate o operador do sistema");
          }catch (IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage() + "\nContate o operador do sistema");
          }
        }
      }
    });
    mnOpo.add(mntmDeletarDados);

    JMenu mnOpo_1 = new JMenu("Sobre");
    menuBar.add(mnOpo_1);

    JMenuItem mntmOpo_3 = new JMenuItem("Sobre o programa");
    mntmOpo_3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        DialogoSobre sobre = new DialogoSobre();
        sobre.setVisible(true);
      }
    });
    mnOpo_1.add(mntmOpo_3);

    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);

    //toolBar com os botões Clientes, Servicos, Contrato, Estrátegia e Relátorio
    JToolBar toolBar = new JToolBar();
    toolBar.setFloatable(false);

    //JDesktopPane onde as janelas do programa são criadas
    painelPrincipal.setBorder(new LineBorder(new Color(0, 0, 0)));
    painelPrincipal.setBackground(SystemColor.window);
    GroupLayout gl_contentPane = new GroupLayout(contentPane);
    gl_contentPane.setHorizontalGroup(
        gl_contentPane.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_contentPane.createSequentialGroup()
            .addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 958, Short.MAX_VALUE)
            .addGap(16))
            .addComponent(painelPrincipal, GroupLayout.DEFAULT_SIZE, 974, Short.MAX_VALUE)
        );
    gl_contentPane.setVerticalGroup(
        gl_contentPane.createParallelGroup(Alignment.LEADING)
        .addGroup(gl_contentPane.createSequentialGroup()
            .addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(ComponentPlacement.RELATED)
            .addComponent(painelPrincipal, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
            .addContainerGap())
        );
    GroupLayout gl_painelPrincipal = new GroupLayout(painelPrincipal);
    gl_painelPrincipal.setHorizontalGroup(
        gl_painelPrincipal.createParallelGroup(Alignment.LEADING)
        .addGap(0, 753, Short.MAX_VALUE)
        );
    gl_painelPrincipal.setVerticalGroup(
        gl_painelPrincipal.createParallelGroup(Alignment.LEADING)
        .addGap(0, 464, Short.MAX_VALUE)
        );
    painelPrincipal.setLayout(gl_painelPrincipal);

    //Criação dos botões na toolBar
    JButton btnClientes = new JButton("Clientes    ");
    btnClientes.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (painelClientes == null || painelClientes.isClosed()){
          painelClientes = new PainelClientes(listaDeHospedes, painelPrincipal);
          painelPrincipal.add(painelClientes);
          painelClientes.show();
        }else{
          painelClientes.toFront();
        }
      }
    });

    btnClientes.setIcon(new ImageIcon(Main.class.getResource("/resources/clientes_icon.png")));
    btnClientes.setFont(new Font("Tahoma", Font.PLAIN, 17));

    toolBar.add(btnClientes);

    JButton btnServios = new JButton("Servi\u00E7os    ");
    btnServios.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (painelServicos == null || painelServicos.isClosed()){
          painelServicos = new PainelServicos(listaContratos, painelPrincipal, listaDeQuartos, listaDeHospedes);
          painelPrincipal.add(painelServicos);
          painelServicos.show();
        }else{
          painelServicos.toFront();
        }
      }
    });
    btnServios.setIcon(new ImageIcon(Main.class.getResource("/resources/servicos_icon.png")));
    btnServios.setFont(new Font("Tahoma", Font.PLAIN, 17));

    toolBar.add(btnServios);

    JButton btnContratos = new JButton("Contratos    ");
    btnContratos.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (painelContratos == null || painelContratos.isClosed()){
          painelContratos = new PainelContratos(listaContratos, painelPrincipal, listaDeHospedes, listaDeQuartos, listaDeEstrategias);
          painelPrincipal.add(painelContratos);
          painelContratos.show();
        }else{
          painelContratos.toFront();
        }
      }
    });
    btnContratos.setIcon(new ImageIcon(Main.class.getResource("/resources/contrato_icon.png")));
    btnContratos.setFont(new Font("Tahoma", Font.PLAIN, 17));
    toolBar.add(btnContratos);

    JButton btnEstratgias = new JButton("Estratégias ");
    btnEstratgias.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (painelEstrategias == null || painelEstrategias.isClosed()){
          painelEstrategias = new PainelEstrategias(listaDeEstrategias, listaDeContratos, painelPrincipal);
          painelPrincipal.add(painelEstrategias);
          painelEstrategias.show();
        }else{
          painelEstrategias.toFront();
        }
      }
    });
    btnEstratgias.setIcon(new ImageIcon(Main.class.getResource("/resources/calendar146.png")));
    btnEstratgias.setFont(new Font("Tahoma", Font.PLAIN, 17));
    toolBar.add(btnEstratgias);

    JButton btnRelatrios = new JButton("Relatórios");
    btnRelatrios.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (painelRelatorio == null || painelRelatorio.isClosed()) {
          painelRelatorio = new PainelRelatorio(listaDeHospedes, listaDeContratos, painelPrincipal);
          painelPrincipal.add(painelRelatorio);
          painelRelatorio.show();
        }else{
          painelRelatorio.toFront();
        }
      }
    });
    btnRelatrios.setIcon(new ImageIcon(Main.class.getResource("/resources/files.png")));
    btnRelatrios.setFont(new Font("Tahoma", Font.PLAIN, 17));
    toolBar.add(btnRelatrios);
    
    JButton btnLogin = new JButton("Editar Login");
    btnLogin.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
        PainelEditaLogin painelEditaLogin = new PainelEditaLogin(listaDeLogins);
        painelPrincipal.add(painelEditaLogin);
        painelEditaLogin.show();
      }
    });
    btnLogin.setIcon(new ImageIcon(Main.class.getResource("/resources/administrator.png")));
    btnLogin.setFont(new Font("Dialog", Font.PLAIN, 17));
    toolBar.add(btnLogin);
    //Fim da criação dos botões

    contentPane.setLayout(gl_contentPane);
    setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{menuBar, mnOpo, mntmOpo, mnOpo_1, mntmOpo_3, contentPane, toolBar, btnClientes, btnServios, btnContratos, painelPrincipal}));
  }

  private void inicializacao() throws URISyntaxException {
    if (!primeiraVezInicializado){	//Não é a primeira vez que o usuário abriu o programa	
      try{
        FileInputStream fileIn = new FileInputStream(arquivo);
        ObjectInputStream objIn = new ObjectInputStream(fileIn);

        /*
         * Índices na listaColecoes:
         * listaDeLogins = 0
         * listaDeContratos = 1
         * listaDeHospedes = 2
         * listaDeQuartos = 3
         * listaDeEstrategias = 4
         */

        @SuppressWarnings("unchecked") //O cast é seguro, mesmo se a IDE mandar um warning.
        List <Object> listaColecoes = (ArrayList<Object>) objIn.readObject();
        listaDeLogins = (ColecaoDeLogins) listaColecoes.get(0);
        listaDeContratos = (ColecaoDeContratos) listaColecoes.get(1);
        listaDeHospedes = (ColecaoDeHospedes) listaColecoes.get(2);
        listaDeQuartos = (ColecaoDeQuartos) listaColecoes.get(3);
        listaDeEstrategias = (ColecaoDeEstrategias) listaColecoes.get(4);

        objIn.close();
      }catch (Exception e){
        /*
         * Um catch bem genérico pois só serão lançadas duas excessões: IOException e FileNotFoundException.
         * Nos dois casos de uma dessas excessões serem lançadas, o comportamento será o mesmo.
         * A única coisa em comum dessas duas excessões é a herança de Exception.
         */
        try {
          apagaArquivo();
          JOptionPane.showMessageDialog(null, "Um erro com os dados salvos no computador ocorreu. Os dados tiveram de ser apagados.");
          listaDeQuartos = new ColecaoDeQuartos();
          listaDeQuartos.criaQuartos();
        } catch (HeadlessException e1) {
          JOptionPane.showMessageDialog(null, e1.getMessage() + "\nContate o operador do sistema.");
        } catch (FileNotFoundException e1) {
          JOptionPane.showMessageDialog(null, e1.getMessage() + "\nContate o operador do sistema.");
        } catch (IOException e1) {
          JOptionPane.showMessageDialog(null, e1.getMessage() + "\nContate o operador do sistema.");
        }
      }
    }else{ //Primeira vez que o usuário abriu o programa
      listaDeLogins = new ColecaoDeLogins();
      listaDeContratos = new ColecaoDeContratos();
      listaDeHospedes = new ColecaoDeHospedes();
      listaDeEstrategias = new ColecaoDeEstrategias();
      listaDeQuartos = new ColecaoDeQuartos();
      listaDeQuartos.criaQuartos();
      listaDeLogins.adicionaContaLogin(new Login("administrador", "admin", "12345", "Senha Padrão!"));
    }
  }
  /**
   * Método para uso privado, deletando todos os dados do arquivo que salva os dados do hotel.
   * @throws URISyntaxException
   * @throws FileNotFoundException
   * @throws IOException
   */
  private void apagaArquivo() throws URISyntaxException, FileNotFoundException, IOException {
    arquivo = new File(url.toURI());
    FileOutputStream deletaConteudo = new FileOutputStream(arquivo); //Criando um FileOutputStream que não vai escrever nada, ou seja, o arquivo ficará vazio.
    deletaConteudo.close();
  }
}
