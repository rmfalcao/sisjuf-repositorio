package br.org.asserjuf.sisjuf.associados.convenio.cliente.web.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.convenio.AtividadeConvenioVO;
import br.org.asserjuf.sisjuf.associados.convenio.cliente.ConvenioDelegate;
import br.org.asserjuf.sisjuf.util.web.UtilDelegate;
//import br.org.asserjuf.sisjuf.associados.convenio.cliente.ConvenioDelegateTestView;

import com.vortice.seguranca.vo.UsuarioVO;
import com.vortice.view.BasePageBean;

public class AtividadeConvenioPageBean extends BasePageBean {

	private AtividadeConvenioVO			atividade;
	
	private ConvenioDelegate	delegate;
	 
	private UtilDelegate utilDelegate;
	
	private Collection<AtividadeConvenioVO>	atividades;
	
	private static final transient SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	private boolean flagDesativo;
	
	private static final transient Logger LOG = Logger.getLogger(ConvenioPageBean.class);

	public AtividadeConvenioPageBean(){
		try{ 
			this.atividade = new AtividadeConvenioVO();
		 }catch(Exception e){
			 LOG.error("Error ", e);
			 tratarExcecao(e);
		 }
	}
	
	public String salvar(){
		try {
			LOG.info("atividade " + atividade.getNome());
			if (this.atividade.getCodigo() != null && this.atividade.getCodigo().intValue() > 0){
				delegate.updateAtividade(atividade);
				FacesMessage msgs = new FacesMessage("Registro Atualizado com sucesso.");
				FacesContext facesContext =  FacesContext.getCurrentInstance();
				facesContext.addMessage(null, msgs);				
			}else{
				delegate.insertAtividade(atividade);
				FacesMessage msgs = new FacesMessage("Registro Salvo com sucesso.");
				FacesContext facesContext =  FacesContext.getCurrentInstance();
				facesContext.addMessage(null, msgs);				
			}
			return getSucesso();
		} catch(SmartAppException appEx){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, appEx.getMensagem(), appEx.getMensagem());
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", appEx);
			return "falha";
		}catch(SmartEnvException envEx){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", envEx);
			return "falha";
		}catch(Exception e){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	public String carregar(){
		try {
			this.atividade = delegate.findAtividadeByPrimaryKey(this.atividade);
			return getSucesso();
		} catch(SmartAppException appEx){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, appEx.getMensagem(), appEx.getMensagem());
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", appEx);
			return "falha";
		}catch(SmartEnvException envEx){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", envEx);
			return "falha";
		}catch(Exception e){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	public String consultar(){
		try {
			this.atividades = this.delegate.findAtividadeConvenioByFilter(this.atividade);
			return getSucesso();
		} catch(SmartAppException appEx){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, appEx.getMensagem(), appEx.getMensagem());
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", appEx);
			return "falha";
		}catch(SmartEnvException envEx){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", envEx);
			return "falha";
		}catch(Exception e){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	public String remover(){
		try {
			this.delegate.removeAtividade(this.atividade);
			FacesMessage msgs = new FacesMessage("Registro removido com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			this.consultar();
			return getSucesso();
		} catch(SmartAppException appEx){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, appEx.getMensagem(), appEx.getMensagem());
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", appEx);
			return "falha";
		}catch(SmartEnvException envEx){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", envEx);
			return "falha";
		}catch(Exception e){
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}

	public AtividadeConvenioVO getAtividade() {
		return this.atividade;
	}

	public void setAtividade(AtividadeConvenioVO atividade) {
		this.atividade = atividade;
	}

	public void setDelegate(ConvenioDelegate delegate) {
		this.delegate = (ConvenioDelegate) delegate;
	}

	public Collection<AtividadeConvenioVO> getAtividades() {
		try{
			return  this.delegate.findAllAtividades();
		} catch (Exception e) {
			tratarExcecao(e);
		}		
		return new ArrayList<AtividadeConvenioVO>();
	}
		
	
	/**
	 * Obtém um flag que informa se a atividade está ativa.
	 * @return bool True/False
	 */
	public boolean getFlagDesativo() {
		if (this.atividade.getDesativo() == null || this.atividade.getDesativo().equals("") || this.atividade.getDesativo().equals("N")) {
			return false;
		} else {
			return true;
		}
	}
	
	public void setFlagDesativo(boolean pFlagDesativo){
		if (Boolean.FALSE == pFlagDesativo){
			this.atividade.setDesativo("N");
		}else if (Boolean.TRUE == pFlagDesativo){
			this.atividade.setDesativo("S");
		}
		flagDesativo = pFlagDesativo;
	}

	public String getOperador(){
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuario");
		LOG.debug("usuario " + usuario);
		if (usuario != null){
			LOG.debug("usuario.getLogin() " + usuario.getLogin());
			LOG.debug("usuario.getNome() " + usuario.getNome());
			return usuario.getLogin();
		}
		else return "";
	}	
	
	
	public String getDataAtual(){
		try{
			return formatter.format(utilDelegate.getCurrentDate());
		}catch(Exception e){
			LOG.error("ERRO NA HORA DE BUSCAR A DATA ATUAL", e);
			return "";
		}
	}	
	
	public String print(){
		try {
			return getSucesso();
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}	
	
	public void setUtilDelegate(UtilDelegate utilDelegate) {
		this.utilDelegate = utilDelegate;
	}
}