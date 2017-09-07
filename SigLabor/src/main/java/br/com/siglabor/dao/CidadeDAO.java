package br.com.siglabor.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import br.com.siglabor.domain.Cidade;
import br.com.siglabor.util.HibernateUtil;

public class CidadeDAO extends GenericDAO<Cidade> {

	// MÉTODO BUSCA POR ESTADO é um método específico da CidadeDAO
	@SuppressWarnings("unchecked")
	public List<Cidade> buscarPorEstado(Long estadoCodigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Cidade.class);
			// Restrictions equivale a where do banco
			consulta.add(Restrictions.eq("estado.codigo", estadoCodigo));
			consulta.addOrder(Order.asc("nome"));
			List<Cidade> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
