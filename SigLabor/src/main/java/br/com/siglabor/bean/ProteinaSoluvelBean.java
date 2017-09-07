package br.com.siglabor.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.siglabor.dao.AmostraDAO;
import br.com.siglabor.dao.ProteinaSoluvelDAO;
import br.com.siglabor.dao.ProteinaTotalDAO;
import br.com.siglabor.domain.Amostra;
import br.com.siglabor.domain.ProteinaSoluvel;
import br.com.siglabor.domain.ProteinaTotal;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProteinaSoluvelBean implements Serializable {

	private ProteinaSoluvel proteinaSoluvel;
	private Amostra amostra;
	private ProteinaTotal proteinaTotal;
	private List<ProteinaSoluvel> proteinasSoluveis;
	private List<Amostra> amostras;
	private List<ProteinaTotal> proteinasTotais;

	public ProteinaSoluvel getProteinaSoluvel() {
		return proteinaSoluvel;
	}

	public void setProteinaSoluvel(ProteinaSoluvel proteinaSoluvel) {
		this.proteinaSoluvel = proteinaSoluvel;
	}

	public Amostra getAmostra() {
		return amostra;
	}

	public void setAmostra(Amostra amostra) {
		this.amostra = amostra;
	}

	public ProteinaTotal getProteinaTotal() {
		return proteinaTotal;
	}

	public void setProteinaTotal(ProteinaTotal proteinaTotal) {
		this.proteinaTotal = proteinaTotal;
	}

	public List<ProteinaSoluvel> getProteinasSoluveis() {
		return proteinasSoluveis;
	}

	public void setProteinasSoluveis(List<ProteinaSoluvel> proteinasSoluveis) {
		this.proteinasSoluveis = proteinasSoluveis;
	}

	public List<Amostra> getAmostras() {
		return amostras;
	}

	public void setAmostras(List<Amostra> amostras) {
		this.amostras = amostras;
	}
	
	

	// Método novo
	public List<ProteinaTotal> getProteinasTotais() {
		return proteinasTotais;
	}

	public void setProteinasTotais(List<ProteinaTotal> proteinasTotais) {
		this.proteinasTotais = proteinasTotais;
	}

	public void novo() {
		try {
			proteinaSoluvel = new ProteinaSoluvel();
			// preencher a listagem de amostras
			AmostraDAO amostraDAO = new AmostraDAO();
			amostras = amostraDAO.listarOrdenadoPorDataAmostra("dataColeta");
			
			ProteinaTotalDAO proteinaTotalDAO = new ProteinaTotalDAO();
			proteinasTotais = proteinaTotalDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar gerar uma nova proteína solúvel.");
			erro.printStackTrace();
		}
	}

	// Método salvar
	public void salvar() {
		try {
			ProteinaSoluvelDAO proteinaSoluvelDAO = new ProteinaSoluvelDAO();
			proteinaSoluvelDAO.salvar(proteinaSoluvel);
			// criar uma nova proteína solúvel
			proteinaSoluvel = new ProteinaSoluvel();
			proteinasSoluveis = proteinaSoluvelDAO.listarOrdenadoPorData("c.dataCheckList");
			Messages.addGlobalInfo("Proteína solúvel sava xom sucesso!");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a proteína solúvel.");
			erro.printStackTrace();
		}
	}
	
	// a anotation @post construct serve para carregar a
	// listagem
	@PostConstruct
	public void listarOrdenado() {
		try {
			ProteinaSoluvelDAO proteinaSoluvelDAO = new ProteinaSoluvelDAO();
			proteinasSoluveis = proteinaSoluvelDAO.listarOrdenadoPorData("c.dataCheckList");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as proteínas.");
			erro.printStackTrace();
		}
	}

	//Edição de proteína solúvel
	public void editar(ActionEvent evento){
		proteinaSoluvel = (ProteinaSoluvel) evento.getComponent().getAttributes().get("proteinasoluvelSelecionada");
	}
	
	// método iniciar serve para preencher a lista quando for construída a
		// página
	@PostConstruct 
	public void iniciar(){
		try{
			ProteinaSoluvelDAO proteinaSoluvelDAO = new ProteinaSoluvelDAO();
			proteinasSoluveis = proteinaSoluvelDAO.listarOrdenadoPorData("c.dataCheckList");
		}catch(RuntimeException runtimeException){
			Messages.addGlobalError("Erro");
			runtimeException.printStackTrace();
		}
	}
	
	// método excluir
	public void excluir(ActionEvent evento) {
		try {
			proteinaSoluvel = (ProteinaSoluvel) evento.getComponent().getAttributes().get("proteinaSoluvelSelecionada");
			ProteinaSoluvelDAO proteinaSoluvelDAO = new ProteinaSoluvelDAO();
			proteinaSoluvelDAO.excluir(proteinaSoluvel);
			proteinasSoluveis = proteinaSoluvelDAO.listarOrdenadoPorData("c.dataCheckList");
			Messages.addGlobalInfo("Proteína solúvel excluída com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar excluir a proteína.");
			erro.printStackTrace();
		}
	}
	
	public void calcularProteinaSoluvel(){
		ProteinaSoluvelDAO proteinaSoluvelDAO = new ProteinaSoluvelDAO();
		proteinaSoluvelDAO.calcularProteinaSoluvel(proteinaSoluvel);
	}
}
