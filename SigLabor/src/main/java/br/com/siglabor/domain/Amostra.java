package br.com.siglabor.domain;

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
	@JoinColumn(name = "produto_id", nullable = false)
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "tipoProduto_id", nullable = false)
	private TipoProduto tipoProduto;

	@OneToOne
	@JoinColumn(name = "checkList_id", nullable = false)
	private CheckList checkList;

	@OneToOne
	@JoinColumn(name = "turno_id", nullable = false)
	private Turno turnoColeta;

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

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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

}
