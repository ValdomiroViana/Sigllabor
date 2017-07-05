package br.com.siglabor.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.siglabor.domain.Produto;

public class ProdutoDAOtest {
	@Test
	//@Ignore
	public void salvar() {
		// instancia um domínio de produto
		Produto produto = new Produto();
		produto.setDescricao("Farelo de soja");
		// instancia um produtoDAO
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.salvar(produto);

	}
	
	@Test
	@Ignore
	public void listar(){
		ProdutoDAO produtoDAO = new ProdutoDAO();
		List<Produto> resultado = produtoDAO.listar();
		// o foreach serve para percorrer a lista
		for(Produto produto : resultado){
			System.out.println(produto.getCodigo()+ "-"+produto.getDescricao());
		}
	}
	
	@Test
	@Ignore
	public void buscar(){
		Long codigo = 3L;
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigo);
		if(produto == null){
			System.out.println("Não encontrado");
		}else{
			System.out.println("Codigo: "+ produto.getCodigo());
			System.out.println("Produto: "+ produto.getDescricao());
		}
		
	}
	
	@Test
	@Ignore
	public void excluir(){
		Long codigo = 3L;
		ProdutoDAO produtoDAO = new ProdutoDAO();
		Produto produto = produtoDAO.buscar(codigo);
		if(produto == null){
			System.out.println("Não encontrou o registro");
			
		}else{
			produtoDAO.excluir(produto);
			System.out.println("O registro foi excluído");
			System.out.println("Produto: "+produto.getDescricao());
		}
	}

}
