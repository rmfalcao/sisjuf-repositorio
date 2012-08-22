package br.org.asserjuf.sisjuf.financeiro.web.bean;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.financeiro.BaixaLancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.ContaVO;
import br.org.asserjuf.sisjuf.financeiro.FormaPagamentoVO;
import br.org.asserjuf.sisjuf.financeiro.LancamentoAssembler;
import br.org.asserjuf.sisjuf.financeiro.LancamentoFiltroAssembler;
import br.org.asserjuf.sisjuf.financeiro.LancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.OrigemLancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.TipoLancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.TipoOperacaoVO;
import br.org.asserjuf.sisjuf.financeiro.web.cliente.FinanceiroDelegate;

import com.vortice.view.BasePageBean;

/**
 *  Classe que implementa o "bean" (JSF) de sincronização para a entidade lancamento.
 * @author Paulo Prado
 *
 */
public class LancamentoPageBean extends BasePageBean {
	
	/**
	 * Representa o encapsulamento dos dados de negócio da entidade lancamento. 
	 */
	private LancamentoVO lancamento;
	
	private LancamentoAssembler lancamentoAssembler;
	
	/**
	 * Classe que implementa o padrão "proxy" para comunicação com o "façade" Financeiro.
	 */
	private transient FinanceiroDelegate delegate;
	
	/**
	 * Objeto de log (log4j)
	 */
	private static transient Logger LOG = Logger.getLogger(LancamentoPageBean.class);
	
	/**
	 * Coleção de lancamentos, encapsulados na classe LancamentoVO.
	 */
	private Collection<LancamentoVO> lancamentos;
	
	/**
	 * Coleção de contas, encapsulados na classe ContaVO.
	 */
	private Collection contas;
	
	/**
	 * Coleção de origens de lancamentos, encapsulados na classe OrigemLancamentoVO.
	 */
	private Collection origemLancamentos;
	
	/**
	 * Coleção de tipos de lancamentos, encapsulados na classe TipoLancamentoVO.
	 */
	private Collection tipoLancamentos;
	
	/**
	 * Coleção de tipos de operações, encapsulados na classe TipoOperacaoVO.
	 */
	private Collection tipoOperacoes;
	
	/**
	 * Representa o encapsulamento dos dados a serem usados como filtro na consulta de lancamentos. 
	 */
	private LancamentoFiltroAssembler filtro;
	
	/**
	 * Informação do valor utilizado no caso de baixa parcial. 
	 */
	private Double valorParcial;
	
	/**
	 * Informação do valor utilizado no caso de baixa total.
	 */
	private Double valorTotal;
	
	/**
	 * Informação para controle de qual tipo de baixa está sendo utilizada (Baixa parcial ou total).
	 */
	private Integer tipoBaixa;

	/**
	 * Totalização dos valores dos lançamentos.
	 */
	private Double totalLancado;
	
	/**
	 * Totalização dos valores pagos dos lançamentos.
	 */
	private Double totalPago;
	
	/**
	 * Representa o encapsulamento dos dados de negócio da entidade de Baixa de Lancamento. 
	 */
	private BaixaLancamentoVO baixa;
	
	/**
	 * Coleção de formas de pagamentos a serem utilizadas no cadastro do lançamento.
	 */
	private Collection formasPagamentos;

	/**
	 * Instancia os objetos necessários e o delegate e invoca os método de consulta para preencher os campos de listagem.
	 *
	 */
	public LancamentoPageBean(){
		LOG.debug("Vai construir LancamentoPageBean");
		lancamento = new LancamentoVO();
		lancamento.setContaVO(new ContaVO());
		lancamento.setOrigemLancamentoVO(new OrigemLancamentoVO());
		lancamento.setTipoLancamentoVO(new TipoLancamentoVO());
		lancamento.setTipoOperacaoVO(new TipoOperacaoVO());
		lancamento.setFormaPagamentoVO(new FormaPagamentoVO());
		
		filtro = new LancamentoFiltroAssembler();
		filtro.setContaVO(new ContaVO());
		filtro.setOrigemLancamentoVO(new OrigemLancamentoVO());
		filtro.setTipoLancamentoVO(new TipoLancamentoVO());
		filtro.setTipoOperacaoVO(new TipoOperacaoVO());

		totalLancado = new Double(0);
		totalPago = new Double(0);
		
		baixa = new BaixaLancamentoVO();
		baixa.setLancamentoVO(lancamento);
		baixa.setFormaPagamentoVO(new FormaPagamentoVO());
		
		lancamentoAssembler = new LancamentoAssembler();
	}
	
