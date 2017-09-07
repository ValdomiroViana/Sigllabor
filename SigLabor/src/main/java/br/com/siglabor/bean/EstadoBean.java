package br.com.siglabor.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;

import br.com.siglabor.dao.EstadoDAO;
import br.com.siglabor.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
// managedBean serve para os arquivos html da tela se comunicarem com a parte lógica
@ViewScoped
//é a maneira como a tela irá se comunicar 

public class EstadoBean implements Serializable{
	//já foi criado na classe de dominio 
		// instancia o objeto os atributos já estão no dominio
		private Estado estado;

		//para gerar uma lista de estados
		
		private List<Estado> estados;
		
		public EstadoBean(){
			estado = new Estado();
		}

		public List<Estado> getEstados() {
			return estados;
		}

		public void setEstados(List<Estado> estados) {
			this.estados = estados;
		}

		public Estado getEstado() {
			return estado;
		}

		public void setEstado(Estado estado) {
			this.estado = estado;
		}
		
		

		@PostConstruct
		public void iniciar() {
			try {
				EstadoDAO estadoDAO = new EstadoDAO();
				estados = estadoDAO.listarOrdenado("nome");

			} catch (RuntimeException runtimeException) {
				// Exibe uma mensagem de erro
				Messages.addGlobalError(runtimeException.getMessage());
			}
		}
		

}
