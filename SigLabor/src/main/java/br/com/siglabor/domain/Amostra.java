package br.com.siglabor.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Amostra extends GenericDomain {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataColeta;

	@ManyToOne
	@JoinColumn(nullable = false)
	private TipoProduto tipoProduto;

	@OneToOne
	@JoinColumn( nullable = false)
	private CheckList checkList;

	@OneToOne
	@JoinColumn(nullable = false)
	private Turno turnoColeta;
	
	@Column (nullable = true, precision = 7, scale = 6)
	private BigDecimal fatorCorrecao;

	public Turno getTurnoColeta() {
		return turnoColeta;
	}

	public void setTurnoColeta(Turno turnoColeta) {
		this.turnoColeta = turnoColeta;
	}

	public Date getDataColeta() {
		return dataColeta;
	}

	public void setDataColeta(Date dataColeta) {
		this.dataColeta = dataColeta;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public CheckList getCheckList() {
		return checkList;
	}

	public void setCheckList(CheckList checkList) {
		this.checkList = checkList;
	}

	public BigDecimal getFatorCorrecao() {
		return fatorCorrecao;
	}

	public void setFatorCorrecao(BigDecimal fatorCorrecao) {
		this.fatorCorrecao = fatorCorrecao;
	}
	
	

}
