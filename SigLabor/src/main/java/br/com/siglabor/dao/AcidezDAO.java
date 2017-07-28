package br.com.siglabor.dao;

import java.math.BigDecimal;
import java.math.MathContext;

import br.com.siglabor.domain.Acidez;

public class AcidezDAO extends GenericDAO<Acidez>{
	public Acidez calcularAcidez(Acidez acidez){
		acidez.setAcidez(BigDecimal.ZERO);
		acidez.setAcidez((acidez.getValorGasto().multiply(new BigDecimal(2.82))).divide(acidez.getPa(), MathContext.DECIMAL128));
		
		System.out.println("Umidade" + acidez.getAcidez());
		return acidez;
		
		
	}

}
