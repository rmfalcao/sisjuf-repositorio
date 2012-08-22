package com.vortice.seguranca.cliente.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

import org.apache.log4j.Logger;

import com.vortice.exception.AmbienteException;
import com.vortice.exception.AplicacaoException;
import com.vortice.seguranca.abstracao.cliente.SegurancaDelegateIf;
import com.vortice.seguranca.abstracao.cliente.bean.SegurancaPageBean;
import com.vortice.seguranca.vo.FuncionalidadeVO;
import com.vortice.seguranca.vo.PerfilVO;

public class PerfilBean extends SegurancaPageBean {
	
	private PerfilVO 				perfil;
	private SegurancaDelegateIf 	delegate;
	private Collection<PerfilVO>	perfis;
	private List 					funcionalidades;
	private List 					nfuncionalidades;
	private FuncionalidadeVO 		funcionalidade;
	private Logger 					log = Logger.getLogger(PerfilBean.class);
	
	public PerfilBean(){
		try{
			perfis = new ArrayList<PerfilVO>();
			perfil = new PerfilVO();
			funcionalidades = new ArrayList();
			funcionalidade = new FuncionalidadeVO();
			
		//	this.consultar();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String salvar(){
		try{
			if (getPerfil().getCodigo() != null && getPerfil().getCodigo().intValue() > 0){
				Collection collFuncionalidade = delegate.findFuncionalidadeByPerfil(perfil);
				perfil.setFuncionalidades(collFuncionalidade);
				delegate.update(getPerfil());
			}else{
				delegate.insert(getPerfil());
			}
			this.consultar();
			FacesMessage msgs = new FacesMessage("Registro Salvo com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return getSucesso();
		}catch(Exception e){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return tratarExcecao(e);
		}
	}
	
	public String consultar(){
		try{
			setPerfis(delegate.findByFilter(new PerfilVO()));
			log.debug("Qtd " + perfis.size());
			return getSucesso();
		}catch(Exception e){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return tratarExcecao(e);
		}
	}
	
	public String reset(){
		try{
			perfil = new PerfilVO();
			return getSucesso();
		}catch(Exception e){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return tratarExcecao(e);
		}
	}
	
	public String adicionarFuncionalidade(){
		try{
			perfil = delegate.findByPrimaryKey(perfil);
			perfil.setFuncionalidades(delegate.findFuncionalidadeByPerfil(perfil));
			perfil.getFuncionalidades().add(funcionalidade);
			delegate.update(perfil);
			perfil.setFuncionalidades(delegate.findFuncionalidadeByPerfil(perfil));
			Collection<FuncionalidadeVO> collFuncionalidades = delegate.findFuncionalidadeByNPerfil(getPerfil());
			nfuncionalidades = getSelect(collFuncionalidades, "codigo", "nome");
//			for (FuncionalidadeVO funcionalidade : collFuncionalidades){
//				
//			}
			FacesMessage msgs = new FacesMessage("Funcionalidade adicionada ao perfil com sucesso.");
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
	
	public String removerFuncionalidade(){
		try{
			perfil = delegate.findByPrimaryKey(perfil);
			perfil.setFuncionalidades(delegate.findFuncionalidadeByPerfil(perfil));
			log.debug("Funcionalidade que vai ser removida" + funcionalidade.getCodigo());
			perfil.getFuncionalidades().remove(funcionalidade);
			log.debug("Qtd de funcionalidade do perfil para atualizar " + perfil.getFuncionalidades().size());
			delegate.update(perfil);
			Collection collFuncionalidades = delegate.findFuncionalidadeByNPerfil(getPerfil());
			nfuncionalidades = getSelect(collFuncionalidades, "codigo", "nome");
			FacesMessage msgs = new FacesMessage("Funcionalidade removida do perfil com sucesso.");
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
	
	public String remover(){
		try{
			delegate.remove(getPerfil());
			FacesMessage msgs = new FacesMessage("Registro Removido com Sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			this.consultar();
			return getSucesso();
		}catch(Exception e){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return tratarExcecao(e);
		}
	}
	
	public String carregar(){
		try{
			setPerfil(delegate.findByPrimaryKey(getPerfil()));
			perfil.setFuncionalidades(delegate.findFuncionalidadeByPerfil(getPerfil()));
			log.debug("Quantidade de funcionalidades do perfil " + perfil.getFuncionalidades().size());
			Collection collFuncionalidades = delegate.findFuncionalidadeByNPerfil(getPerfil());
			nfuncionalidades = getSelect(collFuncionalidades, "codigo", "nome");
			return getSucesso();
		}catch(Exception e){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return tratarExcecao(e);
		}
	}
	
	public void scrollerAction(ActionEvent event){
    }

	public final PerfilVO getPerfil() {
		return perfil;
	}

	public final void setPerfil(PerfilVO perfil) {
		this.perfil = perfil;
	}

	public List getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(List funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	public List getNfuncionalidades() {
		try {
			nfuncionalidades = (delegate.findFuncionalidadeAll() != null && delegate.findFuncionalidadeAll().size() > 0) ? getSelect(delegate.findFuncionalidadeAll(), "codigo", "nome") : new ArrayList();
		} catch (Exception e) {
			log.error("Erro no momento de recuperar nfuncionalidades", e);
		}
		return nfuncionalidades;
	}

	public void setNfuncionalidades(List nfuncionalidades) {
		this.nfuncionalidades = nfuncionalidades;
	}
	
	public void validaFuncao(FacesContext context, UIComponent component, Object value)  throws ValidatorException{
		funcionalidades = new ArrayList();
		String[] s = (String[])value;
		for (int i = 0; i < s.length; i++){
			funcionalidades.add(new SelectItem(s[i], "", null));
		}
	}

	public FuncionalidadeVO getFuncionalidade() {
		return funcionalidade;
	}

	public void setFuncionalidade(FuncionalidadeVO funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	public Collection<PerfilVO> getPerfis() {
		if (perfis == null || perfis.size() == 0) {
			this.consultar();
		}
		return perfis;
	}

	public void setPerfis(Collection<PerfilVO> perfis) {
		this.perfis = perfis;
	}

	public void setDelegate(SegurancaDelegateIf delegate) {
		this.delegate = delegate;
	}
	
}