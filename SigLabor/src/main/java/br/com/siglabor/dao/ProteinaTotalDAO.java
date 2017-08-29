package br.com.siglabor.dao;
import java.math.BigDecimal;
import java.math.MathContext;
import br.com.siglabor.domain.ProteinaTotal;


public class ProteinaTotalDAO extends GenericDAO<ProteinaTotal> {
	public ProteinaTotal calcularProteina(ProteinaTotal proteinaTotal) {
		proteinaTotal.setProteinaTotal(BigDecimal.ZERO);
		proteinaTotal
				.setProteinaTotal((((((proteinaTotal.getValorBranco().subtract(proteinaTotal.getValorGastoAmostra()))
						.multiply(new BigDecimal("0.8755"))).divide(proteinaTotal.getPa(), MathContext.DECIMAL128)))
								.multiply(proteinaTotal.getFatorSolucao().getFatorAcido()))
										.multiply(proteinaTotal.getFatorSolucao().getFatorHidroxido()));
		System.out.println("Proteína total: " + proteinaTotal.getProteinaTotal());
		System.out.println("Umidade" + proteinaTotal.getP_umidade());
		return proteinaTotal;

	}
	

}
