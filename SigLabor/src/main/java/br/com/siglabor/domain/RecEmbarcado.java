package br.com.siglabor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class RecEmbarcado extends GenericDomain {

	private static final long serialVersionUID = 1L;
	@Column(nullable = false, length = 8)
	private String placa;

	@Column(nullable = true, length = 7)
	private Long nf;

	@ManyToOne
	@JoinColumn(nullable = false)
	private FornecedorCliente fornecedorCliente;

	@OneToOne
	@JoinColumn
	private Amostra amostra;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Long getNf() {
		return nf;
	}

	public void setNf(Long nf) {
		this.nf = nf;
	}

	public FornecedorCliente getFornecedorCliente() {
		return fornecedorCliente;
	}

	public void setFornecedorCliente(FornecedorCliente fornecedorCliente) {
		this.fornecedorCliente = fornecedorCliente;
	}

	public Amostra getAmostra() {
		return amostra;
	}

	public void setAmostra(Amostra amostra) {
		this.amostra = amostra;
	}

}
