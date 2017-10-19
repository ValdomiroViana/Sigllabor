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
import javax.persistence.Transient;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
public class Amostra extends GenericDomain {

	private static final long serialVersionUID = 1L;
	//Cria um código auto-incremento para cada amostra cadastrada
	@Column(columnDefinition="serial")
	@Generated(GenerationTime.INSERT)
	private Long codigoAmostra;

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

	@OneToOne (mappedBy="amostra")
	private RecEmbarcado recembarcado;
	
	@Transient 
	private Amostra amostraSelecionada;

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

	public Long getCodigoAmostra() {
		return codigoAmostra;
	}

	public void setCodigoAmostra(Long codigoAmostra) {
		this.codigoAmostra = codigoAmostra;
	}

	public RecEmbarcado getRecembarcado() {
		return recembarcado;
	}

	public void setRecembarcado(RecEmbarcado recembarcado) {
		this.recembarcado = recembarcado;
	}

	public Amostra getAmostraSelecionada() {
		return amostraSelecionada;
	}

	public void setAmostraSelecionada(Amostra amostraSelecionada) {
		this.amostraSelecionada = amostraSelecionada;
	}
	
	
	

}
