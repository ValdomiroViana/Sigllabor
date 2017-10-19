package br.com.siglabor.bean;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.siglabor.dao.UmidadeDAO;
import br.com.siglabor.domain.Amostra;
import br.com.siglabor.domain.Umidade;

public class TesteConsulta {
	public static void main(String[] args){
		
		
		Map<Date, BigDecimal> valores =  valoresTotaisPorData(15, null, null);
		for(Date data : valores.keySet()){
			System.out.println(data + "=" + valores.get(data));
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Map<Date, BigDecimal> valoresTotaisPorData(Integer numeroDeDias, Amostra criadoPor, Session session) {
		Long amostraSelecionada = 44L;
		//pega a data atual
		numeroDeDias -= 1;
		Calendar dataInicial = Calendar.getInstance();
		//DateUtils da biblioteca CommonsLang
		//Truncate é usado para truncar a data no dia do mês para não mostrar hora, segundos...
		dataInicial = DateUtils.truncate(dataInicial, Calendar.DAY_OF_MONTH);
		//Data inicial é a data atual menos o numeroDeDias
		//para fazer a subtração adiciona-se um número negatido
		dataInicial.add(Calendar.DAY_OF_MONTH, numeroDeDias * -1);
		
		Map<Date, BigDecimal> resultado = criarMapaVazio(numeroDeDias, dataInicial);
		UmidadeDAO umidadeDao = new UmidadeDAO();
		List<Umidade> resultadosProData = umidadeDao.listar();
		
		for(Umidade umidade : resultadosProData){
			resultado.put(umidade.getAmostra().getDataColeta(), umidade.getUmidade());
			
		}
		
		return resultado;
	}

	private static Map<Date, BigDecimal> criarMapaVazio(Integer numeroDeDias, Calendar dataInicial) {
		dataInicial = (Calendar) dataInicial.clone();
		Map<Date, BigDecimal> mapaInicial = new TreeMap<>();
		for (int i = 0; i <= numeroDeDias; i++) {
			mapaInicial.put(dataInicial.getTime(), BigDecimal.ZERO);
			dataInicial.add(Calendar.DAY_OF_MONTH, 1);
		}
		return mapaInicial;
	}
}
	
	
