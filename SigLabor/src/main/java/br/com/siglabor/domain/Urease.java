package br.com.siglabor.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Urease extends GenericDomain {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, precision = 7, scale = 2)
	private BigDecimal pHa;

	@Column(precision = 7, scale = 2)
	private BigDecimal pHs;

	@Column(precision = 7, scale = 2)
	private BigDecimal diferenca;

	@Column(precision = 7, scale = 2)
	private BigDecimal urease;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Amostra amostra;

	public BigDecimal getpHa() {
		return pHa;
	}

	public void setpHa(BigDecimal pHa) {
		this.pHa = pHa;
	}

	public BigDecimal getpHs() {
		return pHs;
	}

	public void setpHs(BigDecimal pHs) {
		this.pHs = pHs;
	}

	public BigDecimal getDiferenca() {
		return diferenca;
	}

	public void setDiferenca(BigDecimal diferenca) {
		this.diferenca = diferenca;
	}

	public BigDecimal getUrease() {
		return urease;
	}

	public void setUrease(BigDecimal urease) {
		this.urease = urease;
	}

	public Amostra getAmostra() {
		return amostra;
	}

	public void setAmostra(Amostra amostra) {
		this.amostra = amostra;
	}

}
