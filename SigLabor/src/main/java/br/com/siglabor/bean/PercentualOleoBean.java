package br.com.siglabor.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.siglabor.dao.AmostraDAO;
import br.com.siglabor.dao.PercentualOleoDAO;
import br.com.siglabor.domain.Amostra;
import br.com.siglabor.domain.PercentualOleo;
@SuppressWarnings ("serial")
@ManagedBean
@ViewScoped
public class PercentualOleoBean implements Serializable{
	//instanciar um percentual de óleo
	private PercentualOleo percentualOleo;
	//instanciar uma amostra
	private Amostra amostra;
	//instanciar uma lista de percentuais de óleo
	private List<PercentualOleo> percentuaisOleo;
	//instanciar uma lista de amostras
	private List<Amostra> amostras;
	public PercentualOleo getPercentualOleo() {
		return percentualOleo;
	}
	public void setPercentualOleo(PercentualOleo percentualOleo) {
		this.percentualOleo = percentualOleo;
	}
	public Amostra getAmostra() {
		return amostra;
	}
	public void setAmostra(Amostra amostra) {
		this.amostra = amostra;
	}
	public List<PercentualOleo> getPercentuaisOleo() {
		return percentuaisOleo;
	}
	public void setPercentuaisOleo(List<PercentualOleo> percentuaisOleo) {
		this.percentuaisOleo = percentuaisOleo;
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
			percentualOleo = new PercentualOleo();
			//preencher a lista de amostras
			AmostraDAO amostraDAO = new AmostraDAO();
			amostras = amostraDAO.listarOrdenadoPorDataAmostra("dataColeta");
		}catch(RuntimeException erro){
			Messages.addGlobalError("Erro ao gerar um novo percentual de óleo.");
			erro.printStackTrace();
		}
	}
	
	//Método salvar
	public void salvar(){
		try{
			PercentualOleoDAO percentualOleoDAO = new PercentualOleoDAO();
			percentualOleoDAO.salvar(percentualOleo);
			percentualOleo = new PercentualOleo();
			percentuaisOleo = percentualOleoDAO.listarOrdenadoPorData("c.dataCheckList");
			Messages.addGlobalInfo("Percentual de óleo salvo com sucesso.");
			
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o percentual de Óleo.");
			erro.printStackTrace();
		}
	}
	
	// a anotation @post construct serve para carregar a
		// listagem
		@PostConstruct
		public void listarOrdenado(){
			try{
				PercentualOleoDAO percentualOleoDAO = new PercentualOleoDAO();
				percentuaisOleo = percentualOleoDAO.listarOrdenadoPorData("c.dataCheckList");
			}catch(RuntimeException erro){
				Messages.addGlobalError("Ocorreu um erro ao tentar listar os percentuais de óleo");
				erro.printStackTrace();
			}
		}
	
		//Método editar
		public void editar(ActionEvent evento){
			percentualOleo = (PercentualOleo) evento.getComponent().getAttributes().get("percentualOleoSelecionado");
			
		}
		
		// método iniciar serve para preencher a lista quando for construída a
		// página
		@PostConstruct
		public void iniciar(){
			try{
				PercentualOleoDAO percentualOleoDAO = new PercentualOleoDAO();
				percentuaisOleo = percentualOleoDAO.listar();
			}catch(RuntimeException runtimeException){
				Messages.addGlobalError("Erro");
				runtimeException.printStackTrace();
			}
		}
		
		//Método para excluir percentual de óleo
		public void excluir(ActionEvent evento){
			try{
				percentualOleo = (PercentualOleo) evento.getComponent().getAttributes().get("percentualOleoSelecionado");
				PercentualOleoDAO percentualOleoDAO = new PercentualOleoDAO();
				percentualOleoDAO.excluir(percentualOleo);
				percentuaisOleo = percentualOleoDAO.listarOrdenadoPorData("c.dataCheckList");
				Messages.addGlobalInfo("Percentual de óleo excluído com sucesso!");
			}catch(RuntimeException erro){
				Messages.addGlobalError("Erro ao tentar excluir o percentual de óleo.");
				erro.printStackTrace();
			}
		}
		
		//Método para realizar o cálculo de Percentual de óleo
		public void calcularPercentualOleo(){
			try{
				PercentualOleoDAO percentualOleoDAO = new PercentualOleoDAO();
				percentualOleoDAO.calcularPercentualOleo(percentualOleo);
			}catch(RuntimeException erro){
				Messages.addGlobalError("Erro");
				erro.printStackTrace();
			}
		}
}
