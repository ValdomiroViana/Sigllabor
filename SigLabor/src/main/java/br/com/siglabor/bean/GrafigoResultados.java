package br.com.siglabor.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.siglabor.domain.Amostra;
import br.com.siglabor.domain.Umidade;
@ManagedBean
@ViewScoped
public class GrafigoResultados implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM");
	
	private Umidade umidade;
	private Amostra amostra;

	private CartesianChartModel model;
	public Umidade getUmidade() {
		return umidade;
	}
	public Amostra getAmostra() {
		return amostra;
	}
	public CartesianChartModel getModel() {
		return model;
	}
	
	public void preRender(){
		this.model = new CartesianChartModel();
		adicionarSeries("Todos os pedidos", null);
		adicionarSeries("Meus pedidos", amostra.getAmostraSelecionada());
	}
	
	private void adicionarSeries(String rotulo, Amostra criadoPor){
		//Realiza a consulta
		Map<Date, BigDecimal> valoresPorData = this.umidade.valoresTotaisPorData(15, criadoPor);
		ChartSeries series = new ChartSeries(rotulo);
			for(Date data : valoresPorData.keySet()){
				series.set(DATE_FORMAT.format(data), valoresPorData.get(data));
			}
			
			this.model.addSeries(series);
	}
	
	
}
