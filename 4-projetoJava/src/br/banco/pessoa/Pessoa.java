package br.banco.pessoa;

public abstract class Pessoa {

	protected final int cpf;
	protected final String nome;
	private final String senha;

	public Pessoa(int cpf, String nome, String senha) {
		this.cpf = cpf;
		this.nome = nome;
		this.senha = senha;
	}

	public int getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public boolean logar(int cpf, String senha) {
		if (this.cpf == cpf && this.senha.equals(senha)) return true;
		else return false;
	}

}
