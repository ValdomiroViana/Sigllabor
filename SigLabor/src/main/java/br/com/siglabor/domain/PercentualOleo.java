package br.com.siglabor.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class PercentualOleo extends GenericDomain{

	private static final long serialVersionUID = 1L;
	
	@Column(length = 4, nullable = false)
	private String identificacao;
	
	@Column(nullable = false, precision = 7, scale = 4)
	private BigDecimal pa;
	
	@Column( precision = 7, scale = 4)
	private BigDecimal pb;
	
	@Column( precision = 7, scale = 4)
	private BigDecimal pf;
	
	@Column( precision = 7, scale = 4)
	private BigDecimal percentualOleo;
	
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

	public BigDecimal getPb() {
		return pb;
	}

	public void setPb(BigDecimal pb) {
		this.pb = pb;
	}

	public BigDecimal getPf() {
		return pf;
	}

	public void setPf(BigDecimal pf) {
		this.pf = pf;
	}

	public BigDecimal getPercentualOleo() {
		return percentualOleo;
	}

	public void setPercentualOleo(BigDecimal percentualOleo) {
		this.percentualOleo = percentualOleo;
	}

	public Amostra getAmostra() {
		return amostra;
	}

	public void setAmostra(Amostra amostra) {
		this.amostra = amostra;
	}
	
	
}
