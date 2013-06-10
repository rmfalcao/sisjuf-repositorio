package com.vortice.seguranca.cliente.bean;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.vortice.exception.AmbienteException;
import com.vortice.exception.AplicacaoException;
import com.vortice.seguranca.abstracao.cliente.SegurancaDelegateIf;
import com.vortice.seguranca.abstracao.cliente.bean.SegurancaPageBean;
import com.vortice.seguranca.vo.PerfilVO;
import com.vortice.seguranca.vo.UsuarioVO;

public class UsuarioBean extends SegurancaPageBean {

	private UsuarioVO				usuario;
	private Collection 				perfis;
	private Collection 				nPerfis;
    private SegurancaDelegateIf 	delegate;
    private Collection<UsuarioVO> 	usuarios;
    private String 					rsenha;
    private String 					nsenha;
    private Logger 					log = Logger.getLogger(UsuarioBean.class);
    private PerfilVO 				perfil;

	public UsuarioBean(){
		try{
			usuario = new UsuarioVO();
			usuarios = new ArrayList<UsuarioVO>();
			perfil = new PerfilVO();
			rsenha = "";
			nsenha = "";
		}catch(Exception e){
			tratarExcecao(e);
		}
	}

	public String reset(){
		usuario = new UsuarioVO();
		return this.getSucesso();
	}
	
