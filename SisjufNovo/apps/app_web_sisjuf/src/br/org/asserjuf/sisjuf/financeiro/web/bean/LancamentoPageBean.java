package br.org.asserjuf.sisjuf.financeiro.web.bean;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import br.com.falc.smartFW.exception.SmartAppException;
import br.com.falc.smartFW.exception.SmartEnvException;
import br.org.asserjuf.sisjuf.associados.convenio.FaturaVO;
import br.org.asserjuf.sisjuf.associados.convenio.cliente.ConvenioDelegate;
import br.org.asserjuf.sisjuf.financeiro.BaixaLancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.ContaVO;
import br.org.asserjuf.sisjuf.financeiro.FormaPagamentoVO;
import br.org.asserjuf.sisjuf.financeiro.LancamentoAssembler;
import br.org.asserjuf.sisjuf.financeiro.LancamentoFaturaVO;
import br.org.asserjuf.sisjuf.financeiro.LancamentoFiltroAssembler;
import br.org.asserjuf.sisjuf.financeiro.LancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.OrigemLancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.TipoLancamentoVO;
import br.org.asserjuf.sisjuf.financeiro.TipoOperacaoVO;
import br.org.asserjuf.sisjuf.financeiro.web.cliente.FinanceiroDelegate;
import br.org.asserjuf.sisjuf.util.ParametroVO;
import br.org.asserjuf.sisjuf.util.web.UtilDelegate;

import com.vortice.view.BasePageBean;

/**
 *  Classe que implementa o "bean" (JSF) de sincroniza��o para a entidade lancamento.
 * @author Paulo Prado
 *
 */
public class LancamentoPageBean extends BasePageBean {
	
	/**
	 * Representa o encapsulamento dos dados de neg�cio da entidade lancamento. 
	 */
	private LancamentoVO lancamento;
	
	private LancamentoAssembler lancamentoAssembler;
	
	/**
	 * Classe que implementa o padr�o "proxy" para comunica��o com o "fa�ade" Financeiro.
	 */
	private transient FinanceiroDelegate delegate;
	
	/**
	 * Objeto de log (log4j)
	 */
	private static transient Logger LOG = Logger.getLogger(LancamentoPageBean.class);
	
	/**
	 * Cole��o de lancamentos, encapsulados na classe LancamentoVO.
	 */
	private Collection<LancamentoVO> lancamentos;
	
	/**
	 * Cole��o de contas, encapsulados na classe ContaVO.
	 */
	private Collection contas;
	
	/**
	 * Cole��o de origens de lancamentos, encapsulados na classe OrigemLancamentoVO.
	 */
	private Collection origemLancamentos;
	
	/**
	 * Cole��o de tipos de lancamentos, encapsulados na classe TipoLancamentoVO.
	 */
	private Collection tipoLancamentos;
	
	/**
	 * Cole��o de tipos de opera��es, encapsulados na classe TipoOperacaoVO.
	 */
	private Collection tipoOperacoes;
	
	/**
	 * Representa o encapsulamento dos dados a serem usados como filtro na consulta de lancamentos. 
	 */
	private LancamentoFiltroAssembler filtro;
	
	/**
	 * Informa��o do valor utilizado no caso de baixa parcial. 
	 */
	private Double valorParcial;
	
	/**
	 * Informa��o do valor utilizado no caso de baixa total.
	 */
	private Double valorTotal;
	
	/**
	 * Informa��o para controle de qual tipo de baixa est� sendo utilizada (Baixa parcial ou total).
	 */
	private Integer tipoBaixa;

	/**
	 * Totaliza��o dos valores dos lan�amentos.
	 */
	private Double totalLancado;
	
	/**
	 * Totaliza��o dos valores pagos dos lan�amentos.
	 */
	private Double totalPago;
	
	/**
	 * Representa o encapsulamento dos dados de neg�cio da entidade de Baixa de Lancamento. 
	 */
	private BaixaLancamentoVO baixa;
	
	/**
	 * Cole��o de formas de pagamentos a serem utilizadas no cadastro do lan�amento.
	 */
	private Collection formasPagamentos;

