package br.com.siglabor.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.siglabor.dao.AmostraDAO;
import br.com.siglabor.dao.SaboesDAO;
import br.com.siglabor.domain.Amostra;
import br.com.siglabor.domain.Saboes;
@SuppressWarnings ("serial")
@ManagedBean
@ViewScoped
public class SaboesBean implements Serializable{
	private Saboes sabao;
	private Amostra amostra;
	private List<Saboes> saboes;
	private List<Amostra> amostras;
	public Saboes getSabao() {
		return sabao;
	}
	public void setSabao(Saboes sabao) {
		this.sabao = sabao;
	}
	public Amostra getAmostra() {
		return amostra;
	}
	public void setAmostra(Amostra amostra) {
		this.amostra = amostra;
	}
	public List<Saboes> getSaboes() {
		return saboes;
	}
	public void setSaboes(List<Saboes> saboes) {
		this.saboes = saboes;
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
			sabao = new Saboes();
			//preencher a lista de amostras
			AmostraDAO amostraDAO = new AmostraDAO();
			amostras = amostraDAO.listarOrdenadoPorDataAmostra("dataColeta");
		}catch(RuntimeException erro){
			Messages.addGlobalError("Erro ao gerar uma nova análise de sabões.");
			erro.printStackTrace();
		}
	}
	
	public void salvar(){
		try{
			SaboesDAO saboesDAO = new SaboesDAO();
			saboesDAO.salvar(sabao);
			sabao = new Saboes();
			saboes = saboesDAO.listarOrdenadoPorData("c.dataCheckList");
			Messages.addGlobalInfo("Sabões salvo com sucesso!");
		}catch(RuntimeException erro){
			Messages.addGlobalError("Erro ao tentar salvar a análise de sabões.");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		sabao = (Saboes) evento.getComponent().getAttributes().get("sabaoSelecionado");
		
	}
	
	// método iniciar serve para preencher a lista quando for construída a
	// página
	@PostConstruct
	public void iniciar(){
		try{
			SaboesDAO saboesDAO = new SaboesDAO();
			saboes = saboesDAO.listar();
		}catch(RuntimeException runtimeException){
			Messages.addGlobalError("Erro");
			runtimeException.printStackTrace();
		}
	}
	
	//Método para excluir uma análise de sabões
	public void excluir(ActionEvent evento){
		try{
			sabao = (Saboes) evento.getComponent().getAttributes().get("sabaoSelecionado");
			SaboesDAO saboesDAO = new SaboesDAO();
			saboesDAO.excluir(sabao);
			saboes = saboesDAO.listarOrdenadoPorData("c.dataCheckList");
			Messages.addGlobalInfo("Análise de sabões excluída com sucesso!");
		}catch(RuntimeException erro){
			Messages.addGlobalError("Erro ao tentar excluir a análise de sabões");
		}
	}
	//Método para realizar o cálculo de análise de sabões
	public void calcularSaboes(){
		try{
			SaboesDAO saboesDAO = new SaboesDAO();
			saboesDAO.calcularSaboes(sabao);
		}catch(RuntimeException erro){
			Messages.addGlobalError("Erro");
			erro.printStackTrace();
		}
	}
}
