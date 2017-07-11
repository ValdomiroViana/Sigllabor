package br.com.siglabor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Usuario extends GenericDomain{

	private static final long serialVersionUID = 1L;
	@Column(length = 50, nullable = false)
	private String nomeUsuario;
	
	@Column(length = 50, nullable = false)
	private String email;
	
	@Column(length = 10, nullable = false)
	private String senha;

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
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
	
	
	
}
