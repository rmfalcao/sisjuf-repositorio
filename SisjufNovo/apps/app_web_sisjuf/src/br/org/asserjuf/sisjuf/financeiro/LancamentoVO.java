package br.org.asserjuf.sisjuf.financeiro;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.vortice.seguranca.vo.UsuarioVO;

/**
 * 
 * Congrega os dados de um lan�amento da Asserjuf.
 * 
 * @author Paulo Prado
 *
 */
public class LancamentoVO implements Serializable{
	
	
	public LancamentoVO() {
		// TODO Auto-generated constructor stub
	}
	
	public LancamentoVO(Integer codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * codigo interno do lan�amento.
	 */
	private Integer codigo;
	
	/**
	 * Objeto que representa a conta do lan�amento.
	 */
	private ContaVO contaVO;
	
	/**
	 * Objeto que representa a origem do lan�amento.
	 */
	private OrigemLancamentoVO origemLancamentoVO;
	
	/**
	 * Objeto que represenra o tipo do lan�amento.
	 */
	private TipoLancamentoVO tipoLancamentoVO;
	
	/**
	 * Objeto que representa o tipo de opera��o do lan�amento.
	 */
	private TipoOperacaoVO tipoOperacaoVO;
	
	/**
	 * Data de previs�o do lan�amento caso seja de um tipo de opera��o de previs�o.
	 */
	private Date dataPrevisao;
	
	/**
	 * Valor do lan�amento.
	 */
	private Double valor;
	
	/**
	 * Texto para descri��o adicional livre do lan�amento.
	 */
	private String descricao;
	
	/**
	 * Data de efetiva��o do lan�amento caso n�o seja de um tipo de opera��o de previs�o.
	 */
	private Date dataEfetivacao;
	
	/**
	 * Valor pago do lan�amento. 
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
		
	private UsuarioVO usuario;
	
	private Date dataCadastro;
	

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

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
	 * Caso a origem seja USUARIO, retorna o tipo do lan�amento, caso contr�rio, retorna a origem.
	 * @return Tipo ou origem do lan�amento.
	 */
	public String getOrigemTipo() {
		return (getTipoLancamentoVO()==null||getTipoLancamentoVO().getNome()==null||getTipoLancamentoVO().getNome().equals("")?getOrigemLancamentoVO().getNome():getTipoLancamentoVO().getNome());
	}
	
	/**
	 * Obt�m o valor da propriedade codigo.
	 * @return C�digo interno do lan�amento
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
	 * Obt�m o objeto que representa a conta do lan�amento.
	 * @return Objeto que representa a conta do lan�amento
	 */
	public ContaVO getContaVO() {
		return contaVO;
	}
	
	/**
	 * Seta o objeto que representa a conta do lan�amento.
	 * @param contaVO Objeto que se deseja setar na propriedade
	 */
	public void setContaVO(ContaVO contaVO) {
		this.contaVO = contaVO;
	}
	
	/**
	 * Obt�m o valor da propriedade descricao.
	 * @return Descri��o do lan�amento
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
	 * Obt�m o objeto que representa a origem do lan�amento.
	 * @return Objeto que representa a origem do lan�amento
	 */
	public OrigemLancamentoVO getOrigemLancamentoVO() {
		return origemLancamentoVO;
	}
	
	/**
	 * Seta o objeto que representa a origem do lan�amento.
	 * @param origemLancamentoVO Objeto que se deseja setar na propriedade
	 */
	public void setOrigemLancamentoVO(OrigemLancamentoVO origemLancamentoVO) {
		this.origemLancamentoVO = origemLancamentoVO;
	}
	
	/**
	 * Obt�m o objeto que representa o tipo do lan�amento.
	 * @return Objeto que representa o tipo do lan�amento
	 */
	public TipoLancamentoVO getTipoLancamentoVO() {
		return tipoLancamentoVO;
	}
	
	/**
	 * Seta o objeto que representa o tipo do lan�amento.
	 * @param tipoLancamentoVO Objeto que se deseja setar na propriedade
	 */
	public void setTipoLancamentoVO(TipoLancamentoVO tipoLancamentoVO) {
		this.tipoLancamentoVO = tipoLancamentoVO;
	}
	
	/**
	 * Obt�m o objeto que representa o tipo de opera��o do lan�amento.
	 * @return Objeto que representa o tipo de opera��o do lan�amento
	 */
	public TipoOperacaoVO getTipoOperacaoVO() {
		return tipoOperacaoVO;
	}
	
	/**
	 * Seta o objeto que representa o tipo de opera��o do lan�amento.
	 * @param tipoOperacaoVO Objeto que se deseja setar na propriedade
	 */
	public void setTipoOperacaoVO(TipoOperacaoVO tipoOperacaoVO) {
		this.tipoOperacaoVO = tipoOperacaoVO;
	}
	
	/**
	 * Obt�m o valor do lan�amento formatado para exibi��o na interface.
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
	 * Obt�m o valor pago do lan�amento formatado para exibi��o da interface.
	 * @return Valor pago do lan�amento
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
	 * Obt�m o valor da propriedade valor.
	 * @return Valor do lan�amento
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
	 * Obt�m o valor da propriedade valorPago.
	 * @return Valor pago do lan�amento
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
	 * Obt�m a data de efetiva��o do lan�amento formatada para exibi��o da interface.
	 * @return Data de efetiva��o formatada
	 */
	public String getDataEfetivacaoFormatada() {
		
		if (dataEfetivacao != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			return formatter.format(dataEfetivacao);
		
		}
		return "";
		
	}

	/**
	 * Obt�m a data de previs�o do lan�amento formatada para exibi��o da interface.
	 * @return Data de previs�o formatada
	 */
	public String getDataPrevisaoFormatada() {
		
		if (dataPrevisao != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			return formatter.format(dataPrevisao);
		
		}
		return "";
		
	}

	/**
	 * Obt�m o valor da propriedade dataEfetivacao.
	 * @return Data de efetiva��o do lan�amento
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
	 * Obt�m o valor da propriedade dataPrevisao.
	 * @return Data de previs�o do lan�amento
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
