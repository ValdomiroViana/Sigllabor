package br.com.siglabor.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Messages;

import br.com.siglabor.dao.UsuarioDAO;
import br.com.siglabor.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable{
	private Usuario usuario;
	private List<Usuario> usuarios;
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public void novo(){
		usuario = new Usuario();
	}
	
	//Método salvar
	public void salvar(){
		try{
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			//criação da senha criprografada com o shiro
			//define o método e o algoritmo de criptografia, bem como o atributo que será criptografado
			SimpleHash hash = new SimpleHash("md5", usuario.getSenha());
			//Executa as regras definidas no hash e transforma a senha
			usuario.setSenha(hash.toHex());
			
			usuarioDAO.salvar(usuario);
			usuario = new Usuario();
			usuarios = usuarioDAO.listarOrdenado("nomeDoUsuario");
			Messages.addGlobalInfo("Usuários salvo com sucesso!", usuario.getNomeDoUsuario());
		}catch(RuntimeException erro){
			Messages.addGlobalInfo("Erro ao tentar salvar o Usuário - !");
			erro.printStackTrace();
		}
	}
	
	@PostConstruct
	public void listar(){
		try{
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar();
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuários");
			erro.printStackTrace();
		}
	}
	
	public void editar(){
		
	}
	
	
}
