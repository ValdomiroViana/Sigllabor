package br.com.siglabor.dao;

import java.util.Date;

import org.junit.Test;

import br.com.siglabor.domain.Analista;
import br.com.siglabor.domain.CheckList;
import br.com.siglabor.domain.Turno;

public class CheckListDAOTest {
	
	@Test
	//@Ignore
	public void salvar() {
		Long codigoAnalista = 6L;
		Long codugoTurno = 11L;
		AnalistaDAO analistaDAO = new AnalistaDAO();
		Analista analista = analistaDAO.buscar(codigoAnalista);
		
		TurnoDAO turnoDAO = new TurnoDAO();
		Turno turno = turnoDAO.buscar(codugoTurno);
		
		// instancia um domínio de produto
		CheckList checkList = new CheckList();
		checkList.setDataCheckList(new Date("2017/07/13"));
		checkList.setAnalista(analista);
		checkList.setTurno(turno);
		
		// instancia um produtoDAO
		CheckListDAO chechListDAO = new CheckListDAO();
		chechListDAO.salvar(checkList);

	}

}
