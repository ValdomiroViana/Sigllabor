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
import br.com.siglabor.dao.ProdutoDAO;
import br.com.siglabor.domain.Amostra;
import br.com.siglabor.domain.CheckList;
import br.com.siglabor.domain.Produto;
import br.com.siglabor.domain.TipoProduto;
@SuppressWarnings ("serial")
@ManagedBean
@ViewScoped
public class AmostraBean implements Serializable{
	//instanciar um novo
	private Amostra amostra;
	//criar uma lista de amostras
	private List<Amostra> amostras;
	//instanciar um checklist
	private CheckList checkList;
	//criar uma lista de checklists
	private List<CheckList> checkLists;
	//Instanciar um produto
	private Produto produto;
	//Criar uma lista de produtos
	private List<Produto> produtos;
	//instanciar um novo tipo de produto
	private TipoProduto tipoProduto;
	//criar uma lista de tipos de produto
	private List<TipoProduto> tiposProduto;
	public AmostraBean(){
		amostra = new Amostra();
		produto = new Produto();
	}
	//Getters and setters
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
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}
	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
	public List<TipoProduto> getTiposProduto() {
		return tiposProduto;
	}
	public void setTiposProduto(List<TipoProduto> tiposProduto) {
		this.tiposProduto = tiposProduto;
	}
	
	//Método Novo
	public void novo(){
		try{
			amostra = new Amostra();
			//preencher a lista de Checklists
			CheckListDAO checkListDAO = new CheckListDAO();
			checkLists = checkListDAO.listarOrdenado("dataCheckList");
			//preencher a lista de produtos para preencher a combobox
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listarOrdenado("descricao");
			//O combobox de tipos de produto fica vazio
			tiposProduto = new ArrayList<>();
			
			
		}catch(RuntimeException erro){
			Messages.addGlobalError("Erro ao gerar uma nova amostra");
			erro.printStackTrace();
		}
	}
	
	// Método salvar
	public void salvar(){
		try{
			AmostraDAO amostraDAO = new AmostraDAO();
			amostraDAO.salvar(amostra);
			//criar uma nova amostra
			amostra = new Amostra();
			amostras = amostraDAO.listar();
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listarOrdenado("descricao");
			tiposProduto = new ArrayList<>();
			Messages.addGlobalInfo("Amostra salva com sucesso!");
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a amostra.");
			erro.printStackTrace();
		}
	}
	
	// a anotation @post construct serve para carregar a
	// listagem
	@PostConstruct
	public void listarOrdenado(){
		try{
			AmostraDAO amostrDAO =  new AmostraDAO();
			amostras = amostrDAO.listar();
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as amostras.");
			erro.printStackTrace();
		}
	}
	
	//edição de amostra
	public void editar(ActionEvent evento){
		amostra = (Amostra) evento.getComponent().getAttributes().get("amostraSelecionada");
		
	}
	
	// método iniciar serve para preencher a lista quando for construída a
	// página
	@PostConstruct
	public void iniciar(){
		try{
			AmostraDAO amostraDAO = new AmostraDAO();
			amostras = amostraDAO.listar();
		}catch(RuntimeException runtimeException){
			
			Messages.addGlobalError(runtimeException.getMessage());
		
		}
	}
	
	
	
}
