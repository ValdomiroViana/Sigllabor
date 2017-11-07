package br.com.siglabor.dao;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.siglabor.domain.Umidade;
import br.com.siglabor.util.HibernateUtil;

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

	@SuppressWarnings("unchecked")
	public List<Umidade> buscarPorAmostra(Long amostraCodigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Umidade.class);
			consulta.add(Restrictions.eq("amostra.codigo", amostraCodigo));
			List<Umidade> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}

	}

	public List<Umidade> listarUmidadeFarelo(Character c, String campoOrdenacao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Umidade.class);
			consulta.add(Restrictions.eq("tipo", c));
			consulta.createAlias("amostra", "a");
			consulta.addOrder(Order.asc(campoOrdenacao));
			@SuppressWarnings("unchecked")
			List<Umidade> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}

	}
	
	public List<Umidade> listarUmidadeFareloII(Character c, String campoOrdenacao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Umidade.class);
			consulta.add(Restrictions.eq("tipo", c));
			consulta.createAlias("amostra", "a");
			//consulta.createAlias("umidade", "u");
			//consulta.createAlias("u.a.tipoproduto", "p");
			//consulta.add(Restrictions.like("p.descricao", "%orodu%"));
			consulta.setMaxResults(4);
			consulta.addOrder(Order.desc(campoOrdenacao));
			
			@SuppressWarnings("unchecked")
			List<Umidade> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}

	}

}
