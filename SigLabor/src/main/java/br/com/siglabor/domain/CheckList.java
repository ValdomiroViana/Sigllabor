package br.com.siglabor.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CheckList extends GenericDomain{

	private static final long serialVersionUID = 1L;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataCheckList;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Analista analista;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Turno turno;

	public Date getDataCheckList() {
		return dataCheckList;
	}

	public void setDataCheckList(Date dataCheckList) {
		this.dataCheckList = dataCheckList;
	}

	public Analista getAnalista() {
		return analista;
	}

	public void setAnalista(Analista analista) {
		this.analista = analista;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	
	
}
