package br.com.siglabor.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ph extends GenericDomain{

	private static final long serialVersionUID = 1L;
	
	@Column( precision = 7, scale = 2)
	private BigDecimal ph;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Amostra amostra;

	public BigDecimal getPh() {
		return ph;
	}

	public void setPh(BigDecimal ph) {
		this.ph = ph;
	}

	public Amostra getAmostra() {
		return amostra;
	}

	public void setAmostra(Amostra amostra) {
		this.amostra = amostra;
	}
	
	

}
