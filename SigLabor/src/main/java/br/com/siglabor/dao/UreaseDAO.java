package br.com.siglabor.dao;

import java.math.BigDecimal;

import br.com.siglabor.domain.Urease;

public class UreaseDAO extends GenericDAO<Urease>{
	public Urease calcularUrease(Urease urease){
		urease.setUrease(BigDecimal.ZERO);
		urease.setUrease((urease.getpHa().subtract(urease.getpHs())).subtract(urease.getDiferenca()));
		System.out.println("Urease: " + urease.getUrease());
		return urease;
	}

}
