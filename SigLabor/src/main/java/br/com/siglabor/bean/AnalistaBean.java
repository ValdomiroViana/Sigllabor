package br.com.siglabor.bean;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.siglabor.dao.AnalistaDAO;
import br.com.siglabor.domain.Analista;

@SuppressWarnings ("serial")
@ManagedBean
@ViewScoped
public class AnalistaBean implements Serializable{
	//instancia um objeto analista
	private Analista analista;
	//cria uma lista para preencher a listagem
	private List<Analista> analistas;
	
	//Getters and setters inicio
	public Analista getAnalista() {
		return analista;
	}
	public void setAnalista(Analista analista) {
		this.analista = analista;
	}
	public List<Analista> getAnalistas() {
		return analistas;
	}
	public void setAnalistas(List<Analista> analistas) {
		this.analistas = analistas;
	}
	//fim getters and setters
	
	//método para criar um novo objeto
	public void novo(){
		analista = new Analista();
	}
	
	//método salvar início
	public void salvar(){
		try{
			AnalistaDAO analistaDAO = new AnalistaDAO();
			analistaDAO.salvar(analista);
			//cria um objeto novo para limpar os dados do objeto recém-criado
			analista = new Analista();
			analistas = analistaDAO.listar();
			Messages.addGlobalInfo("Analista salvo com sucesso!");
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o Analista!");
			erro.printStackTrace();
		}
	}
	
	// a anotation @post construct serve para quando abrie a janela, carregar a listagem
	@PostConstruct
	public void listarOrdenado(){
		try{
			AnalistaDAO analistaDAO = new AnalistaDAO();
			analistas = analistaDAO.listarOrdenado("nome");
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os Analistas");
		}
	}

}
