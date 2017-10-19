package br.com.siglabor.domain;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.apache.commons.lang3.time.DateUtils;
import br.com.siglabor.dao.UmidadeDAO;

@Entity
public class Umidade extends GenericDomain{
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private Character tipo;
	
	@Column(length = 4, nullable = false)
	private String identificacao;
	
	@Column(nullable = false, precision = 7, scale = 4)
	private BigDecimal pa;
	
	@Column( precision = 7, scale = 4)
	private BigDecimal pa_Mais_b;
	
	@Column( precision = 7, scale = 4)
	private BigDecimal pf;
	
	@Column( precision = 7, scale = 4)
	private BigDecimal umidade;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Amostra amostra;

	public Character getTipo() {
		return tipo;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	public BigDecimal getPa() {
		return pa;
	}

	public void setPa(BigDecimal pa) {
		this.pa = pa;
	}

	public BigDecimal getPa_Mais_b() {
		return pa_Mais_b;
	}

	public void setPa_Mais_b(BigDecimal pa_Mais_b) {
		this.pa_Mais_b = pa_Mais_b;
	}

	public BigDecimal getPf() {
		return pf;
	}

	public void setPf(BigDecimal pf) {
		this.pf = pf;
	}

	public BigDecimal getUmidade() {
		return umidade;
	}

	public void setUmidade(BigDecimal umidade) {
		this.umidade = umidade;
	}

	public Amostra getAmostra() {
		return amostra;
	}

	public void setAmostra(Amostra amostra) {
		this.amostra = amostra;
	}
	
	
	@Transient
	public String getTipoFormatado(){
		String tipoFormatado = null;
		
		if(tipo == 'p') {
			tipoFormatado = "Primeira";
		}else if(tipo == 's') {
			tipoFormatado = "Segunda";
		}
		return tipoFormatado;
	}
	
	
	@Transient
	private Amostra amostraSelecionada;

	
	@SuppressWarnings("unchecked")
	public Map<Date, BigDecimal> valoresTotaisPorData(Integer numeroDeDias, Amostra criadoPor) {
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
		List<Umidade> resultadosProData = umidadeDao.buscarPorAmostra(amostraSelecionada);
		
		for(Umidade umidade : resultadosProData){
			resultado.put(umidade.getAmostra().getDataColeta(), umidade.getUmidade());
			
		}
		
		return resultado;
	}

	private  Map<Date, BigDecimal> criarMapaVazio(Integer numeroDeDias, Calendar dataInicial) {
		dataInicial = (Calendar) dataInicial.clone();
		Map<Date, BigDecimal> mapaInicial = new TreeMap<>();
		for (int i = 0; i <= numeroDeDias; i++) {
			mapaInicial.put(dataInicial.getTime(), BigDecimal.ZERO);
			dataInicial.add(Calendar.DAY_OF_MONTH, 1);
		}
		return mapaInicial;
	}
	

}
