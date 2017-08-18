package br.com.siglabor.dao;

import java.math.BigDecimal;
import java.math.MathContext;
import br.com.siglabor.domain.Umidade;

public class UmidadeDAO extends GenericDAO<Umidade> {

	public Umidade calcularUmidade(Umidade umidade) {
		umidade.setUmidade(BigDecimal.ZERO);
		BigDecimal f_calc = new BigDecimal("0.00");
		umidade.setUmidade((((umidade.getPa_Mais_b().subtract(umidade.getPf())
				.divide(umidade.getPa(), MathContext.DECIMAL128).multiply(new BigDecimal("100"))))));
		System.out.println("Umidade" + umidade.getUmidade());
		f_calc = new BigDecimal("100").subtract(umidade.getUmidade());
		System.out.println("fator:" + f_calc);
		return umidade;

	}
	

}
