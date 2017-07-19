package br.com.siglabor.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Produto extends GenericDomain{
	
	private static final long serialVersionUID = 1L;
	@Column(length = 50, nullable = false)
	private String descricao;
	
	@OneToMany(mappedBy = "produto")
	private List<TipoProduto> tiposProduto = new ArrayList<>();
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<TipoProduto> getTiposProduto() {
		return tiposProduto;
	}

	public void setTiposProduto(List<TipoProduto> tiposProduto) {
		this.tiposProduto = tiposProduto;
	}
	
	

}
