package br.com.siglabor.dao;

import java.math.BigDecimal;
import java.math.MathContext;

import br.com.siglabor.domain.Saboes;

public class SaboesDAO extends GenericDAO<Saboes> {
	public Saboes calcularSaboes(Saboes saboes) {
		saboes.setSabao(BigDecimal.ZERO);
		saboes.setSabao(
				(saboes.getValorGasto().multiply(new BigDecimal(3040))).divide(saboes.getPa(), MathContext.DECIMAL128));
		return saboes;
	}

}
