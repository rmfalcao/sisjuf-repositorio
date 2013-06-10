package br.org.asserjuf.sisjuf.financeiro;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * Congrega os dados de um lançamento da Asserjuf.
 * 
 * @author Paulo Prado
 *
 */
public class LancamentoVO implements Serializable{
	
	/**
	 * codigo interno do lançamento.
	 */
	private Integer codigo;
	
	/**
	 * Objeto que representa a conta do lançamento.
	 */
	private ContaVO contaVO;
	
	/**
	 * Objeto que representa a origem do lançamento.
	 */
	private OrigemLancamentoVO origemLancamentoVO;
	
	/**
	 * Objeto que represenra o tipo do lançamento.
	 */
	private TipoLancamentoVO tipoLancamentoVO;
	
	/**
	 * Objeto que representa o tipo de operação do lançamento.
	 */
	private TipoOperacaoVO tipoOperacaoVO;
	
	/**
	 * Data de previsão do lançamento caso seja de um tipo de operação de previsão.
	 */
	private Date dataPrevisao;
	
	/**
	 * Valor do lançamento.
	 */
	private Double valor;
	
	/**
	 * Texto para descrição adicional livre do lançamento.
	 */
	private String descricao;
	
	/**
	 * Data de efetivação do lançamento caso não seja de um tipo de operação de previsão.
	 */
	private Date dataEfetivacao;
	
	/**
	 * Valor pago do lançamento. 
	 */
	private Double valorPago;
	
	private FormaPagamentoVO formaPagamentoVO;
	
	private String bancoCheque;
	
	private String agenciaCheque;
	
	private String digitoAgenciaCheque;
	
	private String contaCheque;
	
	private String digitoContaCheque;
	
	private String numeroCheque;
	
	private String descricaoCompletaFormaPagamento;
		
	

	public String getDescricaoCompletaFormaPagamento() {
		return descricaoCompletaFormaPagamento;
	}

	public void setDescricaoCompletaFormaPagamento(
			String descricaoCompletaFormaPagamento) {
		this.descricaoCompletaFormaPagamento = descricaoCompletaFormaPagamento;
	}

	public String getAgenciaCheque() {
		return agenciaCheque;
	}

	public void setAgenciaCheque(String agenciaCheque) {
		this.agenciaCheque = agenciaCheque;
	}

	public String getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public String getBancoCheque() {
		return bancoCheque;
	}

	public void setBancoCheque(String bancoCheque) {
		this.bancoCheque = bancoCheque;
	}

	public String getContaCheque() {
		return contaCheque;
	}

	public void setContaCheque(String contaCheque) {
		this.contaCheque = contaCheque;
	}

	public String getDigitoAgenciaCheque() {
		return digitoAgenciaCheque;
	}

	public void setDigitoAgenciaCheque(String digitoAgenciaCheque) {
		this.digitoAgenciaCheque = digitoAgenciaCheque;
	}

	public String getDigitoContaCheque() {
		return digitoContaCheque;
	}

	public void setDigitoContaCheque(String digitoContaCheque) {
		this.digitoContaCheque = digitoContaCheque;
	}

	public FormaPagamentoVO getFormaPagamentoVO() {
		return formaPagamentoVO;
	}

	public void setFormaPagamentoVO(FormaPagamentoVO formaPagamentoVO) {
		this.formaPagamentoVO = formaPagamentoVO;
	}


	/**
	 * Caso a origem seja USUARIO, retorna o tipo do lançamento, caso contrário, retorna a origem.
	 * @return Tipo ou origem do lançamento.
	 */
	public String getOrigemTipo() {
		return (getTipoLancamentoVO()==null||getTipoLancamentoVO().getNome()==null||getTipoLancamentoVO().getNome().equals("")?getOrigemLancamentoVO().getNome():getTipoLancamentoVO().getNome());
	}
	
	/**
	 * Obtém o valor da propriedade codigo.
	 * @return Código interno do lançamento
	 */
	public Integer getCodigo() {
		return codigo;
	}
	
	/**
	 * Seta o valor da propriedade codigo.
	 * @param codigo Valor que se deseja setar na propriedade
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * Obtém o objeto que representa a conta do lançamento.
	 * @return Objeto que representa a conta do lançamento
	 */
	public ContaVO getContaVO() {
		return contaVO;
	}
	
	/**
	 * Seta o objeto que representa a conta do lançamento.
	 * @param contaVO Objeto que se deseja setar na propriedade
	 */
	public void setContaVO(ContaVO contaVO) {
		this.contaVO = contaVO;
	}
	
