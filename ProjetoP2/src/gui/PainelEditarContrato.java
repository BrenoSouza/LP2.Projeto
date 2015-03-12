package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import core.Contrato;
import core.Hospede;
import core.Quarto;
import core.Servico;
import core.colecoes.ColecaoDeHospedes;
import core.colecoes.ColecaoDeQuartos;

public class PainelEditarContrato extends JInternalFrame implements Atualizador{

  private static final long serialVersionUID = 2678439180208101446L;
  private JLabel lblQuartos;
  private JLabel lblHospedes;
  private JScrollPane scrollPane_1;
  private JScrollPane scrollPane;
  private Contrato contrato;
  private JTable tabelaHospedes;
  private List<Quarto> listaQuartos;
  private List<Hospede> listaHospedes;
  private List<Servico> listaServicos;
  private JScrollPane scrollPane_2;
  private JLabel lblServicos;
  private JTable tabelaServicos;
  private JTable tabelaQuartos;
  private JButton btnRemoverDinamico;
  private JDesktopPane painelPrincipal;
  private Object objetoDinamico = null;
  private JButton btnAdicionarDinamico;
  private JButton btnEditarDinamico;
  private Hospede hospedePrincipal;
  private double precoTotal;
  private JLabel lblPrecoTotal;
  private JButton btnFinalizar;
  private ColecaoDeQuartos listaQuartosHotel;
  private final static int SELECAO_HOSPEDE = 1;
  private final static int SELECAO_SERVICO = 2;
  private final static int SELECAO_QUARTO = 3;
  private int selecaoRelativa = 0;
  private ColecaoDeHospedes listaHospedesHotel;
  private PainelEditarContratoAdicionarHospede painelAddHospede;
  private PainelAdicionaServico painelAdd;
  private PainelEditarCliente painelEditarHospede;
  private Atualizador framePai;

