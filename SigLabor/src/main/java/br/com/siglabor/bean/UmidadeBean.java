package br.com.siglabor.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.siglabor.dao.AmostraDAO;
import br.com.siglabor.dao.UmidadeDAO;
import br.com.siglabor.domain.Amostra;
import br.com.siglabor.domain.Umidade;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UmidadeBean implements Serializable {
	// instanciar uma nova umidade
	private Umidade umidade;
	// criar uma lista de umidades
	private List<Umidade> umidades;
	// instanciar uma amostra
	private Amostra amostra;
	// criar uma lista de amostras
	private List<Amostra> amostras;

	public Umidade getUmidade() {
		return umidade;
	}

	public void setUmidade(Umidade umidade) {
		this.umidade = umidade;
	}

	public List<Umidade> getUmidades() {
		return umidades;
	}

	public void setUmidades(List<Umidade> umidades) {
		this.umidades = umidades;
	}

	public Amostra getAmostra() {
		return amostra;
	}

	public void setAmostra(Amostra amostra) {
		this.amostra = amostra;
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
			umidade = new Umidade();
			// prencher a lista de amostras
			AmostraDAO amostraDAO = new AmostraDAO();
			amostras = amostraDAO.listarOrdenadoPorData("dataColeta");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao gerar uma nova umidade");
			erro.printStackTrace();
		}
	}

	// Método salvar
	public void salvar() {
		try {
			UmidadeDAO umidadeDAO = new UmidadeDAO();
			umidadeDAO.salvar(umidade);
			// criar uma nova umidade
			umidade = new Umidade();
			umidades = umidadeDAO.listar();
			Messages.addGlobalInfo("Umidade salva com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a umidade");
			erro.printStackTrace();
		}
	}

	// a anotation @post construct serve para carregar a
	// listagem
	@PostConstruct
	public void listarordenado() {
		try {
			UmidadeDAO umidadeDAO = new UmidadeDAO();
			umidades = umidadeDAO.listarOrdenadoPorData("amostra.dataColeta");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as umidades");
			erro.printStackTrace();
		}
	}

	// Edição de amostras
	public void editar(ActionEvent evento) {
		umidade = (Umidade) evento.getComponent().getAttributes().get("umidadeSelecionada");
	}

	// método iniciar serve para preencher a lista quando for construída a
	// página
	@PostConstruct
	public void iniciar() {
		try {
			UmidadeDAO umidadeDAO = new UmidadeDAO();
			umidades = umidadeDAO.listar();
		} catch (RuntimeException runtimeException) {
			Messages.addGlobalError("Erro");
			runtimeException.printStackTrace();
		}
	}
	
	//Método para excluir umidade
	public void excluir(ActionEvent evento){
		try{
			umidade = (Umidade) evento.getComponent().getAttributes().get("umidadeSelecionada");
			UmidadeDAO umidadeDAO = new UmidadeDAO();
			umidadeDAO.excluir(umidade);
			umidades = umidadeDAO.listar();
			Messages.addGlobalInfo("Umidade excluída com sucesso!");
		}catch(RuntimeException erro){
			Messages.addGlobalError("Erro ao tentar excluir a umidade");
			erro.printStackTrace();
		}
	}
	
// Método para realizar o cálculo de umidade
	public void calcularUmidade() {
		try {
			UmidadeDAO umidadeDAO = new UmidadeDAO();
			umidade = umidadeDAO.calcularUmidade(umidade);
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro");
			erro.printStackTrace();
		}

	}
}
