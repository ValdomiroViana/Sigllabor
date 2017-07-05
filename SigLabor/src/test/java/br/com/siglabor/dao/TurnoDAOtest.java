package br.com.siglabor.dao;

import java.sql.Time;
import java.util.Date;

import org.junit.Test;

import br.com.siglabor.domain.Turno;

public class TurnoDAOtest {
	@Test
	public void salvar(){
		Turno turno = new Turno();
		turno.setDescricao("1º Turno");
		turno.setHoraInicio(new Time(05, 20, 00));
		turno.setHoraFim(new Time(13, 40, 00));
		
		TurnoDAO turnoDAO = new TurnoDAO();
		turnoDAO.salvar(turno);
	}

}
