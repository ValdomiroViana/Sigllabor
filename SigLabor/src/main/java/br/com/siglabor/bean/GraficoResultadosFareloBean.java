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

	@PostConstruct
	public void init() {
		createLineModels();
	}

	public CartesianChartModel getLineModel1() {
		return lineModel1;
	}

	private void createLineModels() {
		lineModel1 = initLinearModel();
		lineModel1.setTitle("Resultados Farelo Média Produção");
		lineModel1.setLegendPosition("e");
		lineModel1.setShowPointLabels(true);
		lineModel1.getAxes().put(AxisType.X, new CategoryAxis("Data"));
		Axis yAxis = lineModel1.getAxis(AxisType.Y);
		yAxis.setLabel("");
		yAxis.setMin(00.01);
		yAxis.setMax(50.01);

	}

	private LineChartModel initLinearModel() {
		LineChartModel model = new LineChartModel();

		UmidadeDAO umidadeDAO = new UmidadeDAO();
		List<Umidade> umidades = umidadeDAO.listarOrdenadoPorData("c.dataCheckList");
		ChartSeries series1 = new ChartSeries();
		for (Umidade umdds : umidades) {
			System.out.println("Umidades" + umdds.getUmidade() + "-" + umdds.getAmostra().getTipoProduto().getProduto().getDescricao());
			series1.setLabel("Umidades");
			series1.set(dataFormatada.format(umdds.getAmostra().getDataColeta()), umdds.getUmidade());
		}
		ProteinaTotalDAO proteinaTotalDAO = new ProteinaTotalDAO();
		List<ProteinaTotal> proteinasTotais = proteinaTotalDAO.listarOrdenadoPorData("c.dataCheckList");
		ChartSeries series2 = new ChartSeries();
		for (ProteinaTotal protTts : proteinasTotais) {
			series2.setLabel("Proteínas");
			series2.set(dataFormatada.format(protTts.getAmostra().getDataColeta()), protTts.getProteinaTotal());
		}
		
		PercentualOleoDAO percentualOleoDAO = new PercentualOleoDAO();
		List<PercentualOleo> percentuaisOleo = percentualOleoDAO.listarOrdenadoPorData("c.dataCheckList");
		ChartSeries series3 = new ChartSeries();
		for(PercentualOleo percentOleo : percentuaisOleo){
			series3.setLabel("% de Óleo");
			series3.set(dataFormatada.format(percentOleo.getAmostra().getDataColeta()),percentOleo.getPercentualOleo());
		}

		model.addSeries(series1);
		model.addSeries(series2);
		model.addSeries(series3);

		return model;
	} 
	

}
