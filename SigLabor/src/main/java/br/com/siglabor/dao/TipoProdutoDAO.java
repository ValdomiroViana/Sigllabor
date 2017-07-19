package br.com.siglabor.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import br.com.siglabor.domain.TipoProduto;
import br.com.siglabor.util.HibernateUtil;

public class TipoProdutoDAO extends GenericDAO<TipoProduto> {
	// Método buscarPorProduto é específico da classe TipoProdutoDAO
	@SuppressWarnings("unchecked")
	public List<TipoProduto> buscarPorProduto(Long produtoCodigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(TipoProduto.class);
			// restrictions equivale a clausula where
			consulta.add(Restrictions.eq("produto.codigo", produtoCodigo));
			consulta.addOrder(Order.asc("descricaoTipo"));
			List<TipoProduto> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}

	}

}
