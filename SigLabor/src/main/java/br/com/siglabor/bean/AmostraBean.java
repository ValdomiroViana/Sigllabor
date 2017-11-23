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
import br.com.siglabor.dao.ProdutoDAO;
import br.com.siglabor.dao.TipoProdutoDAO;
import br.com.siglabor.domain.Amostra;
import br.com.siglabor.domain.CheckList;
import br.com.siglabor.domain.FornecedorCliente;
import br.com.siglabor.domain.Produto;
import br.com.siglabor.domain.RecEmbarcado;
import br.com.siglabor.domain.TipoProduto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class AmostraBean implements Serializable {
	// instanciar um novo
	private Amostra amostra;
	private Amostra ulimaAmostra;
	private Produto produto;
	// criar uma lista de amostras
	private List<Amostra> amostras;
	// instanciar um checklist
	private CheckList checkList;
	// criar uma lista de checklists
	private List<CheckList> checkLists;
	// Instanciar um produto
	private List<Produto> produtos;
	// criar uma lista de tipos de produto
	private List<TipoProduto> tiposProduto;
	private RecEmbarcado recEmbarcado;
	private List<RecEmbarcado> recEmbarcados;
	private FornecedorCliente fornecedorCliente;
	private List< FornecedorCliente> fornecedorClientes;

	public AmostraBean() {
		amostra = new Amostra();
		recEmbarcado = new RecEmbarcado();
		setFornecedorCliente(new FornecedorCliente());
	}

	// Getters and setters
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

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<TipoProduto> getTiposProduto() {
		return tiposProduto;
	}

	public void setTiposProduto(List<TipoProduto> tiposProduto) {
		this.tiposProduto = tiposProduto;
	}

	public RecEmbarcado getRecEmbarcado() {
		return recEmbarcado;
	}

	public void setRecEmbarcado(RecEmbarcado recEmbarcado) {
		this.recEmbarcado = recEmbarcado;
	}

	public List<RecEmbarcado> getRecEmbarcados() {
		return recEmbarcados;
	}

	public void setRecEmbarcados(List<RecEmbarcado> recEmbarcados) {
		this.recEmbarcados = recEmbarcados;
	}

	public FornecedorCliente getFornecedorCliente() {
		return fornecedorCliente;
	}

	public void setFornecedorCliente(FornecedorCliente fornecedorCliente) {
		this.fornecedorCliente = fornecedorCliente;
	}
	
	

	public List<FornecedorCliente> getFornecedorClientes() {
		return fornecedorClientes;
	}

	public void setFornecedorClientes(List<FornecedorCliente> fornecedorClientes) {
		this.fornecedorClientes = fornecedorClientes;
	}
	

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	// Método Novo
	public void novo() {
		try {
			amostra = new Amostra();
			recEmbarcado = new RecEmbarcado();
			amostra.setRecembarcado(new RecEmbarcado());
			recEmbarcado.setFornecedorCliente(new FornecedorCliente());
			// preencher a lista de produtos para preencher a combobox
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listarOrdenado("descricao");
			// O combobox de tipos de produto fica vazio
			tiposProduto = new ArrayList<>();
			// preencher a lista de Checklists
			CheckListDAO checkListDAO = new CheckListDAO();
			checkLists = checkListDAO.listarOrdenado("dataCheckList");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao gerar uma nova amostra");
			erro.printStackTrace();
		}
	}

	// Método salvar
	public void salvar() {
		try {
			AmostraDAO amostraDAO = new AmostraDAO();
			amostraDAO.salvar(amostra);
			// criar uma nova amostra
			amostra = new Amostra();
			amostras = amostraDAO.listar();
			Long ultima = amostraDAO.sqlMax(amostra);
			//Amostra ultimaAmostra = amostraDAO.buscar(ultima);
			System.out.println("Código amostra: " + ultima);
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listarOrdenado("descricao");
			tiposProduto = new ArrayList<>();
			Messages.addGlobalInfo("Amostra salva com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a amostra.");
			erro.printStackTrace();
		}
	}
	

	// a anotation @post construct serve para carregar a
	// listagem
	@PostConstruct
	public void listarOrdenado() {
		try {
			AmostraDAO amostrDAO = new AmostraDAO();
			amostras = amostrDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as amostras.");
			erro.printStackTrace();
		}
	}

	// edição de amostra
	public void editar(ActionEvent evento) {
		try{
		amostra = (Amostra) evento.getComponent().getAttributes().get("amostraSelecionada");
		produto = amostra.getTipoProduto().getProduto();
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtos = produtoDAO.listar();
		
		TipoProdutoDAO tipoProdutoDAO = new TipoProdutoDAO();
		tiposProduto = tipoProdutoDAO.buscarPorProduto(produto.getCodigo());
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar selecionar uma amostra");
		}
	}

	// método iniciar serve para preencher a lista quando for construída a
	// página
	@PostConstruct
	public void iniciar() {
		try {
			AmostraDAO amostraDAO = new AmostraDAO();
			amostras = amostraDAO.listarOrdenadoDesc("codigoAmostra");
			CheckListDAO checkListDAO = new CheckListDAO();
			checkLists = checkListDAO.listar();
			FornecedorClienteDAO fornecedorClienteDAO = new FornecedorClienteDAO();
			fornecedorClientes = fornecedorClienteDAO.listar();
		} catch (RuntimeException runtimeException) {

			Messages.addGlobalError(runtimeException.getMessage());

		}
	}

	public Amostra getUlimaAmostra() {
		return ulimaAmostra;
	}

	public void setUlimaAmostra(Amostra ulimaAmostra) {
		this.ulimaAmostra = ulimaAmostra;
	}

}
