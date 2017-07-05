package br.com.siglabor.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.siglabor.domain.Analista;

public class AnalistaDAOtest {
	@Test
	@Ignore
	public void salvar(){
		Analista analista = new Analista();
		analista.setNome("Antônio C.");
		analista.setSobrenome("Arruda");
		AnalistaDAO analistaDAO = new AnalistaDAO();
		analistaDAO.salvar(analista);
	}
	
	@Test
	@Ignore
	public void listar(){
		AnalistaDAO analistaDAO = new AnalistaDAO();
		List<Analista> resultado = analistaDAO.listar();
		// o foreach serve para percorrer a lista
		for(Analista analista : resultado){
			System.out.println(analista.getCodigo() + "-" 
		+ analista.getNome()+" "+analista.getSobrenome());
		}
	
	}
	
	@Test
	@Ignore
	public void buscar(){
		Long codigo = 1L;
		AnalistaDAO analistaDAO = new AnalistaDAO();
		Analista analista = analistaDAO.buscar(codigo);
		if(analista == null){
			System.out.println("Não há registros");
		}else{
			System.out.println("Código: " + analista.getCodigo());
			System.out.println("Analista: " + analista.getNome());
		}
	}
	
	@Test
	public void excluir(){
		Long codigo = 3L;
		AnalistaDAO analistaDAO = new AnalistaDAO();
		Analista analista = analistaDAO.buscar(codigo);
		
		if(analista == null){
			System.out.println("Não encontrou o registro");
		}else{
			analistaDAO.excluir(analista);
			System.out.println("O registro foi excluído");
			System.out.println("Analista: "+ analista.getNome());
		}
		
	}
	


}
