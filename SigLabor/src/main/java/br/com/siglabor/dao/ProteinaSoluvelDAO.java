package br.com.siglabor.dao;

import java.math.BigDecimal;
import java.math.MathContext;

import br.com.siglabor.domain.ProteinaSoluvel;

public class ProteinaSoluvelDAO extends GenericDAO<ProteinaSoluvel> {
	public ProteinaSoluvel calcularProteinaSoluvel(ProteinaSoluvel proteinaSoluvel) {
		
		proteinaSoluvel.setProteinaSoluvel(
				(((((proteinaSoluvel.getValorBranco().subtract(proteinaSoluvel.getValorGastoAmostra()))
						.multiply(new BigDecimal("0.8755")).divide(
								(proteinaSoluvel.getPa().multiply(new BigDecimal(0.25), MathContext.DECIMAL128)),
								MathContext.DECIMAL128)).multiply(proteinaSoluvel.getFatorUmidade().getFatorUmidade()))
										.multiply(proteinaSoluvel.getFatorSolucao().getFatorAcido()).multiply(
												proteinaSoluvel.getFatorSolucao().getFatorHidroxido())).divide(
														proteinaSoluvel.getProteinaTotal()
																.getProteinaTotal(),
														MathContext.DECIMAL128)).multiply(new BigDecimal("100")));
		return proteinaSoluvel;

	}
}
