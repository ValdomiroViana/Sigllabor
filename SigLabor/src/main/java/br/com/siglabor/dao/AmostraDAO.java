package br.com.siglabor.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import br.com.siglabor.domain.Amostra;
import br.com.siglabor.util.HibernateUtil;

public class AmostraDAO extends GenericDAO<Amostra>{
	
	public Long sqlMax(Amostra amostra) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Criteria criteria = sessao.createCriteria(amostra.getClass())
				.setProjection(Projections.max("codigo"));
		Long maxId = (Long) criteria.uniqueResult();

		return maxId;
	}

}
