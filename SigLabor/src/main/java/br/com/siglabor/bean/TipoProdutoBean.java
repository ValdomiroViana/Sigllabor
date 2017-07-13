package br.com.siglabor.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.siglabor.dao.ProdutoDAO;
import br.com.siglabor.dao.TipoProdutoDAO;
import br.com.siglabor.domain.Produto;
import br.com.siglabor.domain.TipoProduto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TipoProdutoBean implements Serializable {
	// instancia um novo objeto
	private TipoProduto tipoProduto;
	// criar uma lista para preencher a listagem
	private List<TipoProduto> tiposProduto;
	// instanciar um objeto produto
	private Produto produto;
	private List<Produto> produtos;

	// getters and setters
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

	// método para criar um novo
	public void novo() {
		try {
			tipoProduto = new TipoProduto();
			ProdutoDAO produtoDAO = new ProdutoDAO();
			produtos = produtoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao gerar um novo tipo de produto");
			erro.printStackTrace();
		}
	}

	// Método salvar
	public void salvar() {
		try {
			TipoProdutoDAO tipoProdutoDAO = new TipoProdutoDAO();
			tipoProdutoDAO.salvar(tipoProduto);
			// criar um novo para realisara limpesa
			tipoProduto = new TipoProduto();
			tiposProduto = tipoProdutoDAO.listarOrdenado("descricaoTipo");
			Messages.addGlobalInfo("Tipo de produto salvo com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o tipo de produto!");
			erro.printStackTrace();
		}
	}

	// a anotation @post construct serve para quando abrie a janela, carregar a
	// listagem
	@PostConstruct
	public void listarOrdenado() {
		try {
			TipoProdutoDAO tipoProdutoDAO = new TipoProdutoDAO();
			tipoProdutoDAO.listarOrdenado("descricaoTipo");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os tipos de produto");
		}
	}

	// Edição Analista
	public void editar(ActionEvent evento) {
		tipoProduto = (TipoProduto) evento.getComponent().getAttributes().get("tipoSelecionado");

	}

	// método iniciar serve para preencher a lista quando for construída a página
	@PostConstruct
	public void iniciar() {
		try {
			TipoProdutoDAO tipoProdutoDAO = new TipoProdutoDAO();
			tiposProduto = tipoProdutoDAO.listarOrdenado("descricaoTipo");
		} catch (RuntimeException runtimeException) {
			Messages.addGlobalError(runtimeException.getMessage());

		}
	}

}
