package br.com.siglabor.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.siglabor.dao.FatorSolucaoDAO;
import br.com.siglabor.domain.FatorSolucao;
@SuppressWarnings ("serial")
@ManagedBean
@ViewScoped
public class FatorSolucaoBean implements Serializable{
	private FatorSolucao fatorSolucao;
	private List<FatorSolucao> fatoresSolucao;
	public FatorSolucao getFatorSolucao() {
		return fatorSolucao;
	}
	public void setFatorSolucao(FatorSolucao fatorSolucao) {
		this.fatorSolucao = fatorSolucao;
	}
	public List<FatorSolucao> getFatoresSolucao() {
		return fatoresSolucao;
	}
	public void setFatoresSolucao(List<FatorSolucao> fatoresSolucao) {
		this.fatoresSolucao = fatoresSolucao;
	}
	
	//Método novo
	public void novo(){
		
			fatorSolucao = new FatorSolucao();
	}
	
	//Método salvar
	public void salvar(){
		try{
			FatorSolucaoDAO fatorSolucaoDAO = new FatorSolucaoDAO();
			fatorSolucaoDAO.salvar(fatorSolucao);
			//criar um objeto para limpar os dados do objeto recém criado
			fatorSolucao = new FatorSolucao();
			fatoresSolucao = fatorSolucaoDAO.listar();
			Messages.addGlobalInfo("Fator solução salvo com sucesso!");
		}catch(RuntimeException erro){
			Messages.addGlobalError("Erro ao tentar salvar a solução.");
		}
	}
	
	// a anotation @post construct serve para quando abrie a janela, carregar a
		// listagem
		@PostConstruct
		public void listarOrdenado(){
			try{
				FatorSolucaoDAO fatorSolucaoDAO = new FatorSolucaoDAO();
				fatoresSolucao = fatorSolucaoDAO.listarOrdenado("dataFatoracao");
			}catch(RuntimeException erro){
				Messages.addGlobalError("Erro ao tentar listar os fatores de soluçaõ");
				erro.printStackTrace();
			}
		}
		
		//Edição de fator de solução
		public void editar(ActionEvent evento){
			fatorSolucao = (FatorSolucao) evento.getComponent().getAttributes().get("solucaoSelecionada");
		}
		
		
		//Método iniciar
		@PostConstruct
		public void iniciar(){
			try{
				FatorSolucaoDAO fatorSolucaoDAO = new FatorSolucaoDAO();
				fatoresSolucao = fatorSolucaoDAO.listar();
			}catch(RuntimeException runtimeException){
				Messages.addGlobalError(runtimeException.getMessage());
			}
		}

}
