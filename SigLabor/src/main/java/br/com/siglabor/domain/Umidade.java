package br.com.siglabor.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Umidade extends GenericDomain{
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private Character tipo;
	
	@Column(length = 4, nullable = false)
	private String identificacao;
	
	@Column(nullable = false, precision = 7, scale = 4)
	private BigDecimal pa;
	
	@Column( precision = 7, scale = 4)
	private BigDecimal pa_Mais_b;
	
	@Column( precision = 7, scale = 4)
	private BigDecimal pf;
	
	@Column( precision = 7, scale = 4)
	private BigDecimal umidade;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Amostra amostra;

	public Character getTipo() {
		return tipo;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

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

	public BigDecimal getPa_Mais_b() {
		return pa_Mais_b;
	}

	public void setPa_Mais_b(BigDecimal pa_Mais_b) {
		this.pa_Mais_b = pa_Mais_b;
	}

	public BigDecimal getPf() {
		return pf;
	}

	public void setPf(BigDecimal pf) {
		this.pf = pf;
	}

	public BigDecimal getUmidade() {
		return umidade;
	}

	public void setUmidade(BigDecimal umidade) {
		this.umidade = umidade;
	}

	public Amostra getAmostra() {
		return amostra;
	}

	public void setAmostra(Amostra amostra) {
		this.amostra = amostra;
	}
	
	

}
