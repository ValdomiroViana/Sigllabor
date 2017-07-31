package br.com.siglabor.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.siglabor.dao.AmostraDAO;
import br.com.siglabor.dao.ClorofilaDAO;
import br.com.siglabor.domain.Amostra;
import br.com.siglabor.domain.Clorofila;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClorofilaBean implements Serializable {
	// instanciar uam clorofila
	private Clorofila clorofila;
	private Amostra amostra;
	private List<Clorofila> clorofilas;
	private List<Amostra> amostras;

	public Clorofila getClorofila() {
		return clorofila;
	}

	public void setClorofila(Clorofila clorofila) {
		this.clorofila = clorofila;
	}

	public Amostra getAmostra() {
		return amostra;
	}

	public void setAmostra(Amostra amostra) {
		this.amostra = amostra;
	}

	public List<Clorofila> getClorofilas() {
		return clorofilas;
	}

	public void setClorofilas(List<Clorofila> clorofilas) {
		this.clorofilas = clorofilas;
	}

	public List<Amostra> getAmostras() {
		return amostras;
	}

	public void setAmostras(List<Amostra> amostras) {
		this.amostras = amostras;
	}

	// Método novo
	public void novo() {
		try {
			clorofila = new Clorofila();
			// preencher a lista de amostras
			AmostraDAO amostraDAO = new AmostraDAO();
			amostras = amostraDAO.listarOrdenadoPorDataAmostra("dataColeta");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao gerar uma nov aclorofila.");
			erro.printStackTrace();
		}
	}

	// Método salvar
	public void salvar() {
		try {
			ClorofilaDAO clorofilaDAO = new ClorofilaDAO();
			clorofilaDAO.salvar(clorofila);
			clorofila = new Clorofila();
			clorofilas = clorofilaDAO.listarOrdenadoPorData("c.dataCheckList");
			Messages.addGlobalInfo("Clorofila salva com sucesso.");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a clorofila.");
			erro.printStackTrace();
		}
	}

	// listagem
	@PostConstruct
	public void listarOrdenado() {
		try {
			ClorofilaDAO clorofilaDAO = new ClorofilaDAO();
			clorofilas = clorofilaDAO.listarOrdenadoPorData("c.dataCheckList");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as clorofilas");
			erro.printStackTrace();
		}
	}

	// Método editar
	public void editar(ActionEvent evento) {
		clorofila = (Clorofila) evento.getComponent().getAttributes().get("clorofilaSelecionada");

	}

	// método iniciar serve para preencher a lista quando for construída a
	// página
	@PostConstruct
	public void iniciar() {
		try {
			ClorofilaDAO clorofilaDAO = new ClorofilaDAO();
			clorofilas = clorofilaDAO.listar();
		} catch (RuntimeException runtimeException) {
			Messages.addGlobalError("Erro");
			runtimeException.printStackTrace();
		}
	}

	// Método para excluir percentual de óleo
	public void excluir(ActionEvent evento) {
		try {
			clorofila = (Clorofila) evento.getComponent().getAttributes().get("clorofilaSelecionada");
			ClorofilaDAO clorofilaDAO = new ClorofilaDAO();
			clorofilaDAO.excluir(clorofila);
			clorofilas = clorofilaDAO.listarOrdenadoPorData("c.dataCheckList");
			Messages.addGlobalInfo("Clorofila excluída com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar excluir a clorofila.");
			erro.printStackTrace();
		}
	}

	// Método para realizar o cálculo de Percentual de óleo
	public void calcularClorofila() {
		try {
			ClorofilaDAO clorofilaDAO = new ClorofilaDAO();
			clorofilaDAO.calcularClorofila(clorofila);
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro");
			erro.printStackTrace();
		}
	}
}
