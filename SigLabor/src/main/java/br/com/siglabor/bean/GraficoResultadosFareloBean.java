package br.com.siglabor.bean;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.omnifaces.util.Messages;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;

import br.com.siglabor.dao.UmidadeDAO;
import br.com.siglabor.domain.Amostra;
import br.com.siglabor.domain.ProteinaTotal;
import br.com.siglabor.domain.Umidade;

import org.primefaces.model.chart.ChartSeries;

@ManagedBean
@RequestScoped
public class GraficoResultadosFareloBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Amostra amostra;
	private Umidade umidade;
	private List<Umidade> umidades;

	private List<ProteinaTotal> proteinasTotais;

	private CartesianChartModel model;

	public void geraRelatorioUmidades() {
		System.out.println("Chamando.....");
		criaModeloBarras();
	}

	private void criaModeloBarras() {
		model = new CartesianChartModel();
		
		UmidadeDAO umidadeDAO = new UmidadeDAO();
		
		List<Umidade> umidades = umidadeDAO.listar();
		
		ChartSeries umidadeGrafico;
		for (Umidade umidade : umidades) {
			System.out.println("Umidade: " + umidade.getUmidade());
			umidadeGrafico = new ChartSeries();
			String label = String.valueOf(umidade.getCodigo());
			String valorUmidade = String.valueOf(umidade.getUmidade());
			if (valorUmidade != null) {
				umidadeGrafico.setLabel(label);
				umidadeGrafico.set(valorUmidade, null);
				model.addSeries(umidadeGrafico);
			}
			
			umidadeGrafico = null;
		}
		 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("model", this.model);

	}

	public CartesianChartModel getModel() {
		 CartesianChartModel model = (CartesianChartModel) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("model");
		  if (model != null)
              return model;

       return new CartesianChartModel();
		
	}

	public void setModel(CartesianChartModel model) {
		this.model = model;
	}

	public Amostra getAmostra() {
		return amostra;
	}

	public void setAmostra(Amostra amostra) {
		this.amostra = amostra;
	}

	public Umidade getUmidade() {
		return umidade;
	}

	public void setUmidade(Umidade umidade) {
		this.umidade = umidade;
	}

	public List<Umidade> getUmidades() {
		return umidades;
	}

	public void setUmidades(List<Umidade> umidades) {
		this.umidades = umidades;
	}

	public List<ProteinaTotal> getProteinasTotais() {
		return proteinasTotais;
	}

	public void setProteinasTotais(List<ProteinaTotal> proteinasTotais) {
		this.proteinasTotais = proteinasTotais;
	}
	
	
	

	/*
	 * 
	 * @PostConstruct public void init() { createLineModels();
	 * buscarResultados(); }
	 * 
	 * public Amostra getAmostra() { return amostra; }
	 * 
	 * 
	 * 
	 * public Umidade getUmidade() { return umidade; }
	 * 
	 * public List<Umidade> getUmidades() { return umidades; }
	 * 
	 * public List<ProteinaTotal> getProteinasTotais() { return proteinasTotais;
	 * }
	 * 
	 * public LineChartModel getLineModel2() { return lineModel2; }
	 * 
	 * private void createLineModels() {
	 * 
	 * lineModel2 = initCategoryModel(); lineModel2.setTitle("Category Chart");
	 * lineModel2.setLegendPosition("e"); lineModel2.setShowPointLabels(true);
	 * lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Data")); Axis
	 * yAxis = lineModel2.getAxis(AxisType.Y); yAxis.setLabel("Resultados");
	 * yAxis.setMin(0); yAxis.setMax(50); }
	 * 
	 * private LineChartModel initCategoryModel() { LineChartModel model = new
	 * LineChartModel();
	 * 
	 * ChartSeries umidade = new ChartSeries(); umidade.setLabel("Umidade");
	 * umidade.set("02/10", 12.12); umidade.set("03/10", 12.45);
	 * umidade.set("04/10", 12.67); umidade.set("05/10", 11.10);
	 * umidade.set("06/10", 11.90); umidade.set("07/10", 13.12);
	 * 
	 * ChartSeries oleo = new ChartSeries(); oleo.setLabel("% de Óleo");
	 * oleo.set("02/10", 2.12); oleo.set("03/10", 1.92); oleo.set("04/10",
	 * 1.70); oleo.set("05/10", 2.44); oleo.set("06/10", 2.00);
	 * oleo.set("07/10", 1.65);
	 * 
	 * ChartSeries proteina = new ChartSeries();
	 * proteina.setLabel("Proteína bruta"); proteina.set("02/10", 46.12);
	 * proteina.set("03/10", 45.92); proteina.set("04/10", 46.70);
	 * proteina.set("05/10", 46.44); proteina.set("06/10", 46.00);
	 * proteina.set("07/10", 46.15);
	 * 
	 * model.addSeries(umidade); model.addSeries(oleo);
	 * model.addSeries(proteina);
	 * 
	 * return model; }
	 * 
	 */

}