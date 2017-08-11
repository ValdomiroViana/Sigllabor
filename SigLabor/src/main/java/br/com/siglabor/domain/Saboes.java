package br.com.siglabor.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Saboes extends GenericDomain{

	private static final long serialVersionUID = 1L;
	
	@Column(length = 4, nullable = false )
	private String identificacao;
	
	@Column(nullable = false, precision = 7, scale = 1)
	private BigDecimal pa;
	
	@Column( precision = 7, scale = 1)
	private BigDecimal valorGasto;
	
	
	@Column( precision = 7, scale = 2)
	private BigDecimal sabao;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Amostra amostra;

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	public BigDecimal getPa() {
		return pa;
	}

	public void setPa(BigDecimal pa) {
		this.pa = pa;
	}

	public BigDecimal getValorGasto() {
		return valorGasto;
	}

	public void setValorGasto(BigDecimal valorGasto) {
		this.valorGasto = valorGasto;
	}

	public BigDecimal getSabao() {
		return sabao;
	}

	public void setSabao(BigDecimal sabao) {
		this.sabao = sabao;
	}

	public Amostra getAmostra() {
		return amostra;
	}

	public void setAmostra(Amostra amostra) {
		this.amostra = amostra;
	}
	
	

}
