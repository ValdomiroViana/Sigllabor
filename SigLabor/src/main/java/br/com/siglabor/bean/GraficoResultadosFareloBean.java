package br.com.siglabor.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import br.com.siglabor.dao.PercentualOleoDAO;
import br.com.siglabor.dao.ProteinaTotalDAO;
import br.com.siglabor.dao.UmidadeDAO;
import br.com.siglabor.domain.PercentualOleo;
import br.com.siglabor.domain.ProteinaTotal;
import br.com.siglabor.domain.Umidade;

@ManagedBean
public class GraficoResultadosFareloBean implements Serializable {

	private static DateFormat dataFormatada = new SimpleDateFormat("dd/MM");

	private static final long serialVersionUID = 1L;
	private LineChartModel lineModel1;
	private BarChartModel barModelUmidade;
	private BarChartModel barModelProteina;
	private BarChartModel barModelPercentOleo;
	private List<Umidade> umidades;
	private Umidade umidade;

	@PostConstruct
	public void init() {
		createLineModels();
		createBarModelsUmidade();
		createBarModelsPerentcOleo();
		createBarModelsProteina();
	}

	public CartesianChartModel getLineModel1() {
		return lineModel1;
	}
	
	
	public BarChartModel getBarModel() {
		return barModelUmidade;
	}
	
	public BarChartModel getBarModelProteina() {
		return barModelProteina;
	}

	public BarChartModel getBarModelPercentOleo() {
		return barModelPercentOleo;
	}

	public List<Umidade> getUmidades() {
		return umidades;
	}
	
	

	public Umidade getUmidade() {
		return umidade;
	}

	private void createLineModels() {
		lineModel1 = initLinearModel();
		lineModel1.setTitle("Resultados Farelo Média Produção");
		lineModel1.setLegendPosition("e");
		lineModel1.setShowPointLabels(true);
		lineModel1.setAnimate(true);
		lineModel1.setDatatipFormat("00.00");
		lineModel1.getAxes().put(AxisType.X, new CategoryAxis("Data"));
		Axis yAxis = lineModel1.getAxis(AxisType.Y);
		yAxis.setLabel("");
		yAxis.setMin(00.01);
		yAxis.setMax(50.01);

	}
	
	//Cria um gráfico de barras para resultados de umidade
	
	@SuppressWarnings("unused")
	private void createBarModelsUmidade(){
		barModelUmidade = initBarModelUmidade();
		barModelUmidade.setLegendPosition("ne");
		barModelUmidade.setAnimate(true);
		barModelUmidade.setBarMargin(3);
		barModelUmidade.getAxes().put(AxisType.X, new CategoryAxis("Data"));
		Axis xAxis = barModelUmidade.getAxis(AxisType.X);
		 Axis yAxis = barModelUmidade.getAxis(AxisType.Y);
	        yAxis.setLabel("Umidade");
	        yAxis.setMin(0);
	        yAxis.setMax(15);
		
	}
	//cria um gráfico de barras para resultados de proteína
	@SuppressWarnings("unused")
	private void createBarModelsProteina(){
		barModelProteina = initBarModelProteina();
		barModelProteina.setLegendPosition("ne");
		barModelProteina.setAnimate(true);
		barModelProteina.setBarMargin(3);
		barModelProteina.getAxes().put(AxisType.X, new CategoryAxis("Data"));
		Axis xAxis = barModelProteina.getAxis(AxisType.X);
		 Axis yAxis = barModelProteina.getAxis(AxisType.Y);
	        yAxis.setLabel("Proteína");
	        yAxis.setMin(0);
	        yAxis.setMax(50);
		
	}
	
	@SuppressWarnings("unused")
	private void createBarModelsPerentcOleo(){
		barModelPercentOleo = initBarModelPercentOleo();
		barModelPercentOleo.setLegendPosition("ne");
		barModelPercentOleo.setAnimate(true);
		barModelPercentOleo.setBarMargin(3);
		barModelPercentOleo.getAxes().put(AxisType.X, new CategoryAxis("Data"));
		Axis xAxis = barModelPercentOleo.getAxis(AxisType.X);
		 Axis yAxis = barModelPercentOleo.getAxis(AxisType.Y);
	        yAxis.setLabel("%'de óleo");
	        yAxis.setMin(0);
	        yAxis.setMax(4);
		
	}

