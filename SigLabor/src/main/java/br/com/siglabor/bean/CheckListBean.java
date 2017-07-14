package br.com.siglabor.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.siglabor.dao.AnalistaDAO;
import br.com.siglabor.dao.CheckListDAO;
import br.com.siglabor.dao.TurnoDAO;
import br.com.siglabor.domain.Analista;
import br.com.siglabor.domain.CheckList;
import br.com.siglabor.domain.Turno;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CheckListBean implements Serializable {
	// instancia um novo objeto
	private CheckList checkList;
	// criar uma lista para preencher a listagem
	private List<CheckList> checkLists;
	// instanciar um analista
	private Analista analista;
	// crir uma lista de analistas
	private List<Analista> analistas;
	// instanciar um turno
	private Turno turno;
	// criar uma lista de turnos
	private List<Turno> turnos;

	// getters and setters
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

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	// Método para criar um novo checklist
	public void novo() {
		try {
			checkList = new CheckList();
			// preencher a lista de analistas
			AnalistaDAO analistaDAO = new AnalistaDAO();
			analistas = analistaDAO.listarOrdenado("dataCheckList");

			// preencher a lista de turnos
			TurnoDAO turnoDAO = new TurnoDAO();
			turnos = turnoDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao gerar um novo CheckList");
			erro.printStackTrace();
		}

	}

	// Método salvar
	public void salvar() {
		try {
			CheckListDAO checkListDAO = new CheckListDAO();
			checkListDAO.salvar(checkList);
			// criar um novo
			checkList = new CheckList();
			checkLists = checkListDAO.listar();
			Messages.addGlobalInfo("Checklist salvo com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o checklist");
			erro.printStackTrace();
		}
	}

	// a anotation @post construct serve para carregar a
	// listagem
	@PostConstruct
	public void listarOrdenado() {
		try {
			CheckListDAO checkListDAO = new CheckListDAO();
			checkLists = checkListDAO.listarOrdenado("dataCheckList");
;		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os checklists");
			erro.printStackTrace();
		}
	}

	// Edição Tipo de checklist
	public void editar(ActionEvent evento) {
		checkList = (CheckList) evento.getComponent().getAttributes().get("checkListSelecionado");

	}

	// método iniciar serve para preencher a lista quando for construída a
	// página
	@PostConstruct
	public void iniciar() {
		try {
			CheckListDAO checkListDAO = new CheckListDAO();
			checkLists = checkListDAO.listarOrdenado("dataCheckList");
		} catch (RuntimeException runtimeException) {
			Messages.addGlobalError(runtimeException.getMessage());

		}
	}

}