  public PainelEditarContrato(Contrato contrato, JDesktopPane painelPrincipal, ColecaoDeQuartos listaQuartosHotelColecao, ColecaoDeHospedes listaHospedesHotel, Atualizador framePai) {
    setFrameIcon(new ImageIcon(PainelEditarContrato.class.getResource("/resources/contrato_icon.png")));
    setTitle("Editar contrato");
    setClosable(true);
    addInternalFrameListener(new InternalFrameAdapter() {
      @Override
      public void internalFrameActivated(InternalFrameEvent arg0) {
        escreveTabelas();
      }
    });
    setBounds(0, 0, 970, 530);
    this.contrato = contrato;
    this.painelPrincipal = painelPrincipal;
    hospedePrincipal = contrato.getHospedePrincipal();
    listaQuartos = contrato.getListaQuartosAlugados();
    listaHospedes = contrato.getListaHospedes();
    listaServicos = contrato.getListaServicos();
    this.framePai = framePai;
    this.listaHospedesHotel = listaHospedesHotel;
    this.listaQuartosHotel = listaQuartosHotelColecao;
    lblHospedes = new JLabel("Hóspedes:");
    lblHospedes.setFont(new Font("Tahoma", Font.PLAIN, 14));
    scrollPane = new JScrollPane();
    scrollPane.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        tabelaQuartos.clearSelection();
        tabelaServicos.clearSelection();
        setSelecaoRelativa(SELECAO_HOSPEDE);
      }
    });
    lblQuartos = new JLabel("Quartos:");
    lblQuartos.setFont(new Font("Tahoma", Font.PLAIN, 14));
    scrollPane_1 = new JScrollPane();
    scrollPane_1.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) { 
        tabelaHospedes.clearSelection();
        tabelaServicos.clearSelection();
        setSelecaoRelativa(SELECAO_QUARTO);
      }
    });

    scrollPane_2 = new JScrollPane();
    scrollPane_2.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        tabelaHospedes.clearSelection();
        tabelaQuartos.clearSelection();
        setSelecaoRelativa(SELECAO_SERVICO);
      }
    });

    lblServicos = new JLabel("Serviços:");
    lblServicos.setFont(new Font("Tahoma", Font.PLAIN, 14));

    btnRemoverDinamico = new JButton("Remover");
    btnRemoverDinamico.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (objetoDinamico instanceof Hospede){
          int escolha = JOptionPane.showOptionDialog(null, "Você realmente deseja retirar esse hóspede do contrato?" + (objetoDinamico == hospedePrincipal ? Main.quebraDeLinha + "Esse hóspede está configurado como o hóspede principal do contrato selecionado." : ""), /*Aqui seria o título, mas não achei necessário */"" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Sim", "Não" }, JOptionPane.NO_OPTION);
          if (escolha == JOptionPane.YES_OPTION){
            listaHospedes.remove(objetoDinamico);
            if (objetoDinamico == hospedePrincipal){
              listaHospedes.remove(objetoDinamico);
              getContrato().setHospedePrincipal(null);
            }
          }
        }else if (objetoDinamico instanceof Quarto){
          int escolha = JOptionPane.showOptionDialog(null, "Você realmente deseja retirar esse quarto do contrato?", "" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Sim", "Não" }, JOptionPane.NO_OPTION);
          if (escolha == JOptionPane.YES_OPTION){
            listaQuartos.remove(objetoDinamico);
            ((Quarto) objetoDinamico).retiraReserva(PainelEditarContrato.this.contrato);
            ((Quarto) objetoDinamico).setCamaExtra(false);
          }
        }else if (objetoDinamico instanceof Servico){
          int escolha = JOptionPane.showOptionDialog(null, "Você realmente deseja retirar esse serviço do contrato?", "" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Sim", "Não" }, JOptionPane.NO_OPTION);
          if (escolha == JOptionPane.YES_OPTION){
            listaServicos.remove(objetoDinamico);
          }
        }
        escreveTabelas();
      }
    });

    btnAdicionarDinamico = new JButton("Adicionar");
    btnAdicionarDinamico.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        String texto = btnAdicionarDinamico.getText();
        if (texto.equals("Adicionar hóspede")){
          if (painelAddHospede == null || painelAddHospede.isClosed()){
            painelAddHospede = new PainelEditarContratoAdicionarHospede(PainelEditarContrato.this.listaHospedesHotel, PainelEditarContrato.this.contrato, PainelEditarContrato.this.painelPrincipal, PainelEditarContrato.this);
            PainelEditarContrato.this.painelPrincipal.add(painelAddHospede);
            painelAddHospede.show();
          }else{
            painelAddHospede.toFront();
          }
        }else{
          if (painelAdd == null || painelAdd.isClosed()){
            painelAdd = new PainelAdicionaServico(null, PainelEditarContrato.this.contrato, PainelEditarContrato.this.painelPrincipal, PainelEditarContrato.this.listaQuartosHotel, PainelEditarContrato.this.listaHospedesHotel, PainelEditarContrato.this);
            PainelEditarContrato.this.painelPrincipal.add(painelAdd);
            painelAdd.show();
          }else{
            painelAdd.toFront();
          }
        }
        escreveTabelas();
      }
    });

    btnEditarDinamico = new JButton("Editar");
    btnEditarDinamico.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        try{
          String texto = btnEditarDinamico.getText();
          if (texto.equals("Editar hóspede")){
            if (painelEditarHospede == null || painelEditarHospede.isClosed()){
              painelEditarHospede = new PainelEditarCliente((Hospede) objetoDinamico, PainelEditarContrato.this.painelPrincipal, PainelEditarContrato.this);
              PainelEditarContrato.this.painelPrincipal.add(painelEditarHospede);
              painelEditarHospede.show();}
            else{
              painelEditarHospede.toFront();
            }
          }else if (texto.equals("Editar serviço") || texto.equals("Editar quarto")){
            if (painelAdd == null || painelAdd.isClosed()){
              painelAdd = new PainelAdicionaServico((Servico) objetoDinamico, PainelEditarContrato.this.contrato, PainelEditarContrato.this.painelPrincipal, PainelEditarContrato.this.listaQuartosHotel, PainelEditarContrato.this.listaHospedesHotel, PainelEditarContrato.this);
              PainelEditarContrato.this.painelPrincipal.add(painelAdd);
              painelAdd.show();
            }else{
              painelAdd.toFront();
            }
          }
          escreveTabelas();
        }catch (java.text.ParseException e5){
          JOptionPane.showMessageDialog(null, e5.getMessage());
        }
      }
    });
    atualizaBotoes();

    precoTotal = contrato.calculaPrecoFinal();
    lblPrecoTotal = new JLabel("Preço a ser pago: R$ " + precoTotal);
    if (contrato.getDiasDeAtraso() > 0){
      lblPrecoTotal.setText("Preço a ser pago, com multa de " + contrato.getDiasDeAtraso() + " dia(s) de atraso: R$ " + precoTotal);
    }else if(contrato.getDiasDeAtraso() < 0){
      lblPrecoTotal.setText("Preço a ser pago, com multa de " + Math.abs(contrato.getDiasDeAtraso()) + " dia(s) de antecipação: R$ " + precoTotal);
    }
    lblPrecoTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));

    btnFinalizar = new JButton("Fazer check-out");
    if (contrato.getStatus().equals("RESERVA")){
      btnFinalizar.setText("Fazer check-in");
    }
    btnFinalizar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        DateTime presente = new DateTime().withTimeAtStartOfDay();
        DateTime checkIn = new DateTime(PainelEditarContrato.this.contrato.getDataCheckIn()).withTimeAtStartOfDay();
        DateTime checkOut = new DateTime(PainelEditarContrato.this.contrato.getDataCheckOut()).withTimeAtStartOfDay();
        if (btnFinalizar.getText().equals("Fazer check-out")){//Se o contrato estiver apto a fazer checkout...
          String mensagem = "Você realmente deseja encerrar esse contrato?" + Main.quebraDeLinha + "Após a operação, não será mais possível editar o contrato.";
          if (checkOut.compareTo(presente) == 1){
            mensagem += Main.quebraDeLinha + "Como a data de CheckOut vem antes do presente, o cliente receberá uma multa";
            int escolha = JOptionPane.showOptionDialog(null, mensagem, /*Aqui seria o título, mas não achei necessário */"" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Sim", "Não" }, JOptionPane.NO_OPTION);
            if (escolha == JOptionPane.YES_OPTION){
              getContrato().fechaContrato();
              for (Quarto quarto: listaQuartos){
                quarto.retiraReserva(PainelEditarContrato.this.contrato);
                quarto.setCamaExtra(false);
              }
              Collections.sort(PainelEditarContrato.this.listaQuartosHotel.getListaQuartos());
              dispose();
            }
          }
        }else{//Se ele ainda for uma reserva...
          
          if (checkIn.compareTo(presente) == 1){
            JOptionPane.showMessageDialog(null, "A data presente vem antes da data de CheckIn do contrato.");
          }else if(checkOut.compareTo(presente) == -1){
            JOptionPane.showMessageDialog(null, "A data de checkout programada veio antes da data presente.");	
          }else{
            PainelEditarContrato.this.contrato.fazCheckIn();
            btnFinalizar.setText("Fazer check-out");
            dispose();
          }
        }
      }
    });
    GroupLayout groupLayout = new GroupLayout(getContentPane());
    groupLayout.setHorizontalGroup(
        groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblHospedes, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblQuartos, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
                        .addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 931, Short.MAX_VALUE)
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 931, Short.MAX_VALUE))
                        .addGap(13))
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(lblServicos)
                            .addContainerGap(888, Short.MAX_VALUE))
                            .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                    .addGroup(groupLayout.createSequentialGroup()
                                        .addComponent(btnRemoverDinamico, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                        .addGap(104)
                                        .addComponent(btnEditarDinamico, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                        .addGap(133)
                                        .addComponent(btnAdicionarDinamico, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                        .addGap(145)
                                        .addComponent(btnFinalizar, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(scrollPane_2, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 929, GroupLayout.PREFERRED_SIZE))
                                        .addGap(15))
                                        .addGroup(groupLayout.createSequentialGroup()
                                            .addComponent(lblPrecoTotal, GroupLayout.PREFERRED_SIZE, 494, GroupLayout.PREFERRED_SIZE)
                                            .addContainerGap())))
        );
    groupLayout.setVerticalGroup(
        groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(lblHospedes, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(ComponentPlacement.RELATED)
            .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(ComponentPlacement.RELATED)
            .addComponent(lblQuartos, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
            .addGap(8)
            .addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(ComponentPlacement.UNRELATED)
            .addComponent(lblServicos)
            .addPreferredGap(ComponentPlacement.RELATED)
            .addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
            .addGap(11)
            .addComponent(lblPrecoTotal)
            .addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
            .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                .addComponent(btnRemoverDinamico)
                .addComponent(btnFinalizar)
                .addComponent(btnAdicionarDinamico)
                .addComponent(btnEditarDinamico))
                .addContainerGap())
        );

    tabelaServicos = new JTable();
    scrollPane_2.setViewportView(tabelaServicos);
    //CRIANDO UMA AÇÃO PRA QUANDO UMA LINHA FOR SELECIONADA
    ListSelectionModel modeloSelecaoLinha = tabelaServicos.getSelectionModel();

    modeloSelecaoLinha.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    modeloSelecaoLinha.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        int[] indiceSelecionado = tabelaServicos.getSelectedRows(); 
        if (indiceSelecionado.length <= 0){
          objetoDinamico = null;
        }else{
          tabelaHospedes.clearSelection();
          tabelaQuartos.clearSelection();
          setObjetoDinamico(indiceSelecionado[0], listaServicos);
        }atualizaBotoes();

      }
    });
    //O event acima se refere a como o programa vai lidar quando o usuário clica em uma linha da tabela.

    tabelaQuartos = new JTable();
    scrollPane_1.setViewportView(tabelaQuartos);
    //CRIANDO UMA AÇÃO PRA QUANDO UMA LINHA FOR SELECIONADA
    ListSelectionModel modeloSelecaoLinha2 = tabelaQuartos.getSelectionModel(); 

    modeloSelecaoLinha2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    modeloSelecaoLinha2.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        int[] indiceSelecionado = tabelaQuartos.getSelectedRows(); 
        if (indiceSelecionado.length <= 0){
          objetoDinamico = null;
        }else{
          tabelaHospedes.clearSelection();
          tabelaServicos.clearSelection();
          setObjetoDinamico(indiceSelecionado[0], listaQuartos);
        }atualizaBotoes();

      }
    });
    //O event acima se refere a como o programa vai lidar quando o usuário clica em uma linha da tabela.

    tabelaHospedes = new JTable();
    scrollPane.setViewportView(tabelaHospedes);
    //CRIANDO UMA AÇÃO PRA QUANDO UMA LINHA FOR SELECIONADA
    ListSelectionModel modeloSelecaoLinha3 = tabelaHospedes.getSelectionModel(); 

    modeloSelecaoLinha3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    modeloSelecaoLinha3.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        int[] indiceSelecionado = tabelaHospedes.getSelectedRows(); 
        if (indiceSelecionado.length <= 0){
          objetoDinamico = null;
        }else{
          tabelaServicos.clearSelection();
          tabelaQuartos.clearSelection();
          setObjetoDinamico(indiceSelecionado[0], listaHospedes);
        }atualizaBotoes();

      }
    });
    //O event acima se refere a como o programa vai lidar quando o usuário clica em uma linha da tabela.
    getContentPane().setLayout(groupLayout);
    escreveTabelas();

  }

  public void escreveTabelas(){
    // INÍCIO DE CONSTRUÇÃO DA TABELA
    Object[][] designTabela = new Object[listaHospedes.size()][3];
    LocalDate presente = LocalDate.now();
    for (int i = 0; i < listaHospedes.size(); i++){
      Hospede hospedeAtual = listaHospedes.get(i);
      //Para preencher a primeira coluna da linha: Nome do hóspede
      designTabela[i][0] = hospedeAtual.getNome();
      //Para preencher a segunda coluna da linha: CPF do hóspede
      designTabela[i][1] = hospedeAtual.getCpf();
      //Para preencher a terceira coluna da linha: Idade do hóspede
      Calendar nascimento = hospedeAtual.getDataNascimento();
      LocalDate diaNascimento = LocalDate.fromCalendarFields(nascimento);
      Period periodoDeTempo = new Period(diaNascimento, presente, PeriodType.yearMonthDay());
      designTabela[i][2] = periodoDeTempo.getYears();

      // FIM DE CONSTRUÇÃO DE TABELA.

    }
    @SuppressWarnings("serial")
    DefaultTableModel modeloTabela = new DefaultTableModel(designTabela, new String[] {
        "Nome", "CPF", "Idade"
    }) {

      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };


    tabelaHospedes.setModel(modeloTabela); 
    tabelaHospedes.setRowSelectionAllowed(true); // Quando der clique, selecionar toda a linha, e não só uma célula

    //INICIO DA CONSTRUÇÃO DE TABELA
    Object [][] tabelaServicosDesign = new Object [listaServicos.size()][4];
    for (int i = 0; i < contrato.getListaServicos().size(); i++){
      Servico servicoAtual = contrato.getListaServicos().get(i);
      //Primeira célula da linha: o método getTipo() do serviço
      tabelaServicosDesign[i][0] = servicoAtual.getTipo();
      //Segunda célula da linha: a data do serviço
      tabelaServicosDesign[i][1] = servicoAtual.getInicioServico();
      //Terceira célula da linha: a hora de entrada do serviço
      tabelaServicosDesign[i][2] = servicoAtual.getHoraEntrada() + ":" + servicoAtual.getMinutosEntrada();
      //Quarta célula da linha: o preço total do serviço
      tabelaServicosDesign[i][3] = "R$ " + servicoAtual.calculaPrecoTotal();
    }
    @SuppressWarnings("serial")
    DefaultTableModel modeloTabelaServicos = new DefaultTableModel(tabelaServicosDesign, new String[] {
        "Serviço", "Data", "Hora", "Preço"
    }) {

      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    tabelaServicos.setModel(modeloTabelaServicos);
    //FIM DE CONSTRUÇÃO DE TABELA
    Collections.sort(listaQuartos);
    designTabela = new Object[listaQuartos.size()][6];	
    for (int j = 0; j < listaQuartos.size(); j++){
      Quarto quartoAtualContrato = listaQuartos.get(j);
      //Para preencher a primeira coluna da linha: Descrição do quarto
      designTabela[j][0] = quartoAtualContrato.getTipo();
      //Para preencher a segunda coluna da linha: O preço da diária
      designTabela[j][1] = "R$ " + quartoAtualContrato.getPrecoDiaria();
      //Para preencher a terceira coluna da linha: O número de diárias setadas
      designTabela[j][2] = contrato.getNumeroDiarias();
      //Para preencher a quarta coluna da linha: O número de pessoas que o quarto acomoda
      designTabela[j][3] = quartoAtualContrato.getNumeroHospedes();
      //Para preencher a quinta coluna da linha: O número do quarto
      designTabela[j][4] = quartoAtualContrato.getNumero();
      //Para preencher a sexta coluna da linha: O preço a ser pago
      designTabela[j][5] = "R$ " + quartoAtualContrato.getDiariasViaReservaDeContrato(contrato) * quartoAtualContrato.getPrecoDiaria();
    }
    @SuppressWarnings("serial")
    DefaultTableModel modeloTabela4 = new DefaultTableModel(designTabela, new String[] {
        "Descrição", "Preço da diária", "Num. de diárias", "Num. máximo de hóspedes (sem cama extra)", "Número", "Preço"
    }) {

      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    tabelaQuartos.setModel(modeloTabela4);
    tabelaQuartos.getColumnModel().getColumn(3).setPreferredWidth(180); //Aumentando o tamanho da quarta coluna, pq a String de título dela é grande
    tabelaQuartos.getColumnModel().getColumn(0).setPreferredWidth(130); // Idem ao comment acima
    tabelaQuartos.setRowSelectionAllowed(true);
    if (contrato.getStatus().equals("RESERVA")){
      lblPrecoTotal.setText("--RESERVA--");	
    }
  }
  public void atualizaBotoes(){
    if (objetoDinamico == null){
      btnRemoverDinamico.setEnabled(false);
      btnAdicionarDinamico.setEnabled(false);
      btnEditarDinamico.setEnabled(false);
    }else{
      btnRemoverDinamico.setEnabled(true);
      btnAdicionarDinamico.setEnabled(true);
      btnEditarDinamico.setEnabled(true);
      if (objetoDinamico instanceof Hospede){
        btnRemoverDinamico.setText("Remover hóspede");
        btnAdicionarDinamico.setText("Adicionar hóspede");
        btnEditarDinamico.setText("Editar hóspede");
      }else if (objetoDinamico instanceof Quarto){
        btnRemoverDinamico.setText("Remover quarto");
        btnAdicionarDinamico.setText("Adicionar quarto");
        btnEditarDinamico.setText("Editar quarto");
      }else if (objetoDinamico instanceof Servico){
        btnRemoverDinamico.setText("Remover serviço");
        btnAdicionarDinamico.setText("Adicionar serviço");
        btnEditarDinamico.setText("Editar serviço");
      }
    }
  }
  public void setObjetoDinamico (int i, List<? extends Object> lista){
    objetoDinamico = lista.get(i);
  }
  public Contrato getContrato(){
    return contrato;
  }
  private void setSelecaoRelativa(int i){
    selecaoRelativa = i;
    if (selecaoRelativa != 0){
      if (selecaoRelativa == SELECAO_HOSPEDE){
        btnAdicionarDinamico.setEnabled(true);
        btnAdicionarDinamico.setText("Adicionar hóspede");
      }else if (selecaoRelativa == SELECAO_QUARTO){
        btnAdicionarDinamico.setEnabled(true);
        btnAdicionarDinamico.setText("Adicionar quarto");
      }else if (selecaoRelativa == SELECAO_SERVICO){
        btnAdicionarDinamico.setEnabled(true);
        btnAdicionarDinamico.setText("Adicionar serviço");
      }
    }
  }
  @Override
  public void dispose(){
    PainelEditarContrato.this.framePai.atualiza();
    super.dispose();
  }

  public void atualiza() {
    escreveTabelas();    
  }
}
