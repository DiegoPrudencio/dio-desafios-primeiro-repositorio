package br.banco.pessoa;

import br.banco.conta.Conta;
import br.banco.conta.ContaPoupanca;
import br.banco.conta.ContaCorrente;
import br.exception.AbrirContaException;
import br.exception.SaldoInsuficienteException;
import br.exception.ValorInvalidoException;

public class Cliente extends Pessoa {

	private ContaPoupanca contaPoupanca;
	private ContaCorrente contaCorrente;

	public Cliente(int cpf, String nome, String senha) {
		super(cpf, nome, senha);
		this.contaPoupanca = null;
		this.contaCorrente = null;
	}

	public ContaPoupanca getContaPoupanca() {
		return contaPoupanca;
	}

	public ContaCorrente getContaCorrente() {
		return contaCorrente;
	}

	public void abrirConta(ContaPoupanca conta) throws AbrirContaException {
		if (contaPoupanca != null) throw new AbrirContaException();

		contaPoupanca = conta;
	}

	public void abrirConta(ContaCorrente conta) throws AbrirContaException {
		if (contaCorrente != null) throw new AbrirContaException();

		contaCorrente = conta;
	}

	public void depositar(double valor, Conta conta) throws ValorInvalidoException {
		conta.depositar(valor);
	}

	public void sacar(double valor, Conta conta) throws ValorInvalidoException, SaldoInsuficienteException {
		conta.sacar(valor);
	}

	public void transferir(double valor, Conta conta, Conta contaDestino) throws SaldoInsuficienteException, ValorInvalidoException {
		conta.transferir(valor, contaDestino);
	}

}
