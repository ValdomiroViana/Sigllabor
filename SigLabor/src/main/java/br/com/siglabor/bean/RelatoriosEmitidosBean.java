package br.com.siglabor.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.siglabor.util.HibernateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
@RequestScoped
@ManagedBean
public class RelatoriosEmitidosBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Date dataInicio;
	private Date dataFim;

	public void emitir() {
		try{
		
			//Opegar o caminho do arquivo .jasper
		String caminho = Faces.getRealPath("/Relatorios/relatorioFarelo.jasper");
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("data_inicio", this.dataInicio);
		parametros.put("data_fim", this.dataFim);
		//Recebe três parâmetros: o caminho do arquivo, os parâmetros e a conexão
		Connection conexao = HibernateUtil.getConexao();
		//JasperPrint recebe um relatório populado
		JasperPrint relatorio = JasperFillManager.fillReport(caminho, parametros, conexao);
		//Parâmetro true habilita o control p
		JasperPrintManager.printReport(relatorio, true);
		
		}catch(JRException erro){
			Messages.addGlobalError("Ocorreu um erro ao gerar o relatório!");
			erro.printStackTrace();
		}
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	

}
