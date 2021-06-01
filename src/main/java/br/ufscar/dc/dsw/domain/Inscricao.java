package br.ufscar.dc.dsw.domain;

import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Vaga;


public class Inscricao {
	private Long id;
	private String cv;
	private String status; //aberta, nao satisfaz, entrevista
	private String data_inscricao;
	private Long profissional; // fk de profissional
	private Long vaga; // fk de vaga
	
	public Inscricao(Long id) {
		this.id = id;
	}
	
	public Inscricao(Long id, String cv, String status, String data_inscricao, Long profissional, Long vaga) {
		this.id = id;
		this.cv = cv;
		this.status = status;
		this.data_inscricao = data_inscricao;
		this.profissional = profissional;
		this.vaga = vaga;
	}

	public Inscricao(String cv, String status, String data_inscricao, Long profissional, Long vaga) {
		this.cv = cv;
		this.status = status;
		this.data_inscricao = data_inscricao;
		this.profissional = profissional;
		this.vaga = vaga;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDataInscricao() {
		return data_inscricao;
	}	
	public void setDataInscricao(String data_inscricao) {
		this.data_inscricao = data_inscricao;
	}
	
	public String getCV() {
		return cv;
	}	
	public void setCV(String cv) {
		this.cv = cv;
	}
	
	public String getStatus() {
		return status;
	}	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Long getProfissional() {
		return profissional;
	}	
	public void setProfissional(Long profissional) {
		this.profissional = profissional;
	}
	
	public Long getVaga() {
		return vaga;
	}	
	public void setVaga(Long vaga) {
		this.vaga = vaga;
	}
	

}
