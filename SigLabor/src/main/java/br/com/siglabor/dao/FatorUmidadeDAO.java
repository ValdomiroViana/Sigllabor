package br.com.siglabor.dao;

import java.math.BigDecimal;
import java.math.MathContext;

import br.com.siglabor.domain.FatorUmidade;

public class FatorUmidadeDAO extends GenericDAO<FatorUmidade> {

	public FatorUmidade calcularFatorUmidade(FatorUmidade fatorUmidade) {

		fatorUmidade.setFatorUmidade(BigDecimal.ZERO);

		fatorUmidade.setFatorUmidade(((new BigDecimal("100").subtract(fatorUmidade.getP_umidade().getUmidade())))
				.divide(((new BigDecimal("100").subtract(fatorUmidade.getS_umidade().getUmidade()))),
						MathContext.DECIMAL128));
		System.out.println(fatorUmidade.getFatorUmidade());
		return fatorUmidade;

	}

}
