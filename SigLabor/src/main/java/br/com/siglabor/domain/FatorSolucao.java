package br.com.siglabor.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class FatorSolucao extends GenericDomain{

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataFatoracao;

	
	@Column (nullable = false, precision = 7, scale = 6)
	private BigDecimal fatorAcido;
	
	@Column (nullable = false, precision = 7, scale = 6)
	private BigDecimal fatorHidroxido;
	
	@Column (nullable = false)
	private String status;


	public Date getDataFatoracao() {
		return dataFatoracao;
	}

	public void setDataFatoracao(Date dataFatoracao) {
		this.dataFatoracao = dataFatoracao;
	}
	
	public BigDecimal getFatorAcido() {
		return fatorAcido;
	}

	public void setFatorAcido(BigDecimal fatorAcido) {
		this.fatorAcido = fatorAcido;
	}

	public BigDecimal getFatorHidroxido() {
		return fatorHidroxido;
	}

	public void setFatorHidroxido(BigDecimal fatorHidroxido) {
		this.fatorHidroxido = fatorHidroxido;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
