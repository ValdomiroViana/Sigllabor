package br.com.siglabor.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.siglabor.dao.AmostraDAO;
import br.com.siglabor.dao.PhDAO;
import br.com.siglabor.domain.Amostra;
import br.com.siglabor.domain.Ph;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PhBean implements Serializable {

	private Ph ph;
	private Amostra amostra;
	private List<Ph> phs;
	private List<Amostra> amostras;

	public PhBean() {
		ph = new Ph();
	}

	public Ph getPh() {
		return ph;
	}

	public void setPh(Ph ph) {
		this.ph = ph;
	}

	public Amostra getAmostra() {
		return amostra;
	}

	public void setAmostra(Amostra amostra) {
		this.amostra = amostra;
	}

	public List<Ph> getPhs() {
		return phs;
	}

	public void setPhs(List<Ph> phs) {
		this.phs = phs;
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
			ph = new Ph();
			// Preencher a lista de amostras
			AmostraDAO amostraDAO = new AmostraDAO();
			amostras = amostraDAO.listarOrdenadoPorDataAmostra("dataColeta");

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Erro ao gerar um novo pH");
			erro.printStackTrace();
		}
	}

	// Método salvar
	public void salvar() {
		try {
			PhDAO phDAO = new PhDAO();
			phDAO.salvar(ph);
			// criar um novo ph
			ph = new Ph();
			phs = phDAO.listarOrdenadoPorData("c.dataCheckList");
			Messages.addGlobalInfo("pH salvo com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar o pH.");
			erro.printStackTrace();
		}
	}

	// Método editar
	public void editar(ActionEvent evento) {
		ph = (Ph) evento.getComponent().getAttributes().get("phSelecionado");
	}

	// método iniciar serve para preencher a lista quando for construída a
	// página
	@PostConstruct
	public void iniciar() {
		try {
			PhDAO phDAO = new PhDAO();
			phs = phDAO.listarOrdenadoPorData("c.dataCheckList");
		} catch (RuntimeException runtimeException) {
			Messages.addGlobalError("Erro.");
			runtimeException.printStackTrace();
		}
	}

	// Método para excluir pH
	public void excluir(ActionEvent evento) {
		try {
			ph = (Ph) evento.getComponent().getAttributes().get("phSelecionado");
			PhDAO phDAO = new PhDAO();
			phDAO.excluir(ph);
			phs = phDAO.listarOrdenadoPorData("c.dataCheckList");
			Messages.addGlobalInfo("pH excluído com sucesso!");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar excluir o pH.");
			erro.printStackTrace();
		}
	}

}
