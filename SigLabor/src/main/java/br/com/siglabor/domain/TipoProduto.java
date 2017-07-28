package br.com.siglabor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@SuppressWarnings("serial")
@Entity
public class TipoProduto extends GenericDomain{
	
	@Column(length = 50, nullable = false)
	private String descricaoTipo;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Produto produto;

	public String getDescricaoTipo() {
		return descricaoTipo;
	}

	public void setDescricaoTipo(String descricaoTipo) {
		this.descricaoTipo = descricaoTipo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
}
