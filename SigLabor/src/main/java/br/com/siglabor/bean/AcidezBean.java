package br.com.siglabor.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.siglabor.dao.AcidezDAO;
import br.com.siglabor.dao.AmostraDAO;
import br.com.siglabor.domain.Acidez;
import br.com.siglabor.domain.Amostra;
@SuppressWarnings ("serial")
@ManagedBean
@ViewScoped
public class AcidezBean implements Serializable{
	private Acidez acidez;
	private Amostra amostra;
	private List<Acidez> acidezes;
	private List<Amostra> amostras;
	
	public AcidezBean(){
		acidez = new Acidez();
		amostra = new Amostra();
	}
	
	public Acidez getAcidez() {
		return acidez;
	}
	public void setAcidez(Acidez acidez) {
		this.acidez = acidez;
	}
	public Amostra getAmostra() {
		return amostra;
	}
	public void setAmostra(Amostra amostra) {
		this.amostra = amostra;
	}
	public List<Acidez> getAcidezes() {
		return acidezes;
	}
	public void setAcidezes(List<Acidez> acidezes) {
		this.acidezes = acidezes;
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
			acidez = new Acidez();
			//prencher a listagem d eamostras
			AmostraDAO amostraDAO = new AmostraDAO();
			amostras = amostraDAO.listarOrdenadoPorDataAmostra("dataColeta");
			
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao gerar uma noca acidez");
			erro.printStackTrace();
		}
	}
	
	//Método salvar
	public void salvar(){
		try{
			AcidezDAO acidezDAO = new AcidezDAO();
			acidezDAO.salvar(acidez);
			//criar uma nova acidez
			acidez = new Acidez();
			acidezes = acidezDAO.listarOrdenadoPorData("c.dataCheckList");
			Messages.addGlobalInfo("Acidez salva com sucesso!");
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a acidez.");
			erro.printStackTrace();
		}
	}
	
	// a anotation @post construct serve para carregar a
		// listagem
		@PostConstruct
		public void listarOrdenado(){
			try{
				AcidezDAO acidezDAO = new AcidezDAO();
				acidezes = acidezDAO.listarOrdenadoPorData("c.dataCheckList");
			}catch(RuntimeException erro){
				Messages.addGlobalError("Ocorreu um erro ao tentar listar as acidezes!");
				erro.printStackTrace();
			}
		}
	
		//Edição de acidez
		public void editar(ActionEvent evento){
			acidez = (Acidez) evento.getComponent().getAttributes().get("acidezSelecionada");
		}
		
		// método iniciar serve para preencher a lista quando for construída a
		// página
		@PostConstruct
		public void iniciar(){
			try{
				AcidezDAO acidezDAO = new AcidezDAO();
				acidezes = acidezDAO.listarOrdenadoPorData("c.dataCheckList");
			}catch(RuntimeException runtimeException){
				Messages.addGlobalError("Erro");
				runtimeException.printStackTrace();
			}
		}
		
		//Método excluir
		public void excluir(ActionEvent evento){
			try{
				acidez = (Acidez) evento.getComponent().getAttributes().get("acidezSelecionada");
				AcidezDAO acidezDAO = new AcidezDAO();
				acidezDAO.excluir(acidez);
				acidezes = acidezDAO.listarOrdenadoPorData("c.dataCheckList");
				Messages.addGlobalInfo("Acidez excluída com sucesso!");
			}catch(RuntimeException erro){
				Messages.addGlobalError("Erro ao tentar excluir a acidez.");
			}
		}
		
		// Método para realizar o cálculo de Acidez
		public void calcularAcidez() {
			try {
				AcidezDAO acidezDAO = new AcidezDAO();
				acidez = acidezDAO.calcularAcidez(acidez);
			} catch (RuntimeException erro) {
				Messages.addGlobalError("Erro");
				erro.printStackTrace();
			}

		}
}
