package br.ufscar.dc.dsw.domain;

public class Empresa {

	private String email;
	private String senha;
	private Long cnpj;
	private String nome;
	private String descricao;
	private String cidade;
	
	public Empresa(Long cnpj) {
		this.cnpj = cnpj;
	}
	
	public Empresa(String email, String senha, Long cnpj, String nome, String descricao, String cidade) {
		this.email = email;
		this.senha = senha;
		this.cnpj = cnpj;
		this.nome = nome;
		this.cidade = cidade;
		this.descricao = descricao;
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
	
	public Long getCnpj() {
		return cnpj;
	}
	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}