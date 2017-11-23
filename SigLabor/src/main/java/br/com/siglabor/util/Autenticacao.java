package br.com.siglabor.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.siglabor.bean.AutenticacaoBean;
import br.com.siglabor.domain.Usuario;

@SuppressWarnings("serial")
public class Autenticacao implements PhaseListener{

	@Override
	public void afterPhase(PhaseEvent event) {
		String paginaAtual = Faces.getViewId();
		System.out.println("Página Atual" + paginaAtual);
		boolean paginaAutenticacao = paginaAtual.contains("login.xhtml");
		
		if(!paginaAutenticacao){
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			if(autenticacaoBean == null){
				Faces.navigate("/login.xhtml");
				return;
			}
			Usuario usuario = autenticacaoBean.getUsuarioLogado();
			if(usuario == null){
				Faces.navigate("/login.xhtml");
				return;
			}
		}
		
		
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		
		return PhaseId.ANY_PHASE;
	}
	
}
