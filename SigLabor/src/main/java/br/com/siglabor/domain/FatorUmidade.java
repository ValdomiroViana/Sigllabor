package br.com.siglabor.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class FatorUmidade extends GenericDomain {

	private static final long serialVersionUID = 1L;
	@OneToOne
	@JoinColumn(nullable = false)
	private Umidade p_umidade;

	@OneToOne
	@JoinColumn(nullable = false)
	private Umidade s_umidade;

	@Column(precision = 7, scale = 6)
	private BigDecimal fatorUmidade;

	public Umidade getP_umidade() {
		return p_umidade;
	}

	public void setP_umidade(Umidade p_umidade) {
		this.p_umidade = p_umidade;
	}

	public Umidade getS_umidade() {
		return s_umidade;
	}

	public void setS_umidade(Umidade s_umidade) {
		this.s_umidade = s_umidade;
	}

	public BigDecimal getFatorUmidade() {
		return fatorUmidade;
	}

	public void setFatorUmidade(BigDecimal fatorUmidade) {
		this.fatorUmidade = fatorUmidade;
	}

}
