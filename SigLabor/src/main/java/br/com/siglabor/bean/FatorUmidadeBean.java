package br.com.siglabor.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.siglabor.dao.AmostraDAO;
import br.com.siglabor.dao.FatorUmidadeDAO;
import br.com.siglabor.domain.Amostra;
import br.com.siglabor.domain.FatorUmidade;
import br.com.siglabor.domain.Umidade;
@SuppressWarnings ("serial")
@ManagedBean
@ViewScoped
public class FatorUmidadeBean implements Serializable{
	//instanciar um novo fator
	private FatorUmidade fatorUmidade;
	//criar uma lista de Fatores
	private List<FatorUmidade> fatoresUmidade;
	//instanciar uma amostra
	private Amostra amostra;
	private List<Amostra> amostras;
	private List<Umidade> umidades;
	
	
	
	public FatorUmidade getFatorUmidade() {
		return fatorUmidade;
	}
	public void setFatorUmidade(FatorUmidade fatorUmidade) {
		this.fatorUmidade = fatorUmidade;
	}
	public List<FatorUmidade> getFatoresUmidade() {
		return fatoresUmidade;
	}
	public void setFatoresUmidade(List<FatorUmidade> fatoresUmidade) {
		this.fatoresUmidade = fatoresUmidade;
	}
	
	public Amostra getAmostra() {
		return amostra;
	}
	public void setAmostra(Amostra amostra) {
		this.amostra = amostra;
	}
	public List<Amostra> getAmostras() {
		return amostras;
	}
	public void setAmostras(List<Amostra> amostras) {
		this.amostras = amostras;
	}
	
	
	public List<Umidade> getUmidades() {
		return umidades;
	}
	public void setUmidades(List<Umidade> umidades) {
		this.umidades = umidades;
	}
	//Método novo
	public void novo(){
		try{
			fatorUmidade = new FatorUmidade();
			//Preencher a lista de amostras
			AmostraDAO amostraDAO = new AmostraDAO();
			amostras = amostraDAO.listarOrdenadoPorDataAmostra("dataColeta");
		}catch(RuntimeException erro){
			Messages.addGlobalError("Erro ao gerar um novo fator de correção");
			erro.printStackTrace();
		}
	}
	
	//Método salvar
	public void salvar(){
		try{
			FatorUmidadeDAO fatorUmidadeDAO = new FatorUmidadeDAO();
			fatorUmidadeDAO.salvar(fatorUmidade);
			//criar um novo fator
			fatorUmidade = new FatorUmidade();
			fatoresUmidade = fatorUmidadeDAO.listar();
			Messages.addGlobalInfo("Fator de Umidade salvo com sucesso!");
		}catch(RuntimeException erro){
			Messages.addGlobalError("Erro ao tentar salvar o fator");
			erro.printStackTrace();
		}
	}
	
		//Edição de fator de correção
	public void editar(ActionEvent evento){
		fatorUmidade = (FatorUmidade) evento.getComponent().getAttributes().get("fatorSelecionado");
	}
	
	// método iniciar serve para preencher a lista quando for construída a
	// página
	@PostConstruct
	
	public void iniciar(){
		try{
			FatorUmidadeDAO fatorUmidadeDAO = new FatorUmidadeDAO();
			fatoresUmidade = fatorUmidadeDAO.listar();
		}catch(RuntimeException runtimeException){
			Messages.addGlobalError("Erro");
			runtimeException.printStackTrace();
		}
	}
	
	//Método para realizar o cálculo do fator de umidade
	public void calculaFator(){
		FatorUmidadeDAO fatorUmidadeDAO = new FatorUmidadeDAO();
		fatorUmidade = fatorUmidadeDAO.calcularFatorUmidade(fatorUmidade);
	}
	
	
}
