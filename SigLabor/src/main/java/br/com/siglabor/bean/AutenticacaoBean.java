package br.com.siglabor.bean;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.siglabor.dao.UsuarioDAO;
import br.com.siglabor.domain.Usuario;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	private Usuario usuario;
	private Usuario usuarioLogado;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	// inicializa o usuário e a pessoa atrelada a ele
	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
	}

	public void autenticar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();

			// usuario logado recebe o resultado do método autenticar que está
			// no Usuario.DAO
			usuarioLogado = usuarioDAO.autenticar(usuario.getEmail(), usuario.getSenha());
			if (usuarioLogado == null) {
				Messages.addGlobalError("Email e/ou senha incorretos");
				System.out.println("Erro");
				return;
			}else{
				System.out.println("Ok");
			Faces.redirect("./resultados/dashboard.xhtml");
			}
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
	}
	
	public boolean temPermissoes(List<String> permissoes){		
		for(String permissao : permissoes){
			if(usuarioLogado.getTipo() == permissao.charAt(0)){
				return true;
			}
		}
		
		return false;
	}
	//Logout
	
	public String sairLogin() {
		return "/login.xhtml?faces-redirect=true";
		
	}
	
	

}
