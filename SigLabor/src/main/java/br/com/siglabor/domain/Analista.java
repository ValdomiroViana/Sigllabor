package br.com.siglabor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Analista extends GenericDomain{
	
	private static final long serialVersionUID = 1L;

	@Column(length = 20, nullable = false)
	private String nome;
	
	@Column(length = 30, nullable = false)
	private String sobrenome;
	
	@Column(length = 10, nullable = false)
	private String status;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	
}
