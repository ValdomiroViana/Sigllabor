package br.com.siglabor.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import br.com.siglabor.domain.Amostra;
import br.com.siglabor.domain.RecEmbarcado;
import br.com.siglabor.util.HibernateUtil;

public class AmostraDAO extends GenericDAO<Amostra>{
	
	public Long sqlMax(Amostra amostra) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Criteria criteria = sessao.createCriteria(amostra.getClass())
				.setProjection(Projections.max("codigoAmostra"));
		Long maxId = (Long) criteria.uniqueResult();

		return maxId;
	}

	public void salvar(Amostra amostra, RecEmbarcado recEmbarcado) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
			try{
				transacao = sessao.beginTransaction();
				//persistência da amostra e captura do id
				//os métodos save e merge do hibernate capturam a chave ´
				//primária dos dados que serão persistidos
				sessao.save(amostra);
				recEmbarcado.setAmostra(amostra);
				sessao.save(recEmbarcado);
				transacao.commit();
			}catch(RuntimeException erro){
				if (transacao != null){
					transacao.rollback();
				}
				throw erro;
				
			} finally{
				sessao.close();
			}
		
	}



}
