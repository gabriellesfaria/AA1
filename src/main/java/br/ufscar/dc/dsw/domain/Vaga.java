package br.ufscar.dc.dsw.domain;


public class Vaga {
	private Long id;
	private String nome;
	private String status;
	private String descricao;
	private Float salario;
	private String data_limite;
	private Long empresa; // cnpj de empresa
	
	public Vaga(Long id) {
		this.id = id;
	}
	
	public Vaga(Long id, String nome, String status, String descricao, Float salario, String data_limite, Long empresa) {
		this.id = id;
		this.nome = nome;
		this.status = status;
		this.descricao = descricao;
		this.salario = salario;
		this.data_limite = data_limite;
		this.empresa = empresa;
	}
	public Vaga(String nome, String status, String descricao, Float salario, String data_limite, Long empresa) {
		this.nome = nome;
		this.status = status;
		this.descricao = descricao;
		this.salario = salario;
		this.data_limite = data_limite;
		this.empresa = empresa;
	}

	public Long getId() {
		return id;
	}	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getStatus() {
		return status;
	}	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDescricao() {
		return descricao;
	}	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Float getSalario() {
		return salario;
	}	
	public void setSalario(Float salario) {
		this.salario = salario;
	}

	public String getDataLimite() {
		return data_limite;
	}	
	public void setDataLimite(String data_limite) {
		this.data_limite = data_limite;
	}
	
	public Long getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Long empresa) {
		this.empresa = empresa;
	}
}