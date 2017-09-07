package br.com.siglabor.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.siglabor.dao.FornecedorClienteDAO;
import br.com.siglabor.domain.Cidade;
import br.com.siglabor.domain.FornecedorCliente;

@ManagedBean
@ViewScoped
public class FornecedorClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// instancia um novo objeto
	private FornecedorCliente fornecedorCliente;
	// criar uma lista pra preencher
	private List<FornecedorCliente> fornecedoresClientes;
	private Cidade cidade;

	public FornecedorCliente getFornecedorCliente() {
		return fornecedorCliente;
	}

	public void setFornecedorCliente(FornecedorCliente fornecedorCliente) {
		this.fornecedorCliente = fornecedorCliente;
	}

	public List<FornecedorCliente> getFornecedoresClientes() {
		return fornecedoresClientes;
	}

	public void setFornecedoresClientes(List<FornecedorCliente> fornecedoresClientes) {
		this.fornecedoresClientes = fornecedoresClientes;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	// método par acriar um novo
	public void novo() {
		fornecedorCliente = new FornecedorCliente();

	}

	// Mpetodo salvar
	public void salvar() {
		try {
			FornecedorClienteDAO fornecedorClienteDAO = new FornecedorClienteDAO();
			fornecedorClienteDAO.salvar(fornecedorCliente);
			// criar um novo para realizar a limpeza
			fornecedorCliente = new FornecedorCliente();
			fornecedoresClientes = fornecedorClienteDAO.listarOrdenado("descricao");
			Messages.addGlobalInfo("Salvo com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar salvar");
			erro.printStackTrace();
		}
	}

	// Método editar
	public void editar(ActionEvent evento) {
		fornecedorCliente = (FornecedorCliente) evento.getComponent().getAttributes()
				.get("fornecedorClienteSelecionado");
	}

	// método iniciar serve para preencher a lista quando for construída a
	// página
	@PostConstruct
	public void iniciar() {
		try {
			FornecedorClienteDAO fornecedorClienteDAO = new FornecedorClienteDAO();
			fornecedoresClientes = fornecedorClienteDAO.listarOrdenado("descricao");
		} catch (RuntimeException runtimeException) {
			Messages.addGlobalError(runtimeException.getMessage());

		}
	}

}
