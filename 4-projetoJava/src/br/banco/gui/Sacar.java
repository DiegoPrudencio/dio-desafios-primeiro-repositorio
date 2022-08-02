package br.banco.gui;

import br.banco.Banco;
import br.exception.ValorInvalidoException;
import br.exception.SaldoInsuficienteException;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Sacar extends JDialog {

	private JPanel panel;
	private JPanel panelBorda;

	private JPanel panelTitulo;
	private JLabel lblTitulo;

	private JPanel panelConta;
	private JComboBox<String> txtConta;

	private JPanel panelValor;
	private JLabel lblValor;
	private JTextField txtValor;

	private JPanel panelBotao;
	private JButton btnSacar;

	public Sacar(JFrame frame) {
		super(frame, true);

		this.iniciarComponentes();
	}

	private void iniciarComponentes() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Banco Java");
		this.setSize(400, 250);
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

		lblTitulo = new JLabel("Sacar");
		lblTitulo.setFont(fontTitulo);
		panelTitulo.add(lblTitulo);

		panelConta = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelBorda.add(panelConta);

		txtConta = new JComboBox<>();
		txtConta.addItem("Selecione a conta");
		txtConta.addItem("Minha Conta Poupança");
		txtConta.addItem("Minha Conta Corrente");
		txtConta.setFont(fontCampo);
		txtConta.setPreferredSize(new Dimension(365, 20));
		panelConta.add(txtConta);

		panelValor = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelBorda.add(panelValor);

		lblValor = new JLabel("Valor:");
		lblValor.setFont(fontTexto);
		panelValor.add(lblValor);

		txtValor = new JTextField();
		txtValor.setFont(fontCampo);
		txtValor.setPreferredSize(new Dimension(100, 20));
		panelValor.add(txtValor);

		panelBotao = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelBorda.add(panelBotao);

		btnSacar = new JButton("Sacar");
		btnSacar.setFont(fontBotao);
		btnSacar.addActionListener((ActionEvent) -> {
			this.sacar();
		});
		panelBotao.add(btnSacar);
	}

	private void sacar() {
		try {
			double valor = Double.parseDouble(txtValor.getText());

			if (txtConta.getSelectedItem().toString().equals("Selecione a conta")) {
				String info = "Selecione uma conta!";
				JOptionPane.showMessageDialog(this, info, "Banco Java", JOptionPane.ERROR_MESSAGE);
				return;
			} else if (txtConta.getSelectedItem().toString().equals("Minha Conta Poupança")) {
				Banco.sacar(valor, Banco.getUsuarioLogado().getContaPoupanca().getNumero());
			} else if (txtConta.getSelectedItem().toString().equals("Minha Conta Corrente")) {
				Banco.sacar(valor, Banco.getUsuarioLogado().getContaCorrente().getNumero());
			}

			String info = "Saque realizado com sucesso!";
			JOptionPane.showMessageDialog(this, info, "Banco Java", JOptionPane.INFORMATION_MESSAGE);

			this.dispose();
		} catch (NumberFormatException | ValorInvalidoException e) {
			String info = "Valor ivnálido!";
			JOptionPane.showMessageDialog(this, info, "Banco Java", JOptionPane.ERROR_MESSAGE);
		} catch (SaldoInsuficienteException e) {
			String info = "Saldo insuficiente!";
			JOptionPane.showMessageDialog(this, info, "Banco Java", JOptionPane.ERROR_MESSAGE);
		} catch (NullPointerException e) {
			String info = "Conta inválida!\nVocê não tem o tipo de conta selecionada!";
			JOptionPane.showMessageDialog(this, info, "Banco Java", JOptionPane.ERROR_MESSAGE);
		}
	}

}
