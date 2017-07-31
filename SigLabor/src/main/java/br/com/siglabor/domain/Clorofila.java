package br.com.siglabor.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Clorofila extends GenericDomain {

	private static final long serialVersionUID = 1L;

	@Column(precision = 7, scale = 3)
	private BigDecimal nm630;

	@Column(precision = 7, scale = 3)
	private BigDecimal nm670;

	@Column(precision = 7, scale = 3)
	private BigDecimal nm710;

	@Column(precision = 7, scale = 4)
	private BigDecimal clorofila;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Amostra amostra;

	public BigDecimal getNm630() {
		return nm630;
	}

	public void setNm630(BigDecimal nm630) {
		this.nm630 = nm630;
	}

	public BigDecimal getNm670() {
		return nm670;
	}

	public void setNm670(BigDecimal nm670) {
		this.nm670 = nm670;
	}

	public BigDecimal getNm710() {
		return nm710;
	}

	public void setNm710(BigDecimal nm710) {
		this.nm710 = nm710;
	}

	public BigDecimal getClorofila() {
		return clorofila;
	}

	public void setClorofila(BigDecimal clorofila) {
		this.clorofila = clorofila;
	}

	public Amostra getAmostra() {
		return amostra;
	}

	public void setAmostra(Amostra amostra) {
		this.amostra = amostra;
	}

}
