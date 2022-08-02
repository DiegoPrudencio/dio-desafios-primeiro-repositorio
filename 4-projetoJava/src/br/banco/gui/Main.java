package br.banco.gui;

import br.banco.Banco;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Main extends JFrame {

	private JPanel panel;
	private JPanel panelBorda;

	private JMenuBar menuBar;

	private JMenu menuContas;
	private JMenuItem menuAbrirConta;
	private JMenuItem menuSair;

	private JMenu menuOperacoes;
	private JMenuItem menuExibirExtrato;
	private JMenuItem menuDepositar;
	private JMenuItem menuSacar;
	private JMenuItem menuTransferir;

	private JMenu menuSobre;
	private JMenuItem menuInfo;

	private JPanel panelTitulo;
	private JLabel lblTitulo;

	public Main() {
		this.iniciarComponentes();
	}

	private void iniciarComponentes() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Banco Java");
		this.setSize(640, 480);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		Font fontTitulo = new Font("Sans Serif", Font.BOLD, 25);

		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.add(panel);

		panelBorda = new JPanel();
		panelBorda.setLayout(new BoxLayout(panelBorda, BoxLayout.Y_AXIS));
		panelBorda.setBorder(new LineBorder(Color.black));
		panel.add(panelBorda);

		menuBar = new JMenuBar();
		menuBar.setBorder(new EmptyBorder(0, 10, 0, 10));
		this.setJMenuBar(menuBar);

		menuContas = new JMenu("Contas");
		menuBar.add(menuContas);

		menuAbrirConta = new JMenuItem("Abrir Conta");
		menuAbrirConta.addActionListener((ActionEvent) -> {
			this.abrirConta();
		});
		menuContas.add(menuAbrirConta);

		menuSair = new JMenuItem("Sair");
		menuSair.addActionListener((ActionEvent) -> {
			this.sair();
		});
		menuContas.add(menuSair);

		menuOperacoes = new JMenu("Operações");
		menuBar.add(menuOperacoes);

		menuExibirExtrato = new JMenuItem("Exibir Extrato");
		menuExibirExtrato.addActionListener((ActionEvent) -> {
			this.exibirExtrato();
		});
		menuOperacoes.add(menuExibirExtrato);

		menuDepositar = new JMenuItem("Depositar");
		menuDepositar.addActionListener((ActionEvent) -> {
			this.depositar();
		});
		menuOperacoes.add(menuDepositar);

		menuSacar = new JMenuItem("Sacar");
		menuSacar.addActionListener((ActionEvent) -> {
			this.sacar();
		});
		menuOperacoes.add(menuSacar);

		menuTransferir = new JMenuItem("Transferir");
		menuTransferir.addActionListener((ActionEvent) -> {
			this.transferir();
		});
		menuOperacoes.add(menuTransferir);

		menuSobre = new JMenu("Sobre");
		menuBar.add(menuSobre);

		menuInfo = new  JMenuItem("Info");
		menuInfo.addActionListener((ActionEvent) -> {
			this.exibirInfo();
		});
		menuSobre.add(menuInfo);

		panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelBorda.add(panelTitulo);

		lblTitulo = new JLabel("Bem vindo, " + Banco.getUsuarioLogado().getNome() + "!");
		lblTitulo.setFont(fontTitulo);
		panelTitulo.add(lblTitulo);
	}

	private void abrirConta() {
		new AbrirConta(this).setVisible(true);
	}

	private void sair() {
		Banco.deslogar();
		new Login().setVisible(true);
		this.dispose();
	}

	private void exibirExtrato() {
		new ExibirExtrato(this).setVisible(true);
	}

	private void depositar() {
		new Depositar(this).setVisible(true);
	}

	private void sacar() {
		new Sacar(this).setVisible(true);
	}

	private void transferir() {
		new Transferir(this).setVisible(true);
	}

	private void exibirInfo() {
		String info = "Banco Java - versão 1.0\n\nDesenvolvido por Júlio Evêncio";
		JOptionPane.showMessageDialog(this, info, "Sobre", JOptionPane.INFORMATION_MESSAGE);
	}

}
