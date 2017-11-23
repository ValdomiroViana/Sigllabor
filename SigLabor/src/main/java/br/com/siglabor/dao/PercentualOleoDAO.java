package br.com.siglabor.dao;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.siglabor.domain.PercentualOleo;
import br.com.siglabor.util.HibernateUtil;

public class PercentualOleoDAO extends GenericDAO<PercentualOleo> {
	public PercentualOleo calcularPercentualOleo(PercentualOleo percentualOleo) {
		percentualOleo.setPercentualOleo(BigDecimal.ZERO);
		percentualOleo.setPercentualOleo((((percentualOleo.getPf().subtract(percentualOleo.getPb())
				.divide(percentualOleo.getPa(), MathContext.DECIMAL128).multiply(new BigDecimal("100"))))));
		System.out.println("Perc. Óleo: " + percentualOleo.getPercentualOleo());
		return percentualOleo;

	}
	
	public List<PercentualOleo> listarPercentualOleoFarelo(String campoOrdenacao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(PercentualOleo.class);
			consulta.createAlias("amostra", "a");
			consulta.setMaxResults(16);
			consulta.addOrder(Order.desc(campoOrdenacao));
			
			@SuppressWarnings("unchecked")
			List<PercentualOleo> resultado = consulta.list();
			Collections.reverse(resultado);
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}

	}



	public List<PercentualOleo> listarPercentualOleoFareloII(String campoOrdenacao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(PercentualOleo.class);
			consulta.createAlias("amostra", "a");
			consulta.setMaxResults(3);
			consulta.addOrder(Order.desc(campoOrdenacao));
			
			@SuppressWarnings("unchecked")
			List<PercentualOleo> resultado = consulta.list();
			Collections.reverse(resultado);
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}

	}

}
