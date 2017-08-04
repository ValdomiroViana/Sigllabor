package br.com.siglabor.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.siglabor.dao.TipoProdutoDAO;
import br.com.siglabor.domain.Produto;
import br.com.siglabor.domain.TipoProduto;

@ManagedBean
@ViewScoped
public class TipoProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// instancia um novo objeto
	private TipoProduto tipoProduto;
	// criar uma lista para preencher a listagem
	private List<TipoProduto> tiposProduto;
	// instanciar um objeto produto
	private Produto produto;
	
	public TipoProdutoBean(){
		tipoProduto = new TipoProduto();
	}

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



	// método para criar um novo
	public void novo() {
		tipoProduto = new TipoProduto();
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

	// a anotation @post construct serve para carregar a
	// listagem
	@PostConstruct
	public void listarOrdenado() {
		try {
			TipoProdutoDAO tipoProdutoDAO = new TipoProdutoDAO();
			tiposProduto = tipoProdutoDAO.listarOrdenado("descricaoTipo");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os tipos de produto");
		}
	}

	// Edição Tipo de produto
	public void editar(ActionEvent evento) {
		tipoProduto = (TipoProduto) evento.getComponent().getAttributes().get("tipoSelecionado");

	}

	// método iniciar serve para preencher a lista quando for construída a
	// página
	@PostConstruct
	public void iniciar() {
		try {
			TipoProdutoDAO tipoProdutoDAO = new TipoProdutoDAO();
			tiposProduto = tipoProdutoDAO.listarOrdenado("descricaoTipo");
		} catch (RuntimeException runtimeException) {
			Messages.addGlobalError(runtimeException.getMessage());

		}
	}

	public void popular(){
		try{
			if(produto != null){
				TipoProdutoDAO tipoProdutoDAO = new TipoProdutoDAO();
				tiposProduto = tipoProdutoDAO.buscarPorProduto(produto.getCodigo());
				System.out.println("Produto ID: " + produto.getCodigo());
				for(TipoProduto tipoProduto : tiposProduto) {
					System.out.println("Tipo Produto: " + tipoProduto.getDescricaoTipo());
				}
				
			}else{
				tiposProduto = new ArrayList<>();
			}
			for(TipoProduto tipoProduto : tiposProduto){
				System.out.println("Tipo Produto: " + tipoProduto.getDescricaoTipo());
			}
		}catch(RuntimeException erro){
			Messages.addGlobalInfo("Erro ao tentar listar os tipos de produto");
			erro.printStackTrace();
		}
	}
	
	

}
