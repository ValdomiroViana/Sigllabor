package br.com.siglabor.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.siglabor.dao.AmostraDAO;
import br.com.siglabor.dao.UreaseDAO;
import br.com.siglabor.domain.Amostra;
import br.com.siglabor.domain.Urease;
@SuppressWarnings ("serial")
@ManagedBean
@ViewScoped
public class UreaseBean implements Serializable{
	private Urease urease;
	private Amostra amostra;
	private List<Urease> ureases;
	private List<Amostra> amostras;
	public Urease getUrease() {
		return urease;
	}
	public void setUrease(Urease urease) {
		this.urease = urease;
	}
	public Amostra getAmostra() {
		return amostra;
	}
	public void setAmostra(Amostra amostra) {
		this.amostra = amostra;
	}
	public List<Urease> getUreases() {
		return ureases;
	}
	public void setUreases(List<Urease> ureases) {
		this.ureases = ureases;
	}
	public List<Amostra> getAmostras() {
		return amostras;
	}
	public void setAmostras(List<Amostra> amostras) {
		this.amostras = amostras;
	}
	
	//Método novo
	public void novo(){
		try{
			urease = new Urease();
			//prencher a listagem d eamostras
			AmostraDAO amostraDAO = new AmostraDAO();
			amostras = amostraDAO.listar();
		}catch(RuntimeException erro){
			Messages.addGlobalError("Erro ao gerar uma nova urease.");
			erro.printStackTrace();
		}
	}
	
	//Método salvar
	public void salvar(){
		try{
		UreaseDAO ureaseDAO = new UreaseDAO();
		ureaseDAO.salvar(urease);
		urease = new Urease();
		ureases = ureaseDAO.listarOrdenadoPorData("c.dataCheckList");
		Messages.addGlobalInfo("Urease salva com sucesso!");
		}catch(RuntimeException erro){
			Messages.addGlobalError("Erro ao tentar salvar a urease");
			erro.printStackTrace();
		}
	}
	// a anotation @post construct serve para carregar a
	// listagem
	@PostConstruct
	public void listarOrdenado(){
		try{
			UreaseDAO ureaseDAO = new UreaseDAO();
			ureases = ureaseDAO.listarOrdenadoPorData("c.dataCheckList");
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os percentuais de óleo");
			erro.printStackTrace();
		}
	}
	
	//Método para a edição de urease
	public void editar(ActionEvent evento){
		urease =  (Urease) evento.getComponent().getAttributes().get("ureaseSelecionada");
	}
	
	//Método iniciar
	public void iniciar(){
		try{
			UreaseDAO ureaseDAO = new UreaseDAO();
			ureases = ureaseDAO.listarOrdenadoPorData("c.dataCheckList");
		}catch(RuntimeException runtimeException){
			Messages.addGlobalError("Erro");
			runtimeException.printStackTrace();
		}
	}
	
	//Método excluir
	public void excluir(ActionEvent evento){
		try{
		urease = (Urease) evento.getComponent().getAttributes().get("ureaseSelecionada");
		UreaseDAO ureaseDAO = new UreaseDAO();
		ureaseDAO.excluir(urease);
		ureases = ureaseDAO.listarOrdenadoPorData("c.dataCheckList");
		Messages.addGlobalInfo("Urease excluída com sucesso!");
		}catch(RuntimeException erro){
			Messages.addGlobalError("Erro ao tentar excluir a urease.");
		}
	}
	
	//Método para calcular urease
	public void calcularUrease(){
		try{
			UreaseDAO ureaseDAO = new UreaseDAO();
			ureaseDAO.calcularUrease(urease);
		}catch(RuntimeException erro){
			Messages.addGlobalError("Erro");
			erro.printStackTrace();
		}
	}

}
