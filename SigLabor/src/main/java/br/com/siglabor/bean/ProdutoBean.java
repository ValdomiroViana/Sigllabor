package br.com.siglabor.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.siglabor.dao.ProdutoDAO;
import br.com.siglabor.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {
	// Instanciando um novo objeto
	private Produto produto;
	// criar uma lista par apreencher a listagem
	private List<Produto> produtos;

	// Getters and setters
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

	// método par acriar um novo objeto
	public void novo() {
		produto = new Produto();
	}

	// Método salvar
	public void salvar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtoDAO.salvar(produto);
			// cria um objeto novo para limpar os dados do objeto recém-criado
			produto = new Produto();
			produtos = produtoDAO.listarOrdenado("descricao");
			Messages.addGlobalInfo("Produto salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Produto!");
			erro.printStackTrace();
		}
	}
	// a anotation @post construct serve para quando abrie a janela, carregar a
	// listagem

	@PostConstruct
	public void listarOrdenado() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listarOrdenado("descricao");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os Produtos");
		}
	}

	public void editar(ActionEvent evento) {
		produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

	}
}