	public String salvar(){
		try{
			if (getUsuario().getCodigo() != null && getUsuario().getCodigo().intValue() > 0){
				delegate.update(getUsuario());
			}else{
				usuario.setSenha("inicio");
				delegate.insert(getUsuario());
			}
			FacesMessage msgs = new FacesMessage("Registro Salvo com Sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return this.getSucesso();
		}catch(Exception e){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return tratarExcecao(e);
		}
	}
	
	public String consultar(){
		try{
			if (usuario.getPerfis() == null) usuario.setPerfis(new ArrayList<PerfilVO>());
			usuario.getPerfis().add(perfil);
			setUsuarios(delegate.findByFilter(getUsuario()));
			
			return getSucesso();
		}catch(Exception e){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return tratarExcecao(e);
		}
	}
	
	public String remover(){
		try{
			delegate.remove(getUsuario());
			FacesMessage msgs = new FacesMessage("Registro Removido com Sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			usuarios = new ArrayList<UsuarioVO>();
			return getSucesso();
		}catch(Exception e){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return tratarExcecao(e);
		}
	}
	
	public String carregar(){
		try{
			setUsuario(delegate.findByPrimaryKey(getUsuario()));
			setPerfis(delegate.findPerfilByNUsuario(usuario));
			Collection<PerfilVO> collPerfil = delegate.findPerfilByNUsuario(usuario);
			perfis = (collPerfil != null) ? this.getSelect(collPerfil, "codigo", "nome") : new ArrayList();
			return getSucesso();
		}catch(Exception e){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return tratarExcecao(e);
		}
	}

	public String logar(){
		try{
			log.debug("Login: " + getUsuario().getLogin());
			log.debug("Senha: " + getUsuario().getSenha());
			UsuarioVO vo = delegate.autenticar(getUsuario());
			log.debug("usuario " + vo);
			if (vo != null){
				setUsuario(vo);
				FacesContext facesContext = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession)facesContext.getExternalContext().getSession(true);
				session.setAttribute("usuario", getUsuario());
				return getSucesso();
			}else{
				String msg = "Login ou senhas incorretos";
				
				FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
				FacesContext.getCurrentInstance().addMessage(null, msgs);
				return getFalha();
			}
		}catch(Exception e){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return tratarExcecao(e);
		}
	}
	
	public String mudarSenha(){
		try{
			UsuarioVO vo = delegate.autenticar(getUsuario());
			if (vo != null){
				setUsuario(vo);
				if (nsenha.equals(rsenha)){
					usuario.setSenha(nsenha);
					delegate.update(usuario);
				}else{
					String msg = "A foram digitadas senhas diferentes";
					FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
					FacesContext.getCurrentInstance().addMessage(null, msgs);
					return getFalha();
				}
				FacesMessage msgs = new FacesMessage("A senha foi modificada com sucesso.");
				FacesContext.getCurrentInstance().addMessage(null, msgs);
				return getSucesso();
			}else{
				String msg = "Login ou senhas incorretos";
				FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
				FacesContext.getCurrentInstance().addMessage(null, msgs);
				return getFalha();
			}
		}catch(Exception e){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return tratarExcecao(e);
		}
	}
	
	public String adicionarPerfil(){
		try{
			usuario = delegate.findByPrimaryKey(usuario);
			usuario.setPerfis(delegate.findPerfilByUsuario(usuario));
			usuario.getPerfis().add(perfil);
			delegate.update(usuario);
			usuario.setPerfis(delegate.findPerfilByUsuario(usuario));
			Collection<PerfilVO> collPerfil = delegate.findPerfilByNUsuario(usuario);
			perfis = (collPerfil != null) ? this.getSelect(collPerfil, "codigo", "nome") : new ArrayList();
			FacesMessage msgs = new FacesMessage("Perfil adicionado ao usuario com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return getSucesso();
		}catch(AplicacaoException aplEx){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, aplEx.getMessage(), aplEx.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			log.error("Erro de Aplicação ", aplEx);
			return "falha";
		}catch(AmbienteException ambEx){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			log.error("Erro de Hambiente ", ambEx);
			return "falha";
		}catch(Exception e){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			log.error("Erro de exception ", e);
			return "falha";
		}
	}
	
	public String removerPerfil(){
		try{
			usuario = delegate.findByPrimaryKey(usuario);
			usuario.setPerfis(delegate.findPerfilByUsuario(usuario));
			log.debug("Perfil que vai ser removido " + perfil.getCodigo());
			usuario.getPerfis().remove(perfil);
			log.debug("Qtd de Perfis do usuario para atualizar " + usuario.getPerfis().size());
			delegate.update(usuario);
			Collection<PerfilVO> collPerfil = delegate.findPerfilByNUsuario(usuario);
			perfis = (collPerfil != null) ? this.getSelect(collPerfil, "codigo", "nome") : new ArrayList();
			FacesMessage msgs = new FacesMessage("Perfil removido do usuário com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return getSucesso();
		}catch(AplicacaoException aplEx){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, aplEx.getMessage(), aplEx.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			log.error("Erro de Aplicação ", aplEx);
			return "falha";
		}catch(AmbienteException ambEx){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			log.error("Erro de Hambiente ", ambEx);
			return "falha";
		}catch(Exception e){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			log.error("Erro de exception ", e);
			return "falha";
		}
	}
	
	public void scrollerAction(ActionEvent event){
    }
	
	public final UsuarioVO getUsuario() {
		return usuario;
	}

	public final void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

	public Collection getPerfis() {
		try {
			perfis = (delegate.findAllPerfil() != null) ? getSelect(delegate.findAllPerfil(), "codigo", "nome") : new ArrayList();
		} catch (Exception e) {
			log.error("ERRO NO MOMENTO DE RETORNAR PERFIS", e);
		}
		return perfis;
	}

	public void setPerfis(Collection perfis) {
		this.perfis = perfis;
	}

	public String getRsenha() {
		return rsenha;
	}

	public void setRsenha(String rsenha) {
		this.rsenha = rsenha;
	}

	public String getNsenha() {
		return nsenha;
	}

	public void setNsenha(String nsenha) {
		this.nsenha = nsenha;
	}

	public PerfilVO getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilVO perfil) {
		this.perfil = perfil;
	}

	public Collection getNPerfis() {
		return nPerfis;
	}

	public void setNPerfis(Collection perfis) {
		try {
			nPerfis = (delegate.findAllPerfil() != null) ? getSelect(delegate.findAllPerfil(), "codigo", "nome") : new ArrayList();
		} catch (Exception e) {
			log.error("ERRO NO MOMENTO DE RETORNAR NPERFIS", e);
		}
		nPerfis = perfis;
	}

	public Collection getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Collection usuarios) {
		this.usuarios = usuarios;
	}

	public void setDelegate(SegurancaDelegateIf delegate) {
		this.delegate = delegate;
	}

}