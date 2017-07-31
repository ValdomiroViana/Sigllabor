package br.com.siglabor.dao;

import java.math.BigDecimal;
import java.math.MathContext;

import br.com.siglabor.domain.Umidade;

public class UmidadeDAO extends GenericDAO<Umidade> {

	public Umidade calcularUmidade(Umidade umidade) {
		umidade.setUmidade(BigDecimal.ZERO);
		umidade.setUmidade((((umidade.getPa_Mais_b().subtract(umidade.getPf())
				.divide(umidade.getPa(), MathContext.DECIMAL128).multiply(new BigDecimal("100"))))));
		System.out.println("Umidade" + umidade.getUmidade());
		return umidade;

	}

}
