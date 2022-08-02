package br.banco.conta;

import br.exception.SaldoInsuficienteException;
import br.exception.ValorInvalidoException;

public abstract class Conta {
	
	protected final int numero;
	protected final int agencia;
	protected String extrato;
	protected double saldo;

	public Conta(int numero, int agencia) {
		this.numero = numero;
		this.agencia = agencia;
		this.saldo = 0.00;
	}

	public int getNumero() {
		return numero;
	}

	public int getAgencia() {
		return agencia;
	}

	public double getSaldo() {
		return saldo;
	}

	public String getExtrato() {
		String extrato = this.extrato;
		extrato += "Saldo: " + saldo;
		return extrato;
	}

	public void depositar(double valor) throws ValorInvalidoException {
		if (valor <= 0) throw new ValorInvalidoException();

		saldo += valor;
		extrato += "Depósito: " + valor + "\n";
	}

	public void sacar(double valor) throws ValorInvalidoException, SaldoInsuficienteException {
		if (valor <= 0) throw new ValorInvalidoException();
		if (valor > saldo) throw new SaldoInsuficienteException();

		saldo -= valor;
		extrato += "Saque: " + valor + "\n";
	}

	public void transferir(double valor, Conta conta) throws SaldoInsuficienteException, ValorInvalidoException {
		if (valor > saldo) throw new SaldoInsuficienteException();
		if (valor <= 0) throw new ValorInvalidoException();

		this.saldo -= valor;
		conta.saldo += valor;
		extrato += "Transfêrencia: -" + valor + "\n";
		conta.extrato += "Transfêrencia: +" + valor + "\n";
	}

}
