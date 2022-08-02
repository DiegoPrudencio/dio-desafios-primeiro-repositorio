package br.banco.conta;

public class ContaPoupanca extends Conta {

	public ContaPoupanca(int numero, int agencia) {
		super(numero, agencia);

		super.extrato = "--- --- --- --- Extrato --- --- --- ---\n"
					  + "Agência: " + super.agencia + "\n"
				      + "Número da conta: " + super.numero + "\n"
					  + "Tipo da Conta: Conta Poupança\n\n"
					  + "-------------- Histórico --------------\n";
	}

}
