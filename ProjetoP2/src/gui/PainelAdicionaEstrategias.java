package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import core.Estrategia;
import core.TipoDeEstrategia;
import core.colecoes.ColecaoDeContratos;
import core.colecoes.ColecaoDeEstrategias;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.text.MaskFormatter;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PainelAdicionaEstrategias extends JInternalFrame {
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblExemplo;
	private JSpinner spinnerPorcentagem;
	private TipoDeEstrategia tipoDeEstrategia;
	private JFormattedTextField formattedTextFieldDe;
	private JFormattedTextField formattedTextFieldAte;
	private JButton btnFinalizar;
	private JTextField textFieldDescricao;
	private ColecaoDeContratos listaContratos;
	private ColecaoDeEstrategias listaEstrategias;
	private JFormattedTextField formattedTextFieldExemplo;

	public PainelAdicionaEstrategias(ColecaoDeContratos listaContratos, ColecaoDeEstrategias listaEstrategias) {
		setClosable(true);
		setFrameIcon(new ImageIcon(PainelAdicionaEstrategias.class.getResource("/resources/calendar146.png")));
		setTitle("Painel Adicionar Estratégias");
		setBounds(100, 100, 605, 261);
		this.listaContratos = listaContratos;
		this.listaEstrategias = listaEstrategias;
		
		JLabel lblDescrio = new JLabel("Descrição:");
		lblDescrio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textFieldDescricao = new JTextField();
		textFieldDescricao.setColumns(10);
		
		JLabel lblPorcentagem = new JLabel("Porcentagem:\r\n");
		lblPorcentagem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		spinnerPorcentagem = new JSpinner();
		spinnerPorcentagem.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				atualizaExemplo();
			}
		});
		spinnerPorcentagem.setModel(new SpinnerNumberModel(1.0, 1.0, 100.0, 1.0));
		tipoDeEstrategia = TipoDeEstrategia.ACRESCIMO;
		
		JLabel label = new JLabel("%");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Acréscimo");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tipoDeEstrategia = TipoDeEstrategia.ACRESCIMO;
				atualizaExemplo();
			}
		});
		rdbtnNewRadioButton.setSelected(true);
		buttonGroup.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Decréscimo");
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipoDeEstrategia = TipoDeEstrategia.DECRESCIMO;
				atualizaExemplo();
			}
		});
		buttonGroup.add(rdbtnNewRadioButton_1);
		
		lblExemplo = new JLabel("New label");
		lblExemplo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblDe = new JLabel("De:");
		lblDe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		try {
			formattedTextFieldDe = new JFormattedTextField(new MaskFormatter("##/##"));
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		
		JLabel lblAt = new JLabel("Até:");
		lblAt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		try {
			formattedTextFieldAte = new JFormattedTextField(new MaskFormatter("##/##"));
		} catch (ParseException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		
		btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				String descricao = textFieldDescricao.getText();
				double porcentagem = (Double) spinnerPorcentagem.getValue();
				
				SimpleDateFormat FormatoData = new SimpleDateFormat("dd/MM");
				
				Calendar dataDeInicio = Calendar.getInstance();
				dataDeInicio.set(Calendar.HOUR_OF_DAY, 0);
				dataDeInicio.setTime(FormatoData.parse(formattedTextFieldDe.getText()));
				
				Calendar dataDeEncerramento = Calendar.getInstance();
				dataDeEncerramento.set(Calendar.HOUR_OF_DAY, 0);
				dataDeEncerramento.setTime(FormatoData.parse(formattedTextFieldAte.getText()));
				
				Estrategia estrategia = new Estrategia(dataDeInicio, dataDeEncerramento, porcentagem, tipoDeEstrategia, descricao);
				
				PainelAdicionaEstrategias.this.listaEstrategias.adicionaEstrategia(estrategia);
//				int numeroModificacoes = PainelAdicionaEstrategias.this.listaContratos.adicionaEstrategia(estrategia);
//				String mensagem = "Nenhum contrato foi modificado com a criação dessa estratégia.";
//				if (numeroModificacoes == 1){
//					mensagem = "Um contrato foi modificado com a criação dessa estratégia";
//				}else if (numeroModificacoes > 1){
//					mensagem = numeroModificacoes + " contratos foram modificados com a criação dessa estratégia";
//				}
//				JOptionPane.showMessageDialog(null, mensagem);
				dispose();
				
				}catch (java.text.ParseException e2){
					JOptionPane.showMessageDialog(null, "Data(s) em formato(s) inválido(s).");
				}catch (Exception e){
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				
			}
		});
		
		formattedTextFieldExemplo = new JFormattedTextField();
		formattedTextFieldExemplo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)){
					e.consume();
					atualizaExemplo();
				}
			}
		});
		formattedTextFieldExemplo.setText("2500.0");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDescrio)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldDescricao, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPorcentagem)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(spinnerPorcentagem, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label)
							.addGap(100)
							.addComponent(rdbtnNewRadioButton)
							.addGap(18)
							.addComponent(rdbtnNewRadioButton_1))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDe)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(formattedTextFieldDe, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblAt)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(formattedTextFieldAte, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnFinalizar, Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblExemplo)
							.addPreferredGap(ComponentPlacement.RELATED, 236, Short.MAX_VALUE)
							.addComponent(formattedTextFieldExemplo, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(textFieldDescricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDescrio))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPorcentagem)
						.addComponent(spinnerPorcentagem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label)
						.addComponent(rdbtnNewRadioButton)
						.addComponent(rdbtnNewRadioButton_1))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblExemplo)
						.addComponent(formattedTextFieldExemplo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDe)
						.addComponent(formattedTextFieldDe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAt)
						.addComponent(formattedTextFieldAte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
					.addComponent(btnFinalizar)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		atualizaExemplo();

	}
	private void atualizaExemplo(){
		double valorPadrao = Double.valueOf(formattedTextFieldExemplo.getText());
		double porcentagem = (Double) spinnerPorcentagem.getValue();
		String estado = tipoDeEstrategia.toString().toLowerCase();
		double precoAdicao = valorPadrao * (porcentagem / 100);
		double precoFinal = valorPadrao + (precoAdicao * tipoDeEstrategia.getMultiplicador());
		
		String exemplo = valorPadrao + " + " + estado + " de R$ " + (new DecimalFormat("0.0").format(precoAdicao)).replace(",", ".") + " = R$" + precoFinal;
		
		lblExemplo.setText(exemplo);
	}
}
