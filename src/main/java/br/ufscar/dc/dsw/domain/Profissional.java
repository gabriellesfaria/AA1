package br.ufscar.dc.dsw.domain;

public class Profissional {

	private String email;
	private String senha;
	private Long cpf; // pk
	private String nome;
	private String telefone;
	private String sexo;
	private String nascimento;
	
	public Profissional(Long cpf) {
		this.cpf = cpf;
	}
	
	public Profissional(String email, String senha, Long cpf, String nome, String telefone, String sexo, String nascimento) {
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.sexo = sexo;
		this.nascimento = nascimento;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Long getCpf() {
		return cpf;
	}
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNascimento() {
		return nascimento;
	}
	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}


}