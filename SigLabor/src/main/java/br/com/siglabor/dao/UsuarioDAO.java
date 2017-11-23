package br.com.siglabor.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.siglabor.domain.Usuario;
import br.com.siglabor.util.HibernateUtil;


public class UsuarioDAO extends GenericDAO<Usuario>{
	
	public Usuario autenticar(String login, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			// comparação de igualdade entre o login recebido pelo método
			// e o presente na tabela/classe/objeto usuario
			consulta.add(Restrictions.eq("email", login));
			// algoritmo de criptografia
			// utilização do algoritmo md5
			// gera uma sequ^qncia hexadecimal de 32 caracters
			SimpleHash hash = new SimpleHash("md5", senha);
			// ToHex é o método utilizado para aplicar o algoritmo
			// tem de criptografar de novo para comparar com a salva no banco
			// (criptografada)
			consulta.add(Restrictions.eq("senha", hash.toHex()));

			// retorna apenas um resultado e faz um "cast" para usuário

			Usuario resultado = (Usuario) consulta.uniqueResult();

			return resultado;
		} catch (Exception erro) {
			throw erro;
		} finally {
			sessao.close();
		}

	}



}
