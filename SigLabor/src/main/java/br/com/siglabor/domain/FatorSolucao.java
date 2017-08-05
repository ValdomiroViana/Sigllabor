package br.com.siglabor.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class FatorSolucao extends GenericDomain{

	private static final long serialVersionUID = 1L;
	
	@Column (nullable = false)
	private String solucao;
	
	@Column (nullable = false, precision = 7, scale = 6)
	private BigDecimal fator;
	
	@Column (nullable = false)
	private String status;

	public String getSolucao() {
		return solucao;
	}

	public void setSolucao(String solucao) {
		this.solucao = solucao;
	}

	public BigDecimal getFator() {
		return fator;
	}

	public void setFator(BigDecimal fator) {
		this.fator = fator;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
