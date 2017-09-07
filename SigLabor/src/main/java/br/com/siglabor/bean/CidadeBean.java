package br.com.siglabor.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;

import br.com.siglabor.dao.CidadeDAO;
import br.com.siglabor.domain.Cidade;
import br.com.siglabor.domain.Estado;

@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Cidade cidade;
	private List<Cidade> cidades;
	private Estado estado;
	
	public CidadeBean(){
		cidade = new Cidade();
	}
	
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public List<Cidade> getCidades() {
		return cidades;
	}
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	

	
	@PostConstruct
	public void iniciar() {
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.listarOrdenado("nome");
		} catch (RuntimeException runtimeException) {
			Messages.addGlobalError(runtimeException.getMessage());
		}
	}
	
	public void popular(){
		try{
			if(estado != null){
				CidadeDAO cidadeDAO = new CidadeDAO();
				cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());
				System.out.println("Estado ID: " + estado.getCodigo());
				for(Cidade cidade : cidades) {
					System.out.println("Cidade: " +cidade.getNome());
				}
				
			}else{
				cidades = new ArrayList<>();
			}
			for(Cidade cidade : cidades){
				System.out.println("Cidade: " +cidade.getNome());
			}
		}catch(RuntimeException erro){
			Messages.addGlobalInfo("Erro ao tentar listar as cidades");
			erro.printStackTrace();
		}
	}
	
}
