package br.org.asserjuf.sisjuf.financeiro.web.bean;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.financeiro.TipoOperacaoVO;
import br.org.asserjuf.sisjuf.financeiro.web.cliente.FinanceiroDelegate;

import com.vortice.view.BasePageBean;

/**
 *  Classe que implementa o "bean" (JSF) de sincronização para a entidade tipo de operação.
 * @author Paulo Prado
 */
public class TipoOperacaoPageBean extends BasePageBean {
	
	/**
	 * Classe que implementa o padrão "proxy" para comunicação com o "façade" Financeiro.
	 */
	private transient FinanceiroDelegate delegate;
	
	/**
	 * Representa o encapsulamento dos dados de negócio da entidade tipo de operação. 
	 */
	private TipoOperacaoVO tipoOperacao;
	
	/**
	 * Coleção de tipos de operação, encapsulados na classe TipoOperacaoVO.
	 */
	private Collection tiposOperacoes;
	
	/**
	 * Objeto de log (log4j)
	 */
	private static transient Logger LOG = Logger.getLogger(TipoOperacaoPageBean.class);
	
	/**
	 * Instancia os objetos VO e delegate.
	 */
	public TipoOperacaoPageBean(){
		try{
			tipoOperacao = new TipoOperacaoVO();
			tiposOperacoes = new ArrayList();
		}catch(Exception e){
			 tratarExcecao(e);
		}
	}
	
	/**
	 * Salva os dados cadastrais de um tipo de operação.
	 * @return Mensagem pós-operação.
	 */
	public String salvar(){
		try{
			if (tipoOperacao.getCodigo() != null && tipoOperacao.getCodigo().intValue() > 0){
				
			}else{
				
			}
			FacesMessage msgs = new FacesMessage("Registro Salvo com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return getSucesso();
		}catch(Exception e){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return tratarExcecao(e);
		}
	}
	
	/**
	 * Obtém todos os tipos de operação a serem exibidos na tela.
	 * @return Mensagem pós-operação.
	 */
	public String consultar(){
		try{
			this.tiposOperacoes = delegate.findAllTipoOperacao();
			return getSucesso();
		}catch(SmartAppException appEx){
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
			if (e instanceof SmartAppException){
				LOG.debug("Naé que é instancia...");
			}
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	/**
	 * Exclui um tipo de operação.
	 * @return Mensagem pós-operação.
	 */
	public String remover(){
		try{
			FacesMessage msgs = new FacesMessage("Registro removido com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return getSucesso();
		}catch(Exception e){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return tratarExcecao(e);
		}
	}
	
	/**
	 * Detalha um tipo de operação.
	 * @return Mensagem pós-operação.
	 */
	public String carregar(){
		try{
			return getSucesso();
		}catch(Exception e){
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return tratarExcecao(e);
		}
	}

	public void setDelegate(FinanceiroDelegate delegate) {
		this.delegate = delegate;
	}

	@Override
	protected boolean isDefaultAscending(String sortColumn) {
		return false;
	}

	@Override
	protected void sort(String column, boolean ascending) {
	}
	
}