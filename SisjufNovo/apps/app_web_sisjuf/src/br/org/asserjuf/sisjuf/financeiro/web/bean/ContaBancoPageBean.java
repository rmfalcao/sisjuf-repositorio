package br.org.asserjuf.sisjuf.financeiro.web.bean;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.financeiro.BancoVO;
import br.org.asserjuf.sisjuf.financeiro.ContaBancoVO;
import br.org.asserjuf.sisjuf.financeiro.TipoContaVO;
import br.org.asserjuf.sisjuf.financeiro.web.cliente.FinanceiroDelegate;

import com.vortice.view.BasePageBean;

/**
 *  Classe que implementa o "bean" (JSF) de sincronização para a entidade conta.
 * @author Paulo Prado
 */
 public class ContaBancoPageBean extends BasePageBean {

	/**
	 * Classe que implementa o padrão "proxy" para comunicação com o "façade" Financeiro.
	 */
	private transient FinanceiroDelegate delegate;
	
	/**
	 * Representa o encapsulamento dos dados de negócio da entidade conta. 
	 */
	private ContaBancoVO conta;
	
	/**
	 * Coleção de contas, encapsulados na classe BancoVO.
	 */
	private Collection contas;
	
	/**
	 * Coleção de bancos, encapsulados na classe BancoVO.
	 */
	private Collection bancos;
	
	/**
	 * Coleção de tipos de contas, encapsulados na classe TipoContaVO.
	 */
	private Collection tipoContas;
	
	/**
	 * Objeto de log (log4j)
	 */
	private transient Logger LOG = Logger.getLogger(ContaBancoPageBean.class);
	
	/**
	 * Instancia os objetos VO e delegate e invoca o método de consulta de todas as contas.
	 *
	 */
	public ContaBancoPageBean(){
		try{
			conta = new ContaBancoVO();
			conta.setBancoVO(new BancoVO());
			conta.setTipoContaVO(new TipoContaVO());
		 }catch(Exception e){
			 LOG.error("Erro no Contrutor de ContaBancoPageBean: ", e);
			 tratarExcecao(e);
		 }
	}
	
	/**
	 * Salva os dados cadastrais de uma conta.
	 * @return Mensagem pós-operação.
	 */
	public String salvar(){
		try{
			LOG.debug("O codigo da conta esta indo com " + conta.getCodigo());
			if (conta.getCodigo() != null && conta.getCodigo().intValue() > 0){
				delegate.updateConta(conta);
			}else{
				delegate.insertConta(conta);
			}
			this.consultar();
			FacesMessage msgs = new FacesMessage("Registro Salvo com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return getSucesso();
		}catch(SmartAppException appEx){
			LOG.debug("App: O codigo da conta esta retornando com " + conta.getCodigo());
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, appEx.getMensagem(), appEx.getMensagem());
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", appEx);
			return "falha";
		}catch(SmartEnvException envEx){
			LOG.debug("Env: O codigo da conta esta retornando com " + conta.getCodigo());
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext facesContext =  FacesContext.getCurrentInstance();
			facesContext.addMessage(null, msgs);
			LOG.error("Error ", envEx);
			return "falha";
		}catch(Exception e){
			LOG.debug("Ex: O codigo da conta esta retornando com " + conta.getCodigo());
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	/**
	 * Obtém todas as contas a serem exibidos na tela.
	 * @return Mensagem pós-operação.
	 */
	public String consultar(){
		try{
			contas = delegate.findAllConta(false);
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
	 * Exclui uma conta.
	 * @return Mensagem pós-operação.
	 */
	public String remover(){
		try{
			delegate.removeConta(conta);
			FacesMessage msgs = new FacesMessage("Registro removido com sucesso.");
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			this.consultar();
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
	 * Detalha uma conta.
	 * @return Mensagem pós-operação.
	 */
	public String carregar(){
		try{
			setConta(delegate.findContaByPrimaryKey(conta));
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
	 * Obtém coleção de bancos no bean para ser utilizado pela interface.
	 * @return Coleção de bancos presente no bean.
	 */
	public Collection getBancos() {
		if (bancos == null){
			try {
				Collection<BancoVO> collBanco = delegate.findAllBanco();
				bancos = (collBanco != null) ? getSelect(delegate.findAllBanco(), "codigo", "nome") : new ArrayList();
			} catch (Exception e) {
				LOG.error("ERRO NO MOMENTO DE CARREGAR OS BANCOS", e);
			}
		}
		return bancos;
	}

	/**
	 * Grava no bean a coleção de bancos vinda da interface.
	 * @param bancos Coleção de bancos com atributos preenchidos na interface.
	 */
	public void setBancos(Collection bancos) {
		this.bancos = bancos;
	}

	/**
	 * Obtém a conta no bean para ser utilizado pela interface.
	 * @return conta presente no bean.
	 */
	public ContaBancoVO getConta() {
		return conta;
	}

	/**
	 *  Grava no bean a conta vinda da interface.
	 * @param conta Conta com atributos preenchidos na interface.
	 */
	public void setConta(ContaBancoVO conta) {
		this.conta = conta;
	}

	/**
	 * Obtém coleção de contas no bean para ser utilizado pela interface.
	 * @return Coleção de contas presente no bean.
	 */
	public Collection getContas() {
		if (contas == null){
			try {
				contas = delegate.findAllConta(false);
			} catch (Exception e) {
				LOG.error("ERRO NO MOMENTO DE CARREGAR AS CONTAS", e);
			}
		}
		return contas;
	}

	/**
	 * Grava no bean a coleção de contas vinda da interface.
	 * @param contas Coleção de contas com atributos preenchidos na interface.
	 */	
	public void setContas(Collection contas) {
		this.contas = contas;
	}

	/**
	 * Obtém coleção de tipos de conta no bean para ser utilizado pela interface.
	 * @return Coleção de tipos de conta presente no bean.
	 */
	public Collection getTipoContas() {
		if (tipoContas == null){
			try {
				Collection collTipoConta = delegate.findAllTipoConta();
				tipoContas = (collTipoConta != null) ? getSelect(collTipoConta, "codigo", "descricao") : new ArrayList();
			} catch (Exception e) {
				LOG.error("ERRO NO MOMENTO DE CARREGAR OS TIPOS DE CONTAS", e);
			}
		}
		return tipoContas;
	}

	/**
	 * Grava no bean a coleção de tipos de conta vinda da interface.
	 * @param tipoContas Coleção de tipos de conta com atributos preenchidos na interface.
	 */	
	public void setTipoContas(Collection tipoContas) {
		this.tipoContas = tipoContas;
	}

	/**
	 * Obtém um flag que informa se a conta é caixa no bean para ser utilizado pela interface.
	 * @return bool True/False
	 */
	public boolean getFlgContaCaixa() {
		if (this.conta.getFlgContaCaixa() == null||this.conta.getFlgContaCaixa().equals("")) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Grava no bean a informação se a conta é caixa.
	 * @param flgContaCaixa
	 */
	public void setFlgContaCaixa(boolean flgContaCaixa) {
		if (flgContaCaixa)
			this.conta.setFlgContaCaixa("S");
		else this.conta.setFlgContaCaixa("");
		
		LOG.debug("Set conta.getFlgContaCaixa() " + conta.getFlgContaCaixa());
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