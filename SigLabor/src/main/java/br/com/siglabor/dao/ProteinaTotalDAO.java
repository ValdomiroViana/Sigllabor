package br.com.siglabor.dao;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.siglabor.domain.ProteinaTotal;
import br.com.siglabor.util.HibernateUtil;


public class ProteinaTotalDAO extends GenericDAO<ProteinaTotal> {
	public ProteinaTotal calcularProteina(ProteinaTotal proteinaTotal) {
		proteinaTotal.setProteinaTotal(BigDecimal.ZERO);
		proteinaTotal
				.setProteinaTotal((((((proteinaTotal.getValorBranco().subtract(proteinaTotal.getValorGastoAmostra()))
						.multiply(new BigDecimal("0.8755"))).divide(proteinaTotal.getPa(), MathContext.DECIMAL128)))
								.multiply(proteinaTotal.getFatorSolucao().getFatorAcido()))
										.multiply(proteinaTotal.getFatorSolucao().getFatorHidroxido()).multiply(proteinaTotal.getFatorUmidade().getFatorUmidade()));
		System.out.println("Proteína total: " + proteinaTotal.getProteinaTotal());
		return proteinaTotal;

	}

	public List<ProteinaTotal> listaProteinaFareloII(String campoOrdenacao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(ProteinaTotal.class);
			consulta.createAlias("amostra", "a");
			consulta.setMaxResults(3);
			consulta.addOrder(Order.desc(campoOrdenacao));
			
			@SuppressWarnings("unchecked")
			List<ProteinaTotal> resultado = consulta.list();
			Collections.reverse(resultado);
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}

	}

	public List<ProteinaTotal> listarProteinaFarelo(String campoOrdenacao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(ProteinaTotal.class);
			consulta.createAlias("amostra", "a");
			consulta.setMaxResults(16);
			consulta.addOrder(Order.desc(campoOrdenacao));
			
			@SuppressWarnings("unchecked")
			List<ProteinaTotal> resultado = consulta.list();
			Collections.reverse(resultado);
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}	
	
	
	

}
