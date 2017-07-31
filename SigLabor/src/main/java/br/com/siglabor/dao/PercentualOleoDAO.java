package br.com.siglabor.dao;

import java.math.BigDecimal;
import java.math.MathContext;

import br.com.siglabor.domain.PercentualOleo;

public class PercentualOleoDAO extends GenericDAO<PercentualOleo> {
	public PercentualOleo calcularPercentualOleo(PercentualOleo percentualOleo) {
		percentualOleo.setPercentualOleo(BigDecimal.ZERO);
		percentualOleo.setPercentualOleo((((percentualOleo.getPf().subtract(percentualOleo.getPb())
				.divide(percentualOleo.getPa(), MathContext.DECIMAL128).multiply(new BigDecimal("100"))))));
		System.out.println("Perc. Óleo: " + percentualOleo.getPercentualOleo());
		return percentualOleo;

	}

}