	private BarChartModel initBarModelUmidade() {
		BarChartModel modelBarUmidade = new BarChartModel();
		UmidadeDAO umidadeDAO = new UmidadeDAO();
		List<Umidade> umidades = umidadeDAO.listarUmidadeFareloII('p', "a.dataColeta");
		ChartSeries serie = new ChartSeries();
		for (Umidade umdds : umidades) {
			System.out.println("Umidades GroupBy" + umdds.getUmidade() + "-" + umdds.getAmostra().getTipoProduto().getProduto().getDescricao() +"-"+ umdds.getTipo());
			serie.setLabel("Umidades");
			serie.set(dataFormatada.format(umdds.getAmostra().getDataColeta()), umdds.getUmidade());
			
		}
		modelBarUmidade.addSeries(serie);
		//alterando a cor do gráfico
		modelBarUmidade.setSeriesColors("20B2AA");
		return modelBarUmidade;
	}
	
	
	//Carrega os dados para o gráfico de barras de proteína
	private BarChartModel initBarModelProteina() {
		BarChartModel modelBarProteina = new BarChartModel();
		ProteinaTotalDAO proteinaTotalDAO = new ProteinaTotalDAO();
		List<ProteinaTotal> proteinas = proteinaTotalDAO.listaProteinaFareloII("a.dataColeta");
		ChartSeries serieProteina = new ChartSeries();
		for (ProteinaTotal pts : proteinas) {
			System.out.println("Umidades" + pts.getProteinaTotal());
			serieProteina.setLabel("Proteínas");
			serieProteina.set(dataFormatada.format(pts.getAmostra().getDataColeta()), pts.getProteinaTotal());
			
		}
		modelBarProteina.addSeries(serieProteina);
		modelBarProteina.setSeriesColors("00FA9A");
		return modelBarProteina;
	}
	
	//Carrega os dados para o gráfico de barras de proteína
	private BarChartModel initBarModelPercentOleo() {
		BarChartModel modelBarPercentOleo = new BarChartModel();
		PercentualOleoDAO percentualOleoDAO = new PercentualOleoDAO();
		List<PercentualOleo> percentualOleos = percentualOleoDAO.listarPercentualOleoFareloII("a.dataColeta");
		ChartSeries serieOleo = new ChartSeries();
		for (PercentualOleo percentOleos : percentualOleos) {
			System.out.println("Óleos" + percentOleos.getPercentualOleo());
			serieOleo.setLabel("% de óleo");
			serieOleo.set(dataFormatada.format(percentOleos.getAmostra().getDataColeta()), percentOleos.getPercentualOleo());
			
		}
		modelBarPercentOleo.addSeries(serieOleo);
		modelBarPercentOleo.setSeriesColors("FFD700");
		return modelBarPercentOleo;
	}

	private LineChartModel initLinearModel() {
		LineChartModel model = new LineChartModel();

		UmidadeDAO umidadeDAO = new UmidadeDAO();
		List<Umidade> umidades = umidadeDAO.listarUmidadeFarelo('p', "a.dataColeta");
		ChartSeries series1 = new ChartSeries();
		
		for (Umidade umdds : umidades) {
			System.out.println("Umidades" + umdds.getUmidade() + "-" + umdds.getAmostra().getTipoProduto().getProduto().getDescricao() +"-"+ umdds.getTipo());
			series1.setLabel("Umidades");
			series1.set(dataFormatada.format(umdds.getAmostra().getDataColeta()), umdds.getUmidade());
		}
		
		ProteinaTotalDAO proteinaTotalDAO = new ProteinaTotalDAO();
		List<ProteinaTotal> proteinasTotais = proteinaTotalDAO.listarProteinaFarelo("a.dataColeta");
		ChartSeries series2 = new ChartSeries();
		for (ProteinaTotal protTts : proteinasTotais) {
			series2.setLabel("Proteínas");
			series2.set(dataFormatada.format(protTts.getAmostra().getDataColeta()), protTts.getProteinaTotal());
		}
		
		PercentualOleoDAO percentualOleoDAO = new PercentualOleoDAO();
		List<PercentualOleo> percentuaisOleo = percentualOleoDAO.listarPercentualOleoFarelo("a.dataColeta");
		ChartSeries series3 = new ChartSeries();
		for(PercentualOleo percentOleo : percentuaisOleo){
			series3.setLabel("% de Óleo");
			series3.set(dataFormatada.format(percentOleo.getAmostra().getDataColeta()),percentOleo.getPercentualOleo());
		}

		model.addSeries(series1);
		model.addSeries(series2);
		model.addSeries(series3);
		//alterar a cor das séries
		model.setSeriesColors("20B2AA, 00FA9A,  FFD700");
		

		return model;
	} 

}
