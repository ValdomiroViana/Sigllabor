package br.com.siglabor.dao;

import java.math.BigDecimal;

import br.com.siglabor.domain.Clorofila;

public class ClorofilaDAO extends GenericDAO<Clorofila> {

	public Clorofila calcularClorofila(Clorofila clorofila) {
		clorofila.setClorofila(BigDecimal.ZERO);
		clorofila.setClorofila(clorofila.getNm670().subtract((clorofila.getNm630().add(clorofila.getNm710()))
				.divide(new BigDecimal("2"))).divide(new BigDecimal("0.10")));
		System.out.println("Clorofila: " + clorofila.getClorofila());
		return clorofila;

	}

}
