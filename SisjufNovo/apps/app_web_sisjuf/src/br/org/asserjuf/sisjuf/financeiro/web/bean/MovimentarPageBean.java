package br.org.asserjuf.sisjuf.financeiro.web.bean;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.vortice.seguranca.vo.UsuarioVO;
import com.vortice.view.BasePageBean;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.financeiro.ContaVO;
import br.org.asserjuf.sisjuf.financeiro.FormaPagamentoVO;
import br.org.asserjuf.sisjuf.financeiro.MovimentacaoAssembler;
import br.org.asserjuf.sisjuf.financeiro.web.cliente.FinanceiroDelegate;

/**
 *  Classe que implementa o "bean" (JSF) de sincronização para a operação de movimentação de valores entre contas.
 * @author Paulo Prado
 */
public class MovimentarPageBean extends BasePageBean{
	
	/**
	 * Coleção de contas, encapsulados na classe ContaVO a ser utilizada na listagem de contas de origem da interface.
	 */
	private Collection contasOrigem;
	
	/**
	 * Coleção de contas, encapsulados na classe ContaVO a ser utilizada na listagem de contas de destino da interface.
	 */
	private Collection contasDestino;
	
	/**
	 * Classe que implementa o padrão "proxy" para comunicação com o "façade" Financeiro.
	 */
	private transient FinanceiroDelegate delegate;
	
	/**
	 * Representa o encapsulamento dos dados a serem usados como filtro na operação de movimentação de valores entre contas. 
	 */
	private MovimentacaoAssembler movimentacao;
	
	/**
	 * Coleção de formas de pagamentos a serem utilizadas no movimentação.
	 */
	private Collection formasPagamentos;
	
	private Collection collContas;
	
	private UsuarioVO usuarioLogado;
	
	
	/**
	 * Objeto de log (log4j)
	 */
	private static transient Logger LOG = Logger.getLogger(MovimentarPageBean.class);
	/**
	 * Instancia os objetos necessários e o delegate e invoca os método de consulta para preencher os campos de listagem.
	 *
	 */
	public MovimentarPageBean(){
		movimentacao = new MovimentacaoAssembler();
		movimentacao.setContaDestinoVO(new ContaVO());
		movimentacao.setContaOrigemVO(new ContaVO());
		movimentacao.setFormaPagamentoVO(new FormaPagamentoVO());
		

		usuarioLogado = (UsuarioVO)this.getHttpSession().getAttribute("usuario");
		
		movimentacao.setUsuario(usuarioLogado);
	}
	
	/**
	 * Salva os dados dos lançamentos da movimentação de valores entre contas.
	 * @return Mensagem pós-operação.
	 */
	public String salvar(){
		try{
			delegate.movimentarValoresEntreContas(movimentacao);
			FacesMessage msgs = new FacesMessage("Transação realizada.");
			FacesContext.getCurrentInstance().addMessage(null, msgs);
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
	 * Obtém coleção de contas no bean para ser utilizado pela interface no campo de contas de destino.
	 * @return Coleção de contas presente no bean
	 */	
	public Collection getContasDestino() {
		if (contasDestino == null){
			try {
				contasDestino 	= (getCollContas() != null) ? getSelect(getCollContas(), "codigo", "NomeDefault") : new ArrayList();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return contasDestino;
	}

	/**
	 * Grava no bean a coleção de contas de destino vinda da interface.
	 * @param contasDestino Coleção de contas
	 */
	public void setContasDestino(Collection contasDestino) {
		this.contasDestino = contasDestino;
	}

	/**
	 * Obtém coleção de contas no bean para ser utilizado pela interface no campo de contas de origem.
	 * @return Coleção de contas presente no bean
	 */	
	public Collection getContasOrigem() {
		if (contasOrigem == null){
			try {
				contasOrigem = (getCollContas() != null) ? getSelect(getCollContas(), "codigo", "NomeDefault") : new ArrayList();
			} catch (Exception e) {
				LOG.error("ERRO NO MOMENTO DE CARREGAR AS CONTAS ORIGEM", e);
			}
		}
		return contasOrigem;
	}

	/**
	 * Grava no bean a coleção de contas de origem vinda da interface.
	 * @param contasOrigem Coleção de contas
	 */
	public void setContasOrigem(Collection contasOrigem) {
		this.contasOrigem = contasOrigem;
	}

	/**
	 * Obtém o objeto de encapsulamento do filtro no bean para ser utilizado pela interface.
	 * @return Objeto de encapsulamento do filtro
	 */	
	public MovimentacaoAssembler getMovimentacao() {
		return movimentacao;
	}

	/**
	 * Grava no bean o objeto de encapsulamento do filtro vinda da interface.
	 * @param movimentacao Objeto de encapsulamento do filtro
	 */
	public void setMovimentacao(MovimentacaoAssembler movimentacao) {
		this.movimentacao = movimentacao;
	}
	
	/**
	 * Obtém a coleção de formas de pagamentos para ser utilizada pela interface.
	 * @return Coleção de contas
	 */
	public Collection getFormasPagamentos() {
		if (formasPagamentos == null){
			try {
				Collection collFormaPagamento = delegate.findAllFormaPagamento();
				formasPagamentos = (collFormaPagamento != null) ? getSelect(collFormaPagamento, "codigo", "nome") : new ArrayList();
			} catch (Exception e) {
				LOG.error("ERRO NO MOMENTO DE CARREGAR AS FORMAS DE PAGAMENTOS", e);
			}
		}
		return formasPagamentos;
	}
	
	/**
	 * Grava no bean a coleção de forma de pagamentos s vinda da interface.
	 * @param tipoLancamentos Coleção de tipos de lançamento 
	 */
	public void setFormasPagamentos(Collection formasPagamentos) {
		this.formasPagamentos = formasPagamentos;
	}

	@Override
	protected boolean isDefaultAscending(String sortColumn) {
		return false;
	}

	@Override
	protected void sort(String column, boolean ascending) {
		
	}

	public Collection<ContaVO> getCollContas() {
		if (collContas == null){
			try {
				collContas = delegate.findAllConta(false);
			} catch (Exception e) {
				LOG.error("ERRO NO MOMENTO DE CARREGAR AS CONTAS", e);
			}
		}
		return collContas;
	}

	public void setDelegate(FinanceiroDelegate delegate) {
		this.delegate = delegate;
	}
	
}