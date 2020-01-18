package br.org.asserjuf.sisjuf.financeiro.web.bean;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;

import br.org.asserjuf.sisjuf.financeiro.ContaVO;
import br.org.asserjuf.sisjuf.financeiro.SaldoAssembler;
import br.org.asserjuf.sisjuf.financeiro.web.cliente.FinanceiroDelegate;

import com.vortice.view.BasePageBean;

/**
 *  Classe que implementa o "bean" (JSF) de sincronização para a operação de movimentação de valores entre contas.
 * @author Paulo Prado
 */
public class SaldoPageBean extends BasePageBean {
	
	/**
	 * Classe que implementa o padrão "proxy" para comunicação com o "façade" Financeiro.
	 */
	private transient FinanceiroDelegate delegate;
	
	/**
	 * Coleção de contas, encapsulados na classe ContaVO.
	 */
	private Collection contas;
	
	/**
	 * Representa o encapsulamento dos dados de negócio da entidade conta. 
	 */
	private ContaVO conta;

	/**
	 * Representa o encapsulamento dos dados a serem usados como filtro na operação de saldo. 
	 */
	private SaldoAssembler saldo;
	
	/**
	 * Objeto de log (log4j)
	 */
	private static transient Logger LOG = Logger.getLogger(SaldoPageBean.class);
	
	/**
	 * Instancia os objetos necessários e o delegate e invoca o método de consulta para obter todas as contas para interface.
	 */
	public SaldoPageBean(){
		try{
			conta = new ContaVO();
			saldo = new SaldoAssembler();
			saldo.setContaVO(new ContaVO());
		}catch(Exception e){
			contas = new ArrayList();
			LOG.error("Erro no construtor de SaldoPageBean", e);
		}
	}

	/**
	 * Busca o saldo da conta escolhida e exibe na tela no ato da operação de seleção da conta.
	 * @param event Para informar a operação de seleção da conta 
	 */
	public void baixaSaldo(ValueChangeEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		if (event.getPhaseId().equals(PhaseId.ANY_PHASE)){
			event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
            event.queue();
            //phaseId = PhaseId.UPDATE_MODEL_VALUES;
        } 
		
		if (event.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)){
			try{
				LOG.debug("(Integer)event.getNewValue() " + (Integer)event.getNewValue());
				if ((Integer)event.getNewValue() != null)
					conta = delegate.obterSaldoConta(new ContaVO((Integer)event.getNewValue()));
				LOG.debug("Conta " + conta.getSaldo());
			}catch(Exception e){
				LOG.error("Erro no momento de baixar Saldo", e);
			}
        }
		LOG.debug("Agora vai com o get.");
		facesContext.renderResponse();
	}

	/**
	 * Obtém a conta no bean para ser utilizado pela interface.
	 * @return conta presente no bean.
	 */
	public ContaVO getConta() {
		LOG.debug("Veio Pegar conta com " + conta.getSaldo());
		return conta;
	}

	/**
	 * Grava no bean a conta com atributos preenchidos na interface.
	 * @param conta com dados da interface.
	 */	
	public void setConta(ContaVO conta) {
		this.conta = conta;
	}

	/**
	 * Obtém coleção de contas no bean para ser utilizado pela interface.
	 * @return Coleção de contas presente no bean.
	 */
	public Collection getContas() {
		if (contas == null){
			try {
				Collection collConta = delegate.findAllConta(true);
				contas = (collConta != null && collConta.size() > 0) ? getSelect(collConta, "codigo", "nomeDefault") : new ArrayList<ContaVO>();
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
	 * Obtém o saldo no bean para ser utilizado pela interface.
	 * @return saldo presente no bean.
	 */
	public SaldoAssembler getSaldo() {
		return saldo;
	}

	/**
	 * Grava no bean o saldo da interface.
	 * @param saldo
	 */
	public void setSaldo(SaldoAssembler saldo) {
		this.saldo = saldo;
	}

	@Override
	protected boolean isDefaultAscending(String sortColumn) {
		return false;
	}

	@Override
	protected void sort(String column, boolean ascending) {
	}

	public void setDelegate(FinanceiroDelegate delegate) {
		this.delegate = delegate;
	}
	
}