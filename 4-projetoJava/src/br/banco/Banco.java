package br.banco;

import java.util.TreeMap;
import java.util.ArrayList;
import br.banco.pessoa.Cliente;
import br.exception.AbrirContaException;
import br.banco.conta.Conta;
import br.banco.conta.ContaPoupanca;
import br.banco.conta.ContaCorrente;
import br.exception.ValorInvalidoException;
import br.exception.SaldoInsuficienteException;
import br.exception.CpfInvalidoException;

public class Banco {

	private static final int AGENCIA = 123;
	private static int contasAbertasTotal = 1;
	private static final TreeMap<Integer, Cliente> clientes = new TreeMap<>();
	private static int usuarioLogado = -1;

	public static Cliente getUsuarioLogado() {
		return clientes.get(usuarioLogado);
	}

	private static Conta getConta(int numero) {
		for (Cliente cliente: clientes.values()) {
			if (cliente.getContaPoupanca() != null) {
				if (cliente.getContaPoupanca().getNumero() == numero) {
					return cliente.getContaPoupanca();
				}
			}

			if (cliente.getContaCorrente() != null) {
				if (cliente.getContaCorrente().getNumero() == numero) {
					return cliente.getContaCorrente();
				}
			}
		}

		return null;
	}

	public static ArrayList<Integer> getContas() {
		ArrayList<Integer> contas = new ArrayList<>();

		for (Cliente cliente: clientes.values()) {
			if (cliente.getContaPoupanca() != null && usuarioLogado != cliente.getCpf()) {
				contas.add(cliente.getContaPoupanca().getNumero());
			}

			if (cliente.getContaCorrente() != null && usuarioLogado != cliente.getCpf()) {
				contas.add(cliente.getContaCorrente().getNumero());
			}
		}

		return contas;
	}

	public static void cadastrarCliente(int cpf, String nome, String senha) throws CpfInvalidoException {
		if (clientes.containsKey(cpf)) throw new CpfInvalidoException();

		clientes.put(cpf, new Cliente(cpf, nome, senha));
	}

	public static boolean logar(int cpf, String senha) {
		if (!clientes.containsKey(cpf)) return false;

		if (clientes.get(cpf).logar(cpf, senha)) usuarioLogado = cpf;
		else return false;

		return true;
	}

	public static void deslogar() {
		usuarioLogado = -1;
	}

	public static void abrirContaPoupanca() throws AbrirContaException {
		clientes.get(usuarioLogado).abrirConta(new ContaPoupanca(contasAbertasTotal, AGENCIA));

		contasAbertasTotal++;
	}

	public static void abrirContaCorrente() throws AbrirContaException {
		clientes.get(usuarioLogado).abrirConta(new ContaCorrente(contasAbertasTotal, AGENCIA));

		contasAbertasTotal++;
	}

	public static void depositar(double valor, int conta) throws ValorInvalidoException {
		clientes.get(usuarioLogado).depositar(valor, Banco.getConta(conta));
	}

	public static void sacar(double valor, int conta) throws ValorInvalidoException, SaldoInsuficienteException {
		clientes.get(usuarioLogado).sacar(valor, Banco.getConta(conta));
	}

	public static void transferir(double valor, int conta, int contaDestino) throws SaldoInsuficienteException, ValorInvalidoException {
		clientes.get(usuarioLogado).transferir(valor, Banco.getConta(conta), Banco.getConta(contaDestino));
	}

}
