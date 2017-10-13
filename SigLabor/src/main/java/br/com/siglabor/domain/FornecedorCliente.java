package br.com.siglabor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
@SuppressWarnings ("serial")
@Entity
public class FornecedorCliente extends GenericDomain{
	
	@Column (length = 50, nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private Character tipo;
	
	@ManyToOne
	@JoinColumn (nullable = false)
	private Cidade cidade;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Character getTipo() {
		return tipo;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	@Transient
	public String getTipoFormatado(){
		String tipoFormatado = null;
		
		if(tipo == 'c') {
			tipoFormatado = "Cliente";
		}else if(tipo == 'f') {
			tipoFormatado = "Fornecedor";
		}
		return tipoFormatado;
	}
	
	

}
