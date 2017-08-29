package br.com.siglabor.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class ProteinaTotal extends GenericDomain{

	
	private static final long serialVersionUID = 1L;
	
	@Column (nullable = false, length = 10)
	private String identificacao;
	
	@Column(nullable = false, precision = 7, scale = 4)
	private BigDecimal pa;
	
	@Column(precision = 7, scale = 2)
	private BigDecimal valorBranco;
	
	@Column(precision = 7, scale = 2)
	private BigDecimal valorGastoAmostra;
	
	@Column( precision = 7, scale = 2)
	private BigDecimal proteinaTotal;
	

	@ManyToOne
	@JoinColumn(nullable = false)
	private Amostra amostra;
	
	@ManyToOne
	@JoinColumn
	private FatorSolucao fatorSolucao;
	
	@Transient
	private Umidade p_umidade;
	@Transient
	private Umidade s_umidade;

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


	public BigDecimal getValorBranco() {
		return valorBranco;
	}


	public void setValorBranco(BigDecimal valorBranco) {
		this.valorBranco = valorBranco;
	}


	public BigDecimal getValorGastoAmostra() {
		return valorGastoAmostra;
	}


	public void setValorGastoAmostra(BigDecimal valorGastoAmostra) {
		this.valorGastoAmostra = valorGastoAmostra;
	}


	public BigDecimal getProteinaTotal() {
		return proteinaTotal;
	}


	public void setProteinaTotal(BigDecimal proteinaTotal) {
		this.proteinaTotal = proteinaTotal;
	}


	public Amostra getAmostra() {
		return amostra;
	}


	public void setAmostra(Amostra amostra) {
		this.amostra = amostra;
	}


	public FatorSolucao getFatorSolucao() {
		return fatorSolucao;
	}


	public void setFatorSolucao(FatorSolucao fatorSolucao) {
		this.fatorSolucao = fatorSolucao;
	}
	
	
	
}
