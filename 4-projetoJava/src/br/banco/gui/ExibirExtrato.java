package br.banco.gui;

import br.banco.Banco;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class ExibirExtrato extends JDialog {

	private JPanel panel;
	private JPanel panelBorda;

	private JPanel panelTitulo;
	private JLabel lblTitulo;
	
	private JPanel panelConta;
	private JLabel lblConta;
	private ButtonGroup grupoConta;
	private JRadioButton txtContaPoupanca;
	private JRadioButton txtContaCorrente;

	private JPanel panelBotao;
	private JButton btnExibirExtrato;

	public ExibirExtrato(JFrame frame) {
		super(frame, true);
		this.iniciarComponentes();
	}

	private void iniciarComponentes() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Banco Java");
		this.setSize(400, 260);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		Font fontTitulo = new Font("Sans Serif", Font.BOLD, 25);
		Font fontTexto = new Font("Sans Serif", Font.BOLD, 15);
		Font fontCampo = new Font("Sans Serif", Font.PLAIN, 15);
		Font fontBotao = new Font("Sans Serif", Font.BOLD, 15);

		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.add(panel);

		panelBorda = new JPanel();
		panelBorda.setLayout(new BoxLayout(panelBorda, BoxLayout.Y_AXIS));
		panelBorda.setBorder(new LineBorder(Color.black));
		panel.add(panelBorda);

		panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelBorda.add(panelTitulo);

		lblTitulo = new JLabel("Exibir Extrato");
		lblTitulo.setFont(fontTitulo);
		panelTitulo.add(lblTitulo);

		panelConta = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelBorda.add(panelConta);

		lblConta = new JLabel("Tipo:");
		lblConta.setFont(fontTexto);
		panelConta.add(lblConta);

		txtContaPoupanca = new JRadioButton("Conta Poupança");
		txtContaPoupanca.setSelected(true);
		txtContaPoupanca.setFont(fontCampo);
		panelConta.add(txtContaPoupanca);

		txtContaCorrente = new JRadioButton("Conta Corrente");
		txtContaCorrente.setFont(fontCampo);
		panelConta.add(txtContaCorrente);

		grupoConta = new ButtonGroup();
		grupoConta.add(txtContaPoupanca);
		grupoConta.add(txtContaCorrente);

		panelBotao = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelBorda.add(panelBotao);

		btnExibirExtrato = new JButton("Exibir Extrato");
		btnExibirExtrato.setFont(fontBotao);
		btnExibirExtrato.addActionListener((ActionEvent) -> {
			this.exibirExtrato();
		});
		panelBotao.add(btnExibirExtrato);
	}

	private void exibirExtrato() {
		try {
			if (txtContaPoupanca.isSelected()) {
				String info = Banco.getUsuarioLogado().getContaPoupanca().getExtrato();
				JOptionPane.showMessageDialog(this, info, "Banco Java", JOptionPane.INFORMATION_MESSAGE);
			} else {
				String info = Banco.getUsuarioLogado().getContaCorrente().getExtrato();
				JOptionPane.showMessageDialog(this, info, "Banco Java", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (NullPointerException e) {
			String info = "Conta inválida!\nVocê não tem o tipo de conta selecionada!";
			JOptionPane.showMessageDialog(this, info, "Banco Java", JOptionPane.ERROR_MESSAGE);
		}
	}

}