	/**
	 * Instancia os objetos necess�rios e o delegate e invoca os m�todo de consulta para preencher os campos de listagem.
	 *
	 */
	
	private FaturaVO fatura;
	
	private ConvenioDelegate convenioDelegate;
	
	private UtilDelegate utilDelegate;
	
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
		fatura = new FaturaVO();
	}
	
	/**
	 * Salva os dados de um lan�amento efetuado.
	 * @return Mensagem p�s-opera��o.
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
	 * Salva os dados dda baixa de um lan�amento.
	 * @return Mensagem p�s-opera��o.
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
	 * Obt�m as informa��es a serem exibidas na tela de baixa.
	 * @return Mensagem p�s-opera��o.
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
	 * Obt�m os lan�amentos a serem exibidos na tela de acordo com o filtro informado na tela.
	 * @return Mensagem p�s-opera��o.
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
	 * Exclui um lan�amento.
	 * @return Mensagem p�s-opera��o.
	 */
	public String remover(){
		try{
			delegate.estornarLancamento(lancamento);
			this.consultar();
			FacesMessage msgs = new FacesMessage("Lan�amento estornado.");
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
	
	public String realizarBaixaFatura(){
		try{
			fatura = convenioDelegate.findByPrimaryKey(fatura);
			Collection<LancamentoFaturaVO> listaLancamentos = delegate.findLancamentoByFatura(fatura);
			Integer codigoTipoOperacaoADebitar = new Integer(utilDelegate.findParametroByPrimaryKey(new ParametroVO("TP_OPERACAO_DEBITO")).getValorTextual());
			for(LancamentoFaturaVO lancamentoFatura:listaLancamentos){
				if(lancamentoFatura.getTipoOperacaoVO().getCodigo().equals(codigoTipoOperacaoADebitar)){
					lancamento = delegate.findLancamentoByPrimaryKey(new LancamentoVO(lancamentoFatura.getCodigo())); 
				}
			}
			if(lancamento.getValor() < 0){
				lancamento.setValor(lancamento.getValor()*-1);
			}
			lancamento.setOrigemLancamentoVO(new OrigemLancamentoVO(new Integer(utilDelegate.findParametroByPrimaryKey(new ParametroVO("ORIGEM_USUARIO")).getValorTextual())));
			lancamento.setDataPrevisao(utilDelegate.getCurrentDate());
			return "realizarLancamentoADebitar";
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
	
	public String detalharVinculacaoLancamento(){
		try{
			lancamento = delegate.findLancamentoByPrimaryKey(lancamento);
			
			return "realizarLancamentoADebitar";
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
	 * Obt�m um lan�amento do bean para ser utilizado na interface.
	 * @return lancamentoVO presente no bean.
	 */
	public LancamentoVO getLancamento() {
		return lancamento;
	}

	/**
	 * Grava no bean as informa��es do lan�amento presente na interface.
	 * @param lancamento Lancamento com atributos preenchidos na interface.
	 */
	public void setLancamento(LancamentoVO lancamento) {
		this.lancamento = lancamento;
	}

	/**
	 * Obt�m cole��o de lan�amentos no bean para ser utilizado pela interface.
	 * @return Cole��o de lancamentos presente no bean.
	 */	
	public Collection<LancamentoVO> getLancamentos() {
		return lancamentos;
	}

	/**
	 * Grava no bean a cole��o de lan�amentos vinda da interface.
	 * @param lancamentos Cole��o de lan�amentos
	 */
	public void setLancamentos(Collection<LancamentoVO> lancamentos) {
		this.lancamentos = lancamentos;
	}

	/**
	 * Obt�m cole��o de origens de lan�amento no bean para ser utilizada pela interface.
	 * @return Cole��o de origens de lan�amento presente no bean
	 */
	public Collection getOrigemLancamentos() {
		if (origemLancamentos == null){
			try {
				Collection collOrigemLancamento	= delegate.findAllOrigemLancamento();
				origemLancamentos = (collOrigemLancamento != null) ? getSelect(collOrigemLancamento, "codigo", "nome") : new ArrayList();
			} catch (Exception e) {
				LOG.error("ERRO NO MOMENTO DE CARREGAR AS ORIGENS DE LAN�AMENTOS", e);
			}
		}
		return origemLancamentos;
	}

	/**
	 * Grava no bean a cole��o de origens de lan�amento vinda da interface.
	 * @param origemLancamentos Cole��o de origens de lan�amento
	 */
	public void setOrigemLancamentos(Collection origemLancamentos) {
		this.origemLancamentos = origemLancamentos;
	}

	/**
	 * Obt�m cole��o de tipos de lan�amento no bean para ser utilizada na interface.
	 * @return Cole��o de tipos de lan�amento presente no bean
	 */
	public Collection getTipoLancamentos() {
		if (tipoLancamentos == null){
			try {
				Collection collTipoLancamento = delegate.findAllTipoLancamento();
				tipoLancamentos	= (collTipoLancamento != null) ? getSelect(collTipoLancamento, "codigo", "nome") : new ArrayList();
			} catch (Exception e) {
				LOG.error("ERRO NO MOMENTO DE CARREGAR OS TIPOS DE LAN�AMENTOS", e);
			}
		}
		return tipoLancamentos;
	}

	/**
	 * Grava no bean a cole��o de tipos de lan�amentos vinda da interface.
	 * @param tipoLancamentos Cole��o de tipos de lan�amento 
	 */
	public void setTipoLancamentos(Collection tipoLancamentos) {
		this.tipoLancamentos = tipoLancamentos;
	}

	/**
	 * Obt�m cole��o de tipos de opera��o no bean para ser utilizada pela interface.
	 * @return Cole��o de tipos de opera��o
	 */
	public Collection getTipoOperacoes() {
		if (tipoOperacoes == null){
			try {
				Collection collTipoOperacao	= delegate.findAllTipoOperacao();
				tipoOperacoes = (collTipoOperacao != null) ? getSelect(collTipoOperacao, "codigo", "nome") : new ArrayList();
			} catch (Exception e) {
				LOG.error("ERRO NO MOMENTO DE CARREGAR OS TIPOS DE OPERA��ES", e);
			}
		}
		return tipoOperacoes;
	}

	/**
	 * Grava no bean cole��o de tipos de opera��o vinda da interface.
	 * @param tipoOperacoes Cole��o de tipos de opera��o
	 */
	public void setTipoOperacoes(Collection tipoOperacoes) {
		this.tipoOperacoes = tipoOperacoes;
	}

	/**
	 * Obt�m o flag que identifica se o lan�amento � n�o quitado no bean para ser utilizada pela interface.
	 *  @return True/False 
	 */
	public boolean getNaoQuitado() {
		if (filtro == null || filtro.getFlgNaoQuitado() == null || filtro.getFlgNaoQuitado().equalsIgnoreCase("")){
			return false;
		}else return true;
	}

	/**
	 * Grava no bean  o flag que identifica se o lan�amento � n�o quitado com o status vindo da interface.
	 * @param naoQuitado Flag N�o Quitado
	 */
	public void setNaoQuitado(boolean naoQuitado) {
		if (naoQuitado) filtro.setFlgNaoQuitado("S");
		else filtro.setFlgNaoQuitado("");
	}
	
	/**
	 * Obt�m o flag que identifica se o lan�amento a ser buscado � pela data de previs�o no bean para ser utilizada pela interface.
	 *  @return True/False 
	 */
	public boolean getPrevisao() {
		if (filtro == null || filtro.getFlgPrevisao() == null || filtro.getFlgPrevisao().equalsIgnoreCase("")){
			return false;
		}else return true;
	}

	/**
	 * Grava no bean o flag que identifica se o lan�amento a ser buscado � pela data de previs�o vindo da interface.
	 * @param previsao Flag para busca
	 */
	public void setPrevisao(boolean previsao) {
		if (previsao) filtro.setFlgPrevisao("S");
		else filtro.setFlgPrevisao("");
	}
	
	/**
	 * Obt�m o flag que identifica se o lan�amento a ser buscado � pela data de efetiva��o no bean para ser utilizada pela interface.
	 *  @return True/False 
	 */
	public boolean getEfetivacao() {
		if (filtro == null || filtro.getFlgEfetivacao() == null || filtro.getFlgEfetivacao().equalsIgnoreCase("")){
			return false;
		}else return true;
	}
	
	/**
	 * Grava no bean o flag que identifica se o lan�amento a ser buscado � pela data de efetiva��o vindo da interface.
	 * @param efetivacao Flag para busca
	 */
	public void setEfetivacao(boolean efetivacao) {
		if (efetivacao) filtro.setFlgEfetivacao("S");
		else filtro.setFlgEfetivacao("");
	}

	/**
	 * Obt�m a cole��o de contas no bean para ser utilizada pela interface.
	 * @return Cole��o de contas
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
	 * Grava no beana cole��o de contas vinda da interface.
	 * @param contas 
	 */
	public void setContas(Collection contas) {
		this.contas = contas;
	}

	/**
	 * Obt�m o filtro da consulta de lan�amentos no bean para ser utilizada pela interface.
	 * @return Objeto de filtro
	 */
	public LancamentoFiltroAssembler getFiltro() {
		return filtro;
	}

	/**
	 * Grava no bean o filtro da consulta de lan�amentos vindo da interface.
	 * @param filtro Objeto de Filtro
	 */
	public void setFiltro(LancamentoFiltroAssembler filtro) {
		this.filtro = filtro;
	}

	/**
	 * Obt�m o valor parcial para baixa no bean para ser utilizado pela interface.
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
	 * Obt�m o valor total para baixa no bean para ser utilizado pela interface.
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
	 * Obt�m o tipo da baixa (se � parcial ou total)  no bean para ser utilizado pela interface.
	 * @return Tipo da baixa
	 */
	public Integer getTipoBaixa() {
		return tipoBaixa;
	}

	/**
	 * Grava no bean o tipo da baixa (se � parcial ou total) vindo da interface.
	 * @param tipoBaixa Tipo da baixa
	 */
	public void setTipoBaixa(Integer tipoBaixa) {
		this.tipoBaixa = tipoBaixa;
	}

	/**
	 * Obt�m o valor total do lan�amento no bean para ser utilizado pela interface.
	 * @return Valor Total do lan�amento
	 */
	public Double getTotalLancado() {
		return totalLancado;
	}

	/**
	 * Grava no bean o valor total do lan�amento vindo da interface.
	 * @param totalLancado Valor total do lan�amento
	 */
	public void setTotalLancado(Double totalLancado) {
		this.totalLancado = totalLancado;
	}

	/**
	 * Obt�m o valor pago do lan�amento no bean para ser utilizado pela interface.
	 * @return Valor pago do lan�amento
	 */
	public Double getTotalPago() {
		return totalPago;
	}

	/**
	 * Grava no bean o valor pago do lan�amento vindo da interface.
	 * @param totalPago Valor pago do lan�amento
	 */
	public void setTotalPago(Double totalPago) {
		this.totalPago = totalPago;
	}

	/**
	 * Obt�m a baixa de lan�amento no bean para ser utilizado pela interface.
	 * @return baixa de lan�amento presente no bean.
	 */
	public BaixaLancamentoVO getBaixa() {
		return baixa;
	}

	/**
	 *  Grava no bean a baixa de lan�amento vinda da interface.
	 * @param baixa Baixa de lan�amento com atributos preenchidos na interface.
	 */
	public void setBaixa(BaixaLancamentoVO baixa) {
		this.baixa = baixa;
	}
	
	/**
	 * Obt�m a cole��o de formas de pagamentos para ser utilizada pela interface.
	 * @return Cole��o de contas
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
	 * Grava no bean a cole��o de forma de pagamentos s vinda da interface.
	 * @param tipoLancamentos Cole��o de tipos de lan�amento 
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

	public FaturaVO getFatura() {
		return fatura;
	}

	public void setFatura(FaturaVO fatura) {
		this.fatura = fatura;
	}
	
	public void setConvenioDelegate(ConvenioDelegate convenioDelegate) {
		this.convenioDelegate = convenioDelegate;
	}
	
	public void setUtilDelegate(UtilDelegate utilDelegate) {
		this.utilDelegate = utilDelegate;
	}
}