	/**
	 * Obtém o valor da propriedade descricao.
	 * @return Descrição do lançamento
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * Seta o valor da propriedade descricao.
	 * @param descricao Valor que se deseja setar na propriedade
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	/**
	 * Obtém o objeto que representa a origem do lançamento.
	 * @return Objeto que representa a origem do lançamento
	 */
	public OrigemLancamentoVO getOrigemLancamentoVO() {
		return origemLancamentoVO;
	}
	
	/**
	 * Seta o objeto que representa a origem do lançamento.
	 * @param origemLancamentoVO Objeto que se deseja setar na propriedade
	 */
	public void setOrigemLancamentoVO(OrigemLancamentoVO origemLancamentoVO) {
		this.origemLancamentoVO = origemLancamentoVO;
	}
	
	/**
	 * Obtém o objeto que representa o tipo do lançamento.
	 * @return Objeto que representa o tipo do lançamento
	 */
	public TipoLancamentoVO getTipoLancamentoVO() {
		return tipoLancamentoVO;
	}
	
	/**
	 * Seta o objeto que representa o tipo do lançamento.
	 * @param tipoLancamentoVO Objeto que se deseja setar na propriedade
	 */
	public void setTipoLancamentoVO(TipoLancamentoVO tipoLancamentoVO) {
		this.tipoLancamentoVO = tipoLancamentoVO;
	}
	
	/**
	 * Obtém o objeto que representa o tipo de operação do lançamento.
	 * @return Objeto que representa o tipo de operação do lançamento
	 */
	public TipoOperacaoVO getTipoOperacaoVO() {
		return tipoOperacaoVO;
	}
	
	/**
	 * Seta o objeto que representa o tipo de operação do lançamento.
	 * @param tipoOperacaoVO Objeto que se deseja setar na propriedade
	 */
	public void setTipoOperacaoVO(TipoOperacaoVO tipoOperacaoVO) {
		this.tipoOperacaoVO = tipoOperacaoVO;
	}
	
	/**
	 * Obtém o valor do lançamento formatado para exibição na interface.
	 * @return Valor formatado
	 */
	public String getValorFormatado() {
		if (valor != null) {
			NumberFormat	 numberFormatter	= NumberFormat.getInstance();
			numberFormatter.setMinimumFractionDigits(2);
			return numberFormatter.format(valor);
			
		}
		return "";

	}

	/**
	 * Obtém o valor pago do lançamento formatado para exibição da interface.
	 * @return Valor pago do lançamento
	 */
	public String getValorPagoFormatado() {
		if (valorPago != null) {
			NumberFormat	 numberFormatter	= NumberFormat.getInstance();
			numberFormatter.setMinimumFractionDigits(2);
			return numberFormatter.format(valorPago);
			
		}
		return "";

	}
	
	/**
	 * Obtém o valor da propriedade valor.
	 * @return Valor do lançamento
	 */
	public Double getValor() {
		return valor;
	}

	/**
	 * Seta o valor da propriedade valor.
	 * @param valor Valor que se deseja setar na propriedade
	 */
	public void setValor(Double valor) {
		this.valor = valor;
	}

	/**
	 * Obtém o valor da propriedade valorPago.
	 * @return Valor pago do lançamento
	 */
	public Double getValorPago() {
		return valorPago;
	}

	/**
	 * Seta o valor da propriedade valorPago.
	 * @param valorPago Valor que se deseja setar na propriedade
	 */
	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}

	/**
	 * Obtém a data de efetivação do lançamento formatada para exibição da interface.
	 * @return Data de efetivação formatada
	 */
	public String getDataEfetivacaoFormatada() {
		
		if (dataEfetivacao != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			return formatter.format(dataEfetivacao);
		
		}
		return "";
		
	}

	/**
	 * Obtém a data de previsão do lançamento formatada para exibição da interface.
	 * @return Data de previsão formatada
	 */
	public String getDataPrevisaoFormatada() {
		
		if (dataPrevisao != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			return formatter.format(dataPrevisao);
		
		}
		return "";
		
	}

	/**
	 * Obtém o valor da propriedade dataEfetivacao.
	 * @return Data de efetivação do lançamento
	 */
	public Date getDataEfetivacao() {
		return dataEfetivacao;
	}

	/**
	 * Seta o valor da propriedade dataEfetivacao.
	 * @param dataEfetivacao Valor que se deseja setar na propriedade
	 */
	public void setDataEfetivacao(Date dataEfetivacao) {
		this.dataEfetivacao = dataEfetivacao;
	}

	/**
	 * Obtém o valor da propriedade dataPrevisao.
	 * @return Data de previsão do lançamento
	 */
	public Date getDataPrevisao() {
		return dataPrevisao;
	}

	/**
	 * Seta o valor da propriedade dataPrevisao.
	 * @param dataPrevisao Valor que se deseja setar na propriedade
	 */
	public void setDataPrevisao(Date dataPrevisao) {
		this.dataPrevisao = dataPrevisao;
	}
}
