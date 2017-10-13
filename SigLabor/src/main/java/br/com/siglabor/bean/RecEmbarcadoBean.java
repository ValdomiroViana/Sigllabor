package br.com.siglabor.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.siglabor.dao.AmostraDAO;
import br.com.siglabor.dao.RecEmbarcadoDAO;
import br.com.siglabor.domain.Amostra;
import br.com.siglabor.domain.CheckList;
import br.com.siglabor.domain.FornecedorCliente;
import br.com.siglabor.domain.RecEmbarcado;

@ManagedBean
@ViewScoped
public class RecEmbarcadoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private RecEmbarcado recEmbarcado;
	private FornecedorCliente fornecedorCliente;
	private Amostra amostra;
	private CheckList checkList;
	private List<RecEmbarcado> recEmbarcados;
	private List<Amostra> amostras;
	private List<FornecedorCliente> fornecedoresClientes;
	private List<CheckList> checkLists;

	public RecEmbarcadoBean() {
		recEmbarcado = new RecEmbarcado();
		amostra = new Amostra();
		checkList = new CheckList();
		checkLists = new ArrayList<>();

	}

	public RecEmbarcado getRecEmbarcado() {
		return recEmbarcado;
	}

	public void setRecEmbarcado(RecEmbarcado recEmbarcado) {
		this.recEmbarcado = recEmbarcado;
	}

	public FornecedorCliente getFornecedorCliente() {
		return fornecedorCliente;
	}

	public void setFornecedorCliente(FornecedorCliente fornecedorCliente) {
		this.fornecedorCliente = fornecedorCliente;
	}

	public Amostra getAmostra() {
		return amostra;
	}

	public void setAmostra(Amostra amostra) {
		this.amostra = amostra;
	}

	public List<RecEmbarcado> getRecEmbarcados() {
		return recEmbarcados;
	}

	public void setRecEmbarcados(List<RecEmbarcado> recEmbarcados) {
		this.recEmbarcados = recEmbarcados;
	}

	public List<Amostra> getAmostras() {
		return amostras;
	}

	public void setAmostras(List<Amostra> amostras) {
		this.amostras = amostras;
	}

	public List<FornecedorCliente> getFornecedoresClientes() {
		return fornecedoresClientes;
	}

	public void setFornecedoresClientes(List<FornecedorCliente> fornecedoresClientes) {
		this.fornecedoresClientes = fornecedoresClientes;
	}

	public CheckList getCheckList() {
		return checkList;
	}

	public void setCheckList(CheckList checkList) {
		this.checkList = checkList;
	}

	public List<CheckList> getCheckLists() {
		return checkLists;
	}

	public void setCheckLists(List<CheckList> checkLists) {
		this.checkLists = checkLists;
	}

	// Método novo
	public void novo() {
		recEmbarcado = new RecEmbarcado();
		amostra = new Amostra();
	}

	// Método salvar
	public void salvar() {
		try {

			AmostraDAO amostraDAO = new AmostraDAO();
			amostraDAO.salvar(amostra);
			// Chamada do método para buscar o último ID da amostra
			// recém-inserida
			Long codigo = amostraDAO.sqlMax(amostra);
			System.out.println("Codigo: " + codigo);
			// Buscar o código
			Amostra amostra = amostraDAO.buscar(codigo);
			// salvar na tabela recEmbarcado
			RecEmbarcadoDAO recEmbarcadoDAO = new RecEmbarcadoDAO();
			recEmbarcado.setAmostra(new Amostra());
			recEmbarcado.setAmostra(amostra);
			recEmbarcadoDAO.salvar(recEmbarcado);
			recEmbarcado = new RecEmbarcado();
			recEmbarcados = recEmbarcadoDAO.listarOrdenadoPorDataAmostra("dataColeta");
			Messages.addGlobalInfo("Salvo com sucesso!");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar.");
			erro.printStackTrace();
		}
	}

}
