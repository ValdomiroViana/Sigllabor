package br.com.siglabor.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.siglabor.dao.AmostraDAO;
import br.com.siglabor.dao.CheckListDAO;
import br.com.siglabor.dao.FornecedorClienteDAO;
import br.com.siglabor.dao.RecEmbarcadoDAO;
import br.com.siglabor.domain.Amostra;
import br.com.siglabor.domain.CheckList;
import br.com.siglabor.domain.FornecedorCliente;
import br.com.siglabor.domain.RecEmbarcado;

@ManagedBean
@ViewScoped
public class RecEmbarcadoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private RecEmbarcado recEmbarcado;
	private FornecedorCliente fornecedorCliente;
	private Amostra amostra;
	private CheckList checkList;
	private List<RecEmbarcado> recEmbarcados;
	private List<Amostra> amostras;
	private List<FornecedorCliente> fornecedoresClientes;
	private List<CheckList> checkLists;

	public RecEmbarcadoBean() {
		recEmbarcado = new RecEmbarcado();
		amostra = new Amostra();
		checkList = new CheckList();
		checkLists = new ArrayList<>();
		fornecedoresClientes = new ArrayList<>();
		fornecedorCliente =new FornecedorCliente();

	}

	public RecEmbarcado getRecEmbarcado() {
		return recEmbarcado;
	}

	public void setRecEmbarcado(RecEmbarcado recEmbarcado) {
		this.recEmbarcado = recEmbarcado;
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

	public List<RecEmbarcado> getRecEmbarcados() {
		return recEmbarcados;
	}

	public void setRecEmbarcados(List<RecEmbarcado> recEmbarcados) {
		this.recEmbarcados = recEmbarcados;
	}

	public List<Amostra> getAmostras() {
		return amostras;
	}

	public void setAmostras(List<Amostra> amostras) {
		this.amostras = amostras;
	}

	public List<FornecedorCliente> getFornecedoresClientes() {
		return fornecedoresClientes;
	}

	public void setFornecedoresClientes(List<FornecedorCliente> fornecedoresClientes) {
		this.fornecedoresClientes = fornecedoresClientes;
	}

	public CheckList getCheckList() {
		return checkList;
	}

	public void setCheckList(CheckList checkList) {
		this.checkList = checkList;
	}

	public List<CheckList> getCheckLists() {
		return checkLists;
	}

	public void setCheckLists(List<CheckList> checkLists) {
		this.checkLists = checkLists;
	}

	// Método novo
	public void novo() {
		recEmbarcado = new RecEmbarcado();
		amostra = new Amostra();

	}
	
	//Método listar
	@PostConstruct
	public void listar(){
		
		try {
			CheckListDAO checkListDAO = new CheckListDAO();
			checkLists = checkListDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar os itens");
			erro.printStackTrace();
		}
	}
	
	public void adicionarFornecedor(){
		try{
			FornecedorClienteDAO fornecedorClienteDAO = new FornecedorClienteDAO();
			fornecedoresClientes = fornecedorClienteDAO.listar();
		}catch(RuntimeException erro){
			Messages.addGlobalError("Erro ao listar os fornecedores");
			erro.printStackTrace();
		}
	}

	// Método salvar
	
	public void salvar(){
		try{
			AmostraDAO amostraDAO = new AmostraDAO();
			amostraDAO.salvar(amostra, recEmbarcado);
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar!");
			erro.printStackTrace();
		}
	}

	
	public void editar(ActionEvent evento){
		recEmbarcado = (RecEmbarcado) evento.getComponent().getAttributes().get("amostraRecEmbarcadoSelecionada");
				
	}
	
	

}
