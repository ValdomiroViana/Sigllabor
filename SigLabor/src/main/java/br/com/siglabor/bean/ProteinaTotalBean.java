package br.com.siglabor.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.siglabor.dao.AmostraDAO;
import br.com.siglabor.dao.ProteinaTotalDAO;
import br.com.siglabor.domain.Amostra;
import br.com.siglabor.domain.ProteinaTotal;
import br.com.siglabor.domain.Umidade;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProteinaTotalBean implements Serializable {
	private ProteinaTotal proteinaTotal;
	private Amostra amostra;
	private List<ProteinaTotal> proteinasTotais;
	private List<Amostra> amostras;
	private List<Umidade> umidades;

	public void setUmidades(List<Umidade> umidades) {
		this.umidades = umidades;
	}

	public ProteinaTotal getProteinaTotal() {
		return proteinaTotal;
	}

	public void setProteinaTotal(ProteinaTotal proteinaTotal) {
		this.proteinaTotal = proteinaTotal;
	}

	public Amostra getAmostra() {
		return amostra;
	}

	public void setAmostra(Amostra amostra) {
		this.amostra = amostra;
	}

	public List<ProteinaTotal> getProteinasTotais() {
		return proteinasTotais;
	}

	public void setProteinasTotais(List<ProteinaTotal> proteinasTotais) {
		this.proteinasTotais = proteinasTotais;
	}

	public List<Amostra> getAmostras() {
		return amostras;
	}

	public void setAmostras(List<Amostra> amostras) {
		this.amostras = amostras;
	}

	public List<Umidade> getUmidades() {
		return umidades;
	}

	// Método novo
	public void novo() {
		try {
			proteinaTotal = new ProteinaTotal();
			// preencher a listagem de amostras
			AmostraDAO amostraDAO = new AmostraDAO();
			amostras = amostraDAO.listarOrdenadoPorDataAmostra("dataColeta");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gerar uma nova proteína total.");
			erro.printStackTrace();
		}
	}

	// Método salvar
	public void salvar() {
		try {
			ProteinaTotalDAO proteinaTotalDAO = new ProteinaTotalDAO();
			proteinaTotalDAO.salvar(proteinaTotal);
			// criar um anova proteína
			proteinaTotal = new ProteinaTotal();
			proteinasTotais = proteinaTotalDAO.listarOrdenadoPorData("c.dataCheckList");
			Messages.addGlobalInfo("Proteína total salva com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar a proteína total.");
			erro.printStackTrace();
		}
	}

	// a anotation @post construct serve para carregar a
	// listagem
	@PostConstruct
	public void listarOrdenado() {
		try {
			ProteinaTotalDAO proteinaTotalDAO = new ProteinaTotalDAO();
			proteinasTotais = proteinaTotalDAO.listarOrdenadoPorData("c.dataCheckList");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as proteínas.");
			erro.printStackTrace();
		}
	}

	// Edição de proteína
	public void editar(ActionEvent evento) {
		proteinaTotal = (ProteinaTotal) evento.getComponent().getAttributes().get("proteinaTotalSelecionado");

	}

	// método iniciar serve para preencher a lista quando for construída a
	// página
	@PostConstruct
	public void iniciar() {
		try {
			ProteinaTotalDAO proteinaTotalDAO = new ProteinaTotalDAO();
			proteinasTotais = proteinaTotalDAO.listarOrdenadoPorData("c.dataCheckList");
		} catch (RuntimeException runtimeException) {
			Messages.addGlobalError("Erro");
			runtimeException.printStackTrace();
		}
	}

	// método excluir
	public void excluir(ActionEvent evento) {
		try {
			proteinaTotal = (ProteinaTotal) evento.getComponent().getAttributes().get("proteinaTotalSelecionada");
			ProteinaTotalDAO proteinaTotalDAO = new ProteinaTotalDAO();
			proteinaTotalDAO.excluir(proteinaTotal);
			proteinasTotais = proteinaTotalDAO.listarOrdenadoPorData("c.dataCheckList");
			Messages.addGlobalInfo("Proteína total excluída com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar excluir a proteína.");
			erro.printStackTrace();
		}
	}

	public void calcularProteina() {
		ProteinaTotalDAO proteinaTotalDAO = new ProteinaTotalDAO();
		proteinaTotalDAO.calcularProteina(proteinaTotal);
	}

}