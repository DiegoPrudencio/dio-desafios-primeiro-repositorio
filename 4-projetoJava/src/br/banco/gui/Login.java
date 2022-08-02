package br.banco.gui;

import br.banco.Banco;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Login extends JFrame {

	private JPanel panel;
	private JPanel panelBorda;

	private JPanel panelTitulo;
	private JLabel lblTitulo;

	private JPanel panelCpf;
	private JLabel lblCpf;
	private JTextField txtCpf;

	private JPanel panelSenha;
	private JLabel lblSenha;
	private JPasswordField txtSenha;

	private JPanel panelBotao;
	private JButton btnEntrar;
	private JButton btnCadastrar;

	public Login() {
		this.iniciarComponentes();
	}

	private void iniciarComponentes() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		lblTitulo = new JLabel("Login");
		lblTitulo.setFont(fontTitulo);
		panelTitulo.add(lblTitulo);

		panelCpf = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelBorda.add(panelCpf);

		lblCpf = new JLabel("CPF:");
		lblCpf.setFont(fontTexto);
		panelCpf.add(lblCpf);

		txtCpf = new JTextField();
		txtCpf.setFont(fontCampo);
		txtCpf.setPreferredSize(new Dimension(300, 20));
		panelCpf.add(txtCpf);

		panelSenha = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelBorda.add(panelSenha);

		lblSenha = new JLabel("Senha:");
		lblSenha.setFont(fontTexto);
		panelSenha.add(lblSenha);

		txtSenha = new JPasswordField();
		txtSenha.setFont(fontCampo);
		txtSenha.setPreferredSize(new Dimension(300, 20));
		panelSenha.add(txtSenha);

		panelBotao = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelBorda.add(panelBotao);

		btnEntrar = new JButton("Entrar");
		btnEntrar.setFont(fontBotao);
		btnEntrar.addActionListener((ActionEvent) -> {
			this.entrar();
		});
		panelBotao.add(btnEntrar);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(fontBotao);
		btnCadastrar.addActionListener((ActionEvent) -> {
			this.cadastrar();
		});
		panelBotao.add(btnCadastrar);
	}

	private void entrar() {
		try {
			int cpf = Integer.parseInt(txtCpf.getText());
			String senha = new String(txtSenha.getPassword());

			if (Banco.logar(cpf, senha)) {
				new Main().setVisible(true);
				this.dispose();
			} else {
				String info = "Acesso negado!";
				JOptionPane.showMessageDialog(this, info, "Banco Java", JOptionPane.ERROR_MESSAGE);
			}
		} catch (NumberFormatException e) {
			String info = "Acesso negado!";
			JOptionPane.showMessageDialog(this, info, "Banco Java", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cadastrar() {
		new Cadastrar(this).setVisible(true);
	}

	public static void main(String[] args) {
		new Login().setVisible(true);
	}

}
