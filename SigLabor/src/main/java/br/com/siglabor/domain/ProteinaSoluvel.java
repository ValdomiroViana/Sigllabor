package br.com.siglabor.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
@Entity
public class ProteinaSoluvel extends GenericDomain {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 10)
	private String identificacao;

	@Column(nullable = false, precision = 7, scale = 4)
	private BigDecimal pa;

	@Column(nullable = true, precision = 7, scale = 2)
	private BigDecimal valorBranco;

	@Column(nullable = true, precision = 7, scale = 2)
	private BigDecimal valorGastoAmostra;

	@Column(nullable = true, precision = 7, scale = 2)
	private BigDecimal proteinaSoluvel;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Amostra amostra;

	@ManyToOne
	@JoinColumn(nullable = true)
	private FatorSolucao fatorSolucao;

	@Transient
	private FatorUmidade fatorUmidade;
	
	@Transient
	private FatorUmidade aliquota;

	@OneToOne
	@JoinColumn(nullable = true)
	private ProteinaTotal proteinaTotal;

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

	public BigDecimal getProteinaSoluvel() {
		return proteinaSoluvel;
	}

	public void setProteinaSoluvel(BigDecimal proteinaSoluvel) {
		this.proteinaSoluvel = proteinaSoluvel;
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

	public ProteinaTotal getProteinaTotal() {
		return proteinaTotal;
	}

	public void setProteinaTotal(ProteinaTotal proteinaTotal) {
		this.proteinaTotal = proteinaTotal;
	}

	public FatorUmidade getFatorUmidade() {
		return fatorUmidade;
	}

	public void setFatorUmidade(FatorUmidade fatorUmidade) {
		this.fatorUmidade = fatorUmidade;
	}

	public FatorUmidade getAliquota() {
		return aliquota;
	}

	public void setAliquota(FatorUmidade aliquota) {
		this.aliquota = aliquota;
	}
	
	

}