	/**
	 * Salva os dados de um lançamento efetuado.
	 * @return Mensagem pós-operação.
	 */
	public String salvar(){
		try{
			delegate.efetuarLancamento(lancamento);
			FacesMessage msgs = new FacesMessage("Registro Salvo com sucesso.");
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
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	/**
	 * Salva os dados dda baixa de um lançamento.
	 * @return Mensagem pós-operação.
	 */
	public String salvarBaixa(){
		try{
			if (tipoBaixa != null){
				if (tipoBaixa == 1){
					baixa.setValor(valorTotal);
				}else if (tipoBaixa == 2){
					baixa.setValor(valorParcial);
				}
				baixa.setLancamentoVO(lancamento);
				delegate.baixarLancamento(baixa);
				
				FacesMessage msgs = new FacesMessage("Baixa realizada.");
				FacesContext.getCurrentInstance().addMessage(null, msgs);
				delegate.findAllBanco();
				return getSucesso();
			}
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Um tipo de baixa tem que ser selecionado", "Um tipo de baixa tem que ser selecionado");
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
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
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	/**
	 * Obtém as informações a serem exibidas na tela de baixa.
	 * @return Mensagem pós-operação.
	 */
	public String baixar(){
		try
		{
			valorTotal = lancamento.getValor();
			
			lancamento = delegate.findLancamentoByPrimaryKey(lancamento);
			
			valorTotal = delegate.obterSaldoLancamentoAbsoluto(lancamento);
			
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
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	/**
	 * Obtém os lançamentos a serem exibidos na tela de acordo com o filtro informado na tela.
	 * @return Mensagem pós-operação.
	 */
	public String consultar(){
		try
		{
			lancamentoAssembler = delegate.findLancamentoByFilter(filtro);
			for (LancamentoVO lc : lancamentoAssembler.getLancamentos()){
				totalLancado += lc.getValor();
				totalPago += lc.getValorPago();
			}
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
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}
	
	/**
	 * Exclui um lançamento.
	 * @return Mensagem pós-operação.
	 */
	public String remover(){
		try{
			delegate.estornarLancamento(lancamento);
			this.consultar();
			FacesMessage msgs = new FacesMessage("Lançamento estornado.");
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
			String msgErr = "Ocorreu um erro inesperado, contate o seu administrador.";
			LOG.error("Error ", e);
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErr, msgErr);
			FacesContext.getCurrentInstance().addMessage(null, msgs);
			return "falha";
		}
	}

	/**
	 * Obtém um lançamento do bean para ser utilizado na interface.
	 * @return lancamentoVO presente no bean.
	 */
	public LancamentoVO getLancamento() {
		return lancamento;
	}

	/**
	 * Grava no bean as informações do lançamento presente na interface.
	 * @param lancamento Lancamento com atributos preenchidos na interface.
	 */
	public void setLancamento(LancamentoVO lancamento) {
		this.lancamento = lancamento;
	}

	/**
	 * Obtém coleção de lançamentos no bean para ser utilizado pela interface.
	 * @return Coleção de lancamentos presente no bean.
	 */	
	public Collection<LancamentoVO> getLancamentos() {
		return lancamentos;
	}

	/**
	 * Grava no bean a coleção de lançamentos vinda da interface.
	 * @param lancamentos Coleção de lançamentos
	 */
	public void setLancamentos(Collection<LancamentoVO> lancamentos) {
		this.lancamentos = lancamentos;
	}

	/**
	 * Obtém coleção de origens de lançamento no bean para ser utilizada pela interface.
	 * @return Coleção de origens de lançamento presente no bean
	 */
	public Collection getOrigemLancamentos() {
		if (origemLancamentos == null){
			try {
				Collection collOrigemLancamento	= delegate.findAllOrigemLancamento();
				origemLancamentos = (collOrigemLancamento != null) ? getSelect(collOrigemLancamento, "codigo", "nome") : new ArrayList();
			} catch (Exception e) {
				LOG.error("ERRO NO MOMENTO DE CARREGAR AS ORIGENS DE LANÇAMENTOS", e);
			}
		}
		return origemLancamentos;
	}

	/**
	 * Grava no bean a coleção de origens de lançamento vinda da interface.
	 * @param origemLancamentos Coleção de origens de lançamento
	 */
	public void setOrigemLancamentos(Collection origemLancamentos) {
		this.origemLancamentos = origemLancamentos;
	}

	/**
	 * Obtém coleção de tipos de lançamento no bean para ser utilizada na interface.
	 * @return Coleção de tipos de lançamento presente no bean
	 */
	public Collection getTipoLancamentos() {
		if (tipoLancamentos == null){
			try {
				Collection collTipoLancamento = delegate.findAllTipoLancamento();
				tipoLancamentos	= (collTipoLancamento != null) ? getSelect(collTipoLancamento, "codigo", "nome") : new ArrayList();
			} catch (Exception e) {
				LOG.error("ERRO NO MOMENTO DE CARREGAR OS TIPOS DE LANÇAMENTOS", e);
			}
		}
		return tipoLancamentos;
	}

	/**
	 * Grava no bean a coleção de tipos de lançamentos vinda da interface.
	 * @param tipoLancamentos Coleção de tipos de lançamento 
	 */
	public void setTipoLancamentos(Collection tipoLancamentos) {
		this.tipoLancamentos = tipoLancamentos;
	}

	/**
	 * Obtém coleção de tipos de operação no bean para ser utilizada pela interface.
	 * @return Coleção de tipos de operação
	 */
	public Collection getTipoOperacoes() {
		if (tipoOperacoes == null){
			try {
				Collection collTipoOperacao	= delegate.findAllTipoOperacao();
				tipoOperacoes = (collTipoOperacao != null) ? getSelect(collTipoOperacao, "codigo", "nome") : new ArrayList();
			} catch (Exception e) {
				LOG.error("ERRO NO MOMENTO DE CARREGAR OS TIPOS DE OPERAÇÕES", e);
			}
		}
		return tipoOperacoes;
	}

	/**
	 * Grava no bean coleção de tipos de operação vinda da interface.
	 * @param tipoOperacoes Coleção de tipos de operação
	 */
	public void setTipoOperacoes(Collection tipoOperacoes) {
		this.tipoOperacoes = tipoOperacoes;
	}

	/**
	 * Obtém o flag que identifica se o lançamento é não quitado no bean para ser utilizada pela interface.
	 *  @return True/False 
	 */
	public boolean getNaoQuitado() {
		if (filtro == null || filtro.getFlgNaoQuitado() == null || filtro.getFlgNaoQuitado().equalsIgnoreCase("")){
			return false;
		}else return true;
	}

	/**
	 * Grava no bean  o flag que identifica se o lançamento é não quitado com o status vindo da interface.
	 * @param naoQuitado Flag Não Quitado
	 */
	public void setNaoQuitado(boolean naoQuitado) {
		if (naoQuitado) filtro.setFlgNaoQuitado("S");
		else filtro.setFlgNaoQuitado("");
	}
	
	/**
	 * Obtém o flag que identifica se o lançamento a ser buscado é pela data de previsão no bean para ser utilizada pela interface.
	 *  @return True/False 
	 */
	public boolean getPrevisao() {
		if (filtro == null || filtro.getFlgPrevisao() == null || filtro.getFlgPrevisao().equalsIgnoreCase("")){
			return false;
		}else return true;
	}

	/**
	 * Grava no bean o flag que identifica se o lançamento a ser buscado é pela data de previsão vindo da interface.
	 * @param previsao Flag para busca
	 */
	public void setPrevisao(boolean previsao) {
		if (previsao) filtro.setFlgPrevisao("S");
		else filtro.setFlgPrevisao("");
	}
	
	/**
	 * Obtém o flag que identifica se o lançamento a ser buscado é pela data de efetivação no bean para ser utilizada pela interface.
	 *  @return True/False 
	 */
	public boolean getEfetivacao() {
		if (filtro == null || filtro.getFlgEfetivacao() == null || filtro.getFlgEfetivacao().equalsIgnoreCase("")){
			return false;
		}else return true;
	}
	
	/**
	 * Grava no bean o flag que identifica se o lançamento a ser buscado é pela data de efetivação vindo da interface.
	 * @param efetivacao Flag para busca
	 */
	public void setEfetivacao(boolean efetivacao) {
		if (efetivacao) filtro.setFlgEfetivacao("S");
		else filtro.setFlgEfetivacao("");
	}

	/**
	 * Obtém a coleção de contas no bean para ser utilizada pela interface.
	 * @return Coleção de contas
	 */
	public Collection getContas() {
		if (contas == null){
			try {
				Collection collContas = delegate.findAllConta();
				contas	= (collContas != null) ? getSelect(collContas, "codigo", "NomeDefault") : new ArrayList();
			} catch (Exception e) {
				LOG.error("ERRO NO MOMENTO DE CARREGAR AS CONTAS", e);
			}
		}
		return contas;
	}

	/**
	 * Grava no beana coleção de contas vinda da interface.
	 * @param contas 
	 */
	public void setContas(Collection contas) {
		this.contas = contas;
	}

	/**
	 * Obtém o filtro da consulta de lançamentos no bean para ser utilizada pela interface.
	 * @return Objeto de filtro
	 */
	public LancamentoFiltroAssembler getFiltro() {
		return filtro;
	}

	/**
	 * Grava no bean o filtro da consulta de lançamentos vindo da interface.
	 * @param filtro Objeto de Filtro
	 */
	public void setFiltro(LancamentoFiltroAssembler filtro) {
		this.filtro = filtro;
	}

	/**
	 * Obtém o valor parcial para baixa no bean para ser utilizado pela interface.
	 * @return Valor Parcial
	 */
	public Double getValorParcial() {
		return valorParcial;
	}

	/**
	 * Grava no bean o valor parcial para baixa vindo da interface.
	 * @param valorParcial Valor parcial da interface
	 */
	public void setValorParcial(Double valorParcial) {
		this.valorParcial = valorParcial;
	}

	/**
	 * Obtém o valor total para baixa no bean para ser utilizado pela interface.
	 * @return Valor Total
	 */
	public Double getValorTotal() {
		return valorTotal;
	}

	/**
	 * Grava no bean o valor total para baixa vindo da interface.
	 * @param valorTotal Valor total da interface
	 */
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	/**
	 * Obtém o tipo da baixa (se é parcial ou total)  no bean para ser utilizado pela interface.
	 * @return Tipo da baixa
	 */
	public Integer getTipoBaixa() {
		return tipoBaixa;
	}

	/**
	 * Grava no bean o tipo da baixa (se é parcial ou total) vindo da interface.
	 * @param tipoBaixa Tipo da baixa
	 */
	public void setTipoBaixa(Integer tipoBaixa) {
		this.tipoBaixa = tipoBaixa;
	}

	/**
	 * Obtém o valor total do lançamento no bean para ser utilizado pela interface.
	 * @return Valor Total do lançamento
	 */
	public Double getTotalLancado() {
		return totalLancado;
	}

	/**
	 * Grava no bean o valor total do lançamento vindo da interface.
	 * @param totalLancado Valor total do lançamento
	 */
	public void setTotalLancado(Double totalLancado) {
		this.totalLancado = totalLancado;
	}

	/**
	 * Obtém o valor pago do lançamento no bean para ser utilizado pela interface.
	 * @return Valor pago do lançamento
	 */
	public Double getTotalPago() {
		return totalPago;
	}

	/**
	 * Grava no bean o valor pago do lançamento vindo da interface.
	 * @param totalPago Valor pago do lançamento
	 */
	public void setTotalPago(Double totalPago) {
		this.totalPago = totalPago;
	}

	/**
	 * Obtém a baixa de lançamento no bean para ser utilizado pela interface.
	 * @return baixa de lançamento presente no bean.
	 */
	public BaixaLancamentoVO getBaixa() {
		return baixa;
	}

	/**
	 *  Grava no bean a baixa de lançamento vinda da interface.
	 * @param baixa Baixa de lançamento com atributos preenchidos na interface.
	 */
	public void setBaixa(BaixaLancamentoVO baixa) {
		this.baixa = baixa;
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

	public LancamentoAssembler getLancamentoAssembler() {
		return lancamentoAssembler;
	}

	public void setLancamentoAssembler(LancamentoAssembler lancamentoAssembler) {
		this.lancamentoAssembler = lancamentoAssembler;
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