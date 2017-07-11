package br.com.siglabor.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.siglabor.dao.TurnoDAO;
import br.com.siglabor.domain.Turno;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TurnoBean implements Serializable {
	// Intancia um objeto Turno
	private Turno turno;
	// Lista pra preencher a listagem
	private List<Turno> turnos;

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

	// método para criar um novo objeto
	public void novo() {
		turno = new Turno();
	}

	// Método salvar
	public void salvar() {
		try {
			TurnoDAO turnoDAO = new TurnoDAO();
			turnoDAO.salvar(turno);
			// criar um novo objeto pra limpar os dados do atual
			turno = new Turno();
			turnos = turnoDAO.listarOrdenado("descricao");
			Messages.addGlobalInfo("Turno salvo com sucesso!");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o turno!");
			erro.printStackTrace();
		}
	}

	// a anotation @post construct serve para quando abrie a janela, carregar a
	// listagem
	@PostConstruct
	public void listarOrdenado() {
		try {
			TurnoDAO turnoDAO = new TurnoDAO();
			turnos = turnoDAO.listarOrdenado("descricao");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os Turnos");
		}
	}
	
	//Método para a edição
	public void editar(ActionEvent evento){
		turno = (Turno) evento.getComponent().getAttributes().get("turnoSelecionado");
	}

}
