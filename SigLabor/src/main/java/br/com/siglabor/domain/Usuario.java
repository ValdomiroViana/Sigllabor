package br.com.siglabor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity

public class Usuario extends GenericDomain{
	
	private static final long serialVersionUID = 1L;
	@Column(length = 50, nullable = false)
	private String nomeDoUsuario;
	
	@Column(length = 50, nullable = false)
	private String email;
	
	
	@Column(length = 32, nullable = false)
	private String senha;
	
	@Transient
	private String senhaSemCriptografia;
	
	@Column(nullable = false)
	private Character tipo;
	
	@Column(nullable = false)
	private Boolean ativo;

	public String getNomeDoUsuario() {
		return nomeDoUsuario;
	}

	public void setNomeDoUsuario(String nomeDoUsuario) {
		this.nomeDoUsuario = nomeDoUsuario;
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

	public String getSenhaSemCriptografia() {
		return senhaSemCriptografia;
	}

	public void setSenhaSemCriptografia(String senhaSemCriptografia) {
		this.senhaSemCriptografia = senhaSemCriptografia;
	}

	public Character getTipo() {
		return tipo;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	@Transient
	public String getTipoFormatado(){
		String tipoFormatado = null;
		if(tipo == 'S'){
			tipoFormatado = "Supervisor";
		}else if(tipo == 'A'){
			tipoFormatado = "Analista";
		}else if(tipo == 'V'){
			tipoFormatado = "Visitante";
		}
		return tipoFormatado;
		
	}
	
	@Transient
	public String getAtivoInativo(){
		String ativo = null;
		if(this.ativo = true){
			ativo = "Ativo";
		}else if(this.ativo != true){
			ativo = "Inativo";
		}
		return ativo;
	}
	
}
