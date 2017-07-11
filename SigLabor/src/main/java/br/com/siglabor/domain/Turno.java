package br.com.siglabor.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Turno extends GenericDomain {

	private static final long serialVersionUID = 1L;

	@Column(length = 20, nullable = false)
	private String descricao;

	@Column(nullable = false)
	@Temporal(TemporalType.TIME)
	private Date horaInicio;

	@Column(nullable = false)
	@Temporal(TemporalType.TIME)
	private Date horaFim;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}

}